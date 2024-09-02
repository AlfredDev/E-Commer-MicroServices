package com.alfred.product.Service;

import com.alfred.product.DTO.ProductRequest;
import com.alfred.product.DTO.ProductPurchaseResponse;
import com.alfred.product.DTO.ProductPusrchaseRequest;
import com.alfred.product.DTO.ProductResponse;
import com.alfred.product.Exception.ProductPurchaseException;
import com.alfred.product.Mapper.ProductMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRespository respository;
    private final ProductMapper mapper;

    public Integer createProduct(ProductRequest request) {

        var product = mapper.toProduct(request);
        return respository.save(product).getId();
    }

    @Transactional
    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPusrchaseRequest> request) {

        // Extract the list of product IDs from the purchase requests
        var productsIds = request
                .stream()
                .map(ProductPusrchaseRequest::productId)
                .toList();

        // Retrieve the list of products from the repository using the product IDs
        var storageProduct = respository.findAllByIdInOrderById(productsIds);

        // Check if the number of retrieved products matches the number of requested products
        if (productsIds.size() != storageProduct.size()) {
            throw new ProductPurchaseException("One or more products does no exist");
        }

        // Sort the original request list by product ID to match the order of retrieved products
        var storeRequest = request
                .stream()
                .sorted(Comparator.comparing(ProductPusrchaseRequest::productId))
                .toList();

        // Create a list to store the results of each successful purchase
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();

        // Loop through the retrieved products to process each purchase
        for (int i = 0; i < storageProduct.size(); i++) {
            var product = storageProduct.get(i);
            var productRequest = storeRequest.get(i);

            // Check if there is enough quantity available for the purchase
            if (product.getAvailableQuantity() < productRequest.quantity()) {
                throw new ProductPurchaseException("Insufficient quantity for product with ID:: " + productRequest.productId());
            }

            // Update the available quantity and save the product back to the repository
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            respository.save(product);

            // Map the product and purchase quantity to a response object and add it to the list
            purchasedProducts.add(mapper.toProductPurchaseResponse(product, productRequest.quantity()));
        }

        // Return the list of successful purchase responses
        return purchasedProducts;
    }

    public ProductResponse findById(Integer productId) {
        return respository.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with the ID ::" + productId));
    }

    public List<ProductResponse> findAll() {
        return respository.findAll().stream()
                .map(mapper::toProductResponse)
                .collect(Collectors.toList());
    }
}

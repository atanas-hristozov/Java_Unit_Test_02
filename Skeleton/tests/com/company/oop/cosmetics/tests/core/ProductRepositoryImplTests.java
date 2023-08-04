package com.company.oop.cosmetics.tests.core;

import com.company.oop.cosmetics.core.ProductRepositoryImpl;
import com.company.oop.cosmetics.exceptions.InvalidUserInputException;
import com.company.oop.cosmetics.models.GenderType;
import com.company.oop.cosmetics.models.contracts.Category;
import com.company.oop.cosmetics.models.contracts.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ProductRepositoryImplTests {
    private ProductRepositoryImpl productRepository;
    @BeforeEach
    private void before(){
        productRepository =new ProductRepositoryImpl();
    }

    @Test
    public void constructor_Should_InitializeProducts() {
        Assertions.assertNotNull(productRepository.getProducts());
    }

    @Test
    public void constructor_Should_InitializeCategories() {
        Assertions.assertNotNull(productRepository.getCategories());
    }

    @Test
    public void getCategories_Should_ReturnCopyOfCollection() {
        List<Category> categories1 = productRepository.getCategories();
        List<Category> categories2 = productRepository.getCategories();
        Assertions.assertNotSame(categories1, categories2);
    }

    @Test
    public void getProducts_Should_ReturnCopyOfCollection() {
        List<Product> products1 = productRepository.getProducts();
        List<Product> products2 = productRepository.getProducts();
        Assertions.assertNotSame(products1,products2);
    }

    @Test
    public void categoryExists_Should_ReturnTrue_When_CategoryExists() {
        productRepository.createCategory("Category");
        Assertions.assertTrue(productRepository.categoryExist("Category"));
    }

    @Test
    public void categoryExists_Should_ReturnFalse_When_CategoryDoesNotExist() {
        productRepository.createCategory("Category");
        Assertions.assertFalse(productRepository.categoryExist("Category1"));
    }

    @Test
    public void productExists_Should_ReturnTrue_When_ProductExists() {
        productRepository.createProduct("Product", "Brand", 10, GenderType.UNISEX);
        Assertions.assertTrue(productRepository.productExist("Product"));
    }

    @Test
    public void productExists_Should_ReturnFalse_When_ProductDoesNotExist() {
        productRepository.createProduct("Product", "Brand", 10, GenderType.UNISEX);
        Assertions.assertFalse(productRepository.productExist("AA"));
    }

    @Test
    public void createProduct_Should_AddToProducts_When_ArgumentsAreValid() {
        productRepository.createProduct("Product", "Brand", 10, GenderType.UNISEX);
        Assertions.assertEquals(1, productRepository.getProducts().size());
    }

    @Test
    public void createCategory_Should_AddToCategories_When_ArgumentsAreValid() {
        productRepository.createCategory("Category");
        Assertions.assertEquals(1, productRepository.getCategories().size());
    }

    @Test
    public void findCategoryByName_Should_ReturnCategory_When_Exists() {
        productRepository.createCategory("Category");
        Assertions.assertDoesNotThrow(() -> productRepository.findCategoryByName("Category"));
    }

    @Test
    public void findCategoryByName_Should_ThrowException_When_DoesNotExist() {
        productRepository.createCategory("Category");
        Assertions.assertThrows(InvalidUserInputException.class, ()-> productRepository.findCategoryByName("AA"));
    }

    @Test
    public void findProductByName_Should_ReturnProduct_When_Exists() {
        productRepository.createProduct("Product", "Brand", 10, GenderType.UNISEX);
        Assertions.assertDoesNotThrow(()-> productRepository.findProductByName("Product"));
    }

    @Test
    public void findProductByName_Should_ThrowException_When_DoesNotExist() {
        productRepository.createProduct("Product", "Brand", 10, GenderType.UNISEX);
        Assertions.assertThrows(InvalidUserInputException.class, ()-> productRepository.findProductByName("AA"));
    }

}

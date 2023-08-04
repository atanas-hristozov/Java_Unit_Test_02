package com.company.oop.cosmetics.tests.models;

import com.company.oop.cosmetics.commands.CreateProductCommand;
import com.company.oop.cosmetics.exceptions.InvalidUserInputException;
import com.company.oop.cosmetics.models.CategoryImpl;
import com.company.oop.cosmetics.models.GenderType;
import com.company.oop.cosmetics.models.ProductImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryImplTests {

    @Test
    public void constructor_Should_InitializeName_When_ArgumentsAreValid() {
        //Arrange
        String categoryName = "Name";
        //Act
        CategoryImpl category = new CategoryImpl(categoryName);
        //Assert
        Assertions.assertEquals(categoryName, category.getName());
    }

    @Test
    public void constructor_Should_InitializeProducts_When_ArgumentsAreValid() {
        //Arrange
        String categoryName = "Name";
        //Act
        CategoryImpl category = new CategoryImpl(categoryName);
        //Assert
        Assertions.assertNotNull(category.getProducts());
    }

    @Test
    public void constructor_Should_ThrowException_When_NameIsShorterThanExpected() {
        //Arrange
        String categoryName = "AA";
        //Act and Assert
        Assertions.assertThrows(InvalidUserInputException.class, ()-> new CategoryImpl(categoryName));
    }

    @Test
    public void addProduct_Should_AddProductToList() {
        //Arrange
        ProductImpl product = new ProductImpl("Product","Brand", 10, GenderType.UNISEX);
        CategoryImpl category = new CategoryImpl("Category");
        //Act
        category.addProduct(product);
        //Assert
        Assertions.assertEquals(1,category.getProducts().size());
    }

    @Test
    public void removeProduct_Should_RemoveProductFromList_When_ProductExist() {
        //Arrange
        ProductImpl product = new ProductImpl("Product","Brand", 10, GenderType.UNISEX);
        CategoryImpl category = new CategoryImpl("Category");
        category.addProduct(product);
        //Act
        category.removeProduct(product);
        //Assert
        Assertions.assertEquals(0,category.getProducts().size());
    }

    @Test
    public void removeProduct_should_notRemoveProductFromList_when_productNotExist() {
        //Arrange
        ProductImpl product = new ProductImpl("Product","Brand", 10, GenderType.UNISEX);
        CategoryImpl category = new CategoryImpl("Category");
        category.addProduct(product);
        ProductImpl productoremove = new ProductImpl("Product","Brand", 10, GenderType.UNISEX);
        //Act
        category.removeProduct(productoremove);
        //Assert
        Assertions.assertEquals(1,category.getProducts().size());
    }
}

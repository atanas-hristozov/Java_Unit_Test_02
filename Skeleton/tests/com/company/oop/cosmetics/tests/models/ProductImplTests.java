package com.company.oop.cosmetics.tests.models;

import com.company.oop.cosmetics.exceptions.InvalidUserInputException;
import com.company.oop.cosmetics.models.GenderType;
import com.company.oop.cosmetics.models.ProductImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductImplTests {
    @Test
    public void constructor_Should_InitializeName_When_ValidName(){
        ProductImpl product = new ProductImpl("Product","Brand", 22.3, GenderType.UNISEX);
        Assertions.assertEquals("Product",product.getName());
    }
    @Test
    public void constructor_Should_InitializeName_When_ValidBrand(){
        ProductImpl product = new ProductImpl("Product","Brand", 22.3, GenderType.UNISEX);
        Assertions.assertEquals("Brand",product.getBrand());
    }
    @Test
    public void constructor_Should_InitializeName_When_ValidPrice(){
        ProductImpl product = new ProductImpl("Product","Brand", 22.3, GenderType.UNISEX);
        Assertions.assertEquals(22.3,product.getPrice());
    }
    @Test
    public void constructor_Should_ThrowException_When_InvalidName(){
        Assertions.assertThrows(InvalidUserInputException.class, ()-> new ProductImpl("AA","Brand", 22.3, GenderType.UNISEX));
    }
    @Test
    public void constructor_Should_ThrowException_When_InvalidBrand(){
        Assertions.assertThrows(InvalidUserInputException.class, ()-> new ProductImpl("Product","A", 22.3, GenderType.UNISEX));
    }
    @Test
    public void constructor_Should_ThrowException_When_InvalidPrice(){
        Assertions.assertThrows(InvalidUserInputException.class, ()-> new ProductImpl("Product","Brand", -22.3, GenderType.UNISEX));
    }
    @Test
    public void print_Should_Initialize_When_ValidParameters(){
        ProductImpl product = new ProductImpl("Product","Brand", 22.3, GenderType.UNISEX);
        String original = String.format(
                        "#%s %s%n" +
                        " #Price: $%.2f%n" +
                        " #Gender: %s%n",
                        "Product",
                        "Brand",
                        22.3,
                        "Unisex");
        String toCompare = product.print();
        Assertions.assertEquals(original, toCompare);
    }
}

package com.company.oop.cosmetics.tests.commands;

import com.company.oop.cosmetics.commands.AddProductToCategoryCommand;
import com.company.oop.cosmetics.commands.CreateCategoryCommand;
import com.company.oop.cosmetics.commands.CreateProductCommand;
import com.company.oop.cosmetics.core.ProductRepositoryImpl;
import com.company.oop.cosmetics.exceptions.InvalidUserInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AddProductToCategoryCommandTests {
    private ProductRepositoryImpl productRepository;
    private CreateCategoryCommand createCategoryCommand;
    private CreateProductCommand createProductCommand;
    private AddProductToCategoryCommand addProductToCategoryCommand;

    @BeforeEach
    private void before(){
        productRepository = new ProductRepositoryImpl();
        createCategoryCommand = new CreateCategoryCommand(productRepository);
        createProductCommand = new CreateProductCommand(productRepository);
        addProductToCategoryCommand = new AddProductToCategoryCommand(productRepository);
        createCategoryCommand.execute(List.of("Category"));
        createProductCommand.execute(List.of("Product","Brand","10","UNISEX"));
    }

    @Test
    public void should_AddProductToCategory_When_ValidParameters(){

        Assertions.assertDoesNotThrow(()->addProductToCategoryCommand.execute(List.of("Category","Product")));
    }
    @Test
    public void should_ThrowException_When_InvalidParameters(){

        Assertions.assertThrows(InvalidUserInputException.class,
                () -> addProductToCategoryCommand.execute(List.of("A","AA")));
    }
    @Test
    public void should_ThrowException_When_InvalidCountParameters(){

        Assertions.assertThrows(InvalidUserInputException.class,
                () -> addProductToCategoryCommand.execute(List.of("Category")));
    }

}

package com.company.oop.cosmetics.tests.commands;

import com.company.oop.cosmetics.commands.CreateProductCommand;
import com.company.oop.cosmetics.core.ProductRepositoryImpl;
import com.company.oop.cosmetics.exceptions.DuplicateEntityException;
import com.company.oop.cosmetics.exceptions.InvalidUserInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CreateProductCommandTests {
    private ProductRepositoryImpl productRepository;
    private CreateProductCommand createProductCommand;


    @BeforeEach
    private void before(){
        productRepository = new ProductRepositoryImpl();
        createProductCommand = new CreateProductCommand(productRepository);
    }


    @Test
    public void should_AddNewProductToRepository_When_ValidParameters(){
        createProductCommand.execute(List.of("Product", "Brand", "10", "UNISEX"));

        Assertions.assertEquals(1,productRepository.getProducts().size());
    }

    @Test
    public void should_ThrowException_When_InvalidNumberOfParameters(){

        Assertions.assertThrows(InvalidUserInputException.class,
                () -> createProductCommand.execute(List.of("Product", "Brand", "10")));
    }

    @Test
    public void should_ThrowException_When_InvalidNameParameter(){

        Assertions.assertThrows(InvalidUserInputException.class,
                () -> createProductCommand.execute(List.of("AA", "Brand", "10","UNISEX")));
    }

    @Test
    public void should_ThrowException_When_InvalidBrandParameter(){

        Assertions.assertThrows(InvalidUserInputException.class,
                () -> createProductCommand.execute(List.of("Product", "A", "10","UNISEX")));
    }

    @Test
    public void should_ThrowException_When_InvalidPriceParameter(){

        Assertions.assertThrows(InvalidUserInputException.class,
                () -> createProductCommand.execute(List.of("Product", "Brand", "AA","UNISEX")));
    }

    @Test
    public void should_ThrowException_When_InvalidGenderTypeParameter(){

        Assertions.assertThrows(InvalidUserInputException.class,
                () -> createProductCommand.execute(List.of("Product", "Brand", "10","AA")));
    }

    @Test
    public void should_ThrowException_When_ProductAlreadyExist(){
        CreateProductCommand createProductCommand1 = new CreateProductCommand(productRepository);
        createProductCommand1.execute(List.of("Product", "Brand", "10", "UNISEX"));

        Assertions.assertThrows(DuplicateEntityException.class,
                () -> createProductCommand.execute(List.of("Product", "Brand", "10","UNISEX")));
    }
}

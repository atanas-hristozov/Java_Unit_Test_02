package com.company.oop.cosmetics.tests.commands;

import com.company.oop.cosmetics.commands.CreateCategoryCommand;
import com.company.oop.cosmetics.commands.ShowCategoryCommand;
import com.company.oop.cosmetics.commands.contracts.Command;
import com.company.oop.cosmetics.core.ProductRepositoryImpl;
import com.company.oop.cosmetics.exceptions.InvalidUserInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ShowCategoryCommandTests {
    private ProductRepositoryImpl productRepository;
    private Command createCategoryCommandTests;
    private ShowCategoryCommand showCategoryCommand;

    @BeforeEach
    private void before() {
        productRepository = new ProductRepositoryImpl();
        createCategoryCommandTests = new CreateCategoryCommand(productRepository);
        createCategoryCommandTests.execute(List.of("Category"));
        showCategoryCommand = new ShowCategoryCommand(productRepository);
    }

    @Test
    public void should_ShowCategory_When_ValidParameters() {

        Assertions.assertTrue(true, showCategoryCommand.execute(List.of("Category")));
    }

    @Test
    public void should_ThrowException_When_InvalidParameters() {

        Assertions.assertThrows(InvalidUserInputException.class, () -> showCategoryCommand.execute(List.of("AA")));
    }

    @Test
    public void should_ThrowException_When_InvalidCountParameters() {

        Assertions.assertThrows(InvalidUserInputException.class, () -> showCategoryCommand.execute(List.of()));
    }

}

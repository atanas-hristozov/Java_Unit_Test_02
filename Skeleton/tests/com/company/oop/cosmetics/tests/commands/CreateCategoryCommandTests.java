package com.company.oop.cosmetics.tests.commands;

import com.company.oop.cosmetics.commands.CreateCategoryCommand;
import com.company.oop.cosmetics.core.ProductRepositoryImpl;
import com.company.oop.cosmetics.exceptions.DuplicateEntityException;
import com.company.oop.cosmetics.exceptions.InvalidUserInputException;
import com.company.oop.cosmetics.models.CategoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CreateCategoryCommandTests {
    // @BeforeEach may help here

    @Test
    public void execute_Should_AddNewCategoryToRepository_When_ValidParameters() {
        //Arrange
        ProductRepositoryImpl repository = new ProductRepositoryImpl();
        CreateCategoryCommand command = new CreateCategoryCommand(repository);
        //Act
        command.execute(List.of("Category"));
        //Assert
        Assertions.assertEquals("Category",repository.getCategories().get(0).getName());
    }

    @Test
    public void execute_Should_ThrowException_When_MissingParameters() {
        //Arrange
        ProductRepositoryImpl repository = new ProductRepositoryImpl();
        CreateCategoryCommand command = new CreateCategoryCommand(repository);
        //Act
        //Assert
        Assertions.assertThrows(InvalidUserInputException.class,() ->  command.execute(List.of()));
    }

    @Test
    public void execute_Should_ThrowException_When_DuplicateCategoryName() {
        ProductRepositoryImpl repository = new ProductRepositoryImpl();
        CreateCategoryCommand command = new CreateCategoryCommand(repository);
        CreateCategoryCommand command1 = new CreateCategoryCommand(repository);

        command1.execute(List.of("Category"));

        Assertions.assertThrows(DuplicateEntityException.class, () -> command1.execute(List.of("Category")));
    }

}

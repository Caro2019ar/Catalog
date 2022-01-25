package com.caro.catalog.services;

import com.caro.catalog.dto.ProductDTO;
import com.caro.catalog.entities.Product;
import com.caro.catalog.repositories.ProductRepository;
import com.caro.catalog.services.exceptions.DatabaseException;
import com.caro.catalog.services.exceptions.ResourceNotFoundException;
import com.caro.catalog.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class ProductServiceTests {
    @InjectMocks
    private ProductService service;

    private long existingId;
    private long nonExistingId;
    private long dependentId;
    private Product product;
    private PageImpl<Product> page;

    @Mock
    private ProductRepository repository;

    @BeforeEach
    void setUp() throws Exception{
        existingId=1L;
        nonExistingId=2L;
        dependentId=3L;
        product= Factory.createProduct();
        page = new PageImpl<>(List.of(product));

        //um argumento qualquer do findaAll é casteado a Pageable:
        Mockito.when(repository.findAll((Pageable)ArgumentMatchers.any())).thenReturn(page);
        Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(product);
        Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(product));
        Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

        Mockito.doNothing().when(repository).deleteById(existingId);
        Mockito.doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExistingId);
        Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentId);
    }
    @Test
    public void findAllPagedShouldReturnPage(){
        Pageable pageable = PageRequest.of(0,10);
        Page<ProductDTO> result = service.findAllPaged(pageable);
        Assertions.assertNotNull(result);
        Mockito.verify(repository, Mockito.times(1)).findAll(pageable);
    }

    @Test
    public void shouldDoNothingWhenExistsId(){
        Assertions.assertDoesNotThrow(()->{
            service.delete(existingId);
        });
        Mockito.verify(repository, Mockito.times(1)).deleteById(existingId);
    }
    @Test
    public void shouldThrowResourceNotFoundExceptionWhenIdDoesNotExist(){
        Assertions.assertThrows(ResourceNotFoundException.class,()->{
            service.delete(nonExistingId);
        });
        Mockito.verify(repository, Mockito.times(1)).deleteById(nonExistingId);
    }
    @Test
    public void deleteShouldThrowDatabaseExceptionWhenDependentId(){
        Assertions.assertThrows(DatabaseException.class,()->{
            service.delete(dependentId);
        });
        Mockito.verify(repository, Mockito.times(1)).deleteById(dependentId);
    }
}

package com.caro.catalog.services;

import com.caro.catalog.dto.CategoryDTO;
import com.caro.catalog.dto.ProductDTO;
import com.caro.catalog.entities.Category;
import com.caro.catalog.entities.Product;
import com.caro.catalog.repositories.CategoryRepository;
import com.caro.catalog.repositories.ProductRepository;
import com.caro.catalog.services.exceptions.DatabaseException;
import com.caro.catalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(Pageable pageable){
        Page<Product> list=repository.findAll(pageable);
        return list.map(x->new ProductDTO(x));

       /*//===== the stream line above does the same as below:
        List<ProductDTO> listDto = new ArrayList<>();
        for(Product cat:list){
            listDto.add(new ProductDTO(cat));
        }
        return listDto;*/
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> obj = repository.findById(id);
        Product entity=obj.orElseThrow(()->new ResourceNotFoundException("Entity not found"));
        return new ProductDTO(entity, entity.getCategories());
    }

   @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
        copyDTOToEntity(dto, entity);
        entity=repository.save(entity);
        return new ProductDTO(entity);
    }
    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            Product entity = repository.getById(id);
            copyDTOToEntity(dto, entity);
            entity=repository.save(entity);
            return new ProductDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException ("Entity not found"+id);
        }
    }

    private void copyDTOToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
        entity.setDate(dto.getDate());
        entity.getCategories().clear();
        for(CategoryDTO catDTO: dto.getCategories()){
           Category category = categoryRepository.getById(catDTO.getId());
            entity.getCategories().add(category);
        }

    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found"+id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Integrity violation");
        }
    }
}

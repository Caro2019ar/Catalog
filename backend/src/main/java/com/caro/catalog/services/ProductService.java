package com.caro.catalog.services;

import com.caro.catalog.dto.ProductDTO;
import com.caro.catalog.entities.Product;
import com.caro.catalog.repositories.ProductRepository;
import com.caro.catalog.services.exceptions.DatabaseException;
import com.caro.catalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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

    @Transactional(readOnly = true)
    public List<ProductDTO> findAll(){
        List<Product> list=repository.findAll();
        return list.stream().map(x->new ProductDTO(x)).collect(Collectors.toList());

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
        //entity.setName(dto.getName());
        entity=repository.save(entity);
        return new ProductDTO(entity);
    }
    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            Product entity = repository.getById(id);
            //entity.setName(dto.getName());
            entity=repository.save(entity);
            return new ProductDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException ("Entity not found"+id);
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
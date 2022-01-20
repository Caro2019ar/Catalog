package com.caro.catalog.services;

import com.caro.catalog.dto.CategoryDTO;
import com.caro.catalog.entities.Category;
import com.caro.catalog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<CategoryDTO> findAll(){
        List<Category> list=repository.findAll();
        return list.stream().map(x->new CategoryDTO(x)).collect(Collectors.toList());

       /*//===== the stream line above does the same as below:
        List<CategoryDTO> listDto = new ArrayList<>();
        for(Category cat:list){
            listDto.add(new CategoryDTO(cat));
        }
        return listDto;*/
    }
}

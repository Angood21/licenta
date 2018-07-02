package com.licenta.service;

import com.licenta.repository.CategoryRepository;
import com.licenta.repository.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("categoryService")
public class CategoryServiceImpl {

    @Autowired
    private CategoryRepository categoryRepository;
}

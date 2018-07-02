package com.licenta.service;

import com.licenta.model.Source;
import com.licenta.repository.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sourceService")
public class SourceServiceImpl implements SourceService {

    @Autowired
    private SourceRepository sourceRepository;


    public List<Source> getAll(){
        return sourceRepository.findAll();
    }
}

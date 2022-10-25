package com.example.jpacustomerexam.service.exam03;

import com.example.jpacustomerexam.model.Dept;
import com.example.jpacustomerexam.repository.exam03.Dept03Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class Dept03Service {

    @Autowired
    Dept03Repository deptRepository;

    public Page<Dept> findAllByOrderByDnoDesc(Pageable pageable){
        Page<Dept> page = deptRepository.findAllByOrderByDnoDesc(pageable);
        return page;
    }

}

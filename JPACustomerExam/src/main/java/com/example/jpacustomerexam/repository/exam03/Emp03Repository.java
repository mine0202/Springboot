package com.example.jpacustomerexam.repository.exam03;

import com.example.jpacustomerexam.model.Emp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Emp03Repository extends JpaRepository<Emp,Integer> {

}

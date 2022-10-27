package com.example.jpacustomerexam.repository.exam07;

import com.example.jpacustomerexam.dto.DeptEmpDto;
import com.example.jpacustomerexam.model.exam04.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface Emp07Repository extends JpaRepository<Employee, Integer>
        , Emp07RepositoryCustom {


}










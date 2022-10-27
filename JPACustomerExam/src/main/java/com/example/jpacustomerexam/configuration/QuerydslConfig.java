package com.example.jpacustomerexam.configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

// Querydsl 을 위한 설정 파일
@Configuration
//@Configuration  자바클래스가 .properties 파일같은 설정파일 용도로 사용할 수있음
public class QuerydslConfig {


//    @PersistenceContext  JPA 영속성 컨텍스트(관리공간)
    @PersistenceContext
    private EntityManager entityManager;

//    @Service , @Repository, @Component 처럼 아래 @Bean 달면 서버가 로딩되면 객체로 생성됨
//    주로 @Configuration 클래스에서 사용
    @Bean
    public JPAQueryFactory jpaQueryFactory(){
        return new JPAQueryFactory(entityManager);
    }

}

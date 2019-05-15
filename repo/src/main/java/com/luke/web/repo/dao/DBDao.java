package com.luke.web.repo.dao;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class DBDao {

    @PersistenceContext(name="entityManagerFactory")
    private EntityManager em;

}

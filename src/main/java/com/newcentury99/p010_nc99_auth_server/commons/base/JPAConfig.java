package com.newcentury99.p010_nc99_auth_server.commons.base;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "com.newcentury99.p010_nc99_auth_server")
public class JPAConfig {
    // JPA EntityManager Configuration for QueryDSL
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory queryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}

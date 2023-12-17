package com.github.yusrproject.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.github.yusrproject.persistence.repository")
public class DatabaseConfiguration {
}

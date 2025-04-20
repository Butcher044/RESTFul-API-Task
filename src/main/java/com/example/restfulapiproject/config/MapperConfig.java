package com.example.restfulapiproject.config;

import com.example.restfulapiproject.mapper.TaskMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация мапперов для преобразования объектов.
 */
@Configuration
public class MapperConfig {

    /**
     * Создает экземпляр TaskMapper.
     * 
     * @return TaskMapper для преобразования между сущностями и DTO
     */
    @Bean
    public TaskMapper taskMapper() {
        return TaskMapper.INSTANCE;
    }
}
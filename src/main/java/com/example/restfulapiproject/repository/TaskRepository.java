package com.example.restfulapiproject.repository;

import com.example.restfulapiproject.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}

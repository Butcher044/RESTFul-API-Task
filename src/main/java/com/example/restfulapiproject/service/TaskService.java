package com.example.restfulapiproject.service;

import com.example.restfulapiproject.model.dto.TaskDTO;

public interface TaskService {
    TaskDTO getById(Long id);

    TaskDTO createOrUpdate(TaskDTO taskDTO);

    void deleteById(Long id);

}

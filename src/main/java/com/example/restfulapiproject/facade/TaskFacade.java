package com.example.restfulapiproject.facade;

import com.example.restfulapiproject.model.dto.TaskDTO;

public interface TaskFacade {

    TaskDTO getById(Long id);

    TaskDTO createOrUpdate(TaskDTO taskDTO);

    void deleteById(Long id);

}

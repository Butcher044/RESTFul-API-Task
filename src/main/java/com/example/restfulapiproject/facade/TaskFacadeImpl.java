package com.example.restfulapiproject.facade;

import com.example.restfulapiproject.model.dto.TaskDTO;
import com.example.restfulapiproject.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class TaskFacadeImpl implements TaskFacade {

    private final TaskService taskService;

    @Override
    @Transactional
    public TaskDTO getById(Long id) {
        return taskService.getById(id);
    }

    @Override
    @Transactional
    public TaskDTO createOrUpdate(TaskDTO taskDTO) {
        return taskService.createOrUpdate(taskDTO);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        taskService.deleteById(id);
    }

}

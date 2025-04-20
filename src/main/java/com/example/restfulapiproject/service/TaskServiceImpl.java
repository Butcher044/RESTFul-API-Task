package com.example.restfulapiproject.service;

import com.example.restfulapiproject.mapper.TaskMapper;
import com.example.restfulapiproject.model.dto.TaskDTO;
import com.example.restfulapiproject.model.entity.Task;
import com.example.restfulapiproject.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;
    private final TaskMapper mapper;

    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Override
    public TaskDTO getById(Long id) {
        try {
            Task task = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id));
            return mapper.toDTO(task);
        } catch (Exception e) {
            logger.error("Error while getting task by id", e);
            throw e;
        }
    }

    @Override
    public TaskDTO createOrUpdate(TaskDTO taskDTO) {
        try {
            Task task = mapper.toEntity(taskDTO);

            if (taskDTO.getId() == null) {
                task.setId(null);
            } else {
                Task existingTask = repository.findById(taskDTO.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + taskDTO.getId()));

                existingTask.setTitle(task.getTitle());
                existingTask.setDescription(task.getDescription());

                task = existingTask;
            }

            Task savedTask = repository.save(task);

            return mapper.toDTO(savedTask);
        } catch (Exception e) {
            logger.error("Error while creating or updating task", e);
            throw e;
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error while deleting task by id", e);
            throw e;
        }
    }

}

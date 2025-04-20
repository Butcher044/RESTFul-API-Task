package com.example.restfulapiproject.controller;

import com.example.restfulapiproject.facade.TaskFacade;
import com.example.restfulapiproject.model.dto.TaskDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для управления задачами.
 * Обрабатывает HTTP-запросы, связанные с задачами.
 */
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskFacade taskFacade;

    public TaskController(TaskFacade taskFacade) {
        this.taskFacade = taskFacade;
    }

    /**
     * Получает задачу по идентификатору.
     * 
     * @param id идентификатор задачи
     * @return TaskDTO задача с указанным идентификатором
     */
    @GetMapping("/{id}")
    public TaskDTO getById(@PathVariable Long id) {
        return taskFacade.getById(id);
    }

    /**
     * Создает или обновляет задачу.
     * 
     * @param taskDTO данные задачи
     * @return TaskDTO созданная или обновленная задача
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO createOrUpdate(@RequestBody TaskDTO taskDTO) {
        return taskFacade.createOrUpdate(taskDTO);
    }

    /**
     * Удаляет задачу по идентификатору.
     * 
     * @param id идентификатор задачи
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        taskFacade.deleteById(id);
    }

}

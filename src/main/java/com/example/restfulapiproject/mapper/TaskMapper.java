package com.example.restfulapiproject.mapper;

import com.example.restfulapiproject.model.dto.TaskDTO;
import com.example.restfulapiproject.model.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskDTO toDTO(Task task);

    Task toEntity(TaskDTO taskDTO);


}

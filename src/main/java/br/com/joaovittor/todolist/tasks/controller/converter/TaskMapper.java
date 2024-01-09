package br.com.joaovittor.todolist.tasks.controller.converter;

import br.com.joaovittor.todolist.tasks.dto.TaskDTO;
import br.com.joaovittor.todolist.tasks.model.Task;
import br.com.joaovittor.todolist.utils.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper extends EntityMapper<TaskDTO, Task> {

    @Override
    @Mapping(target = "userId", expression = "java(model.getUserUUID())")
    TaskDTO toDTO(Task model);

    @Override
    @Mapping(target = "userId", ignore = true)
    Task toModel(TaskDTO dto);
}

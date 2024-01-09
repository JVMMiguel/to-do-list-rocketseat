package br.com.joaovittor.todolist.tasks.controller.converter;

import br.com.joaovittor.todolist.fixtures.TaskFixture;
import br.com.joaovittor.todolist.tasks.dto.TaskDTO;
import br.com.joaovittor.todolist.tasks.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TaskMapperTest {

    @Autowired
    TaskMapper taskMapper;

    @Test
    void toDTO_ShouldConvertToDTO() {
        Task model = TaskFixture.task();
        TaskDTO dto = taskMapper.toDTO(model);

        assertNotNull(dto);
        assertEquals(model.getId(), dto.getId());
        assertEquals(model.getTitle(), dto.getTitle());
        assertEquals(model.getDescription(), dto.getDescription());
        assertEquals(model.getPriority(), dto.getPriority());
        assertEquals(model.getUserId().getId(), dto.getUserId());
        assertEquals(model.getStartAt(), dto.getStartAt());
        assertEquals(model.getEndAt(), dto.getEndAt());
    }

    @Test
    void toDTOList_ShouldConvertToDTOList() {
        List<Task> modelList = TaskFixture.taskList();
        List<TaskDTO> dtoList = taskMapper.toDTO(modelList);

        assertNotNull(dtoList);
        assertEquals(modelList.get(0).getId(), dtoList.get(0).getId());
        assertEquals(modelList.get(0).getTitle(), dtoList.get(0).getTitle());
        assertEquals(modelList.get(0).getDescription(), dtoList.get(0).getDescription());
        assertEquals(modelList.get(0).getPriority(), dtoList.get(0).getPriority());
        assertEquals(modelList.get(0).getUserId().getId(), dtoList.get(0).getUserId());
        assertEquals(modelList.get(0).getStartAt(), dtoList.get(0).getStartAt());
        assertEquals(modelList.get(0).getEndAt(), dtoList.get(0).getEndAt());
    }

    @Test
    void toModel_ShouldConvertToModel() {
        TaskDTO dto = TaskFixture.taskDTO();
        Task model = taskMapper.toModel(dto);

        assertNotNull(model);
        assertEquals(dto.getId(), model.getId());
        assertEquals(dto.getTitle(), model.getTitle());
        assertEquals(dto.getDescription(), model.getDescription());
        assertEquals(dto.getPriority(), model.getPriority());
        assertEquals(dto.getStartAt(), model.getStartAt());
        assertEquals(dto.getEndAt(), model.getEndAt());
    }

    @Test
    void toModelList_ShouldConvertToModelList() {
        List<TaskDTO> dtoList = TaskFixture.taskDTOList();
        List<Task> modelList = taskMapper.toModel(dtoList);

        assertNotNull(modelList);
        assertEquals(dtoList.get(0).getId(), modelList.get(0).getId());
        assertEquals(dtoList.get(0).getTitle(), modelList.get(0).getTitle());
        assertEquals(dtoList.get(0).getDescription(), modelList.get(0).getDescription());
        assertEquals(dtoList.get(0).getPriority(), modelList.get(0).getPriority());
        assertEquals(dtoList.get(0).getStartAt(), modelList.get(0).getStartAt());
        assertEquals(dtoList.get(0).getEndAt(), modelList.get(0).getEndAt());
    }

}

package br.com.joaovittor.todolist.fixtures;

import br.com.joaovittor.todolist.tasks.dto.TaskDTO;
import br.com.joaovittor.todolist.tasks.model.Task;
import br.com.joaovittor.todolist.users.model.enums.PriorityEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.fromString;

public class TaskFixture {

    public static Task task() {
        Task model = new Task();
        String UUID = "412794d1-18c1-4278-9b2a-c87d548fadb0";
        model.setId(fromString(UUID));
        model.setTitle("Title test");
        model.setDescription("Description test");
        model.setPriority(PriorityEnum.LOW);
        model.setUserId(UserFixture.user());
        model.setStartAt(LocalDateTime.of(LocalDate.of(2024, 1, 10), LocalTime.of(0, 0, 0)));
        model.setEndAt(LocalDateTime.of(LocalDate.of(2024, 1, 11), LocalTime.of(0, 0, 0)));
        model.setCreatedAt(LocalDateTime.of(LocalDate.of(2024, 1, 9), LocalTime.of(0, 0, 0)));

        return model;
    }

    public static TaskDTO taskDTO() {
        TaskDTO dto = new TaskDTO();
        String UUID = "412794d1-18c1-4278-9b2a-c87d548fadb0";
        dto.setId(fromString(UUID));
        dto.setTitle("Title test");
        dto.setDescription("Description test");
        dto.setPriority(PriorityEnum.LOW);
        dto.setUserId(UserFixture.user().getId());
        dto.setStartAt(LocalDateTime.of(LocalDate.of(2024, 1, 10), LocalTime.of(0, 0, 0)));
        dto.setEndAt(LocalDateTime.of(LocalDate.of(2024, 1, 11), LocalTime.of(0, 0, 0)));

        return dto;
    }

    public static List<Task> taskList() {
        return new ArrayList<>(Collections.singletonList(task()));
    }

    public static List<TaskDTO> taskDTOList() {
        return new ArrayList<>(Collections.singletonList(taskDTO()));
    }
}

package br.com.joaovittor.todolist.tasks.controller;

import br.com.joaovittor.todolist.fixtures.TaskFixture;
import br.com.joaovittor.todolist.fixtures.UserFixture;
import br.com.joaovittor.todolist.tasks.controller.converter.TaskMapper;
import br.com.joaovittor.todolist.tasks.dto.TaskDTO;
import br.com.joaovittor.todolist.tasks.model.Task;
import br.com.joaovittor.todolist.tasks.repository.TaskRepository;
import br.com.joaovittor.todolist.tasks.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {

    @MockBean
    private TaskRepository repository;

    @MockBean
    private TaskService taskService;

    @MockBean
    private TaskMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Captor
    private ArgumentCaptor<Task> taskArgumentCaptor;

    @Captor
    private ArgumentCaptor<List<Task>> taskArgumentCaptorList;

    @Test
    void listAllTasksByUser_ShouldListAllTasksByUser() throws Exception {
        List<Task> taskList = TaskFixture.taskList();
        List<TaskDTO> taskDTOList = TaskFixture.taskDTOList();

        when(repository.findAllByUserId_id(any())).thenReturn(taskList);
        when(mapper.toDTO(taskList)).thenReturn(taskDTOList);

        int response = mockMvc.perform(get("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getStatus();

        verify(mapper).toDTO(taskArgumentCaptorList.capture());
        assertEquals(200, response);
    }

    @Test
    void registerTask_ShouldRegisterTask() throws Exception {
        Task task = TaskFixture.task();
        TaskDTO taskDTO = TaskFixture.taskDTO();

        when(mapper.toModel(taskDTO)).thenReturn(task);
        doNothing().when(taskService).registerTask(task, UserFixture.user().getId());

        mockMvc.perform(post("/api/tasks")
                        .content(objectMapper.writeValueAsString(TaskFixture.taskDTO()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void findTaskByUUID_ShouldReturnTaskByUUID() throws Exception {
        Task task = TaskFixture.task();
        TaskDTO taskDTO = TaskFixture.taskDTO();

        when(repository.findById(any())).thenReturn(Optional.of(task));
        when(mapper.toDTO(task)).thenReturn(taskDTO);

        int response = mockMvc.perform(get("/api/tasks/{id}", "412794d1-18c1-4278-9b2a-c87d548fadb0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getStatus();

        verify(mapper).toDTO(taskArgumentCaptor.capture());
        assertEquals(200, response);
    }

    @Test
    void findTaskByUUID_ShouldThrowException() {
        when(repository.findById(any())).thenReturn(Optional.empty());

        assertThrows(ServletException.class, () -> {
            mockMvc.perform(get("/api/tasks/{id}", "66ead495-52ab-4388-a19f-8c30cd6eaed4")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andReturn()
                    .getResponse()
                    .getStatus();
        });
    }

    @Test
    void updateTask_ShouldUpdateTask() throws Exception {
        TaskDTO taskDTO = TaskFixture.taskDTO();
        Task task = TaskFixture.task();

        when(mapper.toModel(taskDTO)).thenReturn(task);
        doNothing().when(taskService).updateTask(task, UUID.fromString("412794d1-18c1-4278-9b2a-c87d548fadb0"), UserFixture.user().getId());

        int response = mockMvc.perform(put("/api/tasks/{id}", "412794d1-18c1-4278-9b2a-c87d548fadb0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(TaskFixture.taskDTO())))
                .andReturn()
                .getResponse()
                .getStatus();

        assertEquals(200, response);
    }
}

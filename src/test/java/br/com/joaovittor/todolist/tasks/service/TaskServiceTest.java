package br.com.joaovittor.todolist.tasks.service;

import br.com.joaovittor.todolist.exceptions.ToDoListException;
import br.com.joaovittor.todolist.fixtures.TaskFixture;
import br.com.joaovittor.todolist.fixtures.UserFixture;
import br.com.joaovittor.todolist.tasks.model.Task;
import br.com.joaovittor.todolist.tasks.repository.TaskRepository;
import br.com.joaovittor.todolist.users.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TaskServiceTest {

    @InjectMocks
    private TaskService service;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TaskRepository taskRepository;

    @Test
    void registerTask_ShouldRegisterTask() {
        when(userRepository.findById(UUID.fromString("95581291-2a57-4f8c-8abd-e0bca39b1218"))).thenReturn(Optional.of(UserFixture.user()));

        assertDoesNotThrow(() -> service.registerTask(TaskFixture.task(), UUID.fromString("95581291-2a57-4f8c-8abd-e0bca39b1218")));
    }

    @Test
    void registerTask_ShouldThrowException() {
        Task task = TaskFixture.task();
        task.setEndAt(LocalDateTime.of(LocalDate.of(2024, 1, 9), LocalTime.of(0, 0, 0)));

        when(userRepository.findById(UUID.fromString("95581291-2a57-4f8c-8abd-e0bca39b1218"))).thenReturn(Optional.of(UserFixture.user()));

        assertThrows(DateTimeException.class, () -> service.registerTask(task, UUID.fromString("95581291-2a57-4f8c-8abd-e0bca39b1218")));
    }

    @Test
    void updateTask_ShouldUpdateTask() {
        Task task = TaskFixture.task();
        task.setDescription("Description changed");
        task.setTitle("Title changed");
        task.setPriority(null);

        when(taskRepository.findById(UUID.fromString("412794d1-18c1-4278-9b2a-c87d548fadb0"))).thenReturn(Optional.of(TaskFixture.task()));

        assertDoesNotThrow(() -> service.updateTask(task, UUID.fromString("412794d1-18c1-4278-9b2a-c87d548fadb0"), UUID.fromString("95581291-2a57-4f8c-8abd-e0bca39b1218")));
    }

    @Test
    void updateTask_ShouldThrow_EntityNotFoundException() {
        Task task = TaskFixture.task();
        task.setDescription("Description changed");
        task.setTitle("Title changed");

        when(taskRepository.findById(UUID.fromString("412794d1-18c1-4278-9b2a-c87d548fadb0"))).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.updateTask(task, UUID.fromString("412794d1-18c1-4278-9b2a-c87d548fadb0"), UUID.fromString("95581291-2a57-4f8c-8abd-e0bca39b1218")));
    }

    @Test
    void updateTask_ShouldThrow_ToDoListException() {
        Task task = TaskFixture.task();
        task.setDescription("Description changed");
        task.setTitle("Title changed");

        when(taskRepository.findById(UUID.fromString("412794d1-18c1-4278-9b2a-c87d548fadb0"))).thenReturn(Optional.of(TaskFixture.task()));

        assertThrows(ToDoListException.class, () -> service.updateTask(task, UUID.fromString("412794d1-18c1-4278-9b2a-c87d548fadb0"), UUID.fromString("54cdf6e9-87d2-491a-a2dd-e36f12396d49")));
    }

}

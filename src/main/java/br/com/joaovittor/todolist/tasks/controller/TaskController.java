package br.com.joaovittor.todolist.tasks.controller;

import br.com.joaovittor.todolist.tasks.controller.converter.TaskMapper;
import br.com.joaovittor.todolist.tasks.dto.TaskDTO;
import br.com.joaovittor.todolist.tasks.model.Task;
import br.com.joaovittor.todolist.tasks.repository.TaskRepository;
import br.com.joaovittor.todolist.tasks.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/tasks")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskController {

    private final TaskRepository repository;

    private final TaskMapper mapper;

    private final TaskService service;

    @GetMapping
    public ResponseEntity<List<TaskDTO>> listAllTasksByUser(HttpServletRequest request) {
        Object userId = request.getAttribute("userId");

        List<Task> taskList = repository.findAllByUserId_id((UUID) userId);

        return new ResponseEntity<>(mapper.toDTO(taskList), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> registerTask(@Valid @RequestBody TaskDTO taskDTO, HttpServletRequest request) {
        Object userId = request.getAttribute("userId");

        Task taskModel = mapper.toModel(taskDTO);
        service.registerTask(taskModel, (UUID) userId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TaskDTO> findTaskByUUID(@PathVariable("id") UUID id) {
        log.info("[TO-DO LIST APPLICATION] List an User by his UUID");

        Task task = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found any User with this UUID"));

        return new ResponseEntity<>(mapper.toDTO(task), HttpStatus.OK);
    }
}

package br.com.joaovittor.todolist.users.controller;

import br.com.joaovittor.todolist.users.controller.converter.UserMapper;
import br.com.joaovittor.todolist.users.dto.UserDTO;
import br.com.joaovittor.todolist.users.model.User;
import br.com.joaovittor.todolist.users.repository.UserRepository;
import br.com.joaovittor.todolist.users.service.UserService;
import jakarta.persistence.EntityNotFoundException;
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
@RequestMapping(value = "/api/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserRepository repository;

    private final UserMapper mapper;

    private final UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAllUsers() {
        log.info("[TO-DO LIST APPLICATION] List all Users.");

        List<User> userList = repository.findAll();

        return new ResponseEntity<>(mapper.toDTO(userList), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> registerUser(@Valid @RequestBody UserDTO userDTO) {
        log.info("[TO-DO LIST APPLICATION] Inserting a new User.");

        User model = mapper.toModel(userDTO);
        service.registerUser(model);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findUserByUUID(@PathVariable("id") UUID id) {
        log.info("[TO-DO LIST APPLICATION] List an User by his UUID");

        User user = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found any User with this UUID"));

        return new ResponseEntity<>(mapper.toDTO(user), HttpStatus.OK);
    }
}

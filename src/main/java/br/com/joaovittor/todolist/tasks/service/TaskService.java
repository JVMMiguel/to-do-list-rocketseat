package br.com.joaovittor.todolist.tasks.service;

import br.com.joaovittor.todolist.tasks.model.Task;
import br.com.joaovittor.todolist.tasks.repository.TaskRepository;
import br.com.joaovittor.todolist.users.model.User;
import br.com.joaovittor.todolist.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskService {

    private final TaskRepository repository;

    private final UserRepository userRepository;

    public void registerTask(Task taskModel, UUID userId) {
        Optional<User> byId = userRepository.findById(userId);

        if (taskModel.getEndAt().isBefore(taskModel.getStartAt()))
            throw new DateTimeException("You cant save a task with the end at before start at");

        byId.ifPresent(taskModel::setUserId);

        repository.save(taskModel);
    }

}

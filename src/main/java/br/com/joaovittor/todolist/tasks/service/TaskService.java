package br.com.joaovittor.todolist.tasks.service;

import br.com.joaovittor.todolist.exceptions.ToDoListException;
import br.com.joaovittor.todolist.tasks.model.Task;
import br.com.joaovittor.todolist.tasks.repository.TaskRepository;
import br.com.joaovittor.todolist.users.model.User;
import br.com.joaovittor.todolist.users.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.time.DateTimeException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
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

    public void updateTask(Task task, UUID taskId, UUID loggedUser) {
        Task taskEntity = repository.findById(taskId).orElseThrow(() ->
                new EntityNotFoundException("Not found any task with this ID"));

        if (!taskEntity.getUserId().getId().equals(loggedUser))
            throw new ToDoListException("You cant edit a task that you dont own.");

        copyNonNullProperties(task, taskEntity);

        repository.save(taskEntity);
    }

    private void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullProperties(source));
    }

    private String[] getNullProperties(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<>();

        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null)
                emptyNames.add(pd.getName());
        }

        String[] result = new String[emptyNames.size()];

        return emptyNames.toArray(result);
    }
}

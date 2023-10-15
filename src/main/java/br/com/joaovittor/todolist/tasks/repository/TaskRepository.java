package br.com.joaovittor.todolist.tasks.repository;

import br.com.joaovittor.todolist.tasks.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findAllByUserId_id(UUID id);
}

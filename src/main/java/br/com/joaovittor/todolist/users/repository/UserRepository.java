package br.com.joaovittor.todolist.users.repository;

import br.com.joaovittor.todolist.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUserName(String userName);

}

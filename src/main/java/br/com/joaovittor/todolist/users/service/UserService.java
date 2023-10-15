package br.com.joaovittor.todolist.users.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.joaovittor.todolist.users.exception.UserException;
import br.com.joaovittor.todolist.users.model.User;
import br.com.joaovittor.todolist.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final UserRepository repository;

    public void registerUser(User user) {
        Optional<User> entity = repository.findByUserName(user.getUserName());

        if (entity.isPresent())
            throw new UserException("This username is already in use.");

        String passwordEncrypted = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
        user.setPassword(passwordEncrypted);

        repository.save(user);
    }

}

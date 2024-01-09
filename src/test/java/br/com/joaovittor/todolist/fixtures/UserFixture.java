package br.com.joaovittor.todolist.fixtures;

import br.com.joaovittor.todolist.users.dto.UserDTO;
import br.com.joaovittor.todolist.users.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.UUID.fromString;

public class UserFixture {

    public static User user() {
        User model = new User();
        String UUID = "95581291-2a57-4f8c-8abd-e0bca39b1218";
        model.setId(fromString(UUID));
        model.setFullName("fullNameTest");
        model.setUserName("usernameTest");
        model.setPassword("passwordTest");
        model.setCreatedAt(LocalDateTime.of(LocalDate.of(2024, 1, 9), LocalTime.of(0, 0, 0)));

        return model;
    }

    public static UserDTO userDTO() {
        UserDTO dto = new UserDTO();
        String UUID = "95581291-2a57-4f8c-8abd-e0bca39b1218";
        dto.setId(fromString(UUID));
        dto.setFullName("fullNameTest");
        dto.setUserName("usernameTest");
        dto.setPassword("passwordTest");

        return dto;
    }

    public static List<User> userList() {
        return new ArrayList<>(Collections.singletonList(user()));
    }

    public static List<UserDTO> userDTOList() {
        return new ArrayList<>(Collections.singletonList(userDTO()));
    }
}

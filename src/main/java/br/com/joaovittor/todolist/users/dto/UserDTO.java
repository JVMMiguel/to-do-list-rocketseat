package br.com.joaovittor.todolist.users.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @Builder
public class UserDTO {

    private UUID id;

    @NotBlank(message = "Full name is a required field")
    private String fullName;

    @NotBlank(message = "Username is a required field")
    private String userName;

    @NotBlank(message = "Password is a required field")
    private String password;

}

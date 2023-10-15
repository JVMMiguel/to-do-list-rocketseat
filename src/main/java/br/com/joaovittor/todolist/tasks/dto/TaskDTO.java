package br.com.joaovittor.todolist.tasks.dto;

import br.com.joaovittor.todolist.users.model.enums.PriorityEnum;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @Builder
public class TaskDTO {

    private UUID id;

    @NotBlank(message = "Title is a required field")
    @Size(max = 50, message = "Maximum length for title is 50 characters")
    private String title;

    @NotBlank(message = "Description is a required field")
    @Size(max = 255, message = "Maximum length for title is 255 characters")
    private String description;

    @NotNull(message = "Priority is a required field")
    private PriorityEnum priority;

    private UUID userId;

    @NotNull(message = "Start At is a required field")
    @FutureOrPresent(message = "The data of Start At must be after the actual date and time")
    private LocalDateTime startAt;

    @NotNull(message = "End At is a required field")
    @FutureOrPresent(message = "The data of End At must be after the actual date and time")
    private LocalDateTime endAt;

}

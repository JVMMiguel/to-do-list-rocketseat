package br.com.joaovittor.todolist.users.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PriorityEnum {

    HIGH("HIGH"),
    MEDIUM("MEDIUM"),
    LOW("LOW");

    private String description;

    public String getDescription() { return description; }

}

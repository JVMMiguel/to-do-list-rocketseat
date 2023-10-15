package br.com.joaovittor.todolist.tasks.model;

import br.com.joaovittor.todolist.users.model.User;
import br.com.joaovittor.todolist.users.model.enums.PriorityEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_TASKS")
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private PriorityEnum priority;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by", foreignKey = @ForeignKey(name = "fk_tasks_users"), updatable = false)
    private User userId;

    @Column(name = "start_at")
    private LocalDateTime startAt;

    @Column(name = "end_at")
    private LocalDateTime endAt;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}

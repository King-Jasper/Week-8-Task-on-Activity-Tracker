package com.ifennanwafor.week8task.entity;

import com.ifennanwafor.week8task.dto.TaskDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime completedAt;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public Task(TaskDTO taskDTO) {
        this.title = taskDTO.getTitle();
        this.description = taskDTO.getDescription();
        this.status = taskDTO.getStatus();
    }


}

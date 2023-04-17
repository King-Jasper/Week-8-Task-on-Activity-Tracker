package com.ifennanwafor.week8task.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import com.ifennanwafor.week8task.entity.Task;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDTO {
    private Long id;
    private String title;
    private String description;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime completedAt;

    public TaskDTO(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.status = task.getStatus();
        this.createdAt = task.getCreatedAt();
        this.updatedAt = task.getUpdatedAt();
        this.completedAt = task.getCompletedAt();
    }
}

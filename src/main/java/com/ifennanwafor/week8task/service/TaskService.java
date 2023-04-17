package com.ifennanwafor.week8task.service;

import com.ifennanwafor.week8task.dto.TaskDTO;
import com.ifennanwafor.week8task.exception.ResourceNotFoundException;
import com.ifennanwafor.week8task.entity.Task;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskService {
    Task getTaskById(Long id);
    List<Task> getAllTasks();
    List<Task> getTasksByStatus(String status);
    List<Task> getTasksBySearch(Long userId, String query);
    Task saveTask(TaskDTO taskDTO);

    Task updateTask(Long id, TaskDTO taskDTO) throws ResourceNotFoundException;
    void deleteTask(Long id) throws ResourceNotFoundException;
}

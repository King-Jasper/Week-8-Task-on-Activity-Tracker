package com.ifennanwafor.week8task.implementation;

import com.ifennanwafor.week8task.dto.TaskDTO;
import com.ifennanwafor.week8task.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifennanwafor.week8task.repository.TaskRepository;
import com.ifennanwafor.week8task.service.TaskService;
import com.ifennanwafor.week8task.entity.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAllTasks();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findTaskById(id).get();
    }

    @Override
    public List<Task> getTasksByStatus(String status) {
        return taskRepository.findTasksByStatus(status);
    }

    @Override
    public List<Task> getTasksBySearch(Long userId, String query) {
        return taskRepository.searchByTitleContainingIgnoreCaseAndUser_UserId(query, userId);
    }

    @Override
    public Task saveTask(TaskDTO taskDTO) {
        Task task = new Task(taskDTO);
        task.setCreatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long id, TaskDTO taskDTO) throws ResourceNotFoundException {
        Task task = new Task(taskDTO);
        task.setUpdatedAt(LocalDateTime.now());
        Optional<Task> optionalTask = taskRepository.findTaskById(id);
        Task existingTask;
        if (optionalTask.isPresent()) {
            existingTask = optionalTask.get();
            existingTask.setTitle(task.getTitle());
            existingTask.setDescription(task.getDescription());
            existingTask.setStatus(task.getStatus());
            taskRepository.save(existingTask);
        } else throw new ResourceNotFoundException("Task not found!");
        return existingTask;
    }

    @Override
    public void deleteTask(Long id) throws ResourceNotFoundException {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        taskRepository.delete(task);
    }
    public List<Task> searchTasks(String query) {
        List<Task> allTasks = getAllTasks();
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : allTasks) {
            if (task.getDescription().contains(query)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}

package com.ifennanwafor.week8task.Controller;

import com.ifennanwafor.week8task.dto.TaskDTO;
import com.ifennanwafor.week8task.exception.ResourceNotFoundException;
import com.ifennanwafor.week8task.service.TaskService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import com.ifennanwafor.week8task.entity.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class TaskController {
    private final TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping("/tasks")
    public ModelAndView showDashboard() {
        List<Task> tasks = taskService.getAllTasks();
        ModelAndView mav = new ModelAndView("activity_dashBoard");
        mav.addObject("tasks", tasks);
        return mav;
    }
    @GetMapping("/tasks/add")
    public ModelAndView getAddTaskForm() {
        ModelAndView mav = new ModelAndView("add_Task");
        mav.addObject("taskDTO", new TaskDTO());
        return mav;
    }

    @PostMapping("/tasks/save")
    public ModelAndView saveTask(@ModelAttribute("taskDTO") TaskDTO taskDTO) {
        Task savedTask = taskService.saveTask(taskDTO);
        ModelAndView modelAndView = new ModelAndView("redirect:/tasks"); // Redirect to the pending tasks page
        return modelAndView;
    }

    @GetMapping("/tasks/task-detail/{id}")
    public ModelAndView showTaskDetail(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        ModelAndView mav = new ModelAndView("task-detail");
        mav.addObject("task", task);
        return mav;
    }

    @GetMapping("/tasks/{status}")
    public ModelAndView getTasksByStatus(@PathVariable String status) {
        List<Task> tasks = taskService.getTasksByStatus(status);
        ModelAndView modelAndView = new ModelAndView("tasks");
        modelAndView.addObject("tasks", tasks);
        return modelAndView;
    }

    @PutMapping("/tasks/{id}")
    public ModelAndView updateTask(@PathVariable Long id, @ModelAttribute("task") Task task) throws ResourceNotFoundException {
        TaskDTO taskDTO = new TaskDTO(task);
        Task updatedTask = taskService.updateTask(id, taskDTO);
        ModelAndView modelAndView = new ModelAndView("redirect:/tasks/pending"); // Redirect to the pending tasks page
        return modelAndView;
    }

    @GetMapping("/tasks/delete/{id}")
    public ModelAndView deleteTask(@PathVariable Long id) throws ResourceNotFoundException {
        taskService.deleteTask(id);
        return new ModelAndView("redirect:/tasks");
    }
    @GetMapping("/tasks/edit/{id}")
    public ModelAndView getEditTaskForm(@PathVariable Long id) throws ResourceNotFoundException {
        Task task = taskService.getTaskById(id);
        ModelAndView mav = new ModelAndView("edit_Task");
        mav.addObject("taskDTO", new TaskDTO(task));
        return mav;
    }

    @PostMapping("/tasks/search")
    public ModelAndView searchTasks(@RequestParam("query") String query,
                                    HttpSession session){
        Long userId = (Long) session.getAttribute("userId");

        List<Task> tasks = taskService.getTasksBySearch(userId, query);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tasks", tasks);
        modelAndView.setViewName("activity_dashBoard");
        return modelAndView;
    }
}




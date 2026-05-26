package com.mabadcortes.taskmanager.controller;

import com.mabadcortes.taskmanager.model.Task;
import com.mabadcortes.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    /*
     * Constructor injection.
     */
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /*
     * Returns all tasks.
     */
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    /*
     * Creates a new task.
     */
    @PostMapping
    public Task createTask(@Valid @RequestBody Task task) {
        return taskService.createTask(task);
    }

    /*
     * Updates a task.
     */
    @PutMapping("/{id}")
    public Task updateTask(@Valid @PathVariable Long id, @RequestBody Task newTask) {
        return taskService.updateTask(id, newTask);
    }

    /*
     * Deletes a task by the id.
     */
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }
}

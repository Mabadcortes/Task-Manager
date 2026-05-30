package com.mabadcortes.taskmanager.controller;

import com.mabadcortes.taskmanager.dto.TaskResponseDTO;
import com.mabadcortes.taskmanager.dto.TaskRequestDTO;
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
     * GET /tasks
     * Returns all tasks.
     */
    @GetMapping
    public List<TaskResponseDTO> getAllTasks() {
        return taskService.getAllTasks();
    }

    /*
     * GET /tasks/{id}
     * Returns a task by its ID.
     */
    @GetMapping("/{id}")
    public TaskResponseDTO getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    /*
     * POST /tasks
     * Creates a new task.
     */
    @PostMapping
    public TaskResponseDTO createTask(@Valid @RequestBody TaskRequestDTO requestDTO) {
        return taskService.createTask(requestDTO);
    }

    /*
     * PUT /tasks/{id}
     * Updates a task.
     */
    @PutMapping("/{id}")
    public TaskResponseDTO updateTask(@PathVariable Long id, @Valid @RequestBody TaskRequestDTO requestDTO) {
        return taskService.updateTask(id, requestDTO);
    }

    /*
     * DELETE /tasks/{id}
     * Deletes a task by the id.
     */
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}

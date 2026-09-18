package com.mabadcortes.taskmanager.controller;

import com.mabadcortes.taskmanager.dto.TaskResponseDTO;
import com.mabadcortes.taskmanager.dto.TaskRequestDTO;
import com.mabadcortes.taskmanager.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Task Management", description = "Endpoints for creating, retrieving, updating, and deleting tasks")
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
    @Operation(summary = "Get all tasks", description = "Retrieves a paginated list of tasks. Can be filtered by completion status.")
    @GetMapping
    public ResponseEntity<Page<TaskResponseDTO>> getAllTasks(
            @RequestParam(required = false) Boolean completed,
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        Page<TaskResponseDTO> task = taskService.getAllTasks(completed, pageable);
        return ResponseEntity.ok(task);
    }

    /*
     * GET /tasks/{id}
     * Returns a task by its ID.
     */
    @Operation(summary = "Get a task by ID", description = "Retrieves a specific task using its unique ID.")
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    /*
     * POST /tasks
     * Creates a new task.
     */
    @Operation(summary = "Create a new task", description = "Creates a new task with the provided title and description.")
    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody TaskRequestDTO requestDTO) {

        TaskResponseDTO createdTask = taskService.createTask(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    /*
     * PUT /tasks/{id}
     * Updates a task.
     */
    @Operation(summary = "Update an existing task", description = "Updates the details of an existing task by its ID.")
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id, @Valid @RequestBody TaskRequestDTO requestDTO) {

        TaskResponseDTO updatedTask = taskService.updateTask(id, requestDTO);
        return ResponseEntity.ok(updatedTask);
    }

    /*
     * DELETE /tasks/{id}
     * Deletes a task by the id.
     */
    @Operation(summary = "Delete a task", description = "Permanently removes a task by its ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {

        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}

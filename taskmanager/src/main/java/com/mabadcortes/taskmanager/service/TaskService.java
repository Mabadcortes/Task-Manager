package com.mabadcortes.taskmanager.service;

import com.mabadcortes.taskmanager.exception.TaskNotFoundException;
import com.mabadcortes.taskmanager.model.Task;
import com.mabadcortes.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;
import com.mabadcortes.taskmanager.dto.TaskRequestDTO;
import com.mabadcortes.taskmanager.dto.TaskResponseDTO;

import java.util.List;

/*
 * Service layer responsible for task business logic.
 */
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    /*
     * Constructor injection.
     */
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /*
     * Returns all tasks converted to response DTOs.
     */
    public List<TaskResponseDTO> getAllTasks() {

        return taskRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();

    }

    /*
     * Creates a new task from a request DTO.
     */
    public TaskResponseDTO createTask(TaskRequestDTO requestDTO) {

        Task task = new Task();

        task.setTitle(requestDTO.getTitle());
        task.setDescription(requestDTO.getDescription());
        task.setCompleted(
                requestDTO.getCompleted() != null
                        && requestDTO.getCompleted()
        );

        Task savedtask = taskRepository.save(task);

        return mapToResponseDTO(savedtask);
    }

    /*
     * Finds a task by the id as response DTO.
     * Throws an exception if the task doesn't exist.
     */
    public TaskResponseDTO getTaskById(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));

        return mapToResponseDTO(task);
    }

    /*
     * Updates an existing task.
     */
    public TaskResponseDTO updateTask(Long id, TaskRequestDTO requestDTO) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));


        task.setTitle(requestDTO.getTitle());
        task.setDescription(requestDTO.getDescription());
        task.setCompleted(
                requestDTO.getCompleted() != null
                        && requestDTO.getCompleted());

        Task updatedTask = taskRepository.save(task);

        return mapToResponseDTO(updatedTask);
    }

    /*
     * Deletes a task by the id.
     */
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));

        taskRepository.delete(task);

    }

    /*
     * Converts a Task entity into a TaskResponseDTO.
     */
    private TaskResponseDTO mapToResponseDTO(Task task) {

        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getCompleted()
        );
    }
}

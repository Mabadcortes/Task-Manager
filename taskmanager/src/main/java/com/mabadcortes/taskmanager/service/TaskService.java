package com.mabadcortes.taskmanager.service;

import com.mabadcortes.taskmanager.exception.TaskNotFoundException;
import com.mabadcortes.taskmanager.model.Task;
import com.mabadcortes.taskmanager.mapper.TaskMapper;
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
    private final TaskMapper taskMapper;

    /*
     * Constructor injection.
     */
    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {

        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    /*
     * Returns all tasks converted to response DTOs.
     */
    public List<TaskResponseDTO> getAllTasks() {

        return taskRepository.findAll()
                .stream()
                .map(taskMapper::toResponseDTO)
                .toList();

    }

    /*
     * Creates a new task from a request DTO.
     */
    public TaskResponseDTO createTask(TaskRequestDTO requestDTO) {

        Task task = taskMapper.toEntity(requestDTO);

        Task savedtask = taskRepository.save(task);

        return taskMapper.toResponseDTO(savedtask);
    }

    /*
     * Finds a task by the id as response DTO.
     * Throws an exception if the task doesn't exist.
     */
    public TaskResponseDTO getTaskById(Long id) {

        Task task = findTaskById(id);

        return taskMapper.toResponseDTO(task);
    }

    /*
     * Updates an existing task.
     */
    public TaskResponseDTO updateTask(Long id, TaskRequestDTO requestDTO) {

        Task existingTask = findTaskById(id);

        taskMapper.updateEntityFromDTO(existingTask, requestDTO);

        Task updatedTask = taskRepository.save(existingTask);

        return taskMapper.toResponseDTO(updatedTask);
    }

    /*
     * Deletes a task by the id.
     */
    public void deleteTask(Long id) {
        Task task = findTaskById(id);

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

    /*
     * Finds a task by the ID.
     */
    private Task findTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() ->
                        new TaskNotFoundException(
                                "Task not found with id: " + id
                        )
                );
    }
}

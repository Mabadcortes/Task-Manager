package com.mabadcortes.taskmanager.mapper;

import com.mabadcortes.taskmanager.dto.TaskRequestDTO;
import com.mabadcortes.taskmanager.dto.TaskResponseDTO;
import com.mabadcortes.taskmanager.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    /*
     * Converts a TaskRequestDTO into a Task entity.
     */
    public Task toEntity(TaskRequestDTO requestDTO) {

        Task task = new Task();

        task.setTitle(requestDTO.getTitle());
        task.setDescription(requestDTO.getDescription());
        task.setCompleted(
                requestDTO.getCompleted() != null
                        && requestDTO.getCompleted()
        );

        return task;
    }

    /*
     * Converts a Task entity into a TaskResponseDTO
     */
    public TaskResponseDTO toResponseDTO(Task task) {
        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getCompleted()
        );
    }

    /*
     * Updates an existing Task entity using data from a TaskRequestDTO.
     */
    public void updateEntityFromDTO(Task task, TaskRequestDTO requestDTO) {

        task.setTitle(requestDTO.getTitle());
        task.setDescription(requestDTO.getDescription());
        task.setCompleted(
                requestDTO.getCompleted() != null
                        && requestDTO.getCompleted()
        );
    }

}

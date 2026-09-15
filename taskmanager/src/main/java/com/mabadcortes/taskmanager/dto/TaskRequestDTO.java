package com.mabadcortes.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequestDTO {

    /*
     * Title received from the client.
     * It cannot be null, empty or blank.
     * Must be between 3 and 100 characters.
     */
    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    /*
     * Description received from the client.
     * It cannot be null, empty or blank.
     * Maximum 500 characters.
     */
    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    /*
     * Indicates whether the task is completed or not.
     */
    private Boolean completed;
}
package com.mabadcortes.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequestDTO {

    /*
     * Title received from the client.
     * It cannot be null, empty or blank.
     */
    @NotBlank(message = "Title is required")
    private String title;

    /*
     * Description received from the client.
     * It cannot be null, empty or blank.
     */
    @NotBlank(message = "Description is required")
    private String description;

    /*
     * Indicates whether the task is completed or not.
     */
    private Boolean completed;
}

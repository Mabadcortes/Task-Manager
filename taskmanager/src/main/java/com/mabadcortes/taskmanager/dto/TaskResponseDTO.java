package com.mabadcortes.taskmanager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "Data Transfer Object representing a task returned to the client")
public class TaskResponseDTO {

    /*
     * Task identifier.
     */
    @Schema(description = "Unique identifier of the task")
    private Long id;

    /*
     * Task title returned to the client.
     */
    @Schema(description = "Title of the task")
    private String title;

    /*
     * Task description returned to the client.
     */
    @Schema(description = "Detailed description of the task")
    private String description;

    /*
     * Indicates whether the task is completed or not.
     */
    @Schema(description = "Indicates whether the taks is completed or not")
    private Boolean completed;
}

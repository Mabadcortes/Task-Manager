package com.mabadcortes.taskmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskResponseDTO {

    /*
     * Task identifier.
     */
    private Long id;

    /*
     * Task title returned to the client.
     */
    private String title;

    /*
     * Task description returned to the client.
     */
    private String description;

    /*
     * Indicates whether the task is completed or not.
     */
    private Boolean completed;
}

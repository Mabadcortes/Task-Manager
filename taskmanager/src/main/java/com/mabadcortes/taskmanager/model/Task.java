package com.mabadcortes.taskmanager.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.*;
import lombok.*;

/*
 * Represents a task entity stored in the database.
 * Each instance of this class corresponds to one row
 * in the "tasks" table.
 */

/*
 * Marks this class as a JPA entity
 */
@Entity

/*
 * Specifies the database table name
 */
@Table(name = "tasks")

/*
 * Lombok annotations to reduce boilerplate code
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Task {

    /*
     * Primary key of the task table.
     * Generated automatically by the database.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    /*
     * Title of the task
     */
    private Long id;

    /*
     * Tittle of the task.
     * Cant be blank.
     */
    @NotBlank(message = "Title is required")
    private String title;

    /*
     * Detailed description of the task
     * Cant be blank.
     */
    @NotBlank(message = "Description is required")
    private String description;

    /*
     * Indicates whether the task is completed or not
     */
    private Boolean completed;

}

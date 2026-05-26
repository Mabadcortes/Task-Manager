package com.mabadcortes.taskmanager.service;

import com.mabadcortes.taskmanager.exception.TaskNotFoundException;
import com.mabadcortes.taskmanager.model.Task;
import com.mabadcortes.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

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
     * Returns all tasks from the database.
     */
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /*
     * Creates and stores a new task.
     */
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    /*
     * Finds a task by the id.
     * Throws an exception if the task doesn't exist.
     */
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
    }

    /*
     * Updates an existing task.
     */
    public Task updateTask(Long id, Task newTask) {
        Task task = getTaskById(id);

        task.setTittle(newTask.getTittle());
        task.setDescription(newTask.getDescription());
        task.setCompleted(newTask.getCompleted());

        return taskRepository.save(task);
    }

    /*
     * Deletes a task by the id.
     */
    public void deleteTask(Long id){
        Task task = getTaskById(id);

        taskRepository.delete(task);

    }
}

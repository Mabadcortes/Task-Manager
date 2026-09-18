package com.mabadcortes.taskmanager.repository;

import com.mabadcortes.taskmanager.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    /*
     * Retrieves a paginated list of tasks filtered by their completed status.
     */
    Page<Task> findByCompleted(Boolean completed, Pageable pageable);
}

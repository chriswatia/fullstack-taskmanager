package com.warriors.taskmanager.Repository;

import com.warriors.taskmanager.Models.Task;
import com.warriors.taskmanager.Models.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByProjectId(Long projectId, Pageable pageable);
    Page<Task> findByProjectIdAndStatus(Long projectId, TaskStatus status, Pageable pageable);
    Page<Task> findByProjectIdAndDueDate(Long projectId, Date dueDate, Pageable pageable);
    Page<Task> findByProjectIdAndStatusAndDueDate(Long projectId, TaskStatus status, Date dueDate, Pageable pageable);
    long countByProjectIdAndStatus(Long projectId, TaskStatus status);
}

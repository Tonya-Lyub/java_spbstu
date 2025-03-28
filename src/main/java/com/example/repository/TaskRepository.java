package com.example.repository;

import com.example.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
    List<Task> findByUserId(Long userId);
    
    List<Task> findByCompleted(boolean completed);
    
    @Query("SELECT t FROM Task t WHERE t.dueDate < ?1 AND t.completed = false")
    List<Task> findOverdueTasks(LocalDateTime now);
    
    List<Task> findByUserIdAndCompleted(Long userId, boolean completed);
} 
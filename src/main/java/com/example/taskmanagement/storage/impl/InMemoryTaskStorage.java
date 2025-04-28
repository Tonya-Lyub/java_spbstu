package com.example.taskmanagement.storage.impl;

import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.storage.TaskStorage;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Component
public class InMemoryTaskStorage implements TaskStorage {
    private final ConcurrentHashMap<Long, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Task save(Task task) {
        if (task.getId() == null) {
            task.setId(idGenerator.getAndIncrement());
        }
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(tasks.get(id));
    }

    @Override
    public List<Task> findAll() {
        return List.copyOf(tasks.values());
    }

    @Override
    public void deleteById(Long id) {
        tasks.remove(id);
    }

    @Override
    public List<Task> findByUserId(Long userId) {
        return tasks.values().stream()
                .filter(task -> task.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
} 
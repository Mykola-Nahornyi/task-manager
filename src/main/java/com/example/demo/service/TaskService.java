package com.example.demo.service;

import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    // Получить все задачи пользователя
    public List<Task> getTasksByUsername(String username) {
        log.info(">>> Поиск задач для пользователя: {}", username);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден: " + username));
        return taskRepository.findAllByAssignee(user);
    }

    // Получить задачу по ID
    public Task getTask(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Задача не найдена: " + id));
    }

    // Сохранить новую задачу
    @Transactional
    public Task saveTask(Task task, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден: " + username));
        task.setAssignee(user);
        if (task.getCreatedAt() == null) {
            task.setCreatedAt(OffsetDateTime.now(ZoneOffset.UTC)); // используем OffsetDateTime
        }
        return taskRepository.save(task);
    }

    // Удалить задачу
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    // Получить все задачи (для списка без пагинации)
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}

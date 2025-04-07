package com.app.todo.services;

import com.app.todo.models.Task;
import com.app.todo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public List<Task> getAllTasks(){
        List<Task> tasks = taskRepository.findAllByOrderByIdAsc();
        return tasks;
    }

    public Task createTask(String title){
        Task task = new Task();
        task.setTitle(title);
        task = taskRepository.save(task);
        return task;
    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

    public void toggleTask(Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("invalid id"));
        task.setCompleted(!task.isCompleted());
        taskRepository.save(task);
    }
}

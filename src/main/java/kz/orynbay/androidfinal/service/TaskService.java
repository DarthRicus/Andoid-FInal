package kz.orynbay.androidfinal.service;

import kz.orynbay.androidfinal.entity.Task;
import kz.orynbay.androidfinal.entity.User;
import kz.orynbay.androidfinal.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private TaskRepository taskRepository;
    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void createTask(Task task) {
        taskRepository.save(task);
    }

    public void updateTask(Long id, Task updatedTask) {
        Task task = taskRepository.getOne(id);
        task.setName(updatedTask.getName());
        task.setDescription(updatedTask.getDescription());
        task.setDate(updatedTask.getDate());
        taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public List<Task> findByOwnerOrderByDateDesc(User user) {
        return taskRepository.findByOwnerOrderByDateDesc(user);
    }

    public void setTaskCompleted(Long id) {
        Task task = taskRepository.getOne(id);
        task.setIscompleted(true);
        taskRepository.save(task);
    }

    public void setTaskNotCompleted(Long id) {
        Task task = taskRepository.getOne(id);
        task.setIscompleted(false);
        taskRepository.save(task);
    }

    public List<Task> findFreeTasks() {
        return taskRepository.findAll()
                .stream()
                .filter(task -> task.getOwner() == null && !task.isIscompleted())
                .collect(Collectors.toList());

    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public void assignTaskToUser(Task task, User user) {
        task.setOwner(user);
        taskRepository.save(task);
    }

    public void unassignTask(Task task) {
        task.setOwner(null);
        taskRepository.save(task);
    }

}

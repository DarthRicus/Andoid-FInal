package kz.orynbay.androidfinal.controller;

import kz.orynbay.androidfinal.entity.Task;
import kz.orynbay.androidfinal.entity.User;
import kz.orynbay.androidfinal.service.TaskService;
import kz.orynbay.androidfinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class TaskController {

    private TaskService taskService;
    private UserService userService;

    @Autowired
    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }
    @GetMapping("/task")
    public Task getTaskById(@RequestParam long id){
        try {
            return taskService.getTaskById(id);
        } catch (Exception e) {
            return null;
        }
    }
    @GetMapping("/tasks")
    public List<Task> getAllTasks(){
        try {
            return taskService.findAll();
        } catch (Exception e) {
            return null;
        }
    }
    @GetMapping("/task/assign")
    public boolean getTaskById(@RequestParam long user_id, @RequestParam long task_id){
        try {
            User user = userService.getUserById(user_id);
            Task task = taskService.getTaskById(task_id);
            taskService.assignTaskToUser(task, user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @PostMapping("task")
    public ResponseEntity taskPost(@RequestBody Task task){
        try {
            taskService.createTask(task);
            return ResponseEntity.ok("OK!");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }
}

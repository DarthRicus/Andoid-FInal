package kz.orynbay.androidfinal.controller;


import kz.orynbay.androidfinal.entity.Task;
import kz.orynbay.androidfinal.entity.User;
import kz.orynbay.androidfinal.exception.UserAlreadyExistException;
import kz.orynbay.androidfinal.service.TaskService;
import kz.orynbay.androidfinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping("/register")
    public ResponseEntity testPost(@RequestBody User user){
        try {
            userService.registration(user);
            return ResponseEntity.ok("OK!");
        }catch (UserAlreadyExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("/user")
    public ResponseEntity getUserById(@RequestParam Long id){
        try {
           return ResponseEntity.ok(userService.getUserById(id));
        } catch (Exception e) {
           return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

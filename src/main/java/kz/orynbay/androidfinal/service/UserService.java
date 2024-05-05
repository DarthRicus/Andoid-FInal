package kz.orynbay.androidfinal.service;

import kz.orynbay.androidfinal.entity.User;
import kz.orynbay.androidfinal.exception.UserAlreadyExistException;
import kz.orynbay.androidfinal.repository.TaskRepository;
import kz.orynbay.androidfinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private TaskRepository taskRepository;
    @Autowired
    public UserService(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }
    public User registration(User userToSave) throws UserAlreadyExistException {
        if(userRepository.findByName(userToSave.getName()) != null){
            throw new UserAlreadyExistException("User already exists!");
        }
        return userRepository.save(userToSave);
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean isUserEmailPresent(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUser(Long id) {
        User user = userRepository.getOne(id);
        user.getTasksOwned().forEach(task -> task.setOwner(null));
        userRepository.delete(user);
    }
}

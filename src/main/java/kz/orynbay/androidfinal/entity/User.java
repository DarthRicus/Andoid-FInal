package kz.orynbay.androidfinal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Email(message = "{user.email.not.valid}")
    @NotEmpty(message = "{user.email.not.empty}")
    @Column(name = "email",unique = true)
    private String email;
    @NotEmpty(message = "{user.name.not.empty}")
    @Column(name = "name")
    private String name;
    @NotEmpty(message = "{user.password.not.empty}")
    @Size(min = 5, message = "{user.password.length}")
    @Column(name = "password")
    private String password;
    @Column(name = "photo",columnDefinition = "VARCHAR(255) DEFAULT 'images/user.png'")
    private String photo;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Task> tasksOwned;

    @JsonIgnore
    public List<Task> getTasksCompleted() {
        return tasksOwned.stream()
                .filter(Task::isIscompleted)
                .collect(Collectors.toList());
    }
    @JsonIgnore
    public List<Task> getTasksInProgress() {
        return tasksOwned.stream()
                .filter(task -> !task.isIscompleted())
                .collect(Collectors.toList());
    }


    public User() {
    }

    public User(@Email @NotEmpty String email,
                @NotEmpty String name,
                @NotEmpty @Size(min = 5) String password,
                String photo) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.photo = photo;
    }

    public User(@Email @NotEmpty String email,
                @NotEmpty String name,
                @NotEmpty @Size(min = 5) String password,
                String photo,
                List<Task> tasksOwned){
        this.email = email;
        this.name = name;
        this.password = password;
        this.photo = photo;
        this.tasksOwned = tasksOwned;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<Task> getTasksOwned() {
        return tasksOwned;
    }

    public void setTasksOwned(List<Task> tasksOwned) {
        this.tasksOwned = tasksOwned;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                email.equals(user.email) &&
                name.equals(user.name) &&
                password.equals(user.password) &&
                Objects.equals(photo, user.photo) &&
                Objects.equals(tasksOwned, user.tasksOwned);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, name, password, photo, tasksOwned);
    }
}


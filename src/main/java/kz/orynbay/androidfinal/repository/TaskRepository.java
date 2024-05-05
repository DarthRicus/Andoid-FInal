package kz.orynbay.androidfinal.repository;

import kz.orynbay.androidfinal.entity.Task;
import kz.orynbay.androidfinal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByOwnerOrderByDateDesc(User user);
}
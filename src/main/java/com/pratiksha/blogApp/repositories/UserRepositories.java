package com.pratiksha.blogApp.repositories;

import com.pratiksha.blogApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositories  extends JpaRepository<User, Integer> {
}

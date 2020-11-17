package com.lambdaschool.foundation.repository;

import com.lambdaschool.foundation.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * The CRUD repository connecting User to the rest of the application
 */
public interface UserRepository
    extends CrudRepository<User, Long> {
}

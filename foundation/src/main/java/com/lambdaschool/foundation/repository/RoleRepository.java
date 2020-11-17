package com.lambdaschool.foundation.repository;

import com.lambdaschool.foundation.models.Role;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * The CRUD Repository connecting Role to the rest of the application
 */
public interface RoleRepository
    extends CrudRepository<Role, Long>
{
}

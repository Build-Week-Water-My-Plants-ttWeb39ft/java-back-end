package com.lambdaschool.foundation.repository;

import com.lambdaschool.foundation.models.Plants;
import org.springframework.data.repository.CrudRepository;

/**
 * The CRUD Repository connecting Plants to the rest of the application
 */
public interface PlantsRepository
    extends CrudRepository<Plants, Long>
{
}

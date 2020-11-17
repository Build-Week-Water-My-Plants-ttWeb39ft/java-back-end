package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.models.Plants;
import org.h2.table.Plan;

import java.util.List;


/**
 * The Service that works with the Plants Model
 */
public interface PlantsService
{
    /**
     * Returns a list of all users and their plants
     */
    List<Plants> findAll();

    /**
     * Returns The plant you seek by id
     */
    Plants findPlantsById(long id);

    /**
     * Remove plant based off id
     */
    void delete(long id);

    /**
     * Replaces the plant based off id
     * @return The plant object that you updated
     */
    Plants update(
        Plants plants, long id);

    /**
     * Add a new plant
     */
    Plants save(Plants plants);
}

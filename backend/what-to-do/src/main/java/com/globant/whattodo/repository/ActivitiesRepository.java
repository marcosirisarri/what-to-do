package com.globant.whattodo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globant.whattodo.entities.Activity;

@Repository
public interface ActivitiesRepository extends JpaRepository<Activity, Long> {

	List<Activity> findAll();

	List<Activity> findByCreatorEmail(String userEmail);
}
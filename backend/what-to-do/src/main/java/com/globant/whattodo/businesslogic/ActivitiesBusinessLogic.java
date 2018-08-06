package com.globant.whattodo.businesslogic;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.whattodo.entities.Activity;
import com.globant.whattodo.repository.ActivitiesRepository;

@Service
public class ActivitiesBusinessLogic {

	private final ActivitiesRepository activitiesRepository;

	@Autowired
	public ActivitiesBusinessLogic(ActivitiesRepository activitiesRepo) {
		this.activitiesRepository = activitiesRepo;
	}

	public Collection<Activity> getAllActivities() {
		return activitiesRepository.findAll();
	}
}

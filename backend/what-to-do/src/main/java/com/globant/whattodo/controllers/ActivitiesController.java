package com.globant.whattodo.controllers;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.globant.whattodo.businesslogic.ActivitiesBusinessLogic;
import com.globant.whattodo.entities.Activity;

@RestController
@RequestMapping("/activities")
public class ActivitiesController {

	private final ActivitiesBusinessLogic activitiesBusinessLogic;

	@Autowired
	public ActivitiesController(ActivitiesBusinessLogic activitiesBL) {
		this.activitiesBusinessLogic = activitiesBL;
	}

	@GetMapping
	public Collection<Activity> getAllActivities() {
		return this.activitiesBusinessLogic.getAll();
	}

	@GetMapping("/{id}")
	public Activity getActivityByEmail(@PathVariable Long id) {
		return this.activitiesBusinessLogic.getById(id);
	}

	@PostMapping
	public ResponseEntity<?> addActivity(@RequestBody Activity newActivity) {
		Activity result = this.activitiesBusinessLogic.addActivity(newActivity);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateActivity(@PathVariable Long id, @RequestBody Activity updatedActivity) {
		this.activitiesBusinessLogic.updateActivity(id, updatedActivity);
		return ResponseEntity.noContent().build();
	}
}

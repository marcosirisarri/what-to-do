package com.globant.whattodo.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globant.whattodo.businesslogic.ParticipantsBusinessLogic;
import com.globant.whattodo.entities.User;

@RestController
@RequestMapping("/activities/{activityId}/participants")
public class ParticipantsController {

	private final ParticipantsBusinessLogic participantsBusinessLogic;

	@Autowired
	public ParticipantsController(ParticipantsBusinessLogic participantsBL) {
		this.participantsBusinessLogic = participantsBL;
	}

	@GetMapping
	public Collection<User> getAllParticipantsFromActivity(@PathVariable Long activityId) {
		return this.participantsBusinessLogic.getActivityParticipants(activityId);
	}

	@PostMapping("/{userEmail}")
	public ResponseEntity<?> addParticipantToActivity(@PathVariable Long activityId, @PathVariable String userEmail) {
		this.participantsBusinessLogic.addParticipantToActivity(activityId, userEmail);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{userEmail}")
	public ResponseEntity<?> removeParticipantFromActivity(@PathVariable Long activityId,
			@PathVariable String userEmail) {
		this.participantsBusinessLogic.removeParticipantFromActivity(activityId, userEmail);
		return ResponseEntity.noContent().build();
	}
}

package com.globant.whattodo.businesslogic;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.globant.whattodo.businesslogic.exceptions.ActivityNotFoundException;
import com.globant.whattodo.businesslogic.exceptions.ParticipantAlreadyRegisteredException;
import com.globant.whattodo.businesslogic.exceptions.RequiredFieldMissingException;
import com.globant.whattodo.businesslogic.exceptions.UserNotFoundException;
import com.globant.whattodo.entities.Activity;
import com.globant.whattodo.entities.User;
import com.globant.whattodo.repository.ActivitiesRepository;
import com.globant.whattodo.repository.UsersRepository;

@Service
public class ParticipantsBusinessLogic {

	private final ActivitiesRepository activitiesRepository;
	private final UsersRepository usersRepository;

	@Autowired
	public ParticipantsBusinessLogic(ActivitiesRepository activitiesRepo, UsersRepository usersRepo) {
		this.activitiesRepository = activitiesRepo;
		this.usersRepository = usersRepo;
	}

	public Collection<User> getActivityParticipants(Long activityId) {
		validateActivity(activityId);
		Activity activity = activitiesRepository.findById(activityId).get();
		return activity.getParticipants();
	}

	public void addParticipantToActivity(Long activityId, String userEmail) {
		validateActivity(activityId);
		validateUser(userEmail);
		Activity activity = activitiesRepository.findById(activityId).get();
		User participant = usersRepository.findByEmail(userEmail).get();
		try {
			activity.addParticipant(participant);
		} catch (IllegalArgumentException e) {
			throw new ParticipantAlreadyRegisteredException(userEmail, activityId);
		}
		activitiesRepository.save(activity);
	}

	public void removeParticipantFromActivity(Long activityId, String userEmail) {
		validateActivity(activityId);
		validateUser(userEmail);
		Activity activity = activitiesRepository.findById(activityId).get();
		User participant = usersRepository.findByEmail(userEmail).get();
		activity.removeParticipant(participant);
		activitiesRepository.save(activity);
	}

	private void validateActivity(Long activityId) {
		if (!this.activitiesRepository.findById(activityId).isPresent()) {
			throw new ActivityNotFoundException(activityId);
		}
	}

	private void validateUser(String email) {
		if (StringUtils.isEmpty(email)) {
			throw new RequiredFieldMissingException("email");
		}
		if (!this.usersRepository.findByEmail(email).isPresent()) {
			throw new UserNotFoundException(email);
		}
	}
}

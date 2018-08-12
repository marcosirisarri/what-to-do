package com.globant.whattodo.businesslogic;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.globant.whattodo.businesslogic.exceptions.ActivityNotFoundException;
import com.globant.whattodo.businesslogic.exceptions.InvalidDateException;
import com.globant.whattodo.businesslogic.exceptions.RequiredFieldMissingException;
import com.globant.whattodo.businesslogic.exceptions.UserNotFoundException;
import com.globant.whattodo.entities.Activity;
import com.globant.whattodo.entities.Location;
import com.globant.whattodo.entities.User;
import com.globant.whattodo.repository.ActivitiesRepository;
import com.globant.whattodo.repository.LocationsRepository;
import com.globant.whattodo.repository.UsersRepository;

@Service
public class ActivitiesBusinessLogic {

	private final ActivitiesRepository activitiesRepository;
	private final LocationsRepository locationsRepository;
	private final UsersRepository usersRepository;

	@Autowired
	public ActivitiesBusinessLogic(ActivitiesRepository activitiesRepo, LocationsRepository locationsRepo,
			UsersRepository usersRepo) {
		this.activitiesRepository = activitiesRepo;
		this.locationsRepository = locationsRepo;
		this.usersRepository = usersRepo;
	}

	public Collection<Activity> getAll() {
		return activitiesRepository.findAll();
	}

	public Activity getById(Long id) {
		return this.activitiesRepository.findById(id).orElseThrow(() -> new ActivityNotFoundException(id));
	}

	public Activity addActivity(Activity newActivity) {
		validateActivity(newActivity);

		// Attach existing creator entity to newActivity
		User creator = this.usersRepository.findByEmail(newActivity.getCreator().getEmail()).get();
		newActivity.setCreator(creator);

		// Save location in db
		Location activityLocation = newActivity.getLocation();
		locationsRepository.save(activityLocation);

		return this.activitiesRepository.save(newActivity);
	}

	public Activity updateActivity(Long id, Activity updatedActivity) {
		Activity originalActivity = this.getById(id);
		String updatedName = updatedActivity.getName();
		String updatedDescription = updatedActivity.getDescription();
		Date updatedDateAndTime = updatedActivity.getDateAndTime();
		Integer updatedMaximumParticipants = updatedActivity.getMaximumParticipants();
		String updatedBase64Image = updatedActivity.getBase64Image();
		Location updatedLocation = updatedActivity.getLocation();
		if (!StringUtils.isEmpty(updatedName)) {
			originalActivity.setName(updatedName);
		}
		if (!StringUtils.isEmpty(updatedDescription)) {
			originalActivity.setDescription(updatedDescription);
		}
		if (updatedDateAndTime != null) {
			validateDateAndTime(updatedDateAndTime);
			originalActivity.setDateAndTime(updatedDateAndTime);
		}
		if (updatedMaximumParticipants != null) {
			originalActivity.setMaximumParticipants(updatedMaximumParticipants);
		}
		if (!StringUtils.isEmpty(updatedBase64Image)) {
			originalActivity.setBase64Image(updatedBase64Image);
		}
		if (updatedLocation != null) {
			validateLocation(updatedLocation);
			originalActivity.setLocation(updatedLocation);
		}
		return this.activitiesRepository.save(updatedActivity);
	}

	public void deleteActivity(Long id) {
		if (!activitiesRepository.existsById(id)) {
			throw new ActivityNotFoundException(id);
		}
		activitiesRepository.deleteById(id);
	}

	private void validateActivity(Activity activity) {
		String name = activity.getName();
		String description = activity.getDescription();
		Date dateAndTime = activity.getDateAndTime();
		Location location = activity.getLocation();
		User creator = activity.getCreator();

		if (StringUtils.isEmpty(name)) {
			throw new RequiredFieldMissingException("name");
		}
		if (StringUtils.isEmpty(description)) {
			throw new RequiredFieldMissingException("description");
		}
		validateDateAndTime(dateAndTime);
		validateLocation(location);
		validateCreator(creator);
	}

	private void validateDateAndTime(Date dateAndTime) {
		Date current = new Date();

		if (dateAndTime == null) {
			throw new RequiredFieldMissingException("dateAndTime");
		}
		if (dateAndTime.before(current)) {
			throw new InvalidDateException("Must be present or future");
		}
	}

	private void validateLocation(Location location) {
		if (location == null) {
			throw new RequiredFieldMissingException("location");
		}
		if (location.getLatitude() == null) {
			throw new RequiredFieldMissingException("location latitude");
		}
		if (location.getLongitude() == null) {
			throw new RequiredFieldMissingException("location longitude");
		}
	}

	private void validateCreator(User creator) {
		if (creator == null) {
			throw new RequiredFieldMissingException("creator");
		}
		if (!this.usersRepository.findByEmail(creator.getEmail()).isPresent()) {
			throw new UserNotFoundException(creator.getEmail());
		}
	}
}

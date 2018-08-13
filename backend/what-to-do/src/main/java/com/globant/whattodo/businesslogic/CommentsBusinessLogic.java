package com.globant.whattodo.businesslogic;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.whattodo.entities.Activity;
import com.globant.whattodo.entities.Comment;
import com.globant.whattodo.repository.CommentsRepository;

@Service
public class CommentsBusinessLogic {

	private final CommentsRepository commentsRepository;
	private final ActivitiesBusinessLogic activitiesBusinessLogic;

	@Autowired
	public CommentsBusinessLogic(CommentsRepository commentsRepo, ActivitiesBusinessLogic activitiesBL) {
		this.commentsRepository = commentsRepo;
		this.activitiesBusinessLogic = activitiesBL;
	}

	public Collection<Comment> getActivityComments(Long activityId) {
		// Check if activity exists
		activitiesBusinessLogic.getById(activityId);
		return commentsRepository.findAllByActivityId(activityId);
	}

	public Comment addCommentToActivity(Long activityId, Comment newComment) {
		Activity activity = activitiesBusinessLogic.getById(activityId);
		newComment.setActivity(activity);
		return commentsRepository.save(newComment);
	}
}

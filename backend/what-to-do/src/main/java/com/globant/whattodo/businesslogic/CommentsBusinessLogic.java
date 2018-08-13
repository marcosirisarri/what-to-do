package com.globant.whattodo.businesslogic;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.globant.whattodo.businesslogic.exceptions.ActivityNotFoundException;
import com.globant.whattodo.businesslogic.exceptions.RequiredFieldMissingException;
import com.globant.whattodo.businesslogic.exceptions.UserNotFoundException;
import com.globant.whattodo.entities.Activity;
import com.globant.whattodo.entities.Comment;
import com.globant.whattodo.entities.User;
import com.globant.whattodo.repository.ActivitiesRepository;
import com.globant.whattodo.repository.CommentsRepository;
import com.globant.whattodo.repository.UsersRepository;

@Service
public class CommentsBusinessLogic {

	private final ActivitiesRepository activitiesRepository;
	private final CommentsRepository commentsRepository;
	private final UsersRepository usersRepository;

	@Autowired
	public CommentsBusinessLogic(ActivitiesRepository activitiesRepo, CommentsRepository commentsRepo,
			UsersRepository usersRepo) {
		this.activitiesRepository = activitiesRepo;
		this.commentsRepository = commentsRepo;
		this.usersRepository = usersRepo;
	}

	public Collection<Comment> getActivityComments(Long activityId) {
		validateActivity(activityId);
		return commentsRepository.findAllByActivityId(activityId);
	}

	public Comment addCommentToActivity(Long activityId, Comment newComment) {
		validateActivity(activityId);
		validateComment(newComment);
		Activity activity = activitiesRepository.findById(activityId).get();
		User author = usersRepository.findByEmail(newComment.getAuthor().getEmail()).get();
		newComment.setActivity(activity);
		newComment.setAuthor(author);
		return commentsRepository.save(newComment);
	}

	private void validateComment(Comment comment) {
		String content = comment.getContent();
		User author = comment.getAuthor();
		if (StringUtils.isEmpty(content)) {
			throw new RequiredFieldMissingException("content");
		}
		if (author == null) {
			throw new RequiredFieldMissingException("author");
		}
		if (!this.usersRepository.findByEmail(author.getEmail()).isPresent()) {
			throw new UserNotFoundException(author.getEmail());
		}
	}

	private void validateActivity(Long activityId) {
		if (!this.activitiesRepository.findById(activityId).isPresent()) {
			throw new ActivityNotFoundException(activityId);
		}
	}
}

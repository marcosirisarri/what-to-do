package com.globant.whattodo.controllers;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.globant.whattodo.businesslogic.CommentsBusinessLogic;
import com.globant.whattodo.entities.Comment;

@RestController
@RequestMapping("/activities/{activityId}/comments")
public class CommentsController {

	private final CommentsBusinessLogic commentsBusinessLogic;

	@Autowired
	public CommentsController(CommentsBusinessLogic commentsBL) {
		this.commentsBusinessLogic = commentsBL;
	}

	@GetMapping
	public Collection<Comment> getAllCommentsFromActivity(@PathVariable Long activityId) {
		return this.commentsBusinessLogic.getActivityComments(activityId);
	}

	@PostMapping
	public ResponseEntity<?> addCommentToActivity(@PathVariable Long activityId, @RequestBody Comment newComment) {
		Comment result = this.commentsBusinessLogic.addCommentToActivity(activityId, newComment);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
}

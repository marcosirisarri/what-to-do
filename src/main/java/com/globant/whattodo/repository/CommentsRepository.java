package com.globant.whattodo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globant.whattodo.entities.Comment;

@Repository
public interface CommentsRepository extends JpaRepository<Comment, Long> {
	List<Comment> findAllByActivityId(Long activityId);
}
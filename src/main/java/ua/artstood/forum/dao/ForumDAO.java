package ua.artstood.forum.dao;

import org.springframework.stereotype.Component;
import ua.artstood.forum.entities.Comment;
import ua.artstood.forum.entities.Discussion;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public interface ForumDAO {
    Discussion EMPTY_DISCUSSION = new Discussion(0,"error", "Missing Discussion"," ", new Date(0));

    List<Discussion> getAllDiscussions();

    Discussion getDiscussionById(int id);

    void save(Discussion discussion);

    void update(int id, Discussion updated);

    void delete(int id);

    List<Comment> getAllCommentsByDiscussionId(int id);

    void save(int dis_id, Comment comment);

}

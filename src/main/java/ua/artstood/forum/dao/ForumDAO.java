package ua.artstood.forum.dao;

import org.springframework.stereotype.Component;
import ua.artstood.forum.entities.Comment;
import ua.artstood.forum.entities.Discussion;

import java.util.List;

@Component
public interface ForumDAO {
    List<Discussion> getAllDiscussions();

    Discussion getDiscussionById(int id);

    void save(Discussion discussion);

    void update(int id, Discussion updated);

    void delete(int id);

    List<Comment> getAllCommentsByDiscussionId(int id);

    void save(int dis_id, Comment comment);

}

package ua.artstood.forum.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ua.artstood.forum.entities.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Scope("singleton")
public class CommentsDAO {
    private final List<Comment> database;
    private static int DB_SIZE = 0;


    public CommentsDAO() {
        database = new ArrayList<>();
        database.add(new Comment(DB_SIZE++, 0, "forumMaster99", "Darude - Sandstorm попробуй, определенно она"));
        database.add(new Comment(DB_SIZE++, 0, "RolledRick", "вот, я нашел на ютубе \" https://youtu.be/dQw4w9WgXcQ \" "));
        database.add(new Comment(DB_SIZE++, 1, "AnDrOiD_EnJoYeR", "та не бойся, я свой телефон всегда так заряжаю"));
    }

    public List<Comment> getDiscussionsComments(int disId) {
        return database.stream().filter((comment) -> comment.getDiscussionId() == disId).collect(Collectors.toList());
    }
}

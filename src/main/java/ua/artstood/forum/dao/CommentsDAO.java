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
    private final List<Comment> commentsTable;


    public CommentsDAO() {
        this.commentsTable = new ArrayList<>();
        commentsTable.add(new Comment(0, 0,"forumMaster99", "Darude - Sandstorm попробуй, определенно она"));
        commentsTable.add(new Comment(commentsTable.size(), 0,"RolledRick", "вот, я нашел на ютубе \" https://youtu.be/dQw4w9WgXcQ \" "));
        commentsTable.add(new Comment(commentsTable.size(), 1, "AnDrOiD_EnJoYeR","та не бойся, я свой телефон всегда так заряжаю"));
    }

    public List<Comment> getDiscussionsComments(int dissId){
        return commentsTable.stream().filter((comment)->comment.getDiscussionId()==dissId).collect(Collectors.toList());
    }
}

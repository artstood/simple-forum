package ua.artstood.forum.entities;

import javax.validation.constraints.NotEmpty;

public class Comment {
    private int id;
    private int discussionId;
    @NotEmpty(message = "Имя не должно быть пустым")
    private String username;
    @NotEmpty(message = "Комментарий не может быть пустым")
    private String comment;

    public Comment() {
    }

    public Comment(int id, int discussionId, String username, String comment) {
        this.id = id;
        this.discussionId = discussionId;
        this.username = username;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiscussionId() {
        return discussionId;
    }

    public void setDiscussionId(int discussionId) {
        this.discussionId = discussionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

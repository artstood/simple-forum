package ua.artstood.forum.entities;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class Discussion {
    private int id;
    @Length(min=6, max=18, message = "Имя должно быть не короче 6 букв и не длиннее 18")
    @NotEmpty(message = "Имя не должно быть пустым")
    private String username;
    @NotEmpty(message = "Тема не должна быть пустой")
    private String topic;
    //todo discussion text
    //todo comments list
    //todo date

    public Discussion(int id, String username, String topic) {
        this.id = id;
        this.username = username;
        this.topic = topic;
    }

    public Discussion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}


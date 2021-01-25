package ua.artstood.forum.entities;

public class Discussion {
    private int id;
    private String username;
    private String topic;
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


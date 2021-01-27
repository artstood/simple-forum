package ua.artstood.forum.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ua.artstood.forum.entities.Discussion;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("singleton")
public class DiscussionsDAO {
    private final List<Discussion> database;
    private static int DB_SIZE=0;

    public DiscussionsDAO(){
        database = new ArrayList<>();
        database.add(new Discussion(DB_SIZE++, "Pavel", "Десять лет ищу песню, но не могу найти."));
        database.add(new Discussion(DB_SIZE++, "IPhoneLover1337", "А это правда, что айфон можно заряжать в микроволновке, а то я боюсь проверять"));
        database.add(new Discussion(DB_SIZE++, "IPhoneLover1337", "ПОМОГИТЕ, телефон сгорел в микроволновке!"));
    }

    public List<Discussion> getAllEntries(){
        return database;
    }

    public Discussion getDiscussion(int id){
        return database.stream().filter(discussion -> discussion.getId()==id).findAny().orElse(null);
    }

    public void save(Discussion discussion){
        discussion.setId(DB_SIZE++);
        database.add(discussion);
    }

    public void update(int id, Discussion updated){
        Discussion oldDiscussion = getDiscussion(id);
        oldDiscussion.setTopic(updated.getTopic());
        oldDiscussion.setUsername(updated.getUsername());
    }
    public void delete(int id){
        database.removeIf(discussion -> discussion.getId()==id);
    }
}

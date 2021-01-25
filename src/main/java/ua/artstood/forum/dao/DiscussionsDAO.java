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

    public DiscussionsDAO(){
        database = new ArrayList<>();
        database.add(new Discussion(0, "Pavel", "Десять лет ищу песню, но не могу найти."));
        database.add(new Discussion(database.size(), "IPhoneLover1337", "А это правда, что айфон можно заряжать в микроволновке, а то я боюсь проверять"));
        database.add(new Discussion(database.size(), "IPhoneLover1337", "ПОМОГИТЕ, телефон сгорел в микроволновке!"));
    }

    public List<Discussion> getAllEntries(){
        return database;
    }

    public Discussion getDiscussion(int id){
        return database.get(id);
    }

    public void save(Discussion discussion){
        discussion.setId(database.size());
        database.add(database.size(), discussion);
    }
}

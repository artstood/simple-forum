import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.artstood.forum.dao.ForumDAO;
import ua.artstood.forum.dao.implementation.ForumJDBC;
import ua.artstood.forum.entities.Discussion;

import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class TestDao {
    private Connection connection;
    {
        try {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        ResourceBundle bundle = ResourceBundle.getBundle("db-prop");

        connection = DriverManager.getConnection(
                bundle.getString("url"),
                bundle.getString("user"),
                bundle.getString("password"));
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
        e.printStackTrace();
        }
    }

    @Test
    public void test1(){
        Discussion discussion = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM discussion WHERE id = 1234");
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                discussion = (extractDiscussionFromResultSet(rs));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Optional<Discussion> result = Optional.ofNullable(discussion);

        //System.out.println(result.orElse(new Discussion(1,"name", "error","error",null)));
        System.out.println(result.orElse(new Discussion(1,"name", "error","error",null)).getText());
    }

    public Discussion extractDiscussionFromResultSet(ResultSet resultSet) {
        Discussion discussion = new Discussion();
        try {
            discussion.setId(resultSet.getInt("id"));
            discussion.setUsername(resultSet.getString("username"));
            discussion.setTopic(resultSet.getString("topic"));
            discussion.setText(resultSet.getString("text"));
            discussion.setDate(resultSet.getTimestamp("disc_date"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discussion;
    }

}

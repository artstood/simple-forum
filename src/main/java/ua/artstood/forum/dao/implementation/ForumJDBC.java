package ua.artstood.forum.dao.implementation;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ua.artstood.forum.dao.ForumDAO;
import ua.artstood.forum.entities.Comment;
import ua.artstood.forum.entities.Discussion;

import java.sql.*;
import java.sql.Date;
import java.util.*;

@Component
@Scope("singleton")
public class ForumJDBC implements ForumDAO {
    private static int DIS_ENTRIES_COUNT;
    private static int COMMENT_ENTRIES_COUNT;
    private Connection connection;

    public ForumJDBC() {
        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            ResourceBundle bundle = ResourceBundle.getBundle("db-prop");

            connection = DriverManager.getConnection(
                    bundle.getString("url"),
                    bundle.getString("user"),
                    bundle.getString("password"));
            DIS_ENTRIES_COUNT = tableLastIndex("discussion");
            COMMENT_ENTRIES_COUNT = tableLastIndex("comment");
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public List<Discussion> getAllDiscussions() {
        List<Discussion> discussions = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM discussion");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                discussions.add(extractDiscussionFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discussions;
    }

    public Discussion getDiscussionById(int id) {
        Discussion discussion = new Discussion();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM discussion WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            discussion = extractDiscussionFromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discussion;
    }

    public void save(Discussion discussion) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO discussion(id, username, topic, text)" +
                    " VALUES(?,?,?,?)");
            ps.setInt(1, ++DIS_ENTRIES_COUNT);
            ps.setString(2, discussion.getUsername());
            ps.setString(3, discussion.getTopic());
            ps.setString(4, discussion.getText());
//            ps.setTimestamp(5, new Timestamp(discussion.getDate().getTime()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void update(int id, Discussion updated) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE discussion\n" +
                    "SET username=?, topic=?, text=?\n" +
                    "WHERE id=?");
            ps.setString(1, updated.getUsername());
            ps.setString(2, updated.getTopic());
            ps.setString(3, updated.getText());
            ps.setInt(4, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM discussion" +
                    " WHERE id= ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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

    public int tableLastIndex(String table) {
        int count = 0;
        try {
            @SuppressWarnings("SqlResolve")
            PreparedStatement ps = connection.prepareStatement("SELECT MAX(id) AS count FROM " + table);

            ResultSet rs = ps.executeQuery();
            rs.next();
            count = rs.getInt("count");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Comment> getAllCommentsByDiscussionId(int id) {
        List<Comment> commentList = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM comment WHERE dis_id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                commentList.add(extractCommentFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commentList;
    }

    @Override
    public void save(int dis_id, Comment comment) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO comment values (?,?,?,?)");
            ps.setInt(1, ++COMMENT_ENTRIES_COUNT);
            ps.setInt(2, dis_id);
            ps.setString(3, comment.getUsername());
            ps.setString(4, comment.getComment());
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Comment extractCommentFromResultSet(ResultSet rs) {
        Comment comment = new Comment();
        try {
            comment.setId(rs.getInt("id"));
            comment.setDiscussionId(rs.getInt("dis_id"));
            comment.setUsername(rs.getString("username"));
            comment.setComment(rs.getString("comment"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return comment;
    }

}

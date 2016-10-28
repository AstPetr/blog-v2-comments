package models;

/**
 * Created by Asta on 2016-10-17.
 */
public class Article {
    private int id, userId;
    private String title, body, created_at, updated_at;

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public int getUserId() {

        return userId;
    }

    public void setUserId(int userId) {

        this.userId = userId;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getBody() {

        return body;
    }

    public void setBody(String body) {

        this.body = body;
    }

    public String getCreated_at() {

        return created_at;
    }

    public void setCreated_at(String created_at) {

        this.created_at = created_at;
    }

    public String getUpdated_at() {

        return updated_at;
    }

    public void setUpdated_at(String updated_at) {

        this.updated_at = updated_at;
    }
}

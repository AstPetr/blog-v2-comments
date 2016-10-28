package models;

/**
 * Created by Asta on 2016-10-21.
 */
public class Comment {
    private int id, parentId;
    private String name, body;
//    private String created_at;

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public int getParentId() {

        return parentId;
    }

    public void setParentId(int parentId) {

        this.parentId = parentId;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getBody() {

        return body;
    }

    public void setBody(String body) {

        this.body = body;
    }

}

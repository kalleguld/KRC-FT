package model;

import java.util.Date;

/**
 *
 * @author Randi
 */
public class Post {

    private String text;
    private Date date;
    private User user;
	private final int id;

    public Post(String text, Date date, User user, int id) {
        this.text = text;
        this.date = date;
        this.user = user;
		this.id = id;
    }

	public int getId() {
		return id;
	}
	
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        if (this.user != user) {
            this.user = user;
        }
    }

}

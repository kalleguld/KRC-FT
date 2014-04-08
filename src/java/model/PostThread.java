/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Sigersted
 */
public class PostThread {

    private String topic;
    private Category category;
    private ArrayList<Post> posts = new ArrayList<Post>();
	private final int id;

    public PostThread(String topic, Category category, int id) {
        this.topic = topic;
        this.category = category;
		this.id = id;
        this.posts = posts = new ArrayList<>();
    }

	
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void createPost(String text, Date date, User user, int id) {
        Post post = new Post(text, date, user, id);
        posts.add(post);
        //return post;
    }

    public void deletePost(Post post) {
        if (posts.contains(post)) {
            posts.remove(post);
        }
    }

    public int getPostCount() {
        return posts.size();
    }
}

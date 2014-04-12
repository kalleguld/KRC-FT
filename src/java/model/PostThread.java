/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Sigersted
 */
public class PostThread {

    private String topic;
    private Category category;
    private List<Post> posts = new ArrayList<Post>();
	private final int id;

    PostThread(String topic, Category category, int id) {
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

    public Post createPost(String text, Date date, User user, int id) {
        Post post = new Post(text, date, user, id);
        posts.add(post);
        return post;
    }

    public void deletePost(Post post) {
        if (posts.contains(post)) {
            posts.remove(post);
        }
    }

    public int getPostCount() {
        return posts.size();
    }
	
	public List<Post> getPosts() {
		return new ArrayList<>(posts);
	}
	
	public Post getFirstPost() {
		return posts.get(0);
	}
	
	public Post getLastPost() {
		return posts.get(posts.size()-1);
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;
/**
 *
 * @author Sigersted
 */
public class PostThread {
    
    private String topic;
    private Category category;
    private ArrayList<Post> posts = new ArrayList<Post>();

    public PostThread(String topic, Category category) {
        this.topic = topic;
        this.category = category;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
    
    public void createPost(String text, User user) {
        Post post = new Post(text, user);
        posts.add(post);
    }
    
    public void deletePost(Post post) {
        posts.remove(post);
    }
    
    public int getPostCount() {
        return posts.size();
    }
}

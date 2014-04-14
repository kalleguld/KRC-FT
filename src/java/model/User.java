/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 *
 * @author kalleguld
 */
public class User {

    private boolean admin;
    private String username;
    private String password;

    private final Collection<Post> posts;
    private final Collection<PostThread> threads;
    private final Collection<Category> moderatedCategories;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        posts = new ArrayList<>();
        threads = new ArrayList<>();
        moderatedCategories = new HashSet<>();
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Post> getPosts() {
        return new ArrayList<>(posts);
    }

    public int getPostCount() {
        return posts.size();
    }

    void addPost(Post post) {
        if (!posts.contains(post)) {
            posts.add(post);
        }
    }

    void removePost(Post post) {

        posts.remove(post);
    }

    public Collection<PostThread> getPostThreads() {
        return new ArrayList<>(threads);
    }

    void addPostThread(PostThread pt) {
        if (!threads.contains(pt)) {
            threads.add(pt);
        }
    }

    void removePostThread(PostThread pt) {
        threads.remove(pt);
    }

    public Collection<Category> getModeratedCategories() {
        return new HashSet<>(moderatedCategories);
    }

    void addModeratedCategory(Category c) {
        if (!moderatedCategories.contains(c)) {
            moderatedCategories.add(c);
        }
    }

    void removeModeratedCategory(Category c) {
        moderatedCategories.remove(c);
    }

    public boolean doesPasswordMatch(String pw) {
        if (this.password != null) {
            return this.password.equals(pw);
        }
        return false;
    }
}

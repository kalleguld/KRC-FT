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
	//TODO 
	
	private boolean admin;
	private String username;
	private String password;
	
	Collection<Post> posts;
	Collection<PostThread> threads;
	Collection<Category> moderatedCategories;

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

	public String getPassword() {
		return password;
	}

	public Collection<Post> getPosts() {
		return new ArrayList<>(posts);
	}
	void addPost(Post post) {
		posts.add(post);
	}
	
	void removePost(Post post) {
		posts.remove(post);
	}

	public Collection<PostThread> getPostThreads() {
		return new ArrayList<>(threads);
	}
	
	void addPostThread(PostThread pt) {
		//TODO 
	}
	
	void removePostThread(PostThread pt) {
		//TODO 
	}

	public Collection<Category> getModeratedCategories() {
		return new HashSet<>(moderatedCategories);
	}

	void addModeratedCategory(Category c) {
		//TODO 
	}
	
	void removeModeratedCategory(Category c) {
		//TODO 
	}
	
	public boolean doesPasswordMatch(String pw) {
		//TODO 
		return true;
	}
	
	
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import model.Category;
import model.Post;
import model.PostThread;
import model.User;

/**
 *
 * @author kalleguld
 */
@Named
@ApplicationScoped
public class ApplicationBean {

	private final Map<String, User> users;
	private final Collection<Category> categories;
	private final Collection<PostThread> threads;
	private final Collection<Post> posts;
	
	public ApplicationBean() {
		
		users = new HashMap<>();
		categories = new HashSet<>();
		threads = new HashSet<>();
		posts = new HashSet<>();
	}
	
	public User getUserByName(String name) {
		//TODO 
		return null;
	}
	public Iterator<User> userIterator() {
		//TODO 
		return null;
	}
	public Iterator<Post> postIterator() {
		//TODO 
		return null;
	}
	public Iterator<Category> categoryIterator() {
		//TODO 
		return null;
	}
	public Iterator<PostThread> threadIterator() {
		//TODO 
		return null;
	}
	public User createUser(String name, String password) {
		//TODO 
		return null;
	}
	public PostThread createThread(String topic, Category category, User owner) {
		//TODO 
		return null;
	}
	public Post createPost(PostThread thread, String topic, String text, User owner) {
		//TODO 
		return null;
	}
	public Category createCategory(User owner, String topic) {
		//TODO 
		return null;
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import java.util.ArrayList;
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
		return users.get(name);
	}
	public Iterator<User> userIterator() {
		return new ROIterator<>(users.values().iterator());
	}
	public Iterator<Post> postIterator() {
		return new ROIterator<>(posts.iterator());
	}
	public Iterator<Category> categoryIterator() {
		return new ROIterator<>(categories.iterator());
	}
	public Iterator<PostThread> threadIterator() {
		return new ROIterator<>(threads.iterator());
	}
	public User createUser(String name, String password) {
		User u = new User(name, password);
		users.put(name, u);
		return u;
	}
	public PostThread createThread(String topic, Category category, User owner) {
		//TODO 
		PostThread pt = new PostThread(topic, category, new ArrayList());
		threads.add(pt);
		return pt;
	}
	public Post createPost(PostThread thread, String text, User owner) {
		//TODO 
		return null;
	}
	public Category createCategory(User owner, String topic) {
		//TODO 
		return null;
	}
	
	private class ROIterator<E> implements Iterator<E> {

		Iterator<E> inner;

		public ROIterator(Iterator<E> inner) {
			this.inner = inner;
		}
		
		
		@Override
		public boolean hasNext() {
			return inner.hasNext();
		}

		@Override
		public E next() {
			return inner.next();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
}
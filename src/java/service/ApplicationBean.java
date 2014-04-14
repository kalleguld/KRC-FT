

package service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
	private final List<Category> categories;
	private final List<PostThread> threads;
	private final List<Post> posts;
	private int nextCategoryId = 0;
	private int nextThreadId = 0;
	private int nextPostId = 0;
	
	public ApplicationBean() {
		
		users = new HashMap<>();
		categories = new ArrayList<>();
		threads = new ArrayList<>();
		posts = new ArrayList<>();
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
        
        public void removeUser(User u) {
            users.remove(u.getUsername());
	}
        
	public PostThread createThread(String topic, String text, Category category, User owner) {
		PostThread pt = category.createPostThread(topic, category, nextThreadId);
		nextThreadId++;
		threads.add(pt);
		createPost(pt, text, owner);
		return pt;
	}
	public Post createPost(PostThread thread, String text, User owner) {
		Post p = thread.createPost(text, new Date(), owner, nextPostId);
		nextPostId++;
		posts.add(p);
		return p;
	}
	public Category createCategory(User owner, String topic) {
		Category c = new Category(topic, nextCategoryId);
		nextCategoryId++;
		c.addModerator(owner);
		categories.add(c);
		return c;
	}

	public List<User> getUsers() {
		return new ArrayList<>(users.values());
	}

	public List<Category> getCategories() {
		return Collections.unmodifiableList(categories);
	}

	public List<PostThread> getThreads() {
		return Collections.unmodifiableList(threads);
	}

	public List<Post> getPosts() {
		return Collections.unmodifiableList(posts);
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
			throw new UnsupportedOperationException("Remove cannot be called from a "
					+ "Read-only Iterator");
		}
		
	}
}

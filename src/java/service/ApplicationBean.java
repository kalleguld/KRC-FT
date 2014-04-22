

package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

	/**
	 * All users of the system, indexed by username
	 */
	private final Map<String, User> users;
	/**
	 * All categories, indexed by category ID
	 */
	private final Map<Integer, Category> categories;
	/**
	 * All threads, indexed by thread ID
	 */
	private final Map<Integer, PostThread> threads;
	/**
	 * All posts, indexed by post ID
	 */
	private final Map<Integer, Post> posts;
	private int nextCategoryId = 0;
	private int nextThreadId = 0;
	private int nextPostId = 0;
	
	public ApplicationBean() {
		
		users = new HashMap<>();
		categories = new HashMap<>();
		threads = new HashMap<>();
		posts = new HashMap<>();
	}

	/**
	 * Creates a new user, assuming the username isn't already taken.
	 * @param name the username of the new user
	 * @param password the password of the new user. The password is assumed to 
	 * have been checked for typing errors.
	 * @return The new user, or null if the username was already taken.
	 */
	public User createUser(String name, String password) {
		if (users.containsKey(name)) {
			return null;
		}
		User u = new User(name, password);
		users.put(name, u);
		return u;
		
	}
	
	/**
	 * Creates a new category
	 * @param owner The user who creates the category
	 * @param topic The name of the category
	 * @return The new category, or null if owner isn't allowed to create
	 * categories.
	 */
	public Category createCategory(User owner, String topic) {
		if (!owner.canCreateCategories()) {
			return null;
		}
		Category c = new Category(topic, nextCategoryId);
		categories.put(nextCategoryId, c);
		nextCategoryId++;
		c.addModerator(owner);
		return c;
	}
        
	/**
	 * Creates a new PostThread and one post
	 * @param topic The topic/subject of the thread
	 * @param text All text in the first post
	 * @param category The category that should contain the thread
	 * @param owner The user who started the thread
	 * @return the newly created PostThread, or null if the user is not allowed 
	 * to create a thread in that category
	 */
	public PostThread createThread(String topic, String text, 
							Category category, User owner) {
		if (!owner.canCreateThreads(category)) {
			return null;
		}
		PostThread pt = category.createPostThread(topic, category, nextThreadId, owner);
		threads.put(nextThreadId, pt);
		nextThreadId++;
		createPost(pt, text, owner);
		return pt;
	}
	
	/**
	 * Creates one post as a reply to an existing PostThread
	 * @param thread the thread to reply to
	 * @param text well, the text
	 * @param owner The user who wrote the text
	 * @return the post created, or null if the user is not allowed to
	 * reply to the thread
	 */
	public Post createPost(PostThread thread, String text, User owner) {
		if (!owner.canCreatePosts(thread)) {
			return null;
		}
		Post p = thread.createPost(text, new Date(), owner, nextPostId);
		posts.put(nextPostId, p);
		nextPostId++;
		return p;
	}
	

	public List<User> getUsers() {
		return new ArrayList<>(users.values());
	}

	public List<Category> getCategories() {
		return new ArrayList<>(categories.values());
	}

	public List<PostThread> getThreads() {
		return new ArrayList<>(threads.values());
	}

	public List<Post> getPosts() {
		return new ArrayList<>(posts.values());
	}
	
	/**
	 * Returns the user with the given username, or null if no such user exists.
	 * @param username the username of the user to get.
	 * @return the user with the given username, or null if no such user exists.
	 */
	public User getUserByName(String username) {
		return users.get(username);
	}
	
	/**
	 * Returns the category with the given ID, or null if no such category exists.
	 * @param catId the ID of the category
	 * @return the category with the given ID, or null if no such category exists.
	 */
	public Category getCategoryById(int catId) {
		return categories.get(catId);
	}
	
	/**
	 * Returns the thread with the given ID, or null if no such thread exists.
	 * @param threadId the ID of the thread.
	 * @return the thread with the given ID, or null if no such thread exists.
	 */
	public PostThread getThreadById(int threadId) {
		return threads.get(threadId);
	}
	
	/**
	 * Returns the post with the given ID, or null if no such post exists.
	 * @param postId the ID of the post.
	 * @return the post with the given ID, or null if no such post exists.
	 */
	public Post getPostById(int postId) {
		return posts.get(postId);
	}
       
	public void removeUser(User u) {
		users.remove(u.getUsername());
	}
	
	public void removeCategory(Category cat) {
		categories.remove(cat.getId());
	}
	
	public void removeThread(PostThread pt) {
		threads.remove(pt.getId());
                pt.getCategory().deletePostThread(pt);
	}
	
	public void removePost(Post p) {
		posts.remove(p.getId());
                p.getPostthread().deletePost(p);
	}
}

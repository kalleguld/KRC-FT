package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Randi
 */
public class Category {

    private String topic;
    private List<User> moderators;
    private List<PostThread> postThreads;
	private final int id;

    public Category(String topic, int id) {
        this.topic = topic;
		this.id = id;
        this.moderators = new ArrayList<>();
        this.postThreads = new ArrayList<>();
    }
	
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<User> getModerators() {
        return new ArrayList<>(moderators);
    }
	public List<PostThread> getThreads() {
		return new ArrayList<>(postThreads);
	}

	public int getNumThreads() {
		return postThreads.size();
	}
    public void setModerators(List<User> moderators) {
        this.moderators = moderators;
    }

	
    public void addModerator(User moderator) {
        if (!moderators.contains(moderator)) {
            moderators.add(moderator);
            moderator.addModeratedCategory(this);
        }
    }

    public void removeModerator(User moderator) {
        if (moderators.contains(moderator)) {
            moderators.remove(moderator);
            moderator.addModeratedCategory(this);
        }
    }

	public int getId() {
		return id;
	}

    public PostThread createPostThread(String topic, Category category, int id) {
        PostThread postThread = new PostThread(topic, category, id);
        postThreads.add(postThread);
        return postThread;
    }

    public void addPostThread(PostThread postThread) {
        if (!postThreads.contains(postThread)) {
            postThreads.add(postThread);
        }
    }

    public void deletePostThread(PostThread postThread) {
        if (postThreads.contains(postThread)) {
            postThreads.remove(postThread);
        }
    }

}

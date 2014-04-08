package model;

import java.util.ArrayList;

/**
 *
 * @author Randi
 */
public class Category {

    private String topic;
    private ArrayList<User> moderators;
    private ArrayList<PostThread> postThreads;

    public Category() {
    }

    public Category(String topic, ArrayList<User> moderators, ArrayList<PostThread> postThreads) {
        this.topic = topic;
        this.moderators = moderators;
        this.postThreads = postThreads;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public ArrayList<User> getModerators() {
        return new ArrayList<User>(moderators);
    }

    public void setModerators(ArrayList<User> moderators) {
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

    public PostThread createPostThread(String topic, Category category, ArrayList<Post> posts) {
        PostThread postThread = new PostThread(topic, category, posts);
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

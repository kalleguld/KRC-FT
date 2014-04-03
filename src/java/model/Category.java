package model;

import java.util.ArrayList;

/**
 *
 * @author Randi
 */
public class Category {
    
    private String topic;
    private ArrayList<User> moderators;
    private ArrayList<PostThread> postThread;

    public Category() {
    }

    public Category(String topic, ArrayList<User> moderators) {
        this.topic = topic;
        this.moderators = moderators;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public ArrayList<User> getModerators() {
        return new ArrayList <User> (moderators);
    }

    public void setModerators(ArrayList<User> moderators) {
        this.moderators = moderators;
    }
    
    public void addUser (User moderator) {
        if(!moderators.contains(moderator)) {
            moderators.add(moderator);
            moderator.addCategory(this);
        }
    }
    
    public void removeUser (User moderator){
        if(moderators.contains(moderator)) {
            moderators.remove(moderator);
            moderator.removeCategory(this);
        }
    }
    
    public void addPostThread() {
        if(!postThread.contains(postThread)) {
            postThread.add(postThread);
            postThread.add(postThread);
        }
    }
    
    public void createPostThread(PostThread postThreads) {
        PostThread postThread = new PostThread(postThreads);
        postThreads.add(postThread);
        return postThread;
    }
    
    public void deletePostThread() {
        if(postThread.contains(postThread)) {
            postThread.remove(postThread);
        }
    }
    
}

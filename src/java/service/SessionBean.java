/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import model.PostThread;
import model.User;

/**
 *
 * @author Sigersted
 */
@Named
@SessionScoped
public class SessionBean implements Serializable {
    private User currentUser;
    private String threadTopic;
    private String postText;
    private Category currentCategory;
    private PostThread currentThread;
    private Post currentPost;
    private String loginName;
    private String loginPassword;
    private ApplicationBean application;
    private User selectedUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public String getThreadTopic() {
        return threadTopic;
    }

    public void setThreadTopic(String threadTopic) {
        this.threadTopic = threadTopic;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public Category getCurrentCategory() {
        return currentCategory;
    }

    public void setCurrentCategory(Category currentCategory) {
        this.currentCategory = currentCategory;
    }

    public PostThread getCurrentThread() {
        return currentThread;
    }

    public void setCurrentThread(PostThread currentThread) {
        this.currentThread = currentThread;
    }

    public Post getCurrentPost() {
        return currentPost;
    }

    public void setCurrentPost(Post currentPost) {
        this.currentPost = currentPost;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
    
    public void createUser() {
        User newUser = new User(this.loginName, this.loginPassword);
        application.addUser(newUser);
    }
    
    public void deleteUser() {
        if(currentUser.isAdmin()) {
            application.removeUser(selectedUser);
        }
    }
    
    public void deleteSelf() {
        application.removeUser(currentUser);
    }
    
        public String login() {
        User validUser = application.userCheck(currentUser);
        if (validUser != null) {
            validUser = null; // for at spare plads
            return null;
        } else {
            currentUser = new User("",""); // for at nulstille felterne
            return "error";
        }
    }

}

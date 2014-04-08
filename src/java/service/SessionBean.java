/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import model.Category;
import model.Post;
import model.PostThread;
import model.User;

/**
 *
 * @author Sigersted
 */
@Named
@SessionScoped
public class SessionBean implements Serializable {
    //associeringer 
    private ApplicationBean application;

    //Bruges til at hente informationer omkring der hvor man er nu
    private String currentTopic;    
    private User currentUser = null;
    private String postText;
    private Category currentCategory;
    //Bruges til at lave nye threads
    private String threadTopic;
    private PostThread currentThread;
    //Bruges til at lave nye Posts
    private String currentText;
    private Post currentPost;
    //Bruges til at login og out samt oprette nye Users
    private String loginName;
    private String loginPassword;
    //Bruges til at slette en bestemt bruger
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

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public String getCurrentTopic() {
        return currentTopic;
    }

    public void setCurrentTopic(String currentTopic) {
        this.currentTopic = currentTopic;
    }

    public String getCurrentText() {
        return currentText;
    }

    public void setCurrentText(String currentText) {
        this.currentText = currentText;
    }

    //---------------------------------------------------------------------
    //Rigtige metoder
    public void createUser() {
        application.createUser(this.loginName, this.loginPassword);
    }

    public void deleteUser() {
//        if (currentUser.isAdmin()) {
//            application.removeUser(selectedUser);
//        }
    }

    public void deleteSelf() {
//        if(currentUser != null)
//        application.removeUser(currentUser);
    }

    public String login() {
        User validUser = application.getUserByName(this.loginName);
        if (validUser.doesPasswordMatch(loginPassword)) {
            currentUser = validUser; // for at spare plads
            return null;
        } else {
            currentUser = null; // for at nulstille felterne
            return null;
        }
    }
    
    public String logout() {
        String navigate = null;
        if(currentUser != null) {
            currentUser = null;
            navigate = "index";
        } else navigate = null;
        
        return navigate;
    }
    
    public void createThread() {
        if(currentUser != null)
        application.createThread(this.threadTopic, this.currentCategory, this.currentUser);
    }
    
    public void deleteThread() {
        //TODO
    }

    public void createCategory(){
        if(currentUser != null)
        application.createCategory(this.currentUser, this.threadTopic);
    }
    
    public void deleteCategory(){
        //TODO
    }
    
    public void createPost() {
        if(currentUser != null)
        application.createPost(currentThread, postText, currentUser);
    }

    public void deletePost() {
        
    }
    
    public void editPost() {
        if(currentUser == currentPost.getUser())
        currentPost.setText(this.postText);
    }
}

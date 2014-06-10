/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
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

    @Inject
    private ApplicationBean app;

    //Bruges til at hente informationer omkring der hvor man er nu
    private String currentTopic;
    private User currentUser = null;
    private String postText;
	
    //Bruges til at lave nye threads
    private String threadTopic;
    //Bruges til at lave nye Posts
    private String currentText;
    //Bruges til at login og out samt oprette nye Users
    private String loginName;
    private String loginPassword;
    private String loginPassRepeat;
    //Bruges til at slette en bestemt bruger
    private User selectedUser;
	
	private int currentCategoryId;
	private int currentThreadId;
	private int currentPostId;

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
        if (currentCategoryId == -1) {
			return null;
		} else {
			Category out = app.getCategoryById(currentCategoryId);
			return app.getCategoryById(currentCategoryId);
		}
    }

    public void setCurrentCategory(Category currentCategory) {
        if (currentCategory == null) {
			currentCategoryId = -1;
		} else {
			currentCategoryId = currentCategory.getId();
		}
    }

    public PostThread getCurrentThread() {
        return app.getThreadById(currentThreadId);
    }

    public void setCurrentThread(PostThread thread) {
        if (thread == null) {
			currentThreadId = -1;
		} else {
			currentThreadId = thread.getId();
		}
    }

	public Post getCurrentPost() {
		return app.getPostById(currentPostId);
	}
	
	public void setCurrentPost(Post post) {
		if (post == null) {
			currentPostId = -1;
		} else {
			currentPostId = post.getId();
		}
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

    public String getLoginPassRepeat() {
        return loginPassRepeat;
    }

    public void setLoginPassRepeat(String loginPassRepeat) {
        this.loginPassRepeat = loginPassRepeat;
    }

    public void setCurrentText(String currentText) {
        this.currentText = currentText;
    }

    public void setApplication(ApplicationBean application) {
        this.app = application;
    }

	public int getCurrentCategoryId() {
		return currentCategoryId;
	}

	public void setCurrentCategoryId(int currentCategoryId) {
		this.currentCategoryId = currentCategoryId;
	}

	public int getCurrentThreadId() {
		return currentThreadId;
	}

	public void setCurrentThreadId(int currentThreadId) {
		this.currentThreadId = currentThreadId;
		PostThread pt = getCurrentThread();
		if (pt == null) {
			setCurrentCategoryId(-1);
		} else {
			setCurrentCategoryId(pt.getCategory().getId());
		}
	}

	public int getCurrentPostId() {
		return currentPostId;
	}

	public void setCurrentPostId(int currentPostId) {
		this.currentPostId = currentPostId;
	}

	
	
    //---------------------------------------------------------------------
    //Rigtige metoder
    public void createUser() {
        app.createUser(this.loginName, this.loginPassword);
    }

    public String deleteUser(User us) {
        //TODO move all posts and threads to a dummy user
        //TODO remove user as moderator in all categories
        //TODO Advanced log the user out of all active sessions
        if (currentUser.isAdmin() && !us.equals(currentUser)) {
            for (Category ca : us.getModeratedCategories()) {
                app.removeCategory(ca);
            }
            for (PostThread pt : app.getThreads()) {
                if (pt.getCreator() == us) {
                    app.removeThread(pt);
                    System.out.println("Slet thread");
                }
            }
            for (Post po : app.getPosts()) {
                if (po.getUser() == us) {
                    app.removePost(po);
                    System.out.println("Delete post");
                }
            }
            app.removeUser(us);
        }
        return "users";
    }

    public void deleteSelf() {
        //TODO 
//        if(currentUser != null)
//        application.removeUser(currentUser);
    }

    //Sørger for at det kun er dem der er login som kan lave nye categorier.
    public void ifLogin() throws IOException {
        if (currentUser == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        }
    }

    public String login() {
        currentUser = null;
        String navOutcome = null;
        User requestedUser = app.getUserByName(this.loginName);
        if (requestedUser != null) {
            if (requestedUser.doesPasswordMatch(loginPassword)) {
                currentUser = requestedUser;
                this.loginPassword = null;
                navOutcome = "index";
            }
        }
        return navOutcome;
    }

    public String logout() {
        currentUser = null;
        return "index";
    }

    public String createThread() {
        String out = null;
        if (currentUser != null) {
            PostThread pt = app.createThread(this.threadTopic,
                    this.currentText,
                    this.getCurrentCategory(),
                    this.currentUser);
            setCurrentThread(pt);
            out = "viewPosts";
        }
        return out;
    }

    public void deleteThread() {
        //TODO
    }

    public String createCategory() {
        if (currentUser != null) {
            app.createCategory(this.currentUser, this.threadTopic);
        }
        return "index";
    }

    public void deleteCategory() {
        //TODO
    }

    public String createPost() {
        String out = "login";
        if (currentUser != null) {
            app.createPost(getCurrentThread(), postText, currentUser);
            out = null;
            postText = null;
        }
        return out;
    }

    public void deletePost() {
        //TODO 
    }

    public String editPost() {
        //TODO where should the user go after editing a post?
        String out = null;
        if (currentUser == getCurrentPost().getUser()) {
            getCurrentPost().setText(this.postText);
        }
        return out;
    }

    public String selectCategory(Category c) {
        setCurrentCategory(c);
        return "viewThreads";
    }

    public String selectThread(PostThread pt) {
        setCurrentThread(pt);
        return "viewPosts";
    }

    // Lav en side der hedder newPost som viser alle posts i en thread
    // Lav en side som viser alle brugere i systemet.
    public String creatAndStoreSomeObjects() {
		
		app.reset();

        User user1 = app.createUser("Lars", "1234");
        user1.setAdmin(true);
        User user2 = app.createUser("Hans", "qwerty");
        User user3 = app.createUser("Bo", "5678");
        User user4 = app.createUser("Kim", "123456");
        User user5 = app.createUser("Helle", "password");
        User user6 = app.createUser("Line", "password1");
        User user7 = app.createUser("Louise", "nej");
        User user8 = app.createUser("Sine", "måske");

        Category toilets = app.createCategory(user1,
                "Toilets and toilet-related technologies");

        PostThread pt11 = app.createThread("How to clean a toilet?",
                "Does anyone have any good tips for cleaning a toilets? Mine is really quite dirty!",
                toilets,
                user7);
        Post p111 = app.createPost(pt11,
                "Wait until you have to pee really, really bad, then use the stream to remove any dirt",
                user8);
        Post p112 = app.createPost(pt11, "Thx, it worked!", user7);

        PostThread pt12 = app.createThread("How to sit on a toilet?",
                "I keep falling off! \nRight now I just lie besides the toilet and do my business, but I feel like it could be done in a better way",
                toilets,
                user2);
        Post p121 = app.createPost(pt12, "I think I know", user3);
        Post p122 = app.createPost(pt12, "This clearly belongs in the Obvious-category",
                user8);
        Post p123 = app.createPost(pt12, "No Way!, it's totally about toilet tech. My dad designed the toilet on the ISS, they have the same problem up there",
                user5);

        PostThread pt13 = app.createThread("How to spell to toilet",
                "Someone help me pls",
                toilets,
                user3);

        Category obvious = app.createCategory(user1,
                "Questions with obvious answers");

        PostThread pt21 = app.createThread("Is JSF shit", "I need to know",
                obvious, user8);

        PostThread pt22 = app.createThread("What time is it?", "pls??",
                obvious, user1);

        Post p1 = app.createPost(pt11, "Hello world", user1);
        Post p2 = app.createPost(pt21, "What?", user2);
        Post p3 = app.createPost(pt11, "????", user3);
        Post p4 = app.createPost(pt12, "Glasses", user4);
        Post p5 = app.createPost(pt11, "Damn you all", user5);
        Post p6 = app.createPost(pt22, "I like baloons", user6);
        Post p7 = app.createPost(pt11, "I got hair in my mouth", user7);

        return "index";
    }
}

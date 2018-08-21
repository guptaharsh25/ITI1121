package com.harshgupta;

public class NewsFeed {
    private final int MAX_SIZE = 25;
    private Post [] posts;

    public NewsFeed(){
        this.posts = new Post [MAX_SIZE];
    }

    public void addPost(Post p){
        for(int i = 0; i < this.posts.length; i++){
            if(posts[i] == null){
                posts[i] = p;
                break;
            }
        }
    }


}

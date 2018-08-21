package com.harshgupta;

public class TextPost extends Post {

    private String textMessage;

    public TextPost(String username, String textMessage){
        super(username);

        this.textMessage = textMessage;
    }

    public String toString(){
        return super.toString() + "\nThis is a text post.\nThe text of the post is \"" + this.textMessage + "\".";
    }

    public boolean isPopular(){
        return (this.getLikes() >= 50);
    }

}

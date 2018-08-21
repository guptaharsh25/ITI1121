package com.harshgupta;


public class Post implements Likeable, Comparable{

    private String username;

    private java.util.Date date;

    private int count;

    public Post(String username){
        this.username = username;
        this.date = java.util.Calendar.getInstance().getTime();
        this.count = 0;
    }

    public void like(){
        this.count++;
    }

    public int getLikes(){
        return this.count;
    }

    public int compareTo(Comparable o){
        Post other = (Post) o;

        if(other.date.after(this.date)){
            return 1;
        }
        else if(this.date.after(other.date)){
            return -1;
        }
        else{
            return 0;
        }
    }

    public boolean isPopular(){
        return (this.getLikes() >= 100);
    }

    public String toString(){
        return this.username + " made this post on " + this.date + ".\n" + this.username + "'s post got " + this.getLikes() + " likes.";
    }

}

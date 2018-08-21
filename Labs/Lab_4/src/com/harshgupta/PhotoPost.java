package com.harshgupta;

public class PhotoPost extends Post {

    private String fileName;
    private String caption;

    public PhotoPost(String username, String fileName, String caption){
        super(username);

        this.fileName = fileName;
        this.caption = caption;
    }

    public String toString(){
        return super.toString() + "\nThis post also includes a photo with file name \"" + this.fileName + "\" and caption \"" + this.caption + "\".";
    }

}

package entity;

import java.io.Serializable;

public class Goods implements Serializable{
    // Fields
    private String name;
    private String origin;
    private String description;
    private String imageUrl;


    // Methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // Additional methods for add, edit, delete as needed
    public String printGoodsInDetail(){
        return  "Name= "+ name +"\n"+
                "Origin= "+ origin +"\n"+
                "Description= "+description+"\n"+
                "ImageURL= "+imageUrl+"\n"+
                "--------------------------------";
    }

    public String printGoodsInBrief(){
        return  "Name= "+ name +"\n"+
                "Description= "+ description +"\n"+
                "--------------------------------";
    }

}

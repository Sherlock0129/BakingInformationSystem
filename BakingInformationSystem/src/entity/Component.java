package entity;

import java.io.Serializable;

public class Component implements Serializable {
    // Fields
    private String name;
    private String description;
    private double caloriesPer100g;


    // Methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCaloriesPer100g() {
        return caloriesPer100g;
    }

    public void setCaloriesPer100g(double caloriesPer100g) {
        this.caloriesPer100g = caloriesPer100g;
    }

    // Additional methods for add, edit, delete as needed
    public String printComponentInDetail(){
        return  "Name= "+ name +"\n"+
                "Description= "+ description +"\n"+
                "Calories per 100g= "+caloriesPer100g+"\n"+
                "--------------------------------" ;
    }

    public String printComponentInBrief(){
        return  "Name= "+ name +"\n"+
                "Description= "+ description +"\n"+
                "--------------------------------" ;
    }

    public String printComponentForSearch(){
        return  "Name= "+ name +"\n"+
                "Calories per 100g= "+ caloriesPer100g;

    }
}

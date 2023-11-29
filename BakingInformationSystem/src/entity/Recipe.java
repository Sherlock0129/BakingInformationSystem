package entity;

import tool.HashTable;
import java.io.Serializable;


public class Recipe implements Serializable{
    // Fields
    private Goods bakedGood;
    private HashTable<Component, Double> componentsWithQuantities;

    // Constructor
    public Recipe(Goods bakedGood) {
        this.bakedGood = bakedGood;
        this.componentsWithQuantities = new HashTable<>();
    }

    // Methods
    public Goods getBakedGood() {
        return bakedGood;
    }

    public HashTable<Component, Double> getComponentsWithQuantities() {
        return componentsWithQuantities;
    }

    public void addComponent(Component component, double quantity) {
        componentsWithQuantities.put(component, quantity);
    }

    public void editComponent(Component component, double newQuantity) {
        if (componentsWithQuantities.containsKey(component)) {
            componentsWithQuantities.put(component, newQuantity);
        }
    }

    public void removeComponent(Component component) {
        componentsWithQuantities.remove(component);
    }


}

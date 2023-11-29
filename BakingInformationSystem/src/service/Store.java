package service;

import entity.*;
import tool.*;

import java.io.*;
import java.util.Scanner;
public class Store implements Serializable {

    private DoubleLinkedList<Goods> goods;
    private DoubleLinkedList<Component> components;
    private DoubleLinkedList<Recipe> recipes;
    private HashTable<String, Recipe> recipeHashTable = new HashTable<>();

    public Store(){
        goods = new DoubleLinkedList<>();
        components = new DoubleLinkedList<>();
        recipes = new DoubleLinkedList<>();

    }
    //==================================================================================================================
    public void addRecipe(Recipe recipe){
        recipes.add(recipe);
    }



    public Recipe getRecipe(String bakedGoodName) {
        for (Recipe recipe : recipes) {
            recipeHashTable.put(recipe.getBakedGood().getName().toLowerCase(), recipe);
        }
        return recipeHashTable.get(bakedGoodName.toLowerCase());
    }

    public DoubleLinkedList<Recipe> getRecipeList() {
        return recipes;
    }
    public void removeRecipe(Recipe recipe) {
        recipes.remove(recipe);
        recipeHashTable.remove(recipe.getBakedGood().getName().toLowerCase());
    }
    public void listRecipes() {
        System.out.println("Listing Recipes:");

        if (recipes.isEmpty()) {
            System.out.println("No recipes available.");
        } else {
            for (int i = 0; i < recipes.size(); i++) {
                System.out.println("--------------------------------");
                System.out.println("Recipe " + (i + 1));
                Recipe recipe = recipes.get(i);
                System.out.println("Baked Good: " );
                System.out.println(recipe.getBakedGood().printGoodsInBrief());
                System.out.println("Component: ");
                HashTable<Component, Double> componentsWithQuantities = recipe.getComponentsWithQuantities();
                double totalCalories = 0;
                for (Component component : componentsWithQuantities.keySet()) {
                    double quantity = componentsWithQuantities.get(component);
                    System.out.println(component.printComponentForSearch() + " ---Quantity: " + quantity +"g");
                    totalCalories += component.getCaloriesPer100g() * (quantity/100);
                }
                System.out.println("Total Calories: " + totalCalories);
                System.out.println("--------------------------------");
            }
        }
    }

    //==================================================================================================================
    public void addGoods(Goods good){
        for(int i = 0;i <goods.size();i++) {
            if (goods.get(i).getName().equals(good.getName())) {
                System.out.println("Already exit good,addition failed");
                return;
            }
        }
        goods.add(good);
    }
    public Goods getGoods(int number) {
        if (number >= 1 && number <= goods.size()) {
            return goods.get(number - 1); // The list index starts at 0
        } else {
            return null; // Returning null indicates that it was not found
        }
    }
    public void removeGoods(Goods good) {
        goods.remove(good);
    }
    public void listGoods(){
        SearchAndSort sas = new SearchAndSort(goods,components);
        sas.sortBakedGoodsAlphabetically();
        for(int i = 0; goods!= null&&i < goods.size();i++){
            System.out.println("--------------------------------");
            System.out.println("Good" + (i+1));
            System.out.println(goods.get(i).printGoodsInDetail());
        }
    }

    //==================================================================================================================
    public void addComponent(Component component){
        for(int i = 0;i <components.size();i++) {
            if (components.get(i).getName().equals(component.getName())) {
                System.out.println("Already exit component,addition failed");
                return;
            }
        }
        components.add(component);
    }
    public Component getComponents(int number) {
        if (number >= 1 && number <= components.size()) {
            return components.get(number - 1); // The list index starts at 0
        } else {
            return null; // Returning null indicates that it was not found
        }
    }
    public void removeComponents(Component component) {
        components.remove(component);
    }
      public void listComponent(){
        SearchAndSort sas = new SearchAndSort(goods,components);
        sas.sortComponentsByCalories();
        for(int i = 0; components!= null&&i < components.size();i++){
            System.out.println("--------------------------------");
            System.out.println("Component" + (i+1));
            System.out.println(components.get(i).printComponentInDetail());
        }
    }
    //==================================================================================================================
    public void searchGoodsByName(String fullName){
        SearchAndSort searchAndSort = new SearchAndSort(goods,components);
        Goods resultsByName = searchAndSort.searchGoodsByName(fullName);
        if(resultsByName != null){
            System.out.println(resultsByName.printGoodsInDetail());
        }else{
            System.out.println("Can not find corresponding good.");
        }


    }

    public void searchGoodsByDescription(String keyword){
        Scanner scanner = new Scanner(System.in);
        SearchAndSort searchAndSort = new SearchAndSort(goods,components);
        DoubleLinkedList<Goods> resultsByDescription = searchAndSort.searchGoodsByDescription(keyword);
        SearchAndSort sas = new SearchAndSort(resultsByDescription,components);
        sas.sortBakedGoodsAlphabetically();
        if(resultsByDescription.size() != 0) {
            for (int i = 0; resultsByDescription != null && i < resultsByDescription.size(); i++) {
                System.out.println("--------------------------------");
                System.out.println("SearchedGood" + (i + 1));
                System.out.println(resultsByDescription.get(i).printGoodsInBrief());
            }
            System.out.println("Choose one good to look closely(enter a number):");
            int chosenNumber = scanner.nextInt();
            Goods selectedGood = getGoods(chosenNumber );
            if (selectedGood == null) {
                System.out.println("Invalid choice. No Good found for the selected number.");
            }else{
            System.out.println("--------------------------------");
            System.out.println("SearchedGood" + chosenNumber);
            System.out.println(selectedGood.printGoodsInDetail());
            }
        }else{
            System.out.println("Can not find corresponding good.");
        }
    }

    public void searchComponentByName(String fullName){
        SearchAndSort searchAndSort = new SearchAndSort(goods,components);
        Component resultsByName = searchAndSort.searchComponentByName(fullName);
        if(resultsByName != null) {
            System.out.println(resultsByName.printComponentInDetail());
        }else{
            System.out.println("Can not find corresponding component.");
        }

    }

    public void searchComponentByDescription(String keyword){
        Scanner scanner = new Scanner(System.in);
        SearchAndSort searchAndSort = new SearchAndSort(goods,components);
        DoubleLinkedList<Component> resultsByDescription = searchAndSort.searchComponentByDescription(keyword);
        SearchAndSort sas = new SearchAndSort(goods,resultsByDescription);
        sas.sortComponentsByCalories();
        if(resultsByDescription.size() != 0) {
            for (int i = 0; resultsByDescription != null && i < resultsByDescription.size(); i++) {
                System.out.println("--------------------------------");
                System.out.println("SearchedComponent" + (i + 1));
                System.out.println(resultsByDescription.get(i).printComponentInBrief());
            }
            System.out.println("Choose one component to look closely(enter a number):");
            int chosenNumber = scanner.nextInt();
            Component selectedComponent = getComponents(chosenNumber);
            if (selectedComponent == null) {
                System.out.println("Invalid choice. No Good found for the selected number.");
            }else{
            System.out.println("--------------------------------");
            System.out.println("SearchedComponent" + chosenNumber);
            System.out.println(selectedComponent.printComponentInDetail());
            }
        }else {
            System.out.println("Can not find corresponding component.");
        }
    }

    //==================================================================================================================
    public void serialize() {
        try {
            // Open the file 'store.txt' for serialization
            FileOutputStream fileOut = new FileOutputStream("store.txt");
            // Create an object output stream
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            // Writes the current object to the output stream
            out.writeObject(this);
            // Close the output stream
            out.close();
            // Close the file output stream
            fileOut.close();
            // Print a successful message
            System.out.println("The data was saved successfully");
        } catch (IOException e) {
            System.err.println("Failed to save data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deserialize() {
        try {
            // Open the file 'store.txt' to deserialize
            FileInputStream fileIn = new FileInputStream("store.txt");
            // Create an object input stream
            ObjectInputStream in = new ObjectInputStream(fileIn);
            // Objects are read from the input stream and type-converted
            Store obj = (Store) in.readObject();
            // Close the input stream
            in.close();
            // Close the file input stream
            fileIn.close();
            // Sets the deserialized data to the current object
            this.goods = obj.goods;
            this.components = obj.components;
            this.recipes = obj.recipes;
            // Print a successful message
            System.out.println("The data was read successfully");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Data read failed: " + e.getMessage());
            e.printStackTrace();
        }
    }



}

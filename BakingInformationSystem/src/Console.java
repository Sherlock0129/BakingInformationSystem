import entity.*;
import service.*;
import tool.*;

import java.util.Scanner;
import java.io.Serializable;

public class Console implements Serializable{

    private static Store store = new Store();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Goods/Component/Recipe");
            System.out.println("2. Edit Goods/Component/Recipe");
            System.out.println("3. Remove Goods/Component/Recipe");
            System.out.println("4. List Goods/Component/Recipe");
            System.out.println("5. Search Goods/Component/Recipe");
            System.out.println("6. Save");
            System.out.println("7. Load");
            System.out.println("8. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            try {
                switch (choice) {
                    case 1:
                        addThings(scanner);
                        break;
                    case 2:
                        editThings(scanner);
                        break;
                    case 3:
                        removeThings(scanner);
                        break;
                    case 4:
                        listThings(scanner);
                        break;
                    case 5:
                        searchThings(scanner);
                        break;
                    case 6:
                        saveDate();
                        break;
                    case 7:
                        loadDate();
                        break;
                    case 8:
                        System.exit(0);

                        default:
                        System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void addThings(Scanner scanner) {
        System.out.println("1. Add Goods");
        System.out.println("2. Add Components");
        System.out.println("3. Add Recipes");
        System.out.println("4. Back");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                addGoods(scanner);
                break;
            case 2:
                addComponents(scanner);
                break;
            case 3:
                addRecipes(scanner);
                break;
            case 4:
                break;

            default:
                System.out.println("Invalid choice");
        }
    }

    private static void editThings(Scanner scanner) {
        System.out.println("1. Edit Goods");
        System.out.println("2. Edit Components");
        System.out.println("3. Edit Recipes");
        System.out.println("4. Back");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                editGoods(scanner);
                break;
            case 2:
                editComponents(scanner);
                break;
            case 3:
                editRecipes(scanner);
                break;
            case 4:
                break;

            default:
                System.out.println("Invalid choice");
        }
    }

    private static void listThings(Scanner scanner) {
        System.out.println("1. List Goods");
        System.out.println("2. List Components");
        System.out.println("3. List Recipes");
        System.out.println("4. Back");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                listGoods();
                break;
            case 2:
                listComponents();
                break;
            case 3:
                listRecipes();
                break;
            case 4:
                break;

            default:
                System.out.println("Invalid choice");
        }
    }

    private static void removeThings(Scanner scanner) {
        System.out.println("1. Remove Goods");
        System.out.println("2. Remove Components");
        System.out.println("3. Remove Recipes");
        System.out.println("4. Back");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                removeGoods(scanner);
                break;
            case 2:
                removeComponents(scanner);
                break;
            case 3:
                removeRecipes(scanner);
                break;
            case 4:
                break;

            default:
                System.out.println("Invalid choice");
        }
    }

    private static void searchThings(Scanner scanner) {
        System.out.println("1. Search Goods");
        System.out.println("2. Search Components");
        System.out.println("3. Search Recipes");
        System.out.println("4. Back");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                searchGoods(scanner);
                break;
            case 2:
                searchComponents(scanner);
                break;
            case 3:
                searchRecipes(scanner);
                break;
            case 4:
                break;

            default:
                System.out.println("Invalid choice");
        }
    }
    //==================================================================================================================
    private static void addGoods(Scanner scanner){
        System.out.println("Enter good name:");
        String goodName = scanner.nextLine();
        System.out.println("Enter good origin:");
        String goodOrigin = scanner.nextLine();
        System.out.println("Enter good description:");
        String goodDescription = scanner.nextLine();
        System.out.println("Enter good imageURL:");
        String goodImage = scanner.nextLine();
        Goods good = new Goods();
        good.setName(goodName);
        good.setOrigin(goodOrigin);
        good.setDescription(goodDescription);
        good.setImageUrl(goodImage);
        store.addGoods(good);
    }

    private static void addComponents(Scanner scanner){
        System.out.println("Enter component name:");
        String componentName = scanner.nextLine();
        System.out.println("Enter component description:");
        String componentDescription = scanner.nextLine();
        System.out.println("Enter component calories per 100g:");
        double caloriePer100g = scanner.nextDouble();
        Component component = new Component();
        component.setName(componentName);
        component.setDescription(componentDescription);
        component.setCaloriesPer100g(caloriePer100g);
        store.addComponent(component);
    }

    private static void addRecipes(Scanner scanner) {
        store.listGoods();
        System.out.println("Choose one good to create a recipe:");
        int goodNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        Goods selectedGood = store.getGoods(goodNumber);

        if (selectedGood != null) {
            Recipe recipe = new Recipe(selectedGood);

            // Display available components for the user to choose from
            store.listComponent();
            System.out.println("Choose components and their quantities:");

            boolean continueAddingIngredients = true;

            while (continueAddingIngredients) {
                System.out.println("Enter the number of the component:");
                int componentNumber = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                Component selectedComponent = store.getComponents(componentNumber);

                if (selectedComponent != null&& !recipe.getComponentsWithQuantities().keySet().contains(selectedComponent)) {
                    System.out.println("Enter the quantity(g):");
                    double quantity = scanner.nextDouble();
                    scanner.nextLine(); // Consume the newline character

                    // Add the ingredient to the recipe
                    recipe.addComponent(selectedComponent, quantity);

                    System.out.println("Component added successfully.");

                    // Ask if the user wants to continue adding ingredients
                    System.out.println("Do you want to add another ingredient? (yes/no)");
                    System.out.println("1.YES  2.NO");
                    int choose = scanner.nextInt();
                    if(choose == 2){
                        continueAddingIngredients = false;
                    }
                } else {
                    System.out.println("Invalid component number or component already exit. Please try again.");
                }
            }


            store.addRecipe(recipe);

            System.out.println("Recipe created successfully.");
        } else {
            System.out.println("Invalid good number. Please try again.");
        }
    }

    //==================================================================================================================
    private static void editGoods(Scanner scanner){
        store.listGoods();
        System.out.println("Choose one good to edit(enter a number):");
        int chosenNumber = scanner.nextInt();
        scanner.nextLine();

        // Gets the selected Good
        Goods selectedGood = store.getGoods(chosenNumber);

        if (selectedGood == null) {
            System.out.println("Invalid choice. No Good found for the selected number.");
            return;
        }

        // Displays the details of the currently selected Good
        System.out.println("Selected Good:");
        System.out.println("Name: " + selectedGood.getName());
        System.out.println("Origin: " + selectedGood.getOrigin());
        System.out.println("Description: " + selectedGood.getDescription());
        System.out.println("Image URL: " + selectedGood.getImageUrl());

        // PROMPT THE USER TO SELECT WHAT TO EDIT
        System.out.println("Select an attribute to edit:");
        System.out.println("1. Name");
        System.out.println("2. Origin");
        System.out.println("3. Description");
        System.out.println("4. Image URL");
        System.out.println("5. Cancel");

        int editChoice = scanner.nextInt();
        scanner.nextLine();

        switch (editChoice) {
            case 1:
                System.out.println("Enter the new name:");
                String newName = scanner.nextLine();
                selectedGood.setName(newName);
                break;
            case 2:
                System.out.println("Enter the new origin:");
                String newOrigin = scanner.nextLine();
                selectedGood.setOrigin(newOrigin);
                break;
            case 3:
                System.out.println("Enter the new description:");
                String newDescription = scanner.nextLine();
                selectedGood.setDescription(newDescription);
                break;
            case 4:
                System.out.println("Enter the new image URL:");
                String newImageUrl = scanner.nextLine();
                selectedGood.setImageUrl(newImageUrl);
                break;
            case 5:
                System.out.println("Editing canceled.");
                break;
            default:
                System.out.println("Invalid choice. Editing canceled.");
        }
    }

    private static void editComponents(Scanner scanner){
        store.listComponent();
        System.out.println("Choose one component to edit(enter a number):");
        int chosenNumber = scanner.nextInt();
        scanner.nextLine(); // Consumes line breaks

        // Gets the selected Component
        Component selectedComponent = store.getComponents(chosenNumber);

        if (selectedComponent == null) {
            System.out.println("Invalid choice. No component found for the selected number.");
            return;
        }

        // Displays the details of the currently selected Component
        System.out.println("Selected Component:");
        System.out.println("Name: " + selectedComponent.getName());
        System.out.println("Description: " + selectedComponent.getDescription());
        System.out.println("Calories per 100g: " + selectedComponent.getCaloriesPer100g());

        // Prompt the user to select what to edit
        System.out.println("Select an attribute to edit:");
        System.out.println("1. Name");
        System.out.println("2. Description");
        System.out.println("3. Calories per 100g");
        System.out.println("4. Cancel");

        int editChoice = scanner.nextInt();
        scanner.nextLine();

        switch (editChoice) {
            case 1:
                System.out.println("Enter the new name:");
                String newName = scanner.nextLine();
                selectedComponent.setName(newName);
                break;
            case 2:
                System.out.println("Enter the new description:");
                String newDescription = scanner.nextLine();
                selectedComponent.setDescription(newDescription);
                break;
            case 3:
                System.out.println("Enter the new calories per 100g:");
                double newCalorie = scanner.nextDouble();
                selectedComponent.setCaloriesPer100g(newCalorie);
                break;
            case 4:
                System.out.println("Editing canceled.");
                break;
            default:
                System.out.println("Invalid choice. Editing canceled.");
        }
    }

    private static void editRecipes(Scanner scanner) {
        // Display available recipes
        store.listRecipes();

        System.out.println("Choose a recipe to edit (enter the corresponding recipe name):");
        String recipeName = scanner.nextLine();

        // Get the selected recipe using the recipe name
        Recipe selectedRecipe = store.getRecipe(recipeName);

        if (selectedRecipe == null) {
            System.out.println("Invalid choice. No recipe found for the selected number.");
            return;
        }

        // Display current recipe details
        System.out.println("Selected Recipe:");
        System.out.println("Baked Good: ");
        System.out.println(selectedRecipe.getBakedGood().printGoodsInBrief());
        System.out.println("Component: ");
        HashTable<Component, Double> componentsWithQuantities = selectedRecipe.getComponentsWithQuantities();
        for (Component component : componentsWithQuantities.keySet()) {
            double quantity = componentsWithQuantities.get(component);
            System.out.println(component.printComponentForSearch() + " ---Quantity: " + quantity + "g");
        }

        // Provide options for editing
        System.out.println("Select an option:");
        System.out.println("1. Add Component");
        System.out.println("2. Edit Component");
        System.out.println("3. Remove Component");
        System.out.println("4. Cancel");

        int editOption = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (editOption) {
            case 1:
                // Add Ingredient
                store.listComponent();
                System.out.println("Choose a component to add (enter the corresponding number):");
                int componentNumber = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                Component selectedComponent = store.getComponents(componentNumber);

                if (selectedComponent != null) {
                    System.out.println("Enter the quantity(g):");
                    double quantity = scanner.nextDouble();
                    scanner.nextLine(); // Consume the newline character

                    // Add the ingredient to the recipe
                    selectedRecipe.addComponent(selectedComponent, quantity);

                    System.out.println("Component added successfully.");
                } else {
                    System.out.println("Invalid component number. Please try again.");
                }
                break;
            case 2:
                // Edit Ingredient
                store.listComponent();
                System.out.println("Choose a component to edit (enter the corresponding number):");
                int editComponentNumber = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                Component editSelectedComponent = store.getComponents(editComponentNumber);

                if (editSelectedComponent != null) {
                    System.out.println("Enter the new quantity(g):");
                    double newQuantity = scanner.nextDouble();
                    scanner.nextLine(); // Consume the newline character

                    // Edit the ingredient in the recipe
                    selectedRecipe.editComponent(editSelectedComponent, newQuantity);

                    System.out.println("Ingredient edited successfully.");
                } else {
                    System.out.println("Invalid component number. Please try again.");
                }
                break;
            case 3:
                // Remove Ingredient
                store.listComponent();
                System.out.println("Choose a component to remove (enter the corresponding number):");
                int removeComponentNumber = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                Component removeSelectedComponent = store.getComponents(removeComponentNumber);

                if (removeSelectedComponent != null) {
                    // Remove the ingredient from the recipe
                    selectedRecipe.removeComponent(removeSelectedComponent);

                    System.out.println("Ingredient removed successfully.");
                } else {
                    System.out.println("Invalid component number. Please try again.");
                }
                break;
            case 4:
                // Cancel
                System.out.println("Editing canceled.");
                break;
            default:
                System.out.println("Invalid choice. Editing canceled.");
        }
    }


    //==================================================================================================================
    private static void listGoods(){
        store.listGoods();
    }

    private static void listComponents(){
        store.listComponent();
    }

    private static void listRecipes(){
        store.listRecipes();
    }
    //==================================================================================================================
    private static void removeGoods(Scanner scanner){
        store.listGoods(); // Show all Goods

        System.out.println("Choose a Good to remove (enter the corresponding number):");
        int chosenNumber = scanner.nextInt();
        scanner.nextLine(); // Consumes line breaks

        // Gets the selected Good
        Goods selectedGood = store.getGoods(chosenNumber);

        if (selectedGood == null) {
            System.out.println("Invalid choice. No Good found for the selected number.");
            return;
        }

        // Displays the details of the currently selected Good
        System.out.println("Selected Good to remove:");
        System.out.println("Name: " + selectedGood.getName());
        System.out.println("Origin: " + selectedGood.getOrigin());
        System.out.println("Description: " + selectedGood.getDescription());
        System.out.println("Image URL: " + selectedGood.getImageUrl());

        // Prompt the user to confirm the removal
        System.out.println("Do you want to remove this Good? ");
        System.out.println("1.YES    2.NO");
        int confirmation = scanner.nextInt();

        if (confirmation == 1) {
            store.removeGoods(selectedGood);
            System.out.println("Good removed successfully.");
        } else {
            System.out.println("Removal canceled.");
        }
    }

    private static void removeComponents(Scanner scanner){
        store.listComponent(); // Show all Components

        System.out.println("Choose a component to remove (enter the corresponding number):");
        int chosenNumber = scanner.nextInt();
        scanner.nextLine(); // Consumes line breaks

        // Gets the selected component
        Component selectedComponent = store.getComponents(chosenNumber);

        if (selectedComponent == null) {
            System.out.println("Invalid choice. No Good found for the selected number.");
            return;
        }

        // Displays the details of the currently selected component
        System.out.println("Selected component to remove:");
        System.out.println("Name: " + selectedComponent.getName());
        System.out.println("Description: " + selectedComponent.getDescription());
        System.out.println("Calories per 100g: " + selectedComponent.getCaloriesPer100g());

        // Prompt the user to confirm the removal
        System.out.println("Do you want to remove this Component? ");
        System.out.println("1.YES    2.NO");
        int confirmation = scanner.nextInt();

        if (confirmation == 1) {
            store.removeComponents(selectedComponent);
            System.out.println("Component removed successfully.");
        } else {
            System.out.println("Removal canceled.");
        }
    }

    private static void removeRecipes(Scanner scanner) {
        if (store.getRecipeList().isEmpty()) {
            System.out.println("No recipes available to remove.");
            return;
        }

        // Display available recipes
        for (int i = 0; i < store.getRecipeList().size(); i++) {
            System.out.println((i + 1) + ". " + store.getRecipeList().get(i).getBakedGood().printGoodsInBrief());
        }

        System.out.println("Enter the recipe number you want to remove:");
        // Get user input for the recipe number to remove
        int recipeNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        // Check if the entered recipe number is valid
        if (recipeNumber >= 1 && recipeNumber <= store.getRecipeList().size()) {
            Recipe removedRecipe = store.getRecipeList().get(recipeNumber - 1);
            store.removeRecipe(removedRecipe);
            System.out.println("Recipe removed successfully");
        } else {
            System.out.println("Invalid recipe number. No recipe removed.");
        }
    }
    //==================================================================================================================
    private static void searchGoods(Scanner scanner) {
        System.out.println("Enter the keyword to search for:");
        String keyword = scanner.nextLine();

        // Choose to search by title or product description, as needed
        System.out.println("Choose search option:");
        System.out.println("1. Search by Name(Make sure you enter a full name)");
        System.out.println("2. Search by Description");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                store.searchGoodsByName(keyword);
                break;
            case 2:
                store.searchGoodsByDescription(keyword);
                break;
            default:
                System.out.println("Invalid option. Please choose 1 or 2.");
        }
    }

    private static void searchComponents(Scanner scanner){
        System.out.println("Enter the keyword to search for:");
        String keyword = scanner.nextLine();

        // Choose to search using the component name or component description as needed
        System.out.println("Choose search option:");
        System.out.println("1. Search by Name(Make sure you enter a full name)");
        System.out.println("2. Search by Description");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                store.searchComponentByName(keyword);
                break;
            case 2:
                store.searchComponentByDescription(keyword);
                break;
            default:
                System.out.println("Invalid option. Please choose 1 or 2.");
        }

    }

    private static void searchRecipes(Scanner scanner) {
        System.out.println("Enter the keyword to search for in recipes:");
        String keyword = scanner.nextLine();

        // Choose to search using the recipe name or recipe description as needed
        System.out.println("Choose search option:");
        System.out.println("1. Search by Baked Good Name(Enter a full name)");
        System.out.println("2. Search by Baked Good component");
        int option = scanner.nextInt();
        scanner.nextLine(); // Consumes excess line breaks

        switch (option) {
            case 1:
                searchRecipesByBakedGoodName(keyword);
                break;
            case 2:
                searchRecipesByComponent(keyword);
                break;
            default:
                System.out.println("Invalid option. Please choose 1 or 2.");
        }
    }

    public static void searchRecipesByComponent(String keyword) {
        DoubleLinkedList<Recipe> results = new DoubleLinkedList<>();

        for (Recipe recipe : store.getRecipeList()) { // Let's say recipes are stored in recipes
            HashTable<Component, Double> componentsWithQuantities = recipe.getComponentsWithQuantities();

            for (Component component : componentsWithQuantities.keySet()) {
                if (component.getName().toLowerCase().contains(keyword.toLowerCase())) {
                    results.add(recipe);
                    break; // If a matching component is found, add the formula to the results list and move on to the next recipe
                }
            }
        }
        for (int i = 0; i < results.size(); i++) {
            System.out.println("--------------------------------");
            System.out.println("Recipe " + (i + 1));
            Recipe recipe = results.get(i);
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

    private static void searchRecipesByBakedGoodName(String keyword) {
        Recipe resultRecipe = store.getRecipe(keyword);
        displaySearchResult(resultRecipe);
    }

    private static void displaySearchResult(Recipe result) {
        if (result == null) {
            System.out.println("No recipe available matching the search criteria.");
        } else {
            System.out.println("Search Result:");
            System.out.println("--------------------------------");
            System.out.println("Recipe");
            System.out.println("Baked Good: ");
            System.out.println(result.getBakedGood().printGoodsInBrief());
            System.out.println("Component: ");
            HashTable<Component, Double> componentsWithQuantities = result.getComponentsWithQuantities();
            double totalCalories = 0;
            for (Component component : componentsWithQuantities.keySet()) {
                double quantity = componentsWithQuantities.get(component);
                System.out.println(component.printComponentForSearch() + " ---Quantity: " + quantity + "g");
                totalCalories += component.getCaloriesPer100g() * (quantity / 100);
            }
            System.out.println("Total Calories: " + totalCalories);
            System.out.println("--------------------------------");
        }
    }
    //==================================================================================================================
    private static void saveDate(){
        store.serialize();
    }

    private static void loadDate(){
        store.deserialize();
    }

}

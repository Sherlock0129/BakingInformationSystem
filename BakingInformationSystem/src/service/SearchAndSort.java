package service;

import entity.*;
import tool.*;

import java.io.*;


public class SearchAndSort implements Serializable{
    // Fields
    private DoubleLinkedList<Goods> goodsList;
    private DoubleLinkedList<Component> componentsList;

    private HashTable<String, Goods> goodsHashTable;
    private HashTable<String, Component> componentsHashTable;


    // Constructor
    public SearchAndSort(DoubleLinkedList<Goods> goodsList, DoubleLinkedList<Component> componentsList) {
        this.goodsList = goodsList;
        this.componentsList = componentsList;
        goodsHashTable = new HashTable<>();
        componentsHashTable = new HashTable<>();

        // Add Goods to the hash table
        for (Goods goods : goodsList) {
            goodsHashTable.put(goods.getName(), goods);
        }

        // Add Components to the hash table
        for (Component component : componentsList) {
            componentsHashTable.put(component.getName(), component);
        }
    }


    // Methods
    public Goods searchGoodsByName(String fullName) {
        Goods results = (goodsHashTable.get(fullName));
        return results;
    }

    public DoubleLinkedList<Goods> searchGoodsByDescription(String keyword) {
        DoubleLinkedList<Goods> results = new DoubleLinkedList<>();
        for (Goods good : goodsHashTable.values()) {
            if (good.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(good);
            }
        }
        return results;
    }

    public Component searchComponentByName(String fullName) {
        Component results = componentsHashTable.get(fullName);
        return results;
    }

    public DoubleLinkedList<Component> searchComponentByDescription(String keyword) {
        DoubleLinkedList<Component> results = new DoubleLinkedList<>();
        for (Component component : componentsHashTable.values()) {
            if (component.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(component);
            }
        }
        return results;
    }

    public DoubleLinkedList<Goods> sortBakedGoodsAlphabetically() {
        if(goodsList.size() > 1){
            quickSort(goodsList, 0, goodsList.size() - 1);
        }
        return goodsList;
    }

    public DoubleLinkedList<Component> sortComponentsByCalories() {
        if (componentsList.size() > 1) {
            shellSort(componentsList);
        }
        return componentsList;
    }

    //shellSort
    private void shellSort(DoubleLinkedList<Component> list) {
        int n = list.size();

        // The interval is calculated using the Knuth sequence
        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            // Make the array h-ordered
            for (int i = h; i < n; i++) {
                // let a[i] insert to a[i-h]、a[i-2*h]、a[i-3*h]...
                for (int j = i; j >= h && less(list.get(j), list.get(j - h)); j -= h) {
                    swapShell(list, j, j - h);
                }
            }
            h = h / 3;
        }
    }

    private boolean less(Component v, Component w) {
        return compareComponent(v, w) < 0;
    }

    private void swapShell(DoubleLinkedList<Component> list, int i, int j) {
        Component temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }


    // Quick Sort
    private void quickSort(DoubleLinkedList<Goods> list, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(list, low, high);

            quickSort(list, low, partitionIndex - 1);
            quickSort(list, partitionIndex + 1, high);

        }
    }

    private int partition(DoubleLinkedList<Goods> list, int low, int high) {
        Goods pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compareGoods(list.get(j), pivot) <= 0) {
                i++;
                swap(list, i, j);
            }
        }

        swap(list, i + 1, high);
        return i + 1;
    }

    private void swap(DoubleLinkedList<Goods> list, int i, int j) {
        Goods temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    // Helper method to compare objects
    private int compareGoods(Goods obj1, Goods obj2) {
        return obj1.getName().compareToIgnoreCase(obj2.getName());
    }
    private int compareComponent(Component obj1, Component obj2) {
        if (obj1.getCaloriesPer100g() < obj2.getCaloriesPer100g()) {
            return -1;
        } else if (obj1.getCaloriesPer100g() > obj2.getCaloriesPer100g()) {
            return 1;
        } else {
            return 0;
        }
    }
}

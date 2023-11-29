package test;

import entity.Component;
import entity.Goods;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import service.SearchAndSort;
import tool.DoubleLinkedList;

public class TestShellSort {
    DoubleLinkedList<Goods> list1 = new DoubleLinkedList<>();
    DoubleLinkedList<Component> list2 = new DoubleLinkedList<>();
    @Before
    public  void setup(){
       Component component1 = new Component();
       component1.setCaloriesPer100g(100);
       component1.setName("A");
       list2.add(component1);
       Component component2 = new Component();
       component2.setCaloriesPer100g(0.1);
       component2.setName("B");
       list2.add(component2);
       Component component3 = new Component();
       component3.setCaloriesPer100g(3);
       component3.setName("C");
       list2.add(component3);
       Component component4 = new Component();
       component4.setCaloriesPer100g(300);
       component4.setName("D");
       list2.add(component4);
       Component component5 = new Component();
       component5.setCaloriesPer100g(60);
       component5.setName("E");
       list2.add(component5);

    }

    @After
    public void tearDown() {
        list1 = null;
        list2 = null;
    }

    @Test
    public void testShellSort(){
        SearchAndSort a = new SearchAndSort(list1,list2);
        a.sortComponentsByCalories();
        for(int i = 0; i < list2.size();i++){
            System.out.println(list2.get(i).getName());
            System.out.println(list2.get(i).getCaloriesPer100g());
        }
    }
}

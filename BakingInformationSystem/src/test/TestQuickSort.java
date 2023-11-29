package test;

import entity.Component;
import entity.Goods;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import service.SearchAndSort;
import tool.DoubleLinkedList;

public class TestQuickSort {

    DoubleLinkedList<Goods> list1 = new DoubleLinkedList<>();
    DoubleLinkedList<Component> list2 = new DoubleLinkedList<>();
    @Before
    public  void setup(){
        Goods good1 = new Goods();
        good1.setName("B");
        list1.add(good1);
        Goods good2 = new Goods();
        good2.setName("A");
        list1.add(good2);
        Goods good3 = new Goods();
        good3.setName("D");
        list1.add(good3);
        Goods good4 = new Goods();
        good4.setName("C");
        list1.add(good4);

    }

    @After
    public void tearDown() {
        list1 = null;
        list2 = null;
    }

    @Test
    public void testQuickSort(){
        SearchAndSort a = new SearchAndSort(list1,list2);
        a.sortBakedGoodsAlphabetically();
        for(int i = 0; i < list1.size();i++){
            System.out.println(list1.get(i).getName());
        }
    }

}

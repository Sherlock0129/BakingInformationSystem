package test;

import entity.*;
import service.*;
import tool.*;
import static org.junit.Assert.*;
import org.junit.*;

public class TestDoubleLinkedList {
    public DoubleLinkedList<String> test;

    @Before
    public void setup(){
        test = new DoubleLinkedList<>();
        test.add("A");
        test.add("B");
        test.add("C");
    }

    @After
    public void tearDown(){
        test = null;
    }

    @Test
    public void testAdd(){
        int size = test.size();
        test.add("D");
        assertEquals(test.size(),size + 1);
    }

    @Test
    public void testRemove(){
        int size = test.size();
        test.remove("A");
        assertEquals(test.size(),size - 1);
    }

    @Test
    public void testGet(){
        assertEquals(test.get(1),"B");
    }

}

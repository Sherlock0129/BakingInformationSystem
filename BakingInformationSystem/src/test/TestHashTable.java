package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tool.HashTable;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestHashTable {
    private HashTable<Integer, String> test;

    @Before
    public void setUp() {
        test = new HashTable<>();
        test.put(1, "Recipe 1");
        test.put(2, "Recipe 2");
        test.put(3, "Recipe 3");
        test.put(4, "Recipe 4");
    }

    @After
    public void tearDown() {
        test =  null;
    }

    @Test
    public void testPut() {
        test.put(5,"Recipe 5");
        assertEquals("Recipe",test.get(5));
    }

    @Test
    public void testGet() {
        assertEquals("Recipe",test.get(5));
        assertNull(test.get(99));
    }

    @Test
    public void testRemove(){
        assertNotNull(test.get(4));
        test.remove(4);
        assertNull(test.get(4));
    }

    @Test
    public void testSize(){
        assertEquals(test.size(),4);
        test.put(6, "Recipe 6");
        assertEquals(test.size(),5);
    }

    @Test
    public void testGetKeySet(){
        int[] keys = {1, 2, 3, 4};
        int count = 0;
        for(int x : test.keySet())
            assertEquals(x, keys[count++]);
        assertEquals(count, keys.length);
    }
}

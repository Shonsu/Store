package pl.shonsu;



import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class ItemTest {
    @Test
    public void twoItemsWIthTheSamePriceAndNameShouldBeEqual() {
        assertEquals(new Item("item", 123.12), new Item("item", 123.12));
    }

    @Test
    public void itemsWithDifferentNamesArentEaual() {
        assertNotEquals(new Item("item1", 123.12), new Item("item2", 123.12));
    }

    @Test
    public void itemsWithDifferentPricesArentEqal(){
        assertNotEquals(new Item("item1", 123.00), new Item("item1", 123.12));
    }

    @Test
    public void itemsWithTheSameNameShouldHaveTheSameHashCode(){
        assertEquals(new Item("item1", 123.00).hashCode(), new Item("item1", 123.12).hashCode());
    }

    @Test
    public void itemsWithDifferentNameShouldHaveDifferentHashcode(){
        assertNotEquals(new Item("item1", 123.00).hashCode(), new Item("item2", 123.00).hashCode());
    }

    @Test
    public void itemsWithDifferentPriceAndTheSameNameShouldBeOrdered(){
        assertEquals(-1, new Item("item1", 0.3).compareTo(new Item("item1", 0.2)));
    }
}
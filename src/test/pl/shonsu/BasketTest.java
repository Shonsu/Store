package pl.shonsu;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BasketTest {
    private static final double PRICE_DELTA = 0.001;

    private static Basket basket;
    private static Item toy;

    @BeforeEach
    void setUp() {
        basket = new Basket();
        toy = new Item("toy", 50.55);
    }

    @Test
    void shouldAllowAddItemToBasket() {
        basket.addItem(toy);
        Map<Item, Integer> expected = createOrder(toy, 1);
        assertEquals(expected, basket.getOrder());
    }

    @Test
    void shouldAllowToAddTheSameItemTwice() {
        basket.addItem(toy, 1);
        basket.addItem(toy, 2);
        Map<Item, Integer> expected = createOrder(toy, 3);
        assertEquals(expected, basket.getOrder());
    }

    @Test
    void shouldntAllowToAddItemWithQuantityZero() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> basket.addItem(toy, 0)
        );
        assertEquals("Number of items must be greater then 0", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenAddItemWithNegativeQuantity() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> basket.addItem(toy, -10)
        );
        assertEquals("Number of items must be greater then 0", exception.getMessage());
    }

    @Test
    void shouldAllowToRemoveItemFromBasket() {
        basket.addItem(toy, 5);
        basket.removeItem(toy, 3);
        Map<Item, Integer> order = createOrder(toy, 2);
        assertEquals(order, basket.getOrder());
    }

    @Test
    void shouldThromExceptionWhenRemoveMoreItemsFromBasketThanThereIs() {
        basket.addItem(toy, 3);
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> basket.removeItem(toy, 5)
        );
        assertEquals("You cannot subtract more than the number of item in the basket", exception.getMessage());
    }

    @Test
    void shouldRemoveAllItemsFromBasket() {
        basket.addItem(toy, 5);
        basket.removeItem(toy, 5);
        Map<Item, Integer> order = Collections.emptyMap();
        assertEquals(order, basket.getOrder());
    }

    @Test
    void shouldComputeSimpleOrderValue() {
        basket.addItem(toy, 3);
        double expectedValue = toy.getPrice() * 3;
        assertEquals(expectedValue, basket.getBasketValue(), PRICE_DELTA);
    }

    @Test
    void shouldTrowExcptionWhenRemoving0Items() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> basket.removeItem(toy, 0)
        );
        assertEquals("Number of items must be greater then 0", exception.getMessage());
    }

    @Test
    public void shouldPrintSimpleOrder() {

        basket.addItem(toy,3);
        StringBuilder expected = new StringBuilder();
        expected.append(String.format(Basket.ITEM_ORDER_FORMAT,toy.getName(), toy.getPrice(),3, toy.getPrice()*3));
        expected.append(System.lineSeparator());
        expected.append(String.format("Total: %.2f", 3*50.55D));
        assertEquals(expected.toString(), basket.toString());
    }
    @RepeatedTest(3)
    public void shouldDifferentiateBetweenToysWithSameNameDifferentPrice() {
        basket.addItem(new Item("toy1", 10D));
        basket.addItem(new Item("toy1", 20D));
        StringBuilder expected = new StringBuilder();
        expected.append(String.format(Basket.ITEM_ORDER_FORMAT,"toy1", 10D, 1, 10D));
        expected.append(System.lineSeparator());
        expected.append(String.format(Basket.ITEM_ORDER_FORMAT,"toy1", 20D, 1, 20D));
        expected.append(System.lineSeparator());
        expected.append(String.format("Total: %.2f", 30D));

        assertEquals(expected.toString(),basket.toString());

    }
    private static Map<Item, Integer> createOrder(Object... mapContent) {
        Map<Item, Integer> result = new HashMap<>();

        for (int index = 0; index < mapContent.length; index += 2) {
            Item item = (Item) mapContent[index];
            Integer quantity = (Integer) mapContent[index + 1];
            result.put(item, quantity);
        }

        return result;
    }

}
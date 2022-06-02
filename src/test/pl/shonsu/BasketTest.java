package pl.shonsu;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BasketTest {
    private static final double PRICE_DELTA = 0.001;

    private static Basket basket;
    private static Item toy;

    @BeforeAll
    static void setUp() {
        basket = new Basket();
        toy = new Item("Ball", 50.55);
    }

    @Test
    void shouldAllowAddItemToBasket() {
        basket.addItem(toy);
        Map<Item, Integer> expected = createOrder(toy, 1);
        assertEquals(expected, basket.getOrder());
    }

    @Test
    public void shouldntAllowToAddItemWithQuantityZero() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> basket.addItem(toy, 0)
        );
        assertEquals("Number of items must be greater then 0", exception.getMessage());
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
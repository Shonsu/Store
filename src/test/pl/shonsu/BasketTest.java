package pl.shonsu;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

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
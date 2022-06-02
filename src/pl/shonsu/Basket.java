package pl.shonsu;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Basket {
    protected static final String ITEM_ORDER_FORMAT = "%s (%.2f x %d = %.2f)";
    private Double basketValue = (double) 0;
    private final Map<Item, Integer> orderedItems = new HashMap<>();

    public void addItem(Item item) {
        addItem(item, 1);
    }

    public void addItem(Item item, Integer number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Number of items must be greater then 0");
        }

        if (orderedItems.containsKey(item)) {
            number = orderedItems.get(item) + number;
        }
        orderedItems.put(item, number);
        recalculateBasket();
    }

    public void removeItem(Item item) {
        removeItem(item, 1);
    }

    public void removeItem(Item item, Integer number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Number of items must be greater then 0");
        }
        number = orderedItems.get(item) - number;
        if (number == 0) {
            //System.out.println("usuniecie całkowicie");
            orderedItems.remove(item);
        } else if (number < 0) {
            throw new IllegalStateException("You cannot subtract more than the number of item in the basket");
        } else {
            //System.out.println(number);
            orderedItems.put(item, number);
        }
        recalculateBasket();

    }

    public Double getBasketValue() {
        return this.basketValue;
    }

    private void recalculateBasket() {
        basketValue = 0D;
        for (Map.Entry<Item, Integer> m : orderedItems.entrySet()) {

            basketValue += m.getKey().getPrice() * m.getValue();
        }

    }

    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();
        for (Map.Entry<Item, Integer> m : orderedItems.entrySet()) {
            Item item = m.getKey();
            Integer number = m.getValue();
            String itemString = String.format(
                    ITEM_ORDER_FORMAT,
                    item.getName(),
                    item.getPrice(),
                    number,
                    item.getPrice() * number);
            s.append(itemString);
            s.append(System.lineSeparator());
        }
        s.append(String.format("Total: %.2f", basketValue));
        return s.toString();
    }

    public Map<Item, Integer> getOrder() {
        return Collections.unmodifiableMap(orderedItems);
    }
}

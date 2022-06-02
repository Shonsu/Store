package pl.shonsu;

import java.util.Objects;

public class Item implements Comparable<Item> {
    private final String name;
    private final Double price;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || ! (o instanceof Item)) {
            return false;
        }

        Item item = (Item) o;

        if (Double.compare(item.price, price) != 0) {
            return false;
        }
        return name.equals(item.name);
    }

    @Override
    public int hashCode() {
        return 1410*Objects.hash(name);
    }

    public Item(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + "\t" + price;
    }

    @Override
    public int compareTo(Item o) {
        if (o == null) {
            return 1;
        }
        int comparision = this.getName().compareTo(o.getName());

        if (comparision != 0) {
            return comparision;
        }
        return Double.compare(this.getPrice(), o.getPrice())!=0?-1:-1;

    }
}

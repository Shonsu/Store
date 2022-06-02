package pl.shonsu;

import pl.shonsu.Basket;
import pl.shonsu.Item;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

public class Main {
    public static void main(String[] args) {

        List<Item> items = new ArrayList<>();
        items.add(new Item("Rower", 10D));
        items.add(new Item("Piłka", 2D));
        items.add(new Item("Lalka", 10D));
        items.add(new Item("Rakiet", 5D));
        items.add(new Item("Czarna dziura", 20D));
        items.add(new Item("Pulsar", 50D));

        items.forEach(System.out::println);

        Basket basket = new Basket();
        basket.addItem(items.get(0), 2);
//        basket.addItem(items.get(1), 2);
//        basket.addItem(items.get(2), 2);
//        basket.addItem(items.get(3), 1);
//        basket.addItem(items.get(4), 1);
//        basket.addItem(items.get(5), 2);
        System.out.println(basket.toString());

//        basket.removeItem(items.get(0), 2);
//        System.out.println(basket.toString());

//        basket.addItem(items.get(2), 3);
//        System.out.println(basket.toString());
//
//        basket.removeItem(items.get(4), 1);
//        System.out.println(basket.toString());


    }
}
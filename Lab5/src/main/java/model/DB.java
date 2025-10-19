package model;

import java.util.HashMap;
import java.util.Map;

public class DB {
    public static Map<Integer, Item> items = new HashMap<>();

    static {
        items.put(1, new Item(1, "Samsung Galaxy S24", 899.99, 0));
        items.put(2, new Item(2, "Nokia 2021", 249.50, 0));
        items.put(3, new Item(3, "iPhone 15 Pro", 1199.99, 0));
        items.put(4, new Item(4, "Motorola Edge", 599.99, 0));
        items.put(5, new Item(5, "Xiaomi Redmi", 299.99, 0));
    }
}
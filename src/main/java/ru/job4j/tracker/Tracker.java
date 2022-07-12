package ru.job4j.tracker;

import java.util.Arrays;

public class Tracker {
    private final Item[] items = new Item[100];
    private int ids = 1;
    private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
        items[size++] = item;
        return item;
    }

    public Item findById(int id) {
        /* Находим индекс */
        int index = indexOf(id);
        /* Если индекс найден возвращаем item, иначе null */
        return index != -1 ? items[index] : null;
    }

    public Item[] findByName(String key) {
        Item[] result = new Item[size];
        int tempSize = 0;
        for (int i = 0; i < size; i++) {
            Item item = items[i];
            if (key.equals(item.getName())) {
                result[tempSize] = item;
                tempSize++;
            }
        }
        return Arrays.copyOf(result, tempSize);
    }

    public Item[] findAll() {
        Item[] result = new Item[size];
        int tempSize = 0;
        for (int i = 0; i < size; i++) {
            Item item = items[i];
            if (item != null) {
                result[tempSize] = item;
                tempSize++;
            }
        }
        return Arrays.copyOf(result, tempSize);
    }

    private int indexOf(int id) {
        int rsl = -1;
        for (int index = 0; index < size; index++) {
            if (items[index].getId() == id) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

    public boolean replace(int id, Item item) {
        int indexOfReplacement = indexOf(id);

        if (indexOfReplacement == -1) {
            return false;
        }
        item.setId(id);
        items[indexOfReplacement] = item;
        return true;
    }
}
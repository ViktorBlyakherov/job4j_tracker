package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book harryPotter = new Book("Harry Potter", 498);
        Book lolita = new Book("Lolita", 300);
        Book cleanCode = new Book("Clean Code", 10);
        Book java = new Book("Java for professionals", 1200);
        Book[] books = new Book[4];
        books[0] = harryPotter;
        books[1] = lolita;
        books[2] = cleanCode;
        books[3] = java;
        for (int i = 0; i < books.length; i++) {
            System.out.println(books[i].getName() + ", pg. " + books[i].getPage());
        }
        Book tmpBook = books[0];
        books[0] = books[3];
        books[3] = tmpBook;
        for (int i = 0; i < books.length; i++) {
            System.out.println(books[i].getName() + ", pg. " + books[i].getPage());
        }
        for (int i = 0; i < books.length; i++) {
            if ("Clean Code".equals(books[i].getName())) {
                System.out.println(books[i].getName() + ", pg. " + books[i].getPage());
            }
        }
    }
}

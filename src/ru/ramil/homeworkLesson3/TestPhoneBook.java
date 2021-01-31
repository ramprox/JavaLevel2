package ru.ramil.homeworkLesson3;


import ru.ramil.homeworkLesson3.PhoneBook.Contact;
import ru.ramil.homeworkLesson3.PhoneBook.PhoneBook;

import java.util.List;

public class TestPhoneBook {
    public static void main(String[] args) {
        PhoneBook book = new PhoneBook();
        book.add(new Contact("Иванов", "8-999-999-99-99"));
        book.add(new Contact("Иванов", "8-999-999-99-99"));
        book.add(new Contact("Иванов", "8-888-888-88-88"));
        book.add(new Contact("Петров", "8-777-777-77-77"));
        book.add(new Contact("Петров", "8-777-777-77-77"));
        book.add(new Contact("Петров", "8-555-555-55-55"));
        book.add(new Contact("Сидоров", "8-444-444-44-44"));
        System.out.println("Номера по фамилии Иванов:");
        printList(book.get("Иванов"));
        System.out.println("Номера по фамилии Петров:");
        printList(book.get("Петров"));
        System.out.println("Номера по фамилии Сидоров:");
        printList(book.get("Сидоров"));
    }

    private static void printList(List<String> list) {
        for(String item : list) {
            System.out.println(item);
        }
    }
}

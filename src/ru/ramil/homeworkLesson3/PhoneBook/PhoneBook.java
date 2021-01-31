package ru.ramil.homeworkLesson3.PhoneBook;

import java.util.*;

public class PhoneBook {
    private final List<Contact> contacts = new ArrayList<>();

    public boolean add(Contact contact) {
        for(Contact cont : contacts) {
            if(cont.equals(contact)) {
                return false;
            }
        }
        contacts.add(contact);
        return true;
    }

    public List<String> get(String lastName) {
        List<String> result = new ArrayList<>();
        for(Contact contact : contacts) {
            if(lastName.equals(contact.getLastName())) {
                result.add(contact.getPhoneNumber());
            }
        }
        return result;
    }
}

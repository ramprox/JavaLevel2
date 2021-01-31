package ru.ramil.homeworkLesson3.PhoneBook;

import java.util.Objects;

public class Contact {
    private final String lastName;
    private final String phoneNumber;

    public Contact(String lastName, String phoneNumber) {
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return phoneNumber.equals(contact.phoneNumber);
    }

    @Override
    public int hashCode() {
        return phoneNumber.hashCode();
    }
}

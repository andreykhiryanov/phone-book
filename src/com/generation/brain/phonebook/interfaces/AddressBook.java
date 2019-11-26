package com.generation.brain.phonebook.interfaces;

import com.generation.brain.phonebook.objects.Person;

public interface AddressBook {

    void add (Person person);

    void edit (Person person);

    void delete (Person person);

}
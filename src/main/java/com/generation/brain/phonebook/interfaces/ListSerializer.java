package com.generation.brain.phonebook.interfaces;

import com.generation.brain.phonebook.objects.Person;

import java.util.List;

public interface ListSerializer {

    List readList();

    void writeList(List<Person> list);

    void closeInputStream();

    void closeOutputStream();

}
package com.generation.brain.phonebook.interfaces.impls;

import com.generation.brain.phonebook.interfaces.AddressBook;
import com.generation.brain.phonebook.objects.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CollectionAddressBook implements AddressBook {

    private ObservableList<Person> personList = FXCollections.observableArrayList();

    @Override
    public void add(Person person) {
        personList.add(person);
    }

    @Override
    public void edit(Person person) {

    }

    @Override
    public void delete(Person person) {
        personList.remove(person);
    }

    public ObservableList<Person> getPersonList() {
        return personList;
    }

    //----------------------------

    public void fillTestData(){
        personList.add(new Person("Vasya", "Vaskin", "123"));
        personList.add(new Person("Petya", "Petkin", "456"));
        personList.add(new Person("Dasha", "Dashkina", "789"));
        personList.add(new Person("Glasha", "Glashkina", "987"));
        personList.add(new Person("Juchka", "Juchkina", "654"));
        personList.add(new Person("Vnuchka", "Vnuchkina", "321"));
        personList.add(new Person("Dedka", "Repkin", "666"));
    }

}
package com.generation.brain.phonebook.interfaces.impls;

import com.generation.brain.phonebook.interfaces.PhoneBook;
import com.generation.brain.phonebook.objects.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CollectionPhoneBook implements PhoneBook {

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
        personList.add(new Person("Frederick", "Sanger", "(608) 781-2970"));
        personList.add(new Person("Lekisha", "Blythe", "(608) 882-4143"));
        personList.add(new Person("Arnita", "Mathew", "(479) 936-9910"));
        personList.add(new Person("Charlene", "Quam", "(612) 529-1800"));
        personList.add(new Person("Wilhelmina", "Demeo", "(208) 756-6935"));
        personList.add(new Person("Belkis", "Ritchey", "(814) 695-5400"));
        personList.add(new Person("Alexandria", "Grimmett", "(802) 447-3854"));
    }

}
package com.generation.brain.phonebook.objects;

import com.generation.brain.phonebook.interfaces.ListSerializer;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersonSerializer implements ListSerializer {

    private static PersonSerializer ourInstance = new PersonSerializer();

    public static PersonSerializer getSerializer() {
        return ourInstance;
    }

    private PersonSerializer() {
    }

    private File file = new File("C://persons.data");
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    @Override
    public List<Person> readList() {

        List<Person> persons = new ArrayList<>();

        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
        } catch (IOException e) {
            return persons;
        }

        // Reading all persons from the file.
        while (true) {
            try {
                persons.add((Person) objectInputStream.readObject());
            } catch (IOException | ClassNotFoundException e) {
                break;
            }
        }

        return persons;
    }

    @Override
    public void writeList(List<Person> persons) {

        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try {
            for (Person person : persons) {
                objectOutputStream.writeObject(person);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeInputStream() {
        try {
            if (objectInputStream != null) objectInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeOutputStream() {
        try {
            if (objectOutputStream != null) objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
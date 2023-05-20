package org.example;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String filePath = "Animal.csv";
    private static final File file = new File(filePath);

    private static final String[] header = {"Animal", "Name"};


    public static void main(String[] args) {
        List<Animal> catalogue = readData();
        System.out.println(catalogue);
        addData(catalogue, "pig", "Mike");
        addData(catalogue, "snake", "Josh");
        addData(catalogue, "cat", "Liz");
        updateName(catalogue, "pig", "Peppa");
        removeAnimal(catalogue, "snake");
        System.out.println(catalogue);
        dumpData(catalogue);


    }

    public static void dumpData(List<Animal> catalogue) {
        try {
            FileWriter filewriter = new FileWriter(file);

            CSVWriter writer = new CSVWriter(filewriter);

            writer.writeNext(header);
            for (Animal row : catalogue)
                writer.writeNext(row.toArray());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

    public static List<Animal> readData() {
        List<Animal> catalogue = new ArrayList<>();
        try {
            FileReader filereader = new FileReader(file);
            CSVReader reader = new CSVReader(filereader);
            String[] row;
            while ((row = reader.readNext()) != null) {
                catalogue.add(new Animal(row));
            }
        } catch (IOException e) {
            System.out.println("Error while reading csv data");
        }
        if (catalogue.size() > 0)
            catalogue.remove(0);
        return catalogue;


    }

    public static boolean contains(List<Animal> catalogue, String kind) {
        for (Animal animal : catalogue) {

            if (animal.kind.equals(kind))
                return true;
        }
        return false;
    }

    public static void addData(List<Animal> catalogue, String kind, String name) {
        Animal animal = new Animal(kind, name);
        if (contains(catalogue, kind))
            System.out.printf("We already have %s in the catalogue!%n", kind);
        else catalogue.add(animal);

    }

    public static void updateName(List<Animal> catalogue, String kind, String newName) {
        for (Animal animal : catalogue) {
            if (animal.kind.equals(kind)) {
                animal.name = newName;
                System.out.printf("The %s's new name is %s \n", kind, newName);
                break;
            }
        }
    }

    public static void removeAnimal(List<Animal> catalogue, String kind) {
        Animal animal;
        int index = -1;
        for (int i = 0; i < catalogue.size(); i++) {
            animal = catalogue.get(i);
            if (animal.kind.equals(kind)) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            catalogue.remove(index);
            System.out.printf("%s was removed%n", kind);
        }
    }
}
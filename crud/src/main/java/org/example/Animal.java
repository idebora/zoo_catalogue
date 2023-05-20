package org.example;

public class Animal {
    public String kind;
    public String name;

    public Animal(String[] row) {
        this(row[0], row[1]);

    }

    public Animal(String kind, String name) {
        this.kind = kind;
        this.name = name;
    }

    public String[] toArray() {
        return new String[]{kind, name};
    }

    public String toString() {
        return String.format("(%s, %s)", kind, name);
    }

}

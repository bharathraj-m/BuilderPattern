package com.bharath.carbon;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Getter
public class Person {
    private final String name;
    private final Integer age;
    private final String gender;

    //Made private for builder pattern
    private Person(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName(){
        return this.name;
    }
    public Integer getAge(){
        return this.age;
    }

    public String getGender(){
        return this.gender;
    }

    public static class PersonBuilder{
        AtomicReference<Integer> age = new AtomicReference<>();
        AtomicReference<String> name = new AtomicReference<>();
        AtomicReference<String> gender = new AtomicReference<>();

        public PersonBuilder setName(String name) {
            this.name.set(name);  // Atomic set
            return this;
        }

        public PersonBuilder setAge(int age) {
            this.age.set(age);  // Atomic set
            return this;
        }

        public PersonBuilder setGender(String gender) {
            this.gender.set(gender);  // Atomic set
            return this;
        }

        // Build method that returns the constructed object
        public Person build() {

            // Check that all required fields are set
            if (name.get() == null || gender.get() == null || age.get() == null) {
                throw new IllegalStateException("All fields must be set");
            }

            return new Person(name.get(), age.get(), gender.get());
        }
    }
}

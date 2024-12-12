package com.bharath.carbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarbonApplication {

	public static void main(String[] args) throws IllegalArgumentException{

			Person.PersonBuilder builder = new Person.PersonBuilder();

			// Create two threads that modify the builder concurrently

            Thread t2 = new Thread(() -> {
                		builder.setName("Alice");
						builder.setAge(25);
						builder.setGender("Female");
            });

        Thread t1 = new Thread(() -> {
            builder.setName("John");
            builder.setAge(30);
            builder.setGender("Male");
        });


        t1.start(); //run the first thread
        t2.start(); //run the second thread



        try {
                t1.join(); //Make Main thread/current thread wait for thread1.
                t2.join(); //Make Main thread/current thread wait for thread2.


        } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException("Thread interrupted", e);
            }

            // Build and print the final Person object
            Person person = builder.build();
            System.out.println("Person created: " + person.getName() + ", " + person.getAge() + ", " + person.getGender());
//			SpringApplication.run(CarbonApplication.class, args);

	}

}

package org.MiniSurveyMonkey;

import org.MiniSurveyMonkey.Fields.Field;
import org.MiniSurveyMonkey.Fields.FieldType;
import org.MiniSurveyMonkey.Fields.NumberField;
import org.MiniSurveyMonkey.Fields.TextField;
import org.MiniSurveyMonkey.Repositories.FieldRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SurveyMonkeyApplication implements CommandLineRunner {

	@Autowired
	private FieldRepo repository;

	public static void main(String[] args) {
		SpringApplication.run(SurveyMonkeyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		repository.deleteAll();

		repository.save(new TextField("Alice","1"));
		repository.save(new TextField("ditch","1"));
		repository.save(new NumberField("mitch","1", 1, 10));


		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Field customer : repository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();
		System.out.println("Customers found with findByFieldType(FieldType.NUMBER):");
		System.out.println("-------------------------------");
		for (Field customer : repository.findByFieldType(FieldType.NUMBER)) {
			System.out.println(customer);
		}
		System.out.println();


	}

}

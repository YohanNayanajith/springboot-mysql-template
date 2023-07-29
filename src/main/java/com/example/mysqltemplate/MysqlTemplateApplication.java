package com.example.mysqltemplate;

import com.example.mysqltemplate.dao.DAO;
import com.example.mysqltemplate.model.Course;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class MysqlTemplateApplication {
	private static DAO<Course> dao;

	public MysqlTemplateApplication(DAO<Course> dao) {
		this.dao = dao;
	}

	public static void main(String[] args) {
		SpringApplication.run(MysqlTemplateApplication.class, args);

		List<String> list = new ArrayList<>();

		list.add("Yohan");
		list.add("Kasun");
		list.add("Rashmila");

		for(int i = 0; i<list.size(); i++) {
			System.out.println("My name is " + list.get(i));

		}

		for(String name : list){
			System.out.println("My name is "+name);
		}

		list.forEach(name -> {
			System.out.println("My name is "+name);
		});

		List<Course> courses = dao.list();

		courses.forEach(System.out::println);

		System.out.println("------------------------------Course Create---------------------------");
		Course courseNew = new Course("Course6","For the intermediates","https://hi");
//
//		dao.create(courseNew);

		System.out.println("------------------------------Get one course---------------------------");
		Optional<Course> courseOne = dao.get(1);
        courseOne.ifPresent(System.out::println);

		System.out.println(courseOne.get());

		System.out.println("------------------------------Update course---------------------------");

		courseNew.setLink("https://new");
		dao.update(courseNew, 3);

		System.out.println("------------------------------Delete course---------------------------");

//		dao.delete(5);
	}

}

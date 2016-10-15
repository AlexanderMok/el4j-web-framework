package com.el4j.demo.service;

import java.util.ArrayList;
import java.util.List;

import com.el4j.demo.entity.Person;

import el4j.framework.context.annotation.Service;

@Service
public class PersonService
{

	public List<Person> getPersons()
	{
		List<Person> persons = new ArrayList<>();
		Person person = new Person();
		person.setName("张嘉儿");
		person.setTelephone("135611111747");
		person.setEmail("zsan@exmaple.com");
		persons.add(person);
		Person person1 = new Person();
		person1.setName("笛卡尔");
		person1.setTelephone("13581111478");
		person1.setEmail("lsi@exmaple.com");
		persons.add(person1);
		return persons;
	}

}

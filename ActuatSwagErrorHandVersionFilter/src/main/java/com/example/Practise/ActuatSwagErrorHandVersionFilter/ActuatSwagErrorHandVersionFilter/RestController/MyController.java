package com.example.Practise.ActuatSwagErrorHandVersionFilter.ActuatSwagErrorHandVersionFilter.RestController;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Practise.ActuatSwagErrorHandVersionFilter.ActuatSwagErrorHandVersionFilter.Bean.PersonBean;
import com.example.Practise.ActuatSwagErrorHandVersionFilter.ActuatSwagErrorHandVersionFilter.Bean.PersonResponse;
import com.example.Practise.ActuatSwagErrorHandVersionFilter.ActuatSwagErrorHandVersionFilter.Exception.UserNotFoundException;
import com.example.Practise.ActuatSwagErrorHandVersionFilter.ActuatSwagErrorHandVersionFilter.Repository.JpaRepo;

@RestController
public class MyController {

	@Autowired
	JpaRepo repo;

	@GetMapping("/testing")
	public String testing() {
		return "Testing Successfully";
	}

	// counting the value
	long myCount = 0;

	public long counter() {
		myCount = repo.count();
		return myCount;
	}

	@GetMapping("/findAll")
	public List<PersonBean> findAll() {
		List<PersonBean> myList = repo.findAll();
		
		return myList;
	}
	
	@GetMapping("/findById/{id}")
	public PersonResponse findById(@PathVariable int id) {
		PersonBean personBean = repo.findById(id).orElse(null);
		PersonResponse personResponse=new PersonResponse();
		if (personBean == null) {
			throw new UserNotFoundException("Not Found");
		}
		BeanUtils.copyProperties(personBean, personResponse);
		return personResponse;
	}

	@GetMapping("/findByLocation/{location}")
	public List<PersonBean> findByLocation(@PathVariable String location) {
		List<PersonBean> myList = repo.findAllByLocation(location);
		return myList;
	}

	@GetMapping("/findName/{name}/{location}")
	public List<PersonBean> findByNameLocation(@PathVariable String name, @PathVariable String location) {
		List<PersonBean> myList = repo.findByNameAndLocation(name, location);
		return myList;
	}

//	@PostMapping("/addPerson")
//	public String addPerson(@RequestBody PersonBean person) {
//		if(findById(person.getId())!=null) {
//			return "Person already exists";
//		}
//		repo.save(person);
//		return "Successfully Created";
//	}

	@PostMapping("/add")
	public String addPerson(@RequestBody PersonBean personBean) {
//		int id=(int) counter();
//		System.out.println((int) counter());
//		personBean.setId(id+1);
		repo.save(personBean);
		return personBean.getName() + " successfully added";
	}

	@PostMapping("/updatePerson")
	public String updatePerson(@RequestBody PersonBean person) {
		if (findById(person.getId()) != null) {
			repo.save(person);
			return "Person adetails updated";
		}

		return "Person does'nt exists";
	}

	@DeleteMapping("/deletePerson/{id}")
	public String deletePerson(@PathVariable int id) {
		repo.deleteById(id);
		return "Deleted successfully";
	}
}

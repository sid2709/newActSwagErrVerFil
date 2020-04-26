package com.example.Practise.ActuatSwagErrorHandVersionFilter.ActuatSwagErrorHandVersionFilter.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Practise.ActuatSwagErrorHandVersionFilter.ActuatSwagErrorHandVersionFilter.Bean.PersonBean;


public interface JpaRepo extends JpaRepository<PersonBean, Integer> {

	List<PersonBean> findAllByLocation(String location);

	@Query("from PersonBean where name=?1 and location=?2")
	List<PersonBean> findByNameAndLocation(String name, String location);




}

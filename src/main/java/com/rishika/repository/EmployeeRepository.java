package com.rishika.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event.ID;

import com.rishika.entity.EmployeeList;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeList,Long>
{
	
}

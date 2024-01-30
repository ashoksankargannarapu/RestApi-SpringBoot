package com.nt.service;

import java.util.List;

import com.nt.entity.Person;
import com.nt.entity.PhoneNumber;
import com.nt.exception.TouristNotFoundException;

public interface IOToMAssociationMgmtService {
	
	public Person saveDataUsingParent(Person person);
	public List<Person> fetchAllPersons();
	public Person fetchPersonById(Integer pid) throws TouristNotFoundException;
	public String updatePersonDetails(Person person) throws TouristNotFoundException;
	public String removePersonById(int id)throws TouristNotFoundException;
	public String deleteAllChildsOfAParentById(int id)throws TouristNotFoundException;
	public String addNewChildToAParentById(int id,PhoneNumber ph)throws TouristNotFoundException;
	

}

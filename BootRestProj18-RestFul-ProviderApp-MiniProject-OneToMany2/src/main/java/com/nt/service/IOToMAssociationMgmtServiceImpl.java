package com.nt.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.Person;
import com.nt.entity.PhoneNumber;
import com.nt.exception.TouristNotFoundException;
import com.nt.repository.IPersonRepository;
import com.nt.repository.IPhoneNumberRepository;



@Service("touristService")
public class IOToMAssociationMgmtServiceImpl implements IOToMAssociationMgmtService {

	@Autowired
	private IPersonRepository personRepo;
	
	@Autowired
	private IPhoneNumberRepository phoneRepo;

	@Override
	public Person saveDataUsingParent(Person person) {
        PhoneNumber phone=new PhoneNumber();
		phone.setPersonInfo(person);
		//add child to parent
		Set<PhoneNumber> phoneset=new HashSet();
		phoneset.add(phone);
		person.setContactDetails(phoneset);		
		//save the parent object
		return personRepo.save(person);
		
	}

	@Override
	public List<Person> fetchAllPersons() {
		List<Person> list = personRepo.findAll();
		list.sort((t1, t2) -> t1.getPid().compareTo(t2.getPid()));
		 list.forEach(per->{
			Set<PhoneNumber> childs=per.getContactDetails();
		});
		 return list;
		
	}
	@Override
	public Person fetchPersonById(Integer pid) throws TouristNotFoundException {
		return personRepo.findById(pid)
				.orElseThrow(()-> new TouristNotFoundException(pid + ": tourist is not found"));
	}
	
	@Override
	public String updatePersonDetails(Person person) throws TouristNotFoundException {
		Optional<Person> optional = personRepo.findById(person.getPid());
		if (optional.isPresent()) {
			personRepo.save(person);// save(-)performs either save obj or update obj creation
			return person.getPid() + "Person is updated";
		} else {
			throw new TouristNotFoundException(person.getPid() + " Person is not found");
		}
	}
	
	@Override
	public String removePersonById(int id) throws TouristNotFoundException {
		Optional<Person> optional = personRepo.findById(id);
		if (optional.isPresent()) {
			// use Repo
			personRepo.deleteById(id);
			return id + ":pid Person found and deleted";
		} else {
			throw new TouristNotFoundException(id + ":tid  Person is not found for deletion");
		}
	}
	
	@Override
	public String deleteAllChildsOfAParentById(int id) throws TouristNotFoundException {
		Optional<Person> opt=personRepo.findById(id);
		if(opt.isPresent()) {
			Person per=opt.get();
			
			//get all childs of a person
			Set<PhoneNumber> childs=per.getContactDetails();
			//remove parent link from childs
			childs.forEach(ph->{
				per.setContactDetails(null);
				
			});
			//delete all child objects
			phoneRepo.deleteAll(childs);
			return id + ":pid Person found and deleted";
		} else {
			throw new TouristNotFoundException(id + ":tid  Person is not found for deletion");
		}
	}
	@Override
	public String addNewChildToAParentById(int id,PhoneNumber ph) throws TouristNotFoundException {
		//Load parent object
				Optional<Person> opt=personRepo.findById(id);
				if(opt.isPresent()) {
					Person per=opt.get();
					//get childs of a parent
					Set<PhoneNumber> childs=per.getContactDetails();
					childs.add(null);
					
					per.setContactDetails(childs);
					childs.add(ph);
					phoneRepo.save(ph);
					return id + ":pid Person found and Added";
				} else {
					throw new TouristNotFoundException(id + ":tid  Person is not found for Adding child ");
				}
	}

	

}

package com.nt.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.Player;
import com.nt.entity.Team;
import com.nt.repository.ITeamRepository;
import com.nt.service.IOToMAssociationMgmtService;


@RestController
@RequestMapping("/AssociationMapping")
public class AssociationMappingOperationsController {

	@Autowired
	private IOToMAssociationMgmtService service;
	
	@Autowired
	private ITeamRepository teamRepo;

	@PostMapping("/register")
	public ResponseEntity<String> enrollTeam(@RequestBody Team team) {
		try {
			// use service
			String resultMsg = service.saveDataUsingParent(team);
			return new ResponseEntity<String>(resultMsg, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Problem in Teamd enrollment", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<?> getAllTeams() {
		try {
			List<Team> list = service.fetchAllTeams();
			return new ResponseEntity<List<Team>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Problem in To get Team details", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/find/{id}")
	public ResponseEntity<?> getTeamById(@PathVariable("id") Integer id) {
		try {
			Team team = service.fetchTeamById(id);
			return new ResponseEntity<Team>(team, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/modify")
	public ResponseEntity<String> modifyTeam(@RequestBody Team team) {
		try {
			// use service
			String resultMsg = service.updateTeamDetails(team);
			return new ResponseEntity<String>(resultMsg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteTeamById(@PathVariable("id") int id) {
		try {
            //use service
			String resultMsg = service.removeTeamById(id);
			return new ResponseEntity<String>(resultMsg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("/delete/parent/{id}")
	public ResponseEntity<String> deleteTeamChildsByParentId(@PathVariable("id") int id) {
		try {
	        //use service
			String resultMsg = service.deleteAllChildsOfAParentById(id);
			return new ResponseEntity<String>(resultMsg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/add/parent/{id}")
	public ResponseEntity<String> addTeamChildsByParentId(@PathVariable("id") int id,@RequestBody Player ph) {
		try {
	        //use service
			String resultMsg = service.addNewChildToAParentById(id, ph);
			return new ResponseEntity<String>(resultMsg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

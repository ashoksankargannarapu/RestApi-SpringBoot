package com.nt.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.Tourist;
import com.nt.repository.ITouristRepo;
import com.nt.service.ITouristMgmtService;

@RestController
@RequestMapping("/tourist")
@CrossOrigin(origins = "http://localhost:58130")
public class TouristOperationsController {

	@Autowired
	private ITouristMgmtService service;
	
	@Autowired
	private ITouristRepo touristRepo;

	@PostMapping("/register")
	
	public ResponseEntity<String> enrollTourist(@RequestBody Tourist tourist) {
		try {
			// use service
			String resultMsg = service.registerTourist(tourist);
			return new ResponseEntity<String>(resultMsg, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Problem in tourist enrollment", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<?> getAllTourists() {
		try {
			List<Tourist> list = service.fetchAllTourists();
			return new ResponseEntity<List<Tourist>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Problem in tourist enrollment", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<?> getTouristById(@PathVariable("id") Integer id) {
		try {
			Tourist tourist = service.fetchTouristById(id);
			return new ResponseEntity<Tourist>(tourist, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/findWithName/{name}")
	public ResponseEntity<?> getTouristByName(@PathVariable("name") String name) {
		try {
			List<Tourist> tourist = touristRepo.findByNameContainingIgnoreCase(name);
			return new ResponseEntity<List<Tourist>>(tourist, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/modify")
	public ResponseEntity<String> modifyTourist(@RequestBody Tourist tourist) {
		try {
			// use service
			String resultMsg = service.updateTouristDetails(tourist);
			return new ResponseEntity<String>(resultMsg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PatchMapping("/pupdate/{id}/{percentage}")
	public ResponseEntity<String> modifyTouristBudgetById(@PathVariable("id") int id,
			@PathVariable("percentage") double percentage) {
		try {
			// use service
			String resultMsg = service.updateTouristBudgetById(id, percentage);
			return new ResponseEntity<String>(resultMsg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteTouristById(@PathVariable("id") int id) {
		try {
            //use service
			String resultMsg = service.removeTouristById(id);
			return new ResponseEntity<String>(resultMsg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete/{start}/{end}")
	public ResponseEntity<String> deleteTouristsByBudgetRange(@PathVariable("start") double start,
			@PathVariable("end") double end) {
		try {
			// use service
			String resultMsg = service.removeTouristsByBudgetRange(start, end);
			return new ResponseEntity<String>(resultMsg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

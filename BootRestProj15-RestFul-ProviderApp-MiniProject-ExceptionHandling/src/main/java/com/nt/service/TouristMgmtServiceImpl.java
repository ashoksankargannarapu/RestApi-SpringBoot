package com.nt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.Tourist;
import com.nt.exception.TouristNotFoundException;
import com.nt.repository.ITouristRepo;

@Service("touristService")
public class TouristMgmtServiceImpl implements ITouristMgmtService {

	@Autowired
	private ITouristRepo touristRepo;

	@Override
	public String registerTourist(Tourist tourist) {
		int idVal = touristRepo.save(tourist).getTid();
		return "Tourist is registered having id value::" + idVal;
	}

	@Override
	public List<Tourist> fetchAllTourists() {
		List<Tourist> list = touristRepo.findAll();
		list.sort((t1, t2) -> t1.getTid().compareTo(t2.getTid()));
		return list;
	}

	@Override
	public Tourist fetchTouristById(Integer tid) throws TouristNotFoundException {
		return touristRepo.findById(tid)
				.orElseThrow(() -> new TouristNotFoundException(tid + ": tourist is not found"));
	}

	@Override
	public List<Tourist> findAllTouristsByName(String name) {
		return touristRepo.findByNameContainingIgnoreCase(name);
	}

	@Override
	public String updateTouristDetails(Tourist tourist) throws TouristNotFoundException {
		Optional<Tourist> optional = touristRepo.findById(tourist.getTid());
		if (optional.isPresent()) {
			touristRepo.save(tourist);// save(-)performs either save obj or update obj creation
			return tourist.getTid() + "Tourist is updated";
		} else {
			throw new TouristNotFoundException(tourist.getTid() + " Tourist is not found");
		}
	}

	@Override
	public String updateTouristBudgetById(int tid, double hikePercentage) throws TouristNotFoundException {
		Optional<Tourist> optional = touristRepo.findById(tid);
		if (optional.isPresent()) {
			// get existing budget for the tourist
			Tourist tourist = optional.get();
			double budget = tourist.getBudget();
			double newBudget = budget + (budget * hikePercentage / 100.0);
			// update tourist with new budget
			tourist.setBudget(newBudget);
			touristRepo.save(tourist);
			return "Tourist budget is hiked...and the new budget is:" + newBudget;
		} else {
			throw new TouristNotFoundException(tid + " Tourist is not found");
		}

	}

	@Override
	public String removeTouristById(int id) throws TouristNotFoundException {
		Optional<Tourist> optional = touristRepo.findById(id);
		if (optional.isPresent()) {
			// use Repo
			touristRepo.deleteById(id);
			return id + ":tid Tourist found and deleted";
		} else {
			throw new TouristNotFoundException(id + ":tid  Tourist is not found for deletion");
		}
	}

	@Override
	public String removeTouristsByBudgetRange(double start, double end) {
		// use Repo
		int count = touristRepo.removeTouristsByBudgetRange(start, end);
		return count == 0 ? " Tourist not found for deletion" : count + " no.of Tourists are found and deleted";
	}

}

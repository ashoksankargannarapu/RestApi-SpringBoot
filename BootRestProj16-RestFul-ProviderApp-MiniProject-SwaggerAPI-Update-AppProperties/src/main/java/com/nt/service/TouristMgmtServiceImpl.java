package com.nt.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nt.constants.AppConstants;
import com.nt.entity.Tourist;
import com.nt.exception.TouristNotFoundException;
import com.nt.props.AppProperties;
import com.nt.repository.ITouristRepo;

@Service("touristService")
public class TouristMgmtServiceImpl implements ITouristMgmtService {

	
	private ITouristRepo touristRepo;
	
	private Map<String, String> messages;
	
	
	
	
	// construction injection
		public TouristMgmtServiceImpl(ITouristRepo touristRepo, AppProperties appProps) {
			this.touristRepo=touristRepo;
			this.messages=appProps.getMessages();
			System.out.println(this.messages);
		}

	@Override
	public String registerTourist(Tourist tourist) {
		
		int idVal = touristRepo.save(tourist).getTid();
		return messages.get(AppConstants.TOURIST_SAVE_SUCC) + idVal;
	}

	@Override
	public List<Tourist> fetchAllTourists() {
		
		List<Tourist> list = touristRepo.findAll();
		list.sort((t1, t2) -> t1.getTid().compareTo(t2.getTid()));
		return list;
	}

	@Override
	public Tourist fetchTouristById(Integer tid) throws TouristNotFoundException {
		String msg="";
		return touristRepo.findById(tid)
				.orElseThrow(() -> new TouristNotFoundException(tid + messages.get("fetchTouristIdFail")));
	}

	@Override
	public List<Tourist> findAllTouristsByName(String name) {
		return touristRepo.findByNameContainingIgnoreCase(name);
	}

	@Override
	public String updateTouristDetails(Tourist tourist) throws TouristNotFoundException {
		String msg="";
		Optional<Tourist> optional = touristRepo.findById(tourist.getTid());
		if (optional.isPresent()) {
			touristRepo.save(tourist);// save(-)performs either save obj or update obj creation
			return tourist.getTid() + messages.get("upadateTouristSucc");
		} else {
			throw new TouristNotFoundException(tourist.getTid() + messages.get("upadateTouristFail"));
		}
	}

	@Override
	public String updateTouristBudgetById(int tid, double hikePercentage) throws TouristNotFoundException {
		String msg="";
		Optional<Tourist> optional = touristRepo.findById(tid);
		if (optional.isPresent()) {
			// get existing budget for the tourist
			Tourist tourist = optional.get();
			double budget = tourist.getBudget();
			double newBudget = budget + (budget * hikePercentage / 100.0);
			// update tourist with new budget
			tourist.setBudget(newBudget);
			touristRepo.save(tourist);
			return messages.get("updateTouristBudgetSucc")+ newBudget;
		} else {
			throw new TouristNotFoundException(tid + messages.get("updateTouristBudgetFail"));
		}

	}

	@Override
	public String removeTouristById(int id) throws TouristNotFoundException {
		String msg="";
		Optional<Tourist> optional = touristRepo.findById(id);
		if (optional.isPresent()) {
			// use Repo
			touristRepo.deleteById(id);
			return id + messages.get("touristDeleteSucc");
		} else {
			throw new TouristNotFoundException(id + messages.get("touristDeleteFail"));
		}
	}

	@Override
	public String removeTouristsByBudgetRange(double start, double end) {
		// use Repo
		String msg="";
		int count = touristRepo.removeTouristsByBudgetRange(start, end);
		return count == 0 ? messages.get("touristBudgetDeleteFail") : count + messages.get("touristBudgetDeleteSucc");
	}

}

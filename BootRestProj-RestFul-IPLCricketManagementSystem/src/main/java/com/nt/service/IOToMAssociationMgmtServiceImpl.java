package com.nt.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.Player;
import com.nt.entity.Team;
import com.nt.exception.PlayerNotFoundException;
import com.nt.repository.IPlayerRepository;
import com.nt.repository.ITeamRepository;




@Service("IPLService")
public class IOToMAssociationMgmtServiceImpl implements IOToMAssociationMgmtService {

	@Autowired
	private ITeamRepository teamRepo;
	
	@Autowired
	private IPlayerRepository playerRepo;

	@Override
	public String saveDataUsingParent(Team team) {

		
		  int idVal = teamRepo.save(team).getTid(); 
		  return "Team is registered having id value::" + idVal;
		 
		
		
	}

	@Override
	public List<Team> fetchAllTeams() {
		List<Team> list = teamRepo.findAll();
		list.sort((t1, t2) -> t1.getTid().compareTo(t2.getTid()));
		 list.forEach(per->{
			List<Player> childs=per.getPlayerDetails();
		});
		 return list;
		
	}
	@Override
	public Team fetchTeamById(Integer tid) throws PlayerNotFoundException {
		return teamRepo.findById(tid)
				.orElseThrow(()-> new PlayerNotFoundException(tid + ": tourist is not found"));
	}
	
	@Override
	public String updateTeamDetails(Team team) throws PlayerNotFoundException {
		Optional<Team> optional = teamRepo.findById(team.getTid());
		if (optional.isPresent()) {
			teamRepo.save(team);// save(-)performs either save obj or update obj creation
			return team.getTid() + "Team is updated";
		} else {
			throw new PlayerNotFoundException(team.getTid() + " Team is not found");
		}
	}
	
	@Override
	public String removeTeamById(int id) throws PlayerNotFoundException {
		Optional<Team> optional = teamRepo.findById(id);
		if (optional.isPresent()) {
			// use Repo
			teamRepo.deleteById(id);
			return id + ":tid Person found and deleted";
		} else {
			throw new PlayerNotFoundException(id + ":tid  Person is not found for deletion");
		}
	}
	
	@Override
	public String deleteAllChildsOfAParentById(int id) throws PlayerNotFoundException {
		Optional<Team> opt=teamRepo.findById(id);
		if(opt.isPresent()) {
			Team per=opt.get();
			
			//get all childs of a person
			List<Player> childs=per.getPlayerDetails();
			//remove parent link from childs
			childs.forEach(ph->{
				per.setPlayerDetails(null);
				
			});
			//delete all child objects
			playerRepo.deleteAll(childs);
			return id + ":pid Person found and deleted";
		} else {
			throw new PlayerNotFoundException(id + ":tid  Person is not found for deletion");
		}
	}
	@Override
	public String addNewChildToAParentById(int id,Player ph) throws PlayerNotFoundException {
		//Load parent object
				Optional<Team> opt=teamRepo.findById(id);
				if(opt.isPresent()) {
					Team per=opt.get();
					//get childs of a parent
					List<Player> childs=per.getPlayerDetails();
					childs.add(null);
					
					per.setPlayerDetails(childs);
					childs.add(ph);
					playerRepo.save(ph);
					return id + ":pid Person found and Added";
				} else {
					throw new PlayerNotFoundException(id + ":tid  Team is not found for Adding child ");
				}
	}

	

}

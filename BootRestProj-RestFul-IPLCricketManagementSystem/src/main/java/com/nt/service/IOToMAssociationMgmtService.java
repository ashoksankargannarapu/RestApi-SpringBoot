package com.nt.service;

import java.util.List;

import com.nt.entity.Player;
import com.nt.entity.Team;
import com.nt.exception.PlayerNotFoundException;


public interface IOToMAssociationMgmtService {
	
	public String saveDataUsingParent(Team team);
	public List<Team> fetchAllTeams();
	public Team fetchTeamById(Integer tid) throws PlayerNotFoundException;
	public String updateTeamDetails(Team team) throws PlayerNotFoundException;
	public String removeTeamById(int id)throws PlayerNotFoundException;
	public String deleteAllChildsOfAParentById(int id)throws PlayerNotFoundException;
	public String addNewChildToAParentById(int id,Player ph)throws PlayerNotFoundException;
	

}

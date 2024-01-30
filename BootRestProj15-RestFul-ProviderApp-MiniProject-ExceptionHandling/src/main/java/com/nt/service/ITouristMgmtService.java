package com.nt.service;

import java.util.List;

import com.nt.entity.Tourist;
import com.nt.exception.TouristNotFoundException;

public interface ITouristMgmtService {
	public String registerTourist(Tourist tourist);
	public List<Tourist> fetchAllTourists();
	public Tourist fetchTouristById(Integer tid)throws TouristNotFoundException;
	public List<Tourist> findAllTouristsByName(String name);
	public String updateTouristDetails(Tourist tourist)throws TouristNotFoundException;
	public String updateTouristBudgetById(int tid,double percentage)throws TouristNotFoundException;
	public String removeTouristById(int id)throws TouristNotFoundException;
	public String removeTouristsByBudgetRange(double start,double end);
	

}

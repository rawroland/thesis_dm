package me.rolandawemo.dao;

import java.util.ArrayList;

import me.rolandawemo.dao.model.ReportingGroup;

public interface IReportingGroupDAO {

	/**
	 * 
	 * @param name
	 * @param members
	 * @return
	 */
	int create(String name, ArrayList<Integer> members);
	
	ArrayList<ReportingGroup> getAll();
	
	ReportingGroup getById(int id);
}

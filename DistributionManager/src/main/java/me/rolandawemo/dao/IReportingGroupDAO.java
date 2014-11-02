package me.rolandawemo.dao;

import java.util.ArrayList;

public interface IReportingGroupDAO {

	/**
	 * 
	 * @param name
	 * @param members
	 * @return
	 */
	int create(int name, ArrayList<Integer> members);
}

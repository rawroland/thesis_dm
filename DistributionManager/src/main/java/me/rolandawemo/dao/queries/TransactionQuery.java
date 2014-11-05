package me.rolandawemo.dao.queries;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="TransactionQuery")
public class TransactionQuery {

	private ArrayList<Integer> accounts;
	private int reportingGroupId;
	private String fromDate;
	private String toDate;
	private String type;
	
	public TransactionQuery() {
		super();
	}

	public ArrayList<Integer> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<Integer> accounts) {
		this.accounts = accounts;
	}

	public int getReportingGroupId() {
		return reportingGroupId;
	}

	public void setReportingGroupId(int reportingGroupId) {
		this.reportingGroupId = reportingGroupId;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}

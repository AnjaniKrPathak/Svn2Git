/**
 * 
 */
package com.clinakos.data.model.core;

import java.util.Date;
import java.util.List;

/**
 * @author IDC-0004
 *
 */
public class PracticeSearchModel {
	
	private String diagnosis;//Either diognosis name or diagnosis id
	private String drugs;//Either DrugId or Drug Name
	private String lab;//Either lab name or Lab code
	
	private Date fromDateSearch;
	private Date toDateSearch;
	
	private List<PracticeSearchResultSet> practiceSearchResultSet;
	
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getDrugs() {
		return drugs;
	}
	public void setDrugs(String drugs) {
		this.drugs = drugs;
	}
	
	

	public List<PracticeSearchResultSet> getPracticeSearchResultSet() {
		return practiceSearchResultSet;
	}
	public void setPracticeSearchResultSet(
			List<PracticeSearchResultSet> practiceSearchResultSet) {
		this.practiceSearchResultSet = practiceSearchResultSet;
	}
	public String getLab() {
		return lab;
	}
	public void setLab(String lab) {
		this.lab = lab;
	}
	
	
	

	public Date getFromDateSearch() {
		return fromDateSearch;
	}
	public void setFromDateSearch(Date fromDateSearch) {
		this.fromDateSearch = fromDateSearch;
	}
	public Date getToDateSearch() {
		return toDateSearch;
	}
	public void setToDateSearch(Date toDateSearch) {
		this.toDateSearch = toDateSearch;
	}
	@Override
	public String toString() {
		return "PracticeSearchModel [diagnosis=" + diagnosis + ", drugs="
				+ drugs + ", lab=" + lab + ", fromDateSearch=" + fromDateSearch
				+ ", toDateSearch=" + toDateSearch
				+ ", practiceSearchResultSet=" + practiceSearchResultSet + "]";
	}
	
	
	
	
	
	

}

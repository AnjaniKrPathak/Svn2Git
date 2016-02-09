/**
 * 
 */
package com.clinakos.data.model.patient;

import java.util.ArrayList;
import java.util.List;

/**
 * @author li0008
 *
 */
public class ProcedureType {
	
	private int id;
	private String longName;
	private int labid;
	private String loincCode;
	private String labType;
	private String specimen;
	private String routeAdmin;
	private String procedureDescription;
	private String shortKey;
	private String relatedCode;
	private String range;
	private String units;
	private String laboratoryName;
	private int rank;
	
	
	//Added By Anjani
	private List<ProcedureResultHistory> observationLabList=null;
	
	private ORUPatientLabResult patientLabResult;
	private ProcedureResultHistory procedureResultHistory;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLongName() {
		return longName;
	}
	public void setLongName(String longName) {
		this.longName = longName;
	}
	public int getLabid() {
		return labid;
	}
	public void setLabid(int labid) {
		this.labid = labid;
	}
	
	public String getLoincCode() {
		return loincCode;
	}
	public void setLoincCode(String loincCode) {
		this.loincCode = loincCode;
	}
	
	public String getLabType() {
		return labType;
	}
	public void setLabType(String labType) {
		this.labType = labType;
	}
	public String getSpecimen() {
		return specimen;
	}
	public void setSpecimen(String specimen) {
		this.specimen = specimen;
	}
	public String getRouteAdmin() {
		return routeAdmin;
	}
	public void setRouteAdmin(String routeAdmin) {
		this.routeAdmin = routeAdmin;
	}
	public String getProcedureDescription() {
		return procedureDescription;
	}
	public void setProcedureDescription(String procedureDescription) {
		this.procedureDescription = procedureDescription;
	}
	
	
	public String getShortKey() {
		return shortKey;
	}
	public void setShortKey(String shortKey) {
		this.shortKey = shortKey;
	}
	public String getRelatedCode() {
		return relatedCode;
	}
	public void setRelatedCode(String relatedCode) {
		this.relatedCode = relatedCode;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	/**
	 * @return the laboratoryName
	 */
	public String getLaboratoryName() {
		return laboratoryName;
	}
	/**
	 * @param laboratoryName the laboratoryName to set
	 */
	public void setLaboratoryName(String laboratoryName) {
		this.laboratoryName = laboratoryName;
	}
	/**
	 * @return the observationLabList
	 */
	public List<ProcedureResultHistory> getObservationLabList() {
		if(observationLabList==null){
			observationLabList=new ArrayList<ProcedureResultHistory>();
		}
		return observationLabList;
	}
	/**
	 * @param observationLabList the observationLabList to set
	 */
	public void setObservationLabList(
			List<ProcedureResultHistory> observationLabList) {
		this.observationLabList = observationLabList;
	}
	/**
	 * @return the patientLabResult
	 */
	public ORUPatientLabResult getPatientLabResult() {
		if(patientLabResult==null){
			patientLabResult=new ORUPatientLabResult();
		}
		return patientLabResult;
	}
	/**
	 * @param patientLabResult the patientLabResult to set
	 */
	public void setPatientLabResult(ORUPatientLabResult patientLabResult) {
		this.patientLabResult = patientLabResult;
	}
	/**
	 * @return the procedureResultHistory
	 */
	public ProcedureResultHistory getProcedureResultHistory() {
		if(procedureResultHistory==null){
			procedureResultHistory=new ProcedureResultHistory();
		}
		return procedureResultHistory;
	}
	/**
	 * @param procedureResultHistory the procedureResultHistory to set
	 */
	public void setProcedureResultHistory(
			ProcedureResultHistory procedureResultHistory) {
		this.procedureResultHistory = procedureResultHistory;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	
	
	
	
}

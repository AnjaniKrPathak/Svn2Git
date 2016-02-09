package com.clinakos.data.model.patient;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="master_monitor_parameter")
public class MasterMonitorParameters {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="parentParameter")
	private String parentParameter;
	
	@Transient
	private String childParameter;
	
	@Column(name="status")
	private boolean status;
	
	@Column(name="viewCategory")
	private String viewCategory;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity=ChildMonitoringParameters.class)
	@JoinColumn(name="master_monitoring_parameter_id",referencedColumnName="id")
	private List<ChildMonitoringParameters>childMonitoringParameters=new ArrayList<ChildMonitoringParameters>();
	
	@Column(name="gender")
	private String gender;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getParentParameter() {
		return parentParameter;
	}
	public void setParentParameter(String parentParameter) {
		this.parentParameter = parentParameter;
	}
	public String getChildParameter() {
		return childParameter;
	}
	public void setChildParameter(String childParameter) {
		this.childParameter = childParameter;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getViewCategory() {
		return viewCategory;
	}
	public void setViewCategory(String viewCategory) {
		this.viewCategory = viewCategory;
	}
	public List<ChildMonitoringParameters> getChildMonitoringParameters() {
		return childMonitoringParameters;
	}
	public void setChildMonitoringParameters(
			List<ChildMonitoringParameters> childMonitoringParameters) {
		this.childMonitoringParameters = childMonitoringParameters;
	}
	
	
	
}

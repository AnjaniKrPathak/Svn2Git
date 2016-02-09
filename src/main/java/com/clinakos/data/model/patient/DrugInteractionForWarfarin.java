package com.clinakos.data.model.patient;

public class DrugInteractionForWarfarin {
	private int id;
	private String drugName;
	private String drugClass;
	private String interaction;
	private String checkHighORLowInrFOrMessage;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public String getDrugClass() {
		return drugClass;
	}
	public void setDrugClass(String drugClass) {
		this.drugClass = drugClass;
	}
	public String getInteraction() {
		return interaction;
	}
	public void setInteraction(String interaction) {
		this.interaction = interaction;
	}
	public String getCheckHighORLowInrFOrMessage() {
		return checkHighORLowInrFOrMessage;
	}
	public void setCheckHighORLowInrFOrMessage(
			String checkHighORLowInrFOrMessage) {
		this.checkHighORLowInrFOrMessage = checkHighORLowInrFOrMessage;
	}

	
}

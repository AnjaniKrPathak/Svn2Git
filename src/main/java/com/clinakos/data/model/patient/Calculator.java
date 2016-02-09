
/**
 * @author Lumbini Innovations Pvt Ltd
 *Pojo for Interacting with Doctor's Personal Details
 */

package com.clinakos.data.model.patient;

public class Calculator {

	private double height;
	private double weight;
	private String vkorc1Genotype;
	private String cyp2cGenotype;
	private String race;
	private String takingEnzimeInduce="N";
	private String takingAmiodarone="N";
	private int age;
	
	
	
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		//Math.round(patientDetails.getHeight()*100/(double)100));
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getVkorc1Genotype() {
		return vkorc1Genotype;
	}
	public void setVkorc1Genotype(String vkorc1Genotype) {
		this.vkorc1Genotype = vkorc1Genotype;
	}
	public String getCyp2cGenotype() {
		return cyp2cGenotype;
	}
	public void setCyp2cGenotype(String cyp2cGenotype) {
		this.cyp2cGenotype = cyp2cGenotype;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		//String particularRace = "";
		
		this.race = race;
	}
	public String getTakingEnzimeInduce() {
		return takingEnzimeInduce;
	}
	public void setTakingEnzimeInduce(String takingEnzimeInduce) {
		this.takingEnzimeInduce = takingEnzimeInduce;
	}
	public String getTakingAmiodarone() {
		return takingAmiodarone;
	}
	public void setTakingAmiodarone(String takingAmiodarone) {
		this.takingAmiodarone = takingAmiodarone;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}

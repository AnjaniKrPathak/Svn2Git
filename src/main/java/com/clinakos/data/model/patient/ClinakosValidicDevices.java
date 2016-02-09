/**
 * 
 */
package com.clinakos.data.model.patient;

/**
 * @author IDC-0004
 *
 */
public class ClinakosValidicDevices {

	private boolean fitness;
	private boolean nutrition;
	private boolean sleep;
	private boolean biometric;
	private boolean tobacco;
	private boolean routine;
	private boolean diabetes;
	private boolean weight;
	
	public boolean isFitness() {
		return fitness;
	}
	public void setFitness(boolean fitness) {
		this.fitness = fitness;
	}
	public boolean isNutrition() {
		return nutrition;
	}
	public void setNutrition(boolean nutrition) {
		this.nutrition = nutrition;
	}
	public boolean isSleep() {
		return sleep;
	}
	public void setSleep(boolean sleep) {
		this.sleep = sleep;
	}
	public boolean isBiometric() {
		return biometric;
	}
	public void setBiometric(boolean biometric) {
		this.biometric = biometric;
	}
	public boolean isTobacco() {
		return tobacco;
	}
	public void setTobacco(boolean tobacco) {
		this.tobacco = tobacco;
	}
	public boolean isRoutine() {
		return routine;
	}
	public void setRoutine(boolean routine) {
		this.routine = routine;
	}
	public boolean isDiabetes() {
		return diabetes;
	}
	public void setDiabetes(boolean diabetes) {
		this.diabetes = diabetes;
	}
	public boolean isWeight() {
		return weight;
	}
	public void setWeight(boolean weight) {
		this.weight = weight;
	}
	@Override
	public String toString() {
		return "ClinakosValidicDevices [fitness=" + fitness + ", nutrition="
				+ nutrition + ", sleep=" + sleep + ", biometric=" + biometric
				+ ", tobacco=" + tobacco + ", routine=" + routine
				+ ", diabetes=" + diabetes + ", weight=" + weight + "]";
	}
	
	
	
	
}

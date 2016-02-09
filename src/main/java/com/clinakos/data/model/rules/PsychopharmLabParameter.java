/**
 * 
 */
package com.clinakos.data.model.rules;

import java.util.List;

/**
 * @author IDC-
 *
 */
public class PsychopharmLabParameter extends Message{

	private String labParamter;
	private List<String> frequency;
	private String selectedFrequncy;
	
	public String getLabParamter() {
		return labParamter;
	}
	public void setLabParamter(String labParamter) {
		this.labParamter = labParamter;
	}
	public List<String> getFrequency() {
		return frequency;
	}
	public void setFrequency(List<String> frequency) {
		this.frequency = frequency;
	}
	public String getSelectedFrequncy() {
		return selectedFrequncy;
	}
	public void setSelectedFrequncy(String selectedFrequncy) {
		this.selectedFrequncy = selectedFrequncy;
	}
	
	
	
}

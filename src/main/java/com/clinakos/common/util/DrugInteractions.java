/**
 * 
 */
package com.clinakos.common.util;

/**
 * @author IDC-0004
 *
 */
public enum DrugInteractions {

	drug_drug_interactions(1),
	drug_disease_interactions(2),
	drug_allergy_interactions(3),
	pharmcogenomics_interactions(4);
	
	private int value;

	public int getValue() {
		return value;
	}

	private DrugInteractions(int value) {
		this.value = value;
	}
	
	
}

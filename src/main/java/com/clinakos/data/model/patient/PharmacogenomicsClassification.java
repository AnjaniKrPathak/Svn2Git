/**
 * 
 */
package com.clinakos.data.model.patient;

/**
 * @author li0008
 *
 */
public class PharmacogenomicsClassification {

	private int id;
	private String geneSymbol;
	private String genoType;
	private String phenoType;
	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the geneSymbol
	 */
	public String getGeneSymbol() {
		return geneSymbol;
	}
	/**
	 * @param geneSymbol the geneSymbol to set
	 */
	public void setGeneSymbol(String geneSymbol) {
		this.geneSymbol = geneSymbol;
	}
	/**
	 * @return the genoType
	 */
	public String getGenoType() {
		return genoType;
	}
	/**
	 * @param genoType the genoType to set
	 */
	public void setGenoType(String genoType) {
		this.genoType = genoType;
	}
	/**
	 * @return the phenoType
	 */
	public String getPhenoType() {
		return phenoType;
	}
	/**
	 * @param phenoType the phenoType to set
	 */
	public void setPhenoType(String phenoType) {
		this.phenoType = phenoType;
	}
	
	
	
}

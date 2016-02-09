package com.clinakos.data.model.patient;



public class PharmacogenomicsUserSummary {
	
	private int id;
	private int userId;
	private String geneSymbol;
	private String genoType;
	private String phenoType;
    private String vkorGenoType;
    private String attentionRating;
    private String drugName;
	
	
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
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
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
	public String getVkorGenoType() {
		return vkorGenoType;
	}
	public void setVkorGenoType(String vkorGenoType) {
		this.vkorGenoType = vkorGenoType;
	}
	/**
	 * @return the attentionRating
	 */
	public String getAttentionRating() {
		return attentionRating;
	}
	/**
	 * @param attentionRating the attentionRating to set
	 */
	public void setAttentionRating(String attentionRating) {
		this.attentionRating = attentionRating;
	}
	/**
	 * @return the drugName
	 */
	public String getDrugName() {
		return drugName;
	}
	/**
	 * @param drugName the drugName to set
	 */
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	
	
	
}

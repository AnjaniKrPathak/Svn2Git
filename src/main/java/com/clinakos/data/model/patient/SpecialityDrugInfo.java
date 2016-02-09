/**
 * 
 */
package com.clinakos.data.model.patient;

/**
 * @author IDC-0004
 *
 */
public class SpecialityDrugInfo {

	private String productId;
	private String productNDC;
	private String productTypeName;
	private String proprietaryName;
	private String proprietaryNameSuffix;
	private String nonProprietaryName;
	private String dosageFormName;
	private String routeName;
	private Double startMarketingDate;
	private Double endMarketingDate;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductNDC() {
		return productNDC;
	}
	public void setProductNDC(String productNDC) {
		this.productNDC = productNDC;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	public String getProprietaryName() {
		return proprietaryName;
	}
	public void setProprietaryName(String proprietaryName) {
		this.proprietaryName = proprietaryName;
	}
	public String getProprietaryNameSuffix() {
		return proprietaryNameSuffix;
	}
	public void setProprietaryNameSuffix(String proprietaryNameSuffix) {
		this.proprietaryNameSuffix = proprietaryNameSuffix;
	}
	public String getNonProprietaryName() {
		return nonProprietaryName;
	}
	public void setNonProprietaryName(String nonProprietaryName) {
		this.nonProprietaryName = nonProprietaryName;
	}
	public String getDosageFormName() {
		return dosageFormName;
	}
	public void setDosageFormName(String dosageFormName) {
		this.dosageFormName = dosageFormName;
	}
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public Double getStartMarketingDate() {
		return startMarketingDate;
	}
	public void setStartMarketingDate(Double startMarketingDate) {
		this.startMarketingDate = startMarketingDate;
	}
	public Double getEndMarketingDate() {
		return endMarketingDate;
	}
	public void setEndMarketingDate(Double endMarketingDate) {
		this.endMarketingDate = endMarketingDate;
	}
	
	
}

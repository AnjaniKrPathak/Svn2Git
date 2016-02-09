package com.clinakos.data.model.patient;



public class ClinicProvider {
	private int id;
	private int providerId;
	private int clinicId;
	private int providerLocationId;

	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProviderId() {
		return providerId;
	}
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}
	public int getClinicId() {
		return clinicId;
	}
	public void setClinicId(int clinicId) {
		this.clinicId = clinicId;
	}
	public int getProviderLocationId() {
		return providerLocationId;
	}
	public void setProviderLocationId(int providerLocationId) {
		this.providerLocationId = providerLocationId;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + clinicId;
		result = prime * result + providerId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClinicProvider other = (ClinicProvider) obj;
		if (clinicId != other.clinicId)
			return false;
		if (providerId != other.providerId)
			return false;
		return true;
	}
	
}

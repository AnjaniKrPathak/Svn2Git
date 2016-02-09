package com.clinakos.data.model.patient;

   public class MasterDiagnosis {
	   
	   private int dxId;
	   private  String dxCode;
		private String  formattedDxCode;
		private String  validforCoding;
		private String  shortDesc;
		private   String longDesc;
		
//----------------
		private String icdFormat;
		
		public int getDxId() {
			return dxId;
		}
		public void setDxId(int dxId) {
			this.dxId = dxId;
		}
		public String getDxCode() {
			return dxCode;
		}
		public void setDxCode(String dxCode) {
			this.dxCode = dxCode;
		}
		public String getFormattedDxCode() {
			return formattedDxCode;
		}
		public void setFormattedDxCode(String formattedDxCode) {
			this.formattedDxCode = formattedDxCode;
		}
		public String getValidforCoding() {
			return validforCoding;
		}
		public void setValidforCoding(String validforCoding) {
			this.validforCoding = validforCoding;
		}
		public String getShortDesc() {
			return shortDesc;
		}
		public void setShortDesc(String shortDesc) {
			this.shortDesc = shortDesc;
		}
		public String getLongDesc() {
			return longDesc;
		}
		public void setLongDesc(String longDesc) {
			this.longDesc = longDesc;
		}
		/**
		 * @return the icdFormat
		 */
		public String getIcdFormat() {
			return icdFormat;
		}
		/**
		 * @param icdFormat the icdFormat to set
		 */
		public void setIcdFormat(String icdFormat) {
			this.icdFormat = icdFormat;
		}
		
		
	
}

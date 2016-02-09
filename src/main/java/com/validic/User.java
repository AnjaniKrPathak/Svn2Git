package com.validic;

public class User {
	
	 private String uid;
	 private ValidicUserProfile profile=null;
	 

		

		/**
		 * @return the uid
		 */
		public String getUid() {
			return uid;
		}

		/**
		 * @param uid the uid to set
		 */
		public void setUid(String uid) {
			this.uid = uid;
		}

		/**
		 * @return the profile
		 */
		public ValidicUserProfile getProfile() {
			if(profile==null){
				profile=new ValidicUserProfile();
			}
			return profile;
		}

		/**
		 * @param profile the profile to set
		 */
		public void setProfile(ValidicUserProfile profile) {
			this.profile = profile;
		}


		
	    

}

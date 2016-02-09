/**
 * 
 */
package com.clinakos.service;

/**
 * @author saurabh
 *
 */
public interface IPasswordEncoderGeneratorService {

	void encryptExistingPassword();
	public String encryptPassword(String plainPassword);

}

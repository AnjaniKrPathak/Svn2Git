/**
 * 
 */
package com.clinakos.service.serviceImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.clinakos.data.dao.IUserDao;
import com.clinakos.data.model.core.LoginSecurity;
import com.clinakos.service.IPasswordEncoderGeneratorService;

/**
 * @author Saurabh
 *
 */
public class PasswordEncoderGeneratorServiceImpl implements IPasswordEncoderGeneratorService, Serializable{

	private static final long serialVersionUID = -578098026480990918L;
	public static final Logger logger = Logger.getLogger("PasswordEncoderGeneratorServiceImpl.class");
	private IUserDao userDAO;

	/**
	 * @return the userDAO
	 */
	public IUserDao getUserDAO() {
		return userDAO;
	}

	/**
	 * @param userDAO the userDAO to set
	 */
	public void setUserDAO(IUserDao userDAO) {
		this.userDAO = userDAO;
	}

/*
 * *******************Method for encrypting existing password
 * @see com.clinakos.service.IPasswordEncoderGeneratorService#encryptExistingPassword()
 * *******@ author: saurabh
 */
	public void encryptExistingPassword() {
		logger.info("=============calling encryptExistingPassword in PasswordEncoderGeneratorServiceImpl=========");
		List<LoginSecurity> lsList= new ArrayList<LoginSecurity>();
		lsList=userDAO.fetchAllRecordsFromLogin();
		System.out.println("size of loginsecurityList="+lsList.size());
		for (LoginSecurity ls : lsList) {
			logger.info("inside for each loop===row="+ls.getId()+"===password="+ls.getPassword());
			String encryptedPassword = encryptPassword(ls.getPassword());
			logger.info("=====encryptedPassword="+encryptedPassword);
			userDAO.updatePlainPasswordToEncrypt(ls.getId(),encryptedPassword);
		}
	}
	

/*
 * **********Method to encrypt password by passing plain password
 * ************@author : saurabh
 */
	public String encryptPassword(String plainPassword){
		logger.info("=============calling encryptPassword in PasswordEncoderGeneratorServiceImpl=========");
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encryptedText = passwordEncoder.encode(plainPassword);
		logger.info("==encryptedText="+encryptedText);
		return encryptedText;
	}

}

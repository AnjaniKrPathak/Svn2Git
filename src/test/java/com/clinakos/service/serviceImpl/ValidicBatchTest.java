/**
 * 
 */
package com.clinakos.service.serviceImpl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.clinakos.data.model.patient.ValidicUsersModel;

/**
 * @author IDC-0004
 *
 */
public class ValidicBatchTest {
	
	private List<ValidicUsersModel> dataList;

	/**
	 * Test method for {@link com.clinakos.service.serviceImpl.ValidicRestServiceImpl#getUniqueFitnessUsers(java.util.List)}.
	 */
	@Test
	public void testGetUniqueFitnessUsers() {
		ValidicRestServiceImpl validicRestServiceImpl = new ValidicRestServiceImpl();
		assertEquals("is Unique users ",2, validicRestServiceImpl.getUniqueUsers(dataList).size());		
	}

	@Before
	public void prepareData(){
		dataList = new ArrayList<ValidicUsersModel>();
		
		ValidicUsersModel model1 = new ValidicUsersModel();
		model1.setClinakosUserId(116);
		model1.setFirstName("A");
		model1.setLastName("B");
		model1.setUserAccessToken("C");
		model1.setAppSourceName("daillymile");
		
		ValidicUsersModel model2 = new ValidicUsersModel();
		model2.setClinakosUserId(8774);
		model2.setFirstName("A");
		model2.setLastName("B");
		model2.setUserAccessToken("C");
		model2.setAppSourceName("mapmyfitness");
		
		ValidicUsersModel model3 = new ValidicUsersModel();
		model3.setClinakosUserId(8774);
		model3.setFirstName("A");
		model3.setLastName("B");
		model3.setUserAccessToken("C");
		model3.setAppSourceName("misfit");
		
		ValidicUsersModel model4 = new ValidicUsersModel();
		model4.setClinakosUserId(8774);
		model4.setFirstName("A");
		model4.setLastName("B");
		model4.setUserAccessToken("C");
		model4.setAppSourceName("movesapp");
		
		dataList.add(model1);
		dataList.add(model2);
		dataList.add(model3);
		dataList.add(model4);
		
		
	}
}

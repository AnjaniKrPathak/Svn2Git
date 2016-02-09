/**
 * 
 */
package com.clinakos.service.serviceImpl;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.clinakos.data.model.patient.PharmacyDetail;

/**
 * @author Nagaraj
 * Created Date: 04/Feb/2015
 *
 */
public class ComplianceCalculationTest {


	
	private List<PharmacyDetail>pharmacyHistoryList;
	private List<PharmacyDetail>pharmacyHistoryListTest2;
	private List<PharmacyDetail>pharmacyHistoryListTest3;
	private double drugId;

	@Before
	public void prepareComplianceData(){
		pharmacyHistoryList=new ArrayList<PharmacyDetail>();
		pharmacyHistoryListTest2=new ArrayList<PharmacyDetail>();
		pharmacyHistoryListTest3=new ArrayList<PharmacyDetail>();
		drugId=18172;
		
		PharmacyDetail pharmacyDetail1 =new PharmacyDetail();
		pharmacyDetail1.setDrugId(drugId);
		pharmacyDetail1.setDaysOfSupply(String.valueOf("30"));
		pharmacyDetail1.setDateOfPurchase(new Date("10/31/2014"));
		pharmacyHistoryList.add(pharmacyDetail1);
		
		PharmacyDetail pharmacyDetail2 =new PharmacyDetail();
		pharmacyDetail2.setDrugId(drugId);
		pharmacyDetail2.setDaysOfSupply(String.valueOf("30"));
		pharmacyDetail2.setDateOfPurchase(new Date("11/21/2014"));
		pharmacyHistoryList.add(pharmacyDetail2);
		
		PharmacyDetail pharmacyDetail3 =new PharmacyDetail();
		pharmacyDetail3.setDrugId(drugId);
		pharmacyDetail3.setDaysOfSupply(String.valueOf("30"));
		pharmacyDetail3.setDateOfPurchase(new Date("12/22/2014"));
		pharmacyHistoryList.add(pharmacyDetail3);
		
		PharmacyDetail pharmacyDetail4 =new PharmacyDetail();
		pharmacyDetail4.setDrugId(drugId);
		pharmacyDetail4.setDaysOfSupply(String.valueOf("90"));
		pharmacyDetail4.setDateOfPurchase(new Date("01/07/2015"));
		pharmacyHistoryList.add(pharmacyDetail4);
		
		
		
		PharmacyDetail pharmacyDetail12 =new PharmacyDetail();
		pharmacyDetail12.setDrugId(drugId);
		pharmacyDetail12.setDaysOfSupply(String.valueOf("30"));
		pharmacyDetail12.setDateOfPurchase(new Date("06/16/2014"));
		pharmacyHistoryListTest2.add(pharmacyDetail12);
		
		PharmacyDetail pharmacyDetail13 =new PharmacyDetail();
		pharmacyDetail13.setDrugId(drugId);
		pharmacyDetail13.setDaysOfSupply(String.valueOf("30"));
		pharmacyDetail13.setDateOfPurchase(new Date("07/10/2014"));
		pharmacyHistoryListTest2.add(pharmacyDetail13);
		
		PharmacyDetail pharmacyDetail14 =new PharmacyDetail();
		pharmacyDetail14.setDrugId(drugId);
		pharmacyDetail14.setDaysOfSupply(String.valueOf("30"));
		pharmacyDetail14.setDateOfPurchase(new Date("08/21/2014"));
		pharmacyHistoryListTest2.add(pharmacyDetail14);
		
		
		PharmacyDetail pharmacyDetail21=new PharmacyDetail();
		pharmacyDetail21.setDrugId(drugId);
		pharmacyDetail21.setDaysOfSupply(String.valueOf("8"));
		pharmacyDetail21.setDateOfPurchase(new Date("10/21/2014"));
		pharmacyHistoryListTest3.add(pharmacyDetail21);
		
	}
	
	@Ignore
	@Test
	public void complianceCalculationTestDemo(){
		PatientMedicineServiceImpl patServiceImpl=new PatientMedicineServiceImpl();
		assertEquals("when compliance percentage is equal to 100 or greater than 100",100, patServiceImpl.complianceCalculationForDrug(drugId, pharmacyHistoryList).get(0));
		assertEquals("when compliance percentage is less than 100 ",38, patServiceImpl.complianceCalculationForDrug(drugId, pharmacyHistoryListTest2).get(0));
		assertEquals("when compliance percentage is less than 100 ",7, patServiceImpl.complianceCalculationForDrug(drugId, pharmacyHistoryListTest3).get(0));
	}
	
	@Test
	public void javaJodaDateCheck(){
		Date date=new LocalDate().toDate();
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Current date of Joda to Java date conversion "+sdf.format(date));
	}
}

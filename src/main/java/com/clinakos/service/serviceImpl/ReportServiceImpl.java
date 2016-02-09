package com.clinakos.service.serviceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;

import com.clinakos.data.dao.IReportDAO;
import com.clinakos.data.model.core.RptDrugCategory;
import com.clinakos.data.model.core.RptDrugClassCategory;
import com.clinakos.data.model.core.RptGenericDrugCategory;
import com.clinakos.data.model.core.RptSpecialDrugCategory;
import com.clinakos.data.model.core.RptTherapeuticArea;
import com.clinakos.data.model.patient.ACOMeasure;
import com.clinakos.data.model.patient.AnalyticsDetail;
import com.clinakos.data.model.patient.ClinicPatientDetail;
import com.clinakos.service.IReportService;

public class ReportServiceImpl implements IReportService {
	
	private IReportDAO reportDAO;


	/**
	 * @param reportDAO the reportDAO to set
	 */
	public void setReportDAO(IReportDAO reportDAO) {
		this.reportDAO = reportDAO;
	}

	/**
	 * Get Analytics Data based On Provider 
	 */
	public AnalyticsDetail getAnliticsData() {
		// TODO Auto-generated method stub
		return reportDAO.getOverviewAnlyticsData();
		
	}

	
	public List<ClinicPatientDetail> getClinicPatientDetailData() {
		// TODO Auto-generated method stub
		return reportDAO.getClinicPatientDetailData();
	}

	
	public List<RptDrugClassCategory> getDrugClassCategoryData() {
		// TODO Auto-generated method stub
		return reportDAO.getDrugClassCategoryData();
	}

	
	public List<RptSpecialDrugCategory> getSpecialDrugCategoryData() {
		// TODO Auto-generated method stub
		return reportDAO.getSpecialDrugCategoryData();
	}
	
/*
 * ************ Method to show drug category bar chart
 * @see com.clinakos.service.IReportService#findDrugCategoryValue()
 */
		public List<RptDrugCategory> findDrugCategoryValue() {
			
			return reportDAO.findDrugCategoryValue();
		}

	
		public List<RptGenericDrugCategory> getGenericDrugCategoryData() {
			// TODO Auto-generated method stub
			return reportDAO.getGenericDrugCategoryData();
			
		}


		public List<RptTherapeuticArea> getTherapeuticAreaList() {
			// TODO Auto-generated method stub
			return reportDAO.getTherapeuticAreaList();
		}


	
		public int getMaxValueOfPatientInClinic() {
			// TODO Auto-generated method stub
			return reportDAO.getMaxValueOfPatientInClinic();
		}


		
		public int getMinPatientInClinic() {
			// TODO Auto-generated method stub
			return reportDAO.getMinPatientInClinic();
		}


	
		public ACOMeasure getAcoMeasureValue() {
			// TODO Auto-generated method stub
			return reportDAO.getAcoMeasureValue();
		}


	  
		public void callACOMeasure28Proc() throws HibernateException, SQLException {
			// TODO Auto-generated method stub
			reportDAO.callACOMeasure28Proc();
			
		}


		@Override
		public List<RptDrugCategory> fetchDrugByCategoryChart(
				String levelVal4DrugChart, int providerId) {
			// TODO Auto-generated method stub
			return reportDAO.fetchDrugByCategoryChart(levelVal4DrugChart,providerId);
		}


		
		
		

}

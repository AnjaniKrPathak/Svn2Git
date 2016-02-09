package com.clinakos.service;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;

import com.clinakos.data.model.core.RptDrugCategory;
import com.clinakos.data.model.core.RptDrugClassCategory;
import com.clinakos.data.model.core.RptGenericDrugCategory;
import com.clinakos.data.model.core.RptSpecialDrugCategory;
import com.clinakos.data.model.core.RptTherapeuticArea;
import com.clinakos.data.model.patient.ACOMeasure;
import com.clinakos.data.model.patient.AnalyticsDetail;
import com.clinakos.data.model.patient.ClinicPatientDetail;


public interface IReportService {

	AnalyticsDetail getAnliticsData();

	List<ClinicPatientDetail> getClinicPatientDetailData();

	List<RptDrugClassCategory> getDrugClassCategoryData();

	List<RptSpecialDrugCategory> getSpecialDrugCategoryData();

	List<RptDrugCategory> findDrugCategoryValue();

	List<RptGenericDrugCategory> getGenericDrugCategoryData();
	List<RptTherapeuticArea> getTherapeuticAreaList();

	int getMaxValueOfPatientInClinic();

	int getMinPatientInClinic();

	ACOMeasure getAcoMeasureValue();

	void callACOMeasure28Proc() throws HibernateException, SQLException;

	List<RptDrugCategory> fetchDrugByCategoryChart(String levelVal4DrugChart,
			int i);
	

}

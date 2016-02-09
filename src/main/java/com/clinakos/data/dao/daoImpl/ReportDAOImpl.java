package com.clinakos.data.dao.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.clinakos.common.util.ContextUtil;
import com.clinakos.data.dao.IReportDAO;
import com.clinakos.data.model.core.RptDrugCategory;
import com.clinakos.data.model.core.RptDrugClassCategory;
import com.clinakos.data.model.core.RptGenericDrugCategory;
import com.clinakos.data.model.core.RptSpecialDrugCategory;
import com.clinakos.data.model.core.RptTherapeuticArea;
import com.clinakos.data.model.patient.ACOMeasure;
import com.clinakos.data.model.patient.AnalyticsDetail;
import com.clinakos.data.model.patient.ClinicPatientDetail;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.PreparedStatement;

public class ReportDAOImpl extends BaseDaoImpl implements IReportDAO  {
	public static final Logger logger = Logger.getLogger("ReportDAOImpl.class");
	
	/**
	 * Get all Anlytics Data for pouplation health overview page 
	 * 
	 * 
	 */
	public AnalyticsDetail getOverviewAnlyticsData(){
	    AnalyticsDetail analyticsDetail=new AnalyticsDetail();
	    logger.info("getOverviewAnlyticsData::::::::::::::::::"+new ContextUtil().getProviderId());
		
		try {
			System.out.println("Report Dao Impl MethodStarted ");
			int providerId=new ContextUtil().getProviderId();
			Criteria criteria= getSessionFactory().getCurrentSession().createCriteria(AnalyticsDetail.class);
			                   criteria.add(Restrictions.eq("providerId", providerId));
			                   criteria.setProjection(Projections.max("id"));
			  
			if(!(criteria.uniqueResult()==null)){
				int maxId= (Integer) criteria.uniqueResult();
				analyticsDetail=(AnalyticsDetail) getSessionFactory().getCurrentSession().get(AnalyticsDetail.class, maxId);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return analyticsDetail;
		
		
	}

	/**
	 * Get all the data belogs to particular clinic in one provider
	 */
	public List<ClinicPatientDetail> getClinicPatientDetailData() {
		// TODO Auto-generated method stub
		List<ClinicPatientDetail> clPatientDetailList=new ArrayList<ClinicPatientDetail>();
		try {
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ClinicPatientDetail.class);
			                  criteria.createAlias("clinicProvider", "cliProv");
			                  criteria.add(Restrictions.eq("cliProv.providerId", new ContextUtil().getProviderId()));
			clPatientDetailList=criteria.list();  
			              
		} catch (Exception e) {
			// TODO: handle exception
		}
		return clPatientDetailList;
	}

    /**
     * Get all data for drug class category data 
     */
	public List<RptDrugClassCategory> getDrugClassCategoryData() {
		// TODO Auto-generated method stub
		List<RptDrugClassCategory> drugClassCategoryList=new ArrayList<RptDrugClassCategory>();
		try {
			
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(RptDrugClassCategory.class);
			                 criteria.add(Restrictions.eq("providerId", new ContextUtil().getProviderId()));
			                 criteria.add(Restrictions.eq("drugClassificationId", 2));
			drugClassCategoryList=criteria.list(); 
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return drugClassCategoryList;
	}

   /**
    * get all data for special drug category 
    */
	public List<RptSpecialDrugCategory> getSpecialDrugCategoryData() {
		// TODO Auto-generated method stub
		List<RptSpecialDrugCategory> specialDrugList=new ArrayList<RptSpecialDrugCategory>();
		try {
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(RptSpecialDrugCategory.class);
			                  criteria.add(Restrictions.eq("providerId", new ContextUtil().getProviderId()));
			                  criteria.add(Restrictions.eq("drugClassificationId", 3));
		     specialDrugList = criteria.list();            
		} catch (Exception e) {
			// TODO: handle exception
		}
		return specialDrugList;
	}
	
/*
 * Method to show drug category bar chart
 * @see com.clinakos.data.dao.IReportDAO#findDrugCategoryValue()
 */
	public List<RptDrugCategory> findDrugCategoryValue() {
		logger.info("findDrugCategoryValue Starts @ DAo:::");
		List<RptDrugCategory> drugCategoryList=new ArrayList<RptDrugCategory>();
		try 
		{
		  Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(RptDrugCategory.class,"drugCtg");
			//criteria.add(Restrictions.eq("drugCtg.drugClassificationId", 1));
		 criteria.add(Restrictions.eq("drugCtg.providerId", new ContextUtil().getProviderId()));
		 drugCategoryList=criteria.list();
			
		} 
		catch (Exception e) {
			logger.info("exception in findDrugCategoryValue @DAO:::"+e);
			e.printStackTrace();
		}
		
		return drugCategoryList;
	}

	/**
	 * Get all data for generic drug category 
	 */
	public List<RptGenericDrugCategory> getGenericDrugCategoryData() {
		// TODO Auto-generated method stub
		List<RptGenericDrugCategory> genericDrugList=new ArrayList<RptGenericDrugCategory>();
		try {
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(RptGenericDrugCategory.class);
			                  criteria.add(Restrictions.eq("providerId", new ContextUtil().getProviderId()));
			                  criteria.add(Restrictions.eq("drugClassificationId", 4));
			genericDrugList=criteria.list();      
			System.out.println("in daoImpl the list of the drug is:"+genericDrugList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return genericDrugList;
	}

	
	/* >>>>>>>>>>>>>To Find The Value Of RptTherpeutic Area>>>>>>>>>>>>>>>>>>>>>>>>>>>.
*/	
	public List<RptTherapeuticArea> getTherapeuticAreaList() {
		System.out.println("return the RptTherapeuticArea in dao:::::::::::::::");
		// TODO Auto-generated method stub
		List<RptTherapeuticArea> rptdrugList=new ArrayList<RptTherapeuticArea>();
		//try{
			Criteria rptThcrite=getSessionFactory().getCurrentSession().createCriteria(RptTherapeuticArea.class);
			                   rptThcrite.add(Restrictions.eq("providerId", new ContextUtil().getProviderId()));
			                   // rptThcrite.add(Restrictions.eq("drugClassificationId",5));
			                 rptdrugList=rptThcrite.list();
			                    System.out.println("return the list:::::::::::::::"+rptdrugList);
		//}catch(Exception e){
		
		//	e.printStackTrace();
		//}
		
		return rptdrugList;
	}

	
	public int getMaxValueOfPatientInClinic() {
		// TODO Auto-generated method stub
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ClinicPatientDetail.class)
				.setProjection(Projections.max("totalPatient"));
		
		int totalPatientInClinic=(Integer) criteria.uniqueResult();
		return totalPatientInClinic;
	}

	
	public int getMinPatientInClinic() {
		// TODO Auto-generated method stub
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ClinicPatientDetail.class)
				          .setProjection(Projections.min("totalPatient"));
		int minPatientInClinic=(Integer) criteria.uniqueResult();
		return minPatientInClinic;
	}

	/**
	 * Find ACO Measure Value for Analytics Reporting 
	 */
	public ACOMeasure getAcoMeasureValue() {
		// TODO Auto-generated method stub
		List<ACOMeasure> acoMeasures=new ArrayList<ACOMeasure>();
		ACOMeasure acoMeasure=new ACOMeasure();
		 int maxId=0;
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ACOMeasure.class);
		 criteria.setProjection(Projections.max("id"));
		 if(!(criteria.uniqueResult()==null)){
			 maxId=(Integer) criteria.uniqueResult(); 
		 }
		
		 if(!(maxId==0)){
			
			 acoMeasure=(ACOMeasure) getSessionFactory().getCurrentSession().get(ACOMeasure.class, maxId);
		 }
		
		//acoMeasures=criteria.list();
		 
		System.out.println("acoMeasures.size()  "+acoMeasure.toString().length());
		return acoMeasure;
	}

	/**
	 * Calling Proc for ACO 28 Measure value 
	 * @throws SQLException 
	 * @throws HibernateException 
	 */
	public void callACOMeasure28Proc() throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		CallableStatement statement=(CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_aco_master_measure()}");
		int i=statement.executeUpdate();
		System.out.println("callACOMeasureMasterProc"+i);
		
		
	}

	// Method to fetching data for drill down drug chart
	@Override
	public List<RptDrugCategory> fetchDrugByCategoryChart(
			String levelVal4DrugChart, int providerId) {
		// TODO Auto-generated method stub
		List<RptDrugCategory> drillDownDrugList=new ArrayList<RptDrugCategory>();
		List<RptDrugCategory> drillDownDrugList1=new ArrayList<RptDrugCategory>();
		 CallableStatement statement;
		  try {
			  System.out.println("fetching data fro drill down drug: for "+levelVal4DrugChart);
			  String UpdatedLevelVal="";
			  if (levelVal4DrugChart.contains("'"))
			  {
			  UpdatedLevelVal = levelVal4DrugChart.replace("'", "\\'");
			  }
			  else
			  {
				  UpdatedLevelVal=levelVal4DrugChart;			  
			  }
			  statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_DrugDrillDownChart("+providerId+",'"+UpdatedLevelVal+"')}");

			  ResultSet resultSet=statement.executeQuery();


			  while (resultSet.next()) {
				  RptDrugCategory uld=new RptDrugCategory();
				
				  logger.info("Drugs name is "+resultSet.getString("drugs")+"Total Patients are "+resultSet.getInt("toatl_count"));
				 
				  String drug_name=resultSet.getString("drugs");
				  Integer total_patient=resultSet.getInt("toatl_count");
				  
				  uld.setDrugName(drug_name);
				  uld.setTotalPatient(total_patient);
				
				  System.out.println(uld.getDrugName() + uld.getTotalPatient());
				
				  drillDownDrugList1.add(uld);
			  }
		  } catch (HibernateException e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
		  } catch (SQLException e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
		  }		

		  drillDownDrugList.addAll(drillDownDrugList1);	 
		  	System.out.println("list size:for Drug Category:anand:::--"+ drillDownDrugList.size());
		 
			return drillDownDrugList;
	}

}

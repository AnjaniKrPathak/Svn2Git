package com.clinakos.viewController.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;




import com.clinakos.data.model.core.RptDrugCategory;
import com.clinakos.data.model.core.RptDrugClassCategory;
import com.clinakos.data.model.core.RptGenericDrugCategory;
import com.clinakos.data.model.core.RptSpecialDrugCategory;
import com.clinakos.data.model.core.RptTherapeuticArea;
import com.clinakos.data.model.patient.ACOMeasure;
import com.clinakos.data.model.patient.AnalyticsDetail;
import com.clinakos.data.model.patient.ClinicPatientDetail;
import com.clinakos.service.IReportService;
import com.google.gson.Gson;

public class ReportManageBean  implements Serializable{

	private static final long serialVersionUID = -3432298422905981167L;
	public static final Logger logger = Logger.getLogger("ReportManageBean.class");
	private IReportService reportService;
    private AnalyticsDetail analyticsDetail=new AnalyticsDetail();
    private RptDrugCategory rptDrugCategory= new RptDrugCategory();
    private RptDrugClassCategory rptDrugClassCategory= new RptDrugClassCategory();
    private RptGenericDrugCategory rptGenericDrugCategory= new RptGenericDrugCategory();
    private RptSpecialDrugCategory rptSpecialDrugCategory= new RptSpecialDrugCategory();
    
    List<RptDrugCategory> rptDrugCategoryList=null;
    List<RptDrugClassCategory> rptDrugClassCategoryList=null;
    List<RptGenericDrugCategory> rptGenericDrugCategoryList=null;
    List<RptSpecialDrugCategory> rptSpecialDrugCategoryList=null;
    private CartesianChartModel clinicBarChart;
    private List<ClinicPatientDetail> clinicPatientDetailList;
    private CartesianChartModel drugClassBarChart;
    private List<RptDrugClassCategory> drugClassCategoryList;
    private List<RptSpecialDrugCategory> specialDrugCategoryList;
    private CartesianChartModel specialDrugBarChart;
    private CartesianChartModel drugCategoryModelObj;
    private CartesianChartModel genericDrugBarChart;
    private List<RptGenericDrugCategory> genericDrugCategoryList;
    private PieChartModel therpeuticAreaBarChart;
    private List< RptTherapeuticArea> therapeuticAreaList;
    private int minClinicPatient;
    private int maxClinicPatient;
   //list for D3Js Bar chart
    private List< RptTherapeuticArea> therapeuticAreaPieChart=null;
    private List<RptDrugClassCategory> drugClassCategoryBarList=null;
  
    private List<RptGenericDrugCategory> genericDrugCategoryBarChartList=null;
    private List<ClinicPatientDetail>clinicPatientDetailBarList=null;
    List<RptDrugCategory> rptDrugCategoryBarList=null;
    List<RptDrugCategory> rptMedByDrugCategoryBarList=null;
    private List<RptSpecialDrugCategory> specialDrugCategoryBarList=null;
    private List<ACOMeasure> acoMeasureList=null;
	public double measure28Value;
	public double measure30Value;
	public double measure32Value;
	public double measure33Value;
	public double measure14Value;
	public double measure15Value;
	public double measure29Value;
	public double measure26Value;

	


	

	public void init(){
		logger.info(":::::::::::::::init start in Reportmanage bean start");
		//aco28MeasureValue();
		getPopulationAnliticsValue();
		
	}
	
	
	/**
	 * @param reportService the reportService to set
	 */
	public void setReportService(IReportService reportService) {
		this.reportService = reportService;
	}
	
	public void getPopulationAnliticsValue(){
		logger.info("::::::::::::insidegetPopulationAnliticsValue method::::" );
		createClinicBarChart();
		createDrugClassBarChart();
		createSpecialDrugBarChart();
		drugCategoryGraph();
		createGenericDrugBarChart();
		createTherpeuticAreaDrug();
	}

	/**
	 * @return the analyticsDetail
	 */
	public AnalyticsDetail getAnalyticsDetail() {
		return analyticsDetail;
	}

	/**
	 * @param analyticsDetail the analyticsDetail to set
	 */
	public void setAnalyticsDetail(AnalyticsDetail analyticsDetail) {
		this.analyticsDetail = analyticsDetail;
	}

	/**
	 * @return the rptDrugCategory
	 */
	public RptDrugCategory getRptDrugCategory() {
		return rptDrugCategory;
	}

	/**
	 * @param rptDrugCategory the rptDrugCategory to set
	 */
	public void setRptDrugCategory(RptDrugCategory rptDrugCategory) {
		this.rptDrugCategory = rptDrugCategory;
	}

	/**
	 * @return the rptDrugClassCategory
	 */
	public RptDrugClassCategory getRptDrugClassCategory() {
		return rptDrugClassCategory;
	}

	/**
	 * @param rptDrugClassCategory the rptDrugClassCategory to set
	 */
	public void setRptDrugClassCategory(RptDrugClassCategory rptDrugClassCategory) {
		this.rptDrugClassCategory = rptDrugClassCategory;
	}

	/**
	 * @return the rptGenericDrugCategory
	 */
	public RptGenericDrugCategory getRptGenericDrugCategory() {
		return rptGenericDrugCategory;
	}

	/**
	 * @param rptGenericDrugCategory the rptGenericDrugCategory to set
	 */
	public void setRptGenericDrugCategory(
			RptGenericDrugCategory rptGenericDrugCategory) {
		this.rptGenericDrugCategory = rptGenericDrugCategory;
	}

	/**
	 * @return the rptSpecialDrugCategory
	 */
	public RptSpecialDrugCategory getRptSpecialDrugCategory() {
		return rptSpecialDrugCategory;
	}

	/**
	 * @param rptSpecialDrugCategory the rptSpecialDrugCategory to set
	 */
	public void setRptSpecialDrugCategory(
			RptSpecialDrugCategory rptSpecialDrugCategory) {
		this.rptSpecialDrugCategory = rptSpecialDrugCategory;
	}

	/**
	 * @return the rptDrugCategoryList
	 */
	public List<RptDrugCategory> getRptDrugCategoryList() {
		return rptDrugCategoryList;
	}

	/**
	 * @param rptDrugCategoryList the rptDrugCategoryList to set
	 */
	public void setRptDrugCategoryList(List<RptDrugCategory> rptDrugCategoryList) {
		this.rptDrugCategoryList = rptDrugCategoryList;
	}

	/**
	 * @return the rptDrugClassCategoryList
	 */
	public List<RptDrugClassCategory> getRptDrugClassCategoryList() {
		return rptDrugClassCategoryList;
	}

	/**
	 * @param rptDrugClassCategoryList the rptDrugClassCategoryList to set
	 */
	public void setRptDrugClassCategoryList(
			List<RptDrugClassCategory> rptDrugClassCategoryList) {
		this.rptDrugClassCategoryList = rptDrugClassCategoryList;
	}

	/**
	 * @return the rptGenericDrugCategoryList
	 */
	public List<RptGenericDrugCategory> getRptGenericDrugCategoryList() {
		return rptGenericDrugCategoryList;
	}

	/**
	 * @param rptGenericDrugCategoryList the rptGenericDrugCategoryList to set
	 */
	public void setRptGenericDrugCategoryList(
			List<RptGenericDrugCategory> rptGenericDrugCategoryList) {
		this.rptGenericDrugCategoryList = rptGenericDrugCategoryList;
	}

	/**
	 * @return the rptSpecialDrugCategoryList
	 */
	public List<RptSpecialDrugCategory> getRptSpecialDrugCategoryList() {
		return rptSpecialDrugCategoryList;
	}

	/**
	 * @param rptSpecialDrugCategoryList the rptSpecialDrugCategoryList to set
	 */
	public void setRptSpecialDrugCategoryList(
			List<RptSpecialDrugCategory> rptSpecialDrugCategoryList) {
		this.rptSpecialDrugCategoryList = rptSpecialDrugCategoryList;
	}

	/**
	 * @return the clinicBarChart
	 */
	public CartesianChartModel getClinicBarChart() {
		return clinicBarChart;
	}

	/**
	 * It create bar chart for clinic
	 * How many patient avilable in clinic
	 *  
	 */
	public void createClinicBarChart(){
		clinicBarChart=new CartesianChartModel();
		ChartSeries clinicChartSeries=new ChartSeries();
		clinicChartSeries.setLabel("Clinic Chart");
		for(ClinicPatientDetail clPat:getClinicPatientDetailList()){
			clinicChartSeries.set(clPat.getClinicName(), clPat.getTotalPatient());
			
			
		}
		clinicBarChart.addSeries(clinicChartSeries);
		
	}

	/**
	 * @return the clinicPatientDetailList
	 */
	public List<ClinicPatientDetail> getClinicPatientDetailList() {
		if(clinicPatientDetailList==null){
			clinicPatientDetailList=new ArrayList<ClinicPatientDetail>();
		}
		
		clinicPatientDetailList=reportService.getClinicPatientDetailData();
		return clinicPatientDetailList;
	}

	/**
	 * @param clinicPatientDetailList the clinicPatientDetailList to set
	 */
	public void setClinicPatientDetailList(
			List<ClinicPatientDetail> clinicPatientDetailList) {
		this.clinicPatientDetailList = clinicPatientDetailList;
	}

	/**
	 * @return the drugClassBarChart
	 */
	public CartesianChartModel getDrugClassBarChart() {
		return drugClassBarChart;
	}
	
	/**
	 * It create bar chart for drug class 
	 */
	public void createDrugClassBarChart(){
		drugClassBarChart=new CartesianChartModel();
		ChartSeries drugClassChartSeries=new ChartSeries();
		drugClassChartSeries.setLabel("Drug Class");
		for(RptDrugClassCategory drugClass:getDrugClassCategoryList()){
			drugClassChartSeries.set(drugClass.getDrugCategory(), drugClass.getTotalDrug());
		}
		drugClassBarChart.addSeries(drugClassChartSeries);
		
	}

	/**
	 * @return the drugClassCategoryList
	 */
	public List<RptDrugClassCategory> getDrugClassCategoryList() {
		if(drugClassCategoryList==null){
			drugClassCategoryList=new ArrayList<RptDrugClassCategory>();
		}
		drugClassCategoryList=reportService.getDrugClassCategoryData();
		return drugClassCategoryList;
	}

	/**
	 * @param drugClassCategoryList the drugClassCategoryList to set
	 */
	public void setDrugClassCategoryList(
			List<RptDrugClassCategory> drugClassCategoryList) {
		this.drugClassCategoryList = drugClassCategoryList;
	}

	/**
	 * @return the specialDrugCategoryList
	 */
	public List<RptSpecialDrugCategory> getSpecialDrugCategoryList() {
		if(specialDrugCategoryList==null){
			specialDrugCategoryList=new ArrayList<RptSpecialDrugCategory>();
		}
		specialDrugCategoryList=reportService.getSpecialDrugCategoryData();
		return specialDrugCategoryList;
	}

	/**
	 * @param specialDrugCategoryList the specialDrugCategoryList to set
	 */
	public void setSpecialDrugCategoryList(
			List<RptSpecialDrugCategory> specialDrugCategoryList) {
		this.specialDrugCategoryList = specialDrugCategoryList;
	}

	/**
	 * @return the specialDrugBarChart
	 */
	public CartesianChartModel getSpecialDrugBarChart() {
		return specialDrugBarChart;
	}

	/**
	 * Create Bar chart for special Drug
	 */
	public void createSpecialDrugBarChart(){
		specialDrugBarChart=new CartesianChartModel();
		ChartSeries specialDrugSeries=new ChartSeries();
		specialDrugSeries.setLabel("Special Drug");
	
		
	      
		for(RptSpecialDrugCategory specialDrug:getSpecialDrugCategoryList()){
			specialDrugSeries.set(specialDrug.getDrugCategory(), specialDrug.getTotalDrug());
		}
		specialDrugBarChart.addSeries(specialDrugSeries);
	}
	
	

/*
 * **** Method to show drug category bar chart	
 * Get All Drug Category 
 */
	public void drugCategoryGraph() {
		logger.info("drugCategoryGraph called in bean:::::");
		drugCategoryModelObj=new CartesianChartModel();
		ChartSeries drugClass = new ChartSeries(); 
		
		if (rptDrugCategoryList==null) {
			rptDrugCategoryList=reportService.findDrugCategoryValue();
		 }
		
		for (RptDrugCategory drug : rptDrugCategoryList) {
			drugClass.set(drug.getDrugCategory(), drug.getTotalDrug());
			//Collections.max(drug.getTotalDrug());
		  }
		drugClass.setLabel("Drugs");
        drugCategoryModelObj.addSeries(drugClass);
	}

	/**
	 * @return the drugCategoryModelObj
	 */
	public CartesianChartModel getDrugCategoryModelObj() {
		return drugCategoryModelObj;
	}

	/**
	 * @param drugCategoryModelObj the drugCategoryModelObj to set
	 */
	public void setDrugCategoryModelObj(CartesianChartModel drugCategoryModelObj) {
		this.drugCategoryModelObj = drugCategoryModelObj;
	}

	/**
	 * @return the genericDrugBarChart
	 */
	public CartesianChartModel getGenericDrugBarChart() {
		return genericDrugBarChart;
	}
	
	/**
	 * Create Bar Chart for generic drug
	 */
	public void createGenericDrugBarChart(){
		genericDrugBarChart=new CartesianChartModel();
		ChartSeries genericDrugSeries=new ChartSeries();
		genericDrugSeries.setLabel("GenericDrug");
		
		/*genericDrugBarChart.addSeries(genericDrugSeries);*/
		for(RptGenericDrugCategory genricDrug:getGenericDrugCategoryList()){
			genericDrugSeries.set(genricDrug.getDrugCategory(), genricDrug.getTotalDrug());
		}
	
		
		genericDrugBarChart.addSeries(genericDrugSeries);
	}

	/**
	 * @return the genericDrugCategoryList
	 */
	public List<RptGenericDrugCategory> getGenericDrugCategoryList() {
		if(genericDrugCategoryList==null){
			genericDrugCategoryList=new ArrayList<RptGenericDrugCategory>();
		}
		genericDrugCategoryList=reportService.getGenericDrugCategoryData();
		return genericDrugCategoryList;
	}


	/**
	 * @param genericDrugCategoryList the genericDrugCategoryList to set
	 */
	public void setGenericDrugCategoryList(
			List<RptGenericDrugCategory> genericDrugCategoryList) {
		this.genericDrugCategoryList = genericDrugCategoryList;
	}


 /* >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> creating the Method for Accessing the View from View List>>>>>>>>>>>>>>*/
	
	public void createTherpeuticAreaDrug()
	{ 
		therpeuticAreaBarChart=new 	PieChartModel();
		ChartSeries chrt=new ChartSeries();
		System.out.println("return the RptTherapeuticArea in bean:::::::::::::::");
		logger.info("therpeutic area is called ");
		if(therapeuticAreaList==null)
		{
			therapeuticAreaList=new ArrayList<RptTherapeuticArea>();
			therapeuticAreaList=reportService.getTherapeuticAreaList();
			for(RptTherapeuticArea rpt:therapeuticAreaList)
			{
				therpeuticAreaBarChart.set(rpt.getDrugCategory(),rpt.getTotalDrug());
			 
				
			
		}
		
		
		
		/*therpeuticAreaBarChart.set("Warfarin", 30);
		therpeuticAreaBarChart.set("Clopidogrel",50 );
		therpeuticAreaBarChart.set("amlodipine", 70);
		therpeuticAreaBarChart.set("alendronate", 90);*/
		
		}
		
	
		
		
	}
		
	

	/**
	 * @return the therapeuticAreaList
	 */
	public List<RptTherapeuticArea>  getTherapeuticAreaList() {
		
		
		
		 return therapeuticAreaList;
	}

	/**
	 * @param therapeuticAreaList the therapeuticAreaList to set
	 */
	
	public void setTherapeuticAreaList(List<RptTherapeuticArea> therapeuticAreaList) {
		this.therapeuticAreaList = therapeuticAreaList;
	}


	public void setTherpeuticAreaBarChart(PieChartModel therpeuticAreaBarChart) {
		this.therpeuticAreaBarChart = therpeuticAreaBarChart;
	}


	/**
	 * @return the therpeuticAreaBarChart
	 */
	public PieChartModel getTherpeuticAreaBarChart() {
		return therpeuticAreaBarChart;
	}


	/**
	 * @return the minClinicPatient
	 */
	public int getMinClinicPatient() {
		minClinicPatient=reportService.getMinPatientInClinic();
		return minClinicPatient;
	}


	/**
	 * @param minClinicPatient the minClinicPatient to set
	 */
	public void setMinClinicPatient(int minClinicPatient) {
		this.minClinicPatient = minClinicPatient;
	}


	/**
	 * @return the maxClinicPatient
	 */
	public int getMaxClinicPatient() {
		maxClinicPatient=reportService.getMaxValueOfPatientInClinic();
		maxClinicPatient=maxClinicPatient+(maxClinicPatient*10)/100;
		System.out.println("getMaxClinicPatient Report managed bean ++++"+maxClinicPatient);
		return maxClinicPatient;
	}


	/**
	 * @param maxClinicPatient the maxClinicPatient to set
	 */
	public void setMaxClinicPatient(int maxClinicPatient) {
		this.maxClinicPatient = maxClinicPatient;
	}


	public void setTherapeuticAreaPieChart(List< RptTherapeuticArea> therapeuticAreaPieChart) {
		this.therapeuticAreaPieChart = therapeuticAreaPieChart;
	}

/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>D3 Js Pie Chart For RPT Area>>>>>>>>>>>>>>>>>>>>>>>>
 * @Author Sanket Singh
 * 
 * */
	public String  getTherapeuticAreaPieChart() {
		
		logger.info("dao method:::::::::::");
		
			List< RptTherapeuticArea> therapeuticAreaPieChart=new ArrayList<RptTherapeuticArea>();
			
			for(RptTherapeuticArea rpt:therapeuticAreaList)
			{
				//therpeuticAreaBarChart.set(rpt.getDrugCategory(),rpt.getTotalDrug());
			       
				RptTherapeuticArea rptTherapeuticArea=new RptTherapeuticArea();
				rptTherapeuticArea.setDrugCategory(rpt.getDrugCategory());
				rptTherapeuticArea.setTotalDrug(rpt.getTotalDrug());
				therapeuticAreaPieChart.add(rptTherapeuticArea);
			
		}
		
	
	//System.out.println("List size Anand::"+therapeuticAreaPieChart.size());
		 return  new Gson().toJson(therapeuticAreaPieChart);
	
	}


	public void setDrugClassCategoryBarList(List<RptDrugClassCategory> drugClassCategoryBarList) {
		this.drugClassCategoryBarList = drugClassCategoryBarList;
	}


	public String  getDrugClassCategoryBarList() {
		List<RptDrugClassCategory> drugClassCategoryBarList=new ArrayList<RptDrugClassCategory>();
	
		for(RptDrugClassCategory drugClass:getDrugClassCategoryList()){
		
			
			RptDrugClassCategory rptDrugClassCategory=new RptDrugClassCategory();
			rptDrugClassCategory.setDrugCategory(drugClass.getDrugCategory());
			rptDrugClassCategory.setTotalDrug(drugClass.getTotalDrug());
			drugClassCategoryBarList.add(rptDrugClassCategory);
			
		}
	
		
		
		return  new Gson().toJson(drugClassCategoryBarList);
	}


	public void setGenericDrugCategoryBarChartList(
			List<RptGenericDrugCategory> genericDrugCategoryBarChartList) {
		this.genericDrugCategoryBarChartList = genericDrugCategoryBarChartList;
	}


	public String getGenericDrugCategoryBarChartList() {
			if(genericDrugCategoryBarChartList==null){
				List<RptGenericDrugCategory>genericDrugCategoryBarChartList=new ArrayList<RptGenericDrugCategory>();
			}
	
			genericDrugCategoryBarChartList=reportService.getGenericDrugCategoryData();
		//	System.out.println("in bean list came with the size::"+genericDrugCategoryBarChartList.size());
	
		int count=0;
		for(RptGenericDrugCategory genricDrug:getGenericDrugCategoryList()){
			System.out.println("counter is "+count);
			count++;
			RptGenericDrugCategory rptGenericDrug=new RptGenericDrugCategory();
			
			/*rptGenericDrug.setDrugCategory(genricDrug.getDrugCategory());
			rptGenericDrug.setTotalDrug(rptGenericDrug.getTotalDrug());
			genericDrugCategoryBarChartList.add(rptGenericDrug);*/
			//System.out.println("genericDrugCategoryBarChartList===loop======="+genericDrugCategoryBarChartList.size());
		}
		//System.out.println("genericDrugCategoryBarChartList=========="+genericDrugCategoryBarChartList.size());
		
		return  new Gson().toJson(genericDrugCategoryBarChartList);
	}


	public void setClinicPatientDetailBarList(
			List<ClinicPatientDetail> clinicPatientDetailBarList) {
		this.clinicPatientDetailBarList = clinicPatientDetailBarList;
	}


	
	/*clinic Bar Chart  perticular Provider
	 * 
	 * Author  @ Sanket singh
	 * */
	public String  getClinicPatientDetailBarList() {
		
			clinicPatientDetailBarList=new ArrayList<ClinicPatientDetail>();
		
	   //System.out.println("dao method :::::::");
		for(ClinicPatientDetail clPat:getClinicPatientDetailList()){
			
			ClinicPatientDetail clinicPatient=new ClinicPatientDetail();
			clinicPatient.setClinicName(clPat.getClinicName());
			clinicPatient.setTotalPatient(clPat.getTotalPatient());
			clinicPatientDetailBarList.add(clinicPatient);
			//System.out.println("patient list===="+clinicPatientDetailBarList.size());
		}
		
		  return  new Gson().toJson(clinicPatientDetailBarList);
	}
	
	
	public void setRptDrugCategoryBarList(
			List<RptDrugCategory> rptDrugCategoryBarList) {
		this.rptDrugCategoryBarList = rptDrugCategoryBarList;
	}
	
	public void setRptMedByDrugCategoryBarList(
			List<RptDrugCategory> rptMedByDrugCategoryBarList) {
		this.rptMedByDrugCategoryBarList = rptMedByDrugCategoryBarList;
	}
	
	/**
	 * @see doctorProfile.jsf 
	 * It will give all Drug Category in 
	 * 
	 * @return Json data of All Drug Category 
	 */
	public String getRptDrugCategoryBarList() {
		if(rptMedByDrugCategoryBarList==null)
		{
			rptDrugCategoryBarList=new ArrayList<RptDrugCategory>();
			for (RptDrugCategory drug : rptDrugCategoryList) {
				if(StringUtils.isNotBlank(drug.getDrugCategory()) && StringUtils.isNotEmpty(drug.getDrugCategory())){
				RptDrugCategory rptDrugCategory=new RptDrugCategory();
				rptDrugCategory.setDrugCategory(drug.getDrugCategory());
				rptDrugCategory.setTotalDrug(drug.getTotalDrug());
				System.out.println("DrugCategory::::::: "+drug.getDrugCategory()+"totalDrug::::::: "+drug.getTotalDrug());
				rptDrugCategoryBarList.add(rptDrugCategory);
				}
			  }
		}
		
		  return  new Gson().toJson(rptDrugCategoryBarList);
	}
	String levelVal4DrugChart="";
	
	
	public String getLevelVal4DrugChart() {
		return levelVal4DrugChart;
	}


	public void setLevelVal4DrugChart(String levelVal4DrugChart) {
		this.levelVal4DrugChart = levelVal4DrugChart;
	}


	public String getRptMedByDrugCategoryBarList() {
		rptMedByDrugCategoryBarList=new ArrayList<RptDrugCategory>();
		List<RptDrugCategory> rptMedByDrugCategoryBarListInternal=new ArrayList<RptDrugCategory>();
		rptMedByDrugCategoryBarListInternal = reportService.fetchDrugByCategoryChart(levelVal4DrugChart,1);
		for (RptDrugCategory drug : rptMedByDrugCategoryBarListInternal) {
			if(StringUtils.isNotBlank(drug.getDrugName()) && StringUtils.isNotEmpty(drug.getDrugName())){
			RptDrugCategory rptDrugCategory=new RptDrugCategory();
			rptDrugCategory.setDrugName(drug.getDrugName());
			rptDrugCategory.setTotalPatient(drug.getTotalPatient());
			System.out.println("DrugName::::::: "+drug.getDrugName()+"totalPatient::::::: "+drug.getTotalPatient());
			rptMedByDrugCategoryBarList.add(rptDrugCategory);
			}
		  }
		  return  new Gson().toJson(rptMedByDrugCategoryBarList);
	}
public String fetchDrugByCategoryChart()
{
	System.out.println("Going to fetch list of med for clicked ... "+levelVal4DrugChart);

	getRptMedByDrugCategoryBarList();
	/*RequestContext.getCurrentInstance().execute("PF('drugDrillDownChartDivDialog').show()");*/
	
	return null;
}
	public void setSpecialDrugCategoryBarList(
			List<RptSpecialDrugCategory> specialDrugCategoryBarList) {
		this.specialDrugCategoryBarList = specialDrugCategoryBarList;
	}

/*SpecialDrug Bar chart for D3 Js
 * Author @ Sanket singh
 * 
 * */
	public String getSpecialDrugCategoryBarList() {
		specialDrugCategoryBarList=new ArrayList<RptSpecialDrugCategory>();
		for(RptSpecialDrugCategory specialDrug:getSpecialDrugCategoryList()){
	
			RptSpecialDrugCategory rptSpecialDrug=new RptSpecialDrugCategory();
			rptSpecialDrug.setDrugCategory(specialDrug.getDrugCategory());
			rptSpecialDrug.setTotalDrug(specialDrug.getTotalDrug());
			specialDrugCategoryBarList.add(rptSpecialDrug);
		}
		
	
		return  new Gson().toJson(specialDrugCategoryBarList);
	}


	/**
	 * @return the acoMeasureList
	 */
	public List<ACOMeasure> getAcoMeasureList() {
		if(acoMeasureList==null){
			acoMeasureList=new ArrayList<ACOMeasure>();
		}
		//acoMeasureList=reportService.getAcoMeasureValue();
		return acoMeasureList;
	}


	/**
	 * @param acoMeasureList the acoMeasureList to set
	 */
	public void setAcoMeasureList(List<ACOMeasure> acoMeasureList) {
		this.acoMeasureList = acoMeasureList;
	}

	/**
	 * Measure 28 Value for High Chart 
	 * @return
	 */
	 public void aco28MeasureValue(){
		 logger.info("aco28MeasureValue:::::::::::::");
		ACOMeasure acoMeasure=reportService.getAcoMeasureValue();
		measure28Value=Math.round(acoMeasure.getMeasure28()*100.0)/100.0;
		measure30Value=Math.round(acoMeasure.getMeasure30()*100.0)/100.0;
		measure32Value=Math.round(acoMeasure.getMeasure32()*100.0)/100.0;
		measure33Value=Math.round(acoMeasure.getMeasure33()*100.0)/100.0;
		measure14Value=Math.round(acoMeasure.getMeasure14()*100.0)/100.0;
		measure15Value=Math.round(acoMeasure.getMeasure15()*100.0)/100.0;
		measure29Value=Math.round(acoMeasure.getMeasure29()*100.0)/100.0;
		measure26Value=Math.round(acoMeasure.getMeasure26()*100.0)/100.0;
		logger.info("measure30Value::::::::::"+measure30Value);
		logger.info("measure28Value ::::::::"+measure28Value);
		
		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam("measure28", measure28Value);
		context.addCallbackParam("measure30", measure30Value);
		context.addCallbackParam("measure32", measure32Value);
		context.addCallbackParam("measure33", measure33Value);
		context.addCallbackParam("measure14", measure14Value);
		context.addCallbackParam("measure15", measure15Value);
		context.addCallbackParam("measure29", measure29Value);
		context.addCallbackParam("measure26", measure26Value);
	 }


	
	/**
	 * Call ACO Measure28 Proc 
	 * @throws SQLException 
	 * @throws HibernateException 
	 * 
	 */
	public void callACOMeasure28() throws HibernateException, SQLException{
		reportService.callACOMeasure28Proc();
		aco28MeasureValue();
	}


	public double getMeasure28Value() {
		return measure28Value;
	}


	public void setMeasure28Value(double measure28Value) {
		this.measure28Value = measure28Value;
	}


	public double getMeasure30Value() {
		return measure30Value;
	}


	public void setMeasure30Value(double measure30Value) {
		this.measure30Value = measure30Value;
	}


	public double getMeasure32Value() {
		return measure32Value;
	}


	public void setMeasure32Value(double measure32Value) {
		this.measure32Value = measure32Value;
	}


	public double getMeasure33Value() {
		return measure33Value;
	}


	public void setMeasure33Value(double measure33Value) {
		this.measure33Value = measure33Value;
	}


	public double getMeasure14Value() {
		return measure14Value;
	}


	public void setMeasure14Value(double measure14Value) {
		this.measure14Value = measure14Value;
	}


	public double getMeasure15Value() {
		return measure15Value;
	}


	public void setMeasure15Value(double measure15Value) {
		this.measure15Value = measure15Value;
	}


	public double getMeasure29Value() {
		return measure29Value;
	}


	public void setMeasure29Value(double measure29Value) {
		this.measure29Value = measure29Value;
	}


	public double getMeasure26Value() {
		return measure26Value;
	}


	public void setMeasure26Value(double measure26Value) {
		this.measure26Value = measure26Value;
	}
    /**
     * Method used in doctorProfile.jsf 
     * Get Data Of Analytics Detail Data
     * 
     */
   public void patientAnalyticsDetails(){
	   logger.info("patientAnalyticsDetails method starts::::::::::");
	   try{
		   analyticsDetail=reportService.getAnliticsData(); 
	   }catch(Exception e){
		   e.printStackTrace();
		   logger.info("error at patientAnalyticsDetails method in reportManageBean:::::::");
	   }
	   logger.info("PopuplationHealth Total Patient "+analyticsDetail.getTotalPatient()+"optimizationphrmacogenomics:::"+analyticsDetail.getOptimizedWithPharmecogenomics());
   }
	

}
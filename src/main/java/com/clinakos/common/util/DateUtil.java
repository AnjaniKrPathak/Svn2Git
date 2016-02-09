package com.clinakos.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.log4j.Logger;



public class DateUtil {
	
	/*
	 * author: Gopal Krishna jha
	 */
	
	 private  static Date todayDate;
		public static final Logger logger = Logger.getLogger("DateUtil.class");
	
	
	public String convertDateFormatUsingFormat(Date date,String DATE_FORMAT)
	{
		//String DATE_FORMAT = "MM/dd/yyyy";
        // Create object of SimpleDateFormat and pass the desired date format.
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        String convertDateinString=dateFormat.format(date);
        
        return convertDateinString;
	}
	
	
	public Date convertStringToDateFormat(String date,String DATE_FORMAT)
	{
		 SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		// System.out.println("::::::::date:::"+date+"::::::::DATE_FORMAT::::"+DATE_FORMAT);
		   Date convertedDate = null;
	        //String in = "2013/04/16";
	        try {
	            
	        	convertedDate = (Date) dateFormat.parse(date); 
	           System.out.println(convertedDate+"11Date parsed = " + dateFormat.format(convertedDate));
	            
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        
	     
			return convertedDate;
	}
	
	//convert date time with isd pattern GMT format
	
	public Date convertStringToDateFormatToCurrentTimeZone(String date,String DATE_FORMAT) throws ParseException
	{
		Date convertedDate = null;
		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		    convertedDate = formatter.parse(date);
		    TimeZone tx=TimeZone.getTimeZone("GMT");
		    formatter.setTimeZone(tx);
		    System.out.println("Formatted date in IST = " + formatter.format(convertedDate));
		    String istDateFormat = formatter.format(convertedDate);
		    System.out.println("date form:"+convertedDate);
		  }
		catch (ParseException e) {
			logger.error("EXCEPTION IN convertStringToDateFormatToCurrentTimeZone::::::",e);
            e.printStackTrace();
         }
		 /*SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		 System.out.println("::::::::date:::"+date+"::::::::DATE_FORMAT::::"+DATE_FORMAT);
		   Date convertedDate = null;
	        //String in = "2013/04/16";
	        try {
	            
	        	convertedDate = (Date) dateFormat.parse(date); 
	           System.out.println(convertedDate+"11Date parsed = " + dateFormat.format(convertedDate));
	            
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        
	        TimeZone tx=TimeZone.getTimeZone("Asia/Calcutta");
			  
			  //TimeZone tx=TimeZone.getDefault();
	        dateFormat.setTimeZone(tx);
			  System.out.println("Formatted date in IST = " + dateFormat.format(convertedDate));*/
			 // String istDateFormat = dateFormat.format(d);
			  //Date da=formatter.format(d);
			//  return istDateFormat;
	     
			return convertedDate;
	}
	
	
	//convert date to MM/dd/yyyy format
	public String convertDateFormat(Date date)
	{
		String DATE_FORMAT = "MM/dd/yyyy";
        // Create object of SimpleDateFormat and pass the desired date format.
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        String convertDateinString=dateFormat.format(date);
        /*Date convertedDate = null;
        try{

        	convertedDate = (Date) dateFormat.parse(convertDateinString); 
           
        }
        catch (ParseException e) {
            e.printStackTrace();
        }*/
        return convertDateinString;
	}
	
//date convert string to date format in dd/MM//yyyy
	public Date StringConvertToDate(String date)
	{
		 SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		   Date convertedDate = null;
	        //String in = "2013/04/16";
	        try {
	            
	        	convertedDate = (Date) dateFormat.parse(date); 
	            System.out.println("Date parsed = " + dateFormat.format(convertedDate));
	            
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        
	     
			return convertedDate;
		}
	
	//add date and before date  using current system date if you want after some day just pass positive parameter if you want before just pass negative parameter
	public Date AddDate(int noOfDays) {
        
        // create instance of calendar class
		Date requiredDate=null;
        Calendar currentCal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        /*System.out.println("Current date of Calendar : "
                        + dateFormat.format(currentCal.getTime()));*/
        // Use of add() method for date increment
       
        currentCal.add(Calendar.DATE, noOfDays);
        requiredDate=currentCal.getTime();
//        System.out.println(requiredDate+"After increment date of Calendar : "
//                + dateFormat.format(currentCal.getTime()));
        requiredDate=currentCal.getTime();
        return requiredDate;
     }
	
public Date AddDate(Date date,int noOfDays) {
        
        // create instance of calendar class
	Date requiredDate=null;
	 // System.out.println(":::::::inside date util:::"+date+":::::"+noOfDays);
	Calendar cal = Calendar.getInstance();  
	cal.setTime(date);  
	cal.add(Calendar.DATE, noOfDays);
    requiredDate=cal.getTime();
       System.out.println("::::requiredDate::::"+requiredDate);
    return requiredDate;
     }

//----------------return date in yyyy-MM-dd format

	public String DateInMySqlPattern(Date date)
	{
		logger.info("inside DateInMySqlPattern:"+date);
		String DATE_FORMAT = "yyyy-MM-dd";
	    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
	    String convertDateinString=dateFormat.format(date);
	    
	    return convertDateinString;
	}
	
//-------------
	
	public String DateAndTimeInMySqlPattern(Date date)
	{
		logger.info("inside DateInMySqlPattern:"+date);
		String DATE_FORMAT = "yyyy-MM-dd";
	    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
	    String convertDateinString=dateFormat.format(date);
	    
	    String Time_FORMAT="HH:mm:ss";
		System.out.println("inside DateInMySqlPattern dateUtil:date_format:"+Time_FORMAT);
	    SimpleDateFormat dateFormatForTime = new SimpleDateFormat(Time_FORMAT);
	    String convertTimeinString=dateFormatForTime.format(new Date());
	    System.out.println(new Date()+"convertDateinString:::::::"+convertDateinString);
	    System.out.println(new Date()+"convertDateinString:::::::"+convertDateinString+" "+convertTimeinString);
	    String formatedDate=convertDateinString+" "+convertTimeinString;
	    return formatedDate;
	}


	public static Date getTodayDate() {
		logger.info("today date class");
		return todayDate=new Date();
	}

	public  Date convertTimezone(Date toConvert) {
		DateFormat formatter= new SimpleDateFormat("MM/dd/yyyy HH:mm:ss Z");
		  Calendar instance2 = new GregorianCalendar();
		  formatter.setTimeZone(TimeZone.getTimeZone("EST"));
		  String time="",time1;
		  time1=formatter.format(instance2.getTime());
		  System.out.println(new Date()+"date:::::::::::"+time1);
		  time=time1.substring(11, 20).trim();
		  int hour,minute = 0,sec = 0,year=0,month=0,date=0;
		  month=Integer.parseInt(time1.substring(0,2));
		  date=Integer.parseInt(time1.substring(3,5));
		  year=Integer.parseInt(time1.substring(6,10));
		 
		 hour=Integer.parseInt(time.substring(0,2));
		  minute=Integer.parseInt(time.substring(3,5));
		 sec=Integer.parseInt(time.substring(6,8));
		  System.out.println("1:::::::::::::"+time1.trim()+"::year:"+year+":::month:"+month+":::::::::::date"+date);  
		  System.out.println(":::::::::::::"+time.trim()+"::hour:"+hour+":::minute:"+minute+":::::::::::sec"+sec);
		 // System.out.println(formatter.format(c2.getTime()));
		  Calendar c3=Calendar.getInstance();
		  c3.set(Calendar.YEAR,year);
		  c3.set(Calendar.MONTH,month-1);
		  c3.set(Calendar.DATE,date);
		  c3.set(Calendar.HOUR,hour);
		  c3.set(Calendar.MINUTE,minute);
		  c3.set(Calendar.SECOND,sec);     
		  //c3.set(Calendar.YEAR,2014);
		  
		 // Date d1=new Date(114, month-1, date);
		  Date d4=c3.getTime();
		  System.out.println(c3.getTime()+"::::::::::::+"+d4);
		  return d4;
	}
	
	

	public  Date convertTmezoneFromSystem(Date toConvert) {
	    //Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("EST"));
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(toConvert);
	   // calendar.setTimeZone(TimeZone.getTimeZone("EST"));
	    //calendar.setTime(toConvert);
	   System.out.println(toConvert+":::::::::::::::previusly time zone:::"+calendar.getTime());
	   calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
	  // calendar.setTimeZone(TimeZone.getTimeZone("EST"));
	  //  Calendar currentDt = new GregorianCalendar(TimeZone.getTimeZone("EST"));//, EN_US_LOCALE);
	   System.out.println(calendar.getTimeZone().getDisplayName()+"currentDt.getTime():::::::::::::::::::::"+calendar.getTime());
	    return calendar.getTime();
	}

	public static void setTodayDate(Date todayDate) {
		DateUtil.todayDate = todayDate;
	}


	public int findNumberOfDays(Date startDate, Date endDate) {
		logger.info("findNumberOfDays :::startDate "+startDate+"::::endDate:"+endDate);
		int noOfDays=0;
		noOfDays=(int) ((endDate.getTime() - startDate.getTime())/(24 * 60 * 60 * 1000));
		System.out.println("::::noOfDays:::::::::"+noOfDays);
		return noOfDays;
	}


	public Date changeDateTimeToDate(Date startDate, String datepattern) {
		logger.info("startDate:::"+startDate+":datepattern::"+datepattern);
		SimpleDateFormat sdf = new SimpleDateFormat(datepattern);
		DateFormat formatter = new SimpleDateFormat(datepattern);
		Date convertedDate = null;
		try {
			String changedate=sdf.format(startDate);
			logger.info(" changedate in str"+changedate);
			 convertedDate = (Date) formatter.parse(changedate);
			System.out.println(convertedDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return convertedDate;
	}

}

package com.clinakos.common.util;




import java.io.IOException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Properties;

import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailAuthenticationException;
import static com.clinakos.common.util.ClinakosConstant.ALLOWED_RANDOM_ALPHA_NUMERIC_VALUE;


public class NotificationUtil {
	
	
	
	public boolean sendMail( String email,String subject,String msg) throws MessagingException
	{
		boolean result = false;
		try
		{
			System.out.println("===== Notification Method =========");
			System.out.println("========== email ============="+email);
			FacesContext fc = FacesContext.getCurrentInstance();
    		Properties prop = new Properties();
    		prop.load(getClass().getClassLoader().getResourceAsStream("/com/clinakos/properties/application.properties"));
    		final String EmailId =prop.getProperty("emailId") ; /* mailId to send mail */
    		System.out.println("====== EmailId ========="+EmailId);
    		final String password = prop.getProperty("password");   		
    		System.out.println("====== password ========="+password);	
               //load a properties file
    		
    		prop.put("mail.smtp.auth", "true");
    		prop.put("mail.smtp.starttls.enable", "true");
    		prop.put("mail.smtp.host", "smtpout.secureserver.net");
    		prop.put("mail.smtp.port", "3535");    		
    		Session session = Session.getInstance(prop,
    				  new javax.mail.Authenticator() {
    					protected PasswordAuthentication getPasswordAuthentication() {
    						return new PasswordAuthentication(EmailId, password);
    					}
    		
    		});
    		
    		Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(EmailId));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(email));
			message.setSubject(subject);
			message.setSentDate(new Date());
			message.setContent(msg, "text/html");
			System.out.println("===123456====== Done ==================");
			Transport.send(message);
			result=true;
			System.out.println("========= Done ==================");
		}catch (IOException ex) {
    		ex.printStackTrace();
        }
		catch (MailAuthenticationException e) {
			System.out.println("bruce@fiction.com::ajayayaa::::");
			e.getMessage();
		}
		catch (Exception e) {
			System.out.println("1234bruce@fiction.com::::::");
			result=false;
			e.getMessage();
			// TODO: handle exception
		}
		/*catch (Throwable e) {
			// TODO: handle exception
			e.getMessage();
		}*/
		return result;
	}
	
/************@author Saurabh
 * for recover password url
 * 
 */
	public String generateRandomAlphaNumeric(int noOfDigitRandomNo) {
		 StringBuffer result = new StringBuffer();  
		    try
		    {
		    	SecureRandom rng = new SecureRandom();  
			    for( int i=0; i<noOfDigitRandomNo; i++ )  
			    {  
			        result.append( ALLOWED_RANDOM_ALPHA_NUMERIC_VALUE.charAt(  
			                                rng.nextInt( ALLOWED_RANDOM_ALPHA_NUMERIC_VALUE.length())));  
			    }  
		    }
		    catch(Exception e)
		    {
		    	e.printStackTrace();
		    }
		  
		    return result.toString(); 
		//return null;
	}
}

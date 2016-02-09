package com.clinakos.viewController.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.clinakos.viewController.webservicMangedBean.NcUpdate1WSBean;

/**
 * The Image servlet for showing imgae from absolute path.
 * 
 * @author Gopal Krishna jha from lumbini
 */
public class ImageServlet extends HttpServlet {
	public static final Logger logger = Logger.getLogger("ImageServlet.class");

	public static Properties prop;// = new Properties();
	private Properties properties;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException

	{
		logger.info("ImageServlet class startted");
		
		try {
			/*//taking the name from user interface 
			String filename = request.getParameter("file");
			//load  the properties file from class path
			prop=new Properties();
			prop.load(ImageServlet.class.getClassLoader().getResourceAsStream(
					"com/clinakos/properties/image.properties"));
			// prop.load(new
			// FileInputStream("D:\\logfile\\images\\image.properties"));
			//taking the  directory value from properties file
			String directory = prop.getProperty("imageDirectory");
			File file = new File(directory + filename);
			if (!file.exists()) {
				String defaultname = prop.getProperty("imagedefaultName");
				file = new File(directory + defaultname);
			}
			// System.out.println("::::::::::::::::::::::::::::::::>> heloooo fo checking::::"+directory);
			BufferedInputStream in = new BufferedInputStream(
					new FileInputStream(file));
			byte[] bytes = new byte[in.available()];

			in.read(bytes);
			in.close();

			// Write image contents to response.
			response.getOutputStream().write(bytes);*/
			String file=request.getParameter("file");
			try{
				BufferedInputStream in=new BufferedInputStream(new FileInputStream(getProperties().getProperty("drugImageDirectory")+file));
				byte[] bytes=new byte[in.available()];
				in.read(bytes);
				in.close();
				response.getOutputStream().write(bytes);
			}
			catch(FileNotFoundException fe){
				fe.printStackTrace();
			}
			

		} catch (IOException e) {
			logger.error("ImageServlet class", e);
			e.printStackTrace();

		}

	}
	
	/**
	 * @return the properties
	 */
	public Properties getProperties() {
		if(properties==null){
			properties=new Properties();
		}
		try {
			properties.load(ImageServlet.class.getClassLoader()
					.getResourceAsStream(
							"/com/clinakos/properties/image.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties;
	}
	/**
	 * @param properties the properties to set
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

}
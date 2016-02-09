/**
 * 
 */
package com.clinakos.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author SAURABH
 *
 */

@Component
public class ScheduleProcCall {
	

	//@Scheduled(fixedDelay = 5000)
	@Scheduled(fixedRate = 1000)
	void demoServiceMethod()
	{
	    System.out.println("Method executed at every 5 seconds. Current time is ::::: ScheduleProcCall");
	}


}

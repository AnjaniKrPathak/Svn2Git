package com.clinakos.viewController.controller;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;

public class RunScheduler {
	@Scheduled(fixedDelay = 5000)
	//@Scheduled(fixedRate = 5000)
	public void demoServiceMethod()
	{
	    System.out.println("Method executed at every 5 seconds. Current time is :: "+ new Date());
	}
}

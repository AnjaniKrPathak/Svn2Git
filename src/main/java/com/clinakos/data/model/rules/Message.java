package com.clinakos.data.model.rules;

import java.util.HashMap;
import java.util.Map;

public class Message {
	
	private String id;
	private String medicine;
	private Patient patient;
	private Request request;
	private Map<String, Object> payload = new HashMap<String, Object>();
	private String response="";
	private String test;
	
	public String getTest(){
	    return test;
	}
	
	public Message() {
		// TODO Auto-generated constructor stub
	}
	
	public Message(String id,String medicine,Patient patient,Request type) {
		this.id = id;
		this.medicine = medicine;
		this.patient = patient;
		this.request = type;
	}

	public void put(String key, Object value){
		payload.put(key, value);
	}
	
	public Object get(String key){
		return payload.get(key);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMedicine() {
		return medicine;
	}

	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Map<String, Object> getPayload() {
		return payload;
	}

	public void setPayload(Map<String, Object> payload) {
		this.payload = payload;
	}
	
	public void addParam(String paramName, Object paramValue){
		payload.put(paramName, paramValue);
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	

}

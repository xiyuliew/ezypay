package com.ezypay.backendapi;


import javax.ws.rs.core.MediaType;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.text.html.parser.Entity;
import javax.validation.constraints.Min;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping ("/subscription")
@Validated
public class SubscriptionController{
	@PostMapping(value="/create", produces = MediaType.APPLICATION_JSON)
	public String createSubscription(
			@RequestParam @Min(0) int totalAmount,
			@RequestParam String subscriptionType,
			@RequestParam(required = false) String typeOfDay,
			@RequestParam String startDate,
			@RequestParam String endDate
			) throws Exception {
		ArrayList<String> invoiceDates = new ArrayList<String>(); 
		try {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			
			Calendar startCalendar = Calendar.getInstance();
			Calendar endCalendar = Calendar.getInstance();
			Calendar checkCalendar = Calendar.getInstance();
			
			startCalendar.setTime(df.parse(startDate));
			endCalendar.setTime(df.parse(endDate));
			checkCalendar.setTime(df.parse(endDate));		
			checkCalendar.add(Calendar.MONTH, -3);
			
			subscriptionType = subscriptionType.toUpperCase();
			
			if(endCalendar.compareTo(startCalendar) < 0 ) {
				throw new Exception("End Date cannot earlier be than Start Date");
			}
			
			if(checkCalendar.compareTo(startCalendar) <= 0) {
				if(subscriptionType.equals("DAILY")) {
					while(endCalendar.compareTo(startCalendar) >= 1) {
						invoiceDates.add(df.format(startCalendar.getTime()));
						startCalendar.add(Calendar.DATE, 1);
					}
				}else if(subscriptionType.equals("WEEKLY")) {
					while(endCalendar.compareTo(startCalendar) >= 0) {
						invoiceDates.add(df.format(startCalendar.getTime()));
						startCalendar.add(Calendar.WEEK_OF_YEAR, 1);
					}
				}else if(subscriptionType.equals("MONTHLY")) {
					while(endCalendar.compareTo(startCalendar) >= 0) {
						invoiceDates.add(df.format(startCalendar.getTime()));
						startCalendar.add(Calendar.MONTH, 1);
					}
				}
			}else {
				throw new Exception("Only Three months Subscription is allowed");
			}
		
		
			String jsonString = new JSONObject()
								.put("amount",totalAmount)
								.put("subscriptionType",subscriptionType)
								.put("invoiceDates",invoiceDates)
								.toString();
			return jsonString;
		}catch(Exception e) {
			throw e;
		}

		}
}
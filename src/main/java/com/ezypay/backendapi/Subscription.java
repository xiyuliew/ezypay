package com.ezypay.backendapi;

import java.util.Calendar;

public class Subscription {
	private String totalAmount;
	private String subscriptionType;
	private String typeOfDay;
	private Calendar startDate;
	private Calendar endDate;
	public Subscription() {}
	
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getSubscriptionType() {
		return subscriptionType;
	}
	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}
	public String getTypeOfDay() {
		return typeOfDay;
	}
	public void setTypeOfDay(String typeOfDay) {
		this.typeOfDay = typeOfDay;
	}
	public Calendar getStartDate() {
		return startDate;
	}
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}
	public Calendar getEndDate() {
		return endDate;
	}
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}
	
	
}
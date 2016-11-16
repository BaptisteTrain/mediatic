package fr.dta.mediatic.model;

import java.util.*;
import javax.persistence.*;

@Embeddable
public class Subscription {
	
	/* COLUMNS */
	
	@Column(name = "amount")
	private int amount;
	
	@Column(name = "paymentDate")
	private Date paymentDate;
	
	@Column(name = "subscriptionEndDate")
	@Temporal(TemporalType.DATE)
	private Date subscriptionEndDate;
	
	/* CONSTRUCTORS */
	
	public Subscription() {
	}
	
	public Subscription(int amount, Date paymentDate) {
		this.amount = amount;
		this.paymentDate = paymentDate;
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(paymentDate);
		cal.add(Calendar.YEAR, 1);

        subscriptionEndDate = cal.getTime();
	}
	
	/* GETTERS / SETTERS */
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public Date getPaymentDate() {
		return paymentDate;
	}
	
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
		
		Calendar cal = Calendar.getInstance();
		
		if (subscriptionEndDate == null) {
			cal.setTime(this.paymentDate);
			cal.add(Calendar.YEAR, 1);
		}
		else {
			cal.setTime(this.subscriptionEndDate);
			cal.add(Calendar.YEAR, 1);
		}
		
        subscriptionEndDate = cal.getTime();
	}
	
	public Date getSubscriptionEndDate() {
		return subscriptionEndDate;
	}
	
	public void setSubscriptionEndDate(Date subscriptionEndDate) {
		this.subscriptionEndDate = subscriptionEndDate;
	}
}

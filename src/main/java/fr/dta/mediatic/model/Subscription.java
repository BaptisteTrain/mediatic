package fr.dta.mediatic.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Subscription extends AbstractEntity {

    @NotBlank
    @Column(name = "amount")
    private int amount;

    @NotBlank
    @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}")
    @Column(name = "paymentDate")
    @Temporal(TemporalType.DATE)
    private Date paymentDate;

    @NotBlank
    @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}")
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

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

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
	} else {
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

    @Override
    public String toString() {
	return "Subscription [id=" + id + ", amount=" + amount + ", paymentDate=" + paymentDate + ", subscriptionEndDate=" + subscriptionEndDate + "]";
    }

}

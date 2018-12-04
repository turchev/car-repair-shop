package com.haulmont.testtask.data;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Orders extends AbstractEntity {

	@Column(name = "description", length = 5000)
	private String description = "";

	@ManyToOne
	@JoinColumn(name = "client_id")	
	private Long clientId;

	@ManyToOne
	@JoinColumn(name = "mechanic_id")
	private Long mechanicId;

	@Column(name = "status")
	private String status;

	@Column(name = "date_creat")
	private Timestamp dateCreat;

	@Column(name = "completion_date")
	private Timestamp completionDate;

	@Column(name = "price")
	private BigDecimal price;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getMechanicId() {
		return mechanicId;
	}

	public void setMechanicId(Long mechanicId) {
		this.mechanicId = mechanicId;
	}

	public Timestamp getDateCreat() {
		return dateCreat;
	}

	public void setDateCreat(Timestamp dateCreat) {
		this.dateCreat = dateCreat;
	}

	public Timestamp getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(Timestamp completionDate) {
		this.completionDate = completionDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(OrderStatusType status) {
		this.status = status.name();
	}

	@Override
	public String toString() {
		return description + " " + status;
	}
}

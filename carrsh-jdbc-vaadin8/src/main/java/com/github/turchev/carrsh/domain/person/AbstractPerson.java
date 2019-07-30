package com.github.turchev.carrsh.domain.person;

import com.github.turchev.carrsh.domain.AbstractEntity;

public abstract class AbstractPerson extends AbstractEntity {
	protected String lastName;
	protected String firstName;
	protected String patronnymic;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPatronnymic() {
		return patronnymic;
	}

	public void setPatronnymic(String patronnymic) {
		this.patronnymic = patronnymic;
	}
}

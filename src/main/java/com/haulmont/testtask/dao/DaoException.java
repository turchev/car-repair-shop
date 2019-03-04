package com.haulmont.testtask.dao;

@SuppressWarnings("serial")
public class DaoException extends Exception {

	public DaoException(Throwable throwable) {
		super(throwable);
	}

	public DaoException(String string) {
		super(string);
	}
}

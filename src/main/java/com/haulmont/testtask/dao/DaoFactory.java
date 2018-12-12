package com.haulmont.testtask.dao;

import java.sql.SQLException;

public abstract class DaoFactory {

	public abstract ClientDao getClientDAO() throws DaoException;
	public abstract MechanicDao getMechanicDao() throws DaoException;
	public abstract OrdersDao getOrderDao() throws DaoException;	
	public abstract boolean testConnection();
	public abstract void shutdown() throws SQLException;

	public static DaoFactory get(TypeDb type) throws DaoException {
		try {
			switch (type) {
			case H2:
				return DaoFactoryH2.getInstance();
			case HSQLDB:
				return DaoFactoryHSQLDB.getInstance();
			default:
				throw new IllegalArgumentException(type.toString());
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
		
	}

	public enum TypeDb {
		HSQLDB, H2
	}
}

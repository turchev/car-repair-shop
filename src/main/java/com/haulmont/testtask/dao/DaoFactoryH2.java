package com.haulmont.testtask.dao;

import java.sql.SQLException;

public  class DaoFactoryH2 extends DaoFactory{

	@Override
	public
	ClientDao getClientDAO() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public
	MechanicDao getMechanicDao() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public
	OrdersDao getOrderDao() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	public static DaoFactory getInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean testConnection() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void shutdown() throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
}
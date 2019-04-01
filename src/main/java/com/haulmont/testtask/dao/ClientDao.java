package com.haulmont.testtask.dao;

import java.util.List;

import com.haulmont.testtask.domain.ShortName;
import com.haulmont.testtask.domain.person.Client;

public interface ClientDao extends IDao<Client> {

	List<String> getLastNameList() throws DaoException;

	List<ShortName<Client>> findAllShortName() throws DaoException;

	ShortName<Client> getFioById(Long id) throws DaoException;

}

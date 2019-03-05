package com.haulmont.testtask.view;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.haulmont.testtask.dao.ClientDao;
import com.haulmont.testtask.dao.DaoException;
import com.haulmont.testtask.dao.DaoFactory;
import com.haulmont.testtask.ds.DsType;
import com.haulmont.testtask.entity.Client;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class ClientWindowEdit extends ClientWindowAbstract {
	private static final Logger LOG = LogManager.getLogger();
	private ClientDao clientDao;
	private Long id;

	public ClientWindowEdit(Long id) throws UiException {				 
		try {
			super.setCaption("Редактировать данные клиента");
			this.id = id;
			clientDao = DaoFactory.getFactory(DsType.HSQLDB).getClientDAO();
			Client client = clientDao.findById(id);
			super.txtFirstName.setValue(client.getFirstName());
			super.txtLastName.setValue(client.getLastName());
			super.txtPatronnymic.setValue(client.getPatronnymic());
			super.txtPhone.setValue(client.getPhone());
			LOG.debug("Created ClientWindowEdit");
//			throw new UiException("Лови затрещину от ClientWindowEdit!!!");			
		} catch (Exception e) {						
			throw new UiException(e);			
		}		
	}	
	
	@Override
	protected void btnCancelClick() {
		close();
	}

	@Override
	protected synchronized void btnAppleClick() {
		Client client = new Client(txtLastName.getValue(), txtFirstName.getValue(), txtPatronnymic.getValue());	
		client.setPhone(txtPhone.getValue());
		client.setId(id);
		try {
			clientDao.update(client);
			UI.getCurrent().getNavigator().navigateTo(ClientView.NAME);
			close();
		} catch (DaoException e) {
			LOG.error(e);
		}
	}
}

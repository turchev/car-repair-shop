package com.github.turchev.carrepairshop.view.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.turchev.carrepairshop.domain.person.Client;
import com.github.turchev.carrepairshop.view.UiException;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.data.binder.ValidationException;


@SuppressWarnings("serial")
class ClientWindowEdit extends ClientWindowAbstract {
	private static final Logger LOG = LogManager.getLogger();
	private static final String LABEL = "Редактировать данные клиента";
	private Long id;

	protected ClientWindowEdit(Long id) throws UiException {
		try {
			super.add(new Label(LABEL));
//			super.setCaption("Редактировать данные клиента");
			this.id = id;
			Client client = clientDao.findById(id);
			super.txtFirstName.setValue(client.getFirstName());
			super.txtLastName.setValue(client.getLastName());
			super.txtPatronnymic.setValue(client.getPatronnymic());
			super.txtPhone.setValue(client.getPhone());
		} catch (Exception e) {
			throw new UiException(e);
		}
		LOG.debug("Created ClientWindowEdit");
	}

	@Override
	protected void btnCancelClick() {
		close();
	}

	@Override
	protected synchronized void btnAppleClick() {
		try {
			Client client = new Client();
			super.binder.writeBean(client);			
			client.setId(id);
			super.clientDao.update(client);
//			UI.getCurrent().getNavigator().navigateTo(ClientView.NAME);
			UI.getCurrent().navigate(ClientView.NAME);
			close();
		} catch (ValidationException ev) {
			LOG.debug(ev);
			Notification.show("Проверьте корректность заполнения полей данных");			
		} catch (Exception e) {
			LOG.error(e);
			Notification.show("Не удалось сохранить запись");
		}
	}
}

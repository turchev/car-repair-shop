package com.haulmont.carrepairshop.view.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.haulmont.carrepairshop.domain.person.Client;
import com.haulmont.carrepairshop.view.UiException;
import com.vaadin.data.ValidationException;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
class ClientWindowAdd extends ClientWindowAbstract {
	private static final Logger LOG = LogManager.getLogger();

	protected ClientWindowAdd() throws UiException {
		super.setCaption("Создать запись о клиенте");
		LOG.debug("Created ClientWindowAdd");
	}

	@Override
	protected void btnCancelClick() {
		close();
	}

	@Override
	protected void btnAppleClick() {
		try {
			Client client = new Client();
			super.binder.writeBean(client);
			super.clientDao.create(client);
			UI.getCurrent().getNavigator().navigateTo(ClientView.NAME);
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

package com.github.turchev.carrepairshop.view.mechanic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.turchev.carrepairshop.domain.person.Mechanic;
import com.github.turchev.carrepairshop.view.UiException;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.data.binder.ValidationException;

class MechanicDialogAdd extends MechanicDialogAbstract {
	private static final Logger LOG = LogManager.getLogger();
	private static final String LABEL = "Создать запись о механике";

	protected MechanicDialogAdd() throws UiException {
		try {
			super.add(new Label(LABEL));
		} catch (Exception e) {
			throw new UiException(e);
		}
		LOG.debug("Created MechanicWindowAdd");
	}

	@Override
	protected void btnCancelClick() {
		close();
	}

	@Override
	protected void btnAppleClick() {
		try {
			Mechanic mechanic = new Mechanic();
			binder.writeBean(mechanic);
			super.mechanicDao.create(mechanic);
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

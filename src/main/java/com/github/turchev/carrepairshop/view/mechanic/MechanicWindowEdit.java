package com.github.turchev.carrepairshop.view.mechanic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.turchev.carrepairshop.domain.person.Mechanic;
import com.github.turchev.carrepairshop.view.UiException;
import com.github.turchev.carrepairshop.view.client.ClientView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.data.binder.ValidationException;


@SuppressWarnings("serial")
class MechanicWindowEdit extends MechanicWindowAbstract {
	private static final Logger LOG = LogManager.getLogger();
	private static final String LABEL = "Редактировать данные механика";
	private Long id;

	protected MechanicWindowEdit(Long id) throws UiException {
		try {
//			super.setCaption("Редактировать данные механика");
			super.add(new Label(LABEL));
			this.id = id;
			Mechanic mechanic = super.mechanicDao.findById(id);
			super.txtFirstName.setValue(mechanic.getFirstName());
			super.txtLastName.setValue(mechanic.getLastName());
			super.txtPatronnymic.setValue(mechanic.getPatronnymic());
			super.txtWages.setValue(mechanic.getWages().toString());
		} catch (Exception e) {
			throw new UiException(e);
		}
		LOG.debug("Created MechanicWindowEdit");
	}

	@Override
	protected void btnCancelClick() {
		close();
	}

	@Override
	protected synchronized void btnAppleClick() {
		
		try {
			Mechanic mechanic = new Mechanic();
			binder.writeBean(mechanic);
			mechanic.setId(id);
			super.mechanicDao.update(mechanic);
//			UI.getCurrent().getNavigator().navigateTo(MechanicView.NAME);
			UI.getCurrent().navigate(MechanicView.NAME);
			close();
		} catch (ValidationException ev) {
			LOG.debug(ev);
			Notification.show("Проверьте корректность заполнения полей данных");			
		} catch (Exception e) {
			LOG.error(e);
			Notification.show("Не удалось сохранить запись");
		}
	}
	
//		try {
//			Mechanic mechanic = new Mechanic(super.txtLastName.getValue(), super.txtFirstName.getValue(),
//					super.txtPatronnymic.getValue());
//			mechanic.setWages((BigDecimal) super.dcf.parse(super.txtWages.getValue()));
//			mechanic.setId(id);
//			super.mechanicDao.update(mechanic);
//			UI.getCurrent().getNavigator().navigateTo(MechanicView.NAME);
//			close();
//		} catch (Exception e) {
//			LOG.error(e);
//			Notification.show("Не удалось сохранить запись");
//		}
//	}
}

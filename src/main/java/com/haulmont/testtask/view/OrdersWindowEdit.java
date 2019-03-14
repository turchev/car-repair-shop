package com.haulmont.testtask.view;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.haulmont.testtask.dao.DaoFactory;
import com.haulmont.testtask.dao.OrdersDao;
import com.haulmont.testtask.ds.DsType;
import com.haulmont.testtask.entity.OrdersWithFio;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class OrdersWindowEdit extends OrdersWindowAbstract {
	private static final Logger LOG = LogManager.getLogger();
	private OrdersDao ordersDao;
	private Long id;

	protected OrdersWindowEdit(Long id) throws UiException {
		try {
			super.setCaption("Редактировать заявку");
			this.id = id;
			ordersDao = DaoFactory.getFactory(DsType.HSQLDB).getOrdersDao();
			OrdersWithFio order = ordersDao.findById(id);
			ntsStatus.setValue(order.getStatus());
			cmbClient.setSelectedItem(order);
			cmbMechanic.setSelectedItem(order);
			dtfDateCreat.setValue(order.getDateCreat());
			dtfCompletionDate.setValue(order.getCompletionDate());
			txtPrice.setValue(order.getPrice().toString());
			txrDescription.setValue(order.getDescription());
		} catch (Exception e) {
			throw new UiException(e);
		}
		LOG.debug("Created OrdersWindowEdit");
	}

	@Override
	protected void btnCancelClick() {
		close();
	}

	@Override
	protected void btnAppleClick() {
		
		if (cmbClient.isEmpty()) {
			Notification.show("Выберите клиента из списка или создайте новую запись", Type.WARNING_MESSAGE);
			return;
		}
		if (cmbMechanic.isEmpty()) {
			Notification.show("Выберите механика из списка или создайте новую запись", Type.WARNING_MESSAGE);
			return;
		}
		if (ntsStatus.isEmpty()) {
			Notification.show("Задайте статус заявки", Type.WARNING_MESSAGE);
			return;
		}
		if (txrDescription.isEmpty()) {
			Notification.show("Описание заявки не может быть пустым", Type.WARNING_MESSAGE);
			return;
		}
		if (dtfDateCreat.isEmpty()) {
			Notification.show("Укажите дату заявки", Type.WARNING_MESSAGE);
			return;
		}

		try {
			OrdersWithFio order = new OrdersWithFio(txrDescription.getValue(), cmbClient.getValue().getClientId(),
					cmbMechanic.getValue().getMechanicId());
			BigDecimal price = (BigDecimal) super.dcf.parse(txtPrice.getValue());
			order.setPrice(price);
			order.setDateCreat(dtfDateCreat.getValue());
			order.setCompletionDate(dtfCompletionDate.getValue());
			order.setStatus(ntsStatus.getValue());
			order.setId(id);
			ordersDao.update(order);
			UI.getCurrent().getNavigator().navigateTo(OrdersView.NAME);
			close();
		} catch (Exception e) {
			LOG.error(e);
			Notification.show("Не удалось сохранить запись");
		}
	}
}
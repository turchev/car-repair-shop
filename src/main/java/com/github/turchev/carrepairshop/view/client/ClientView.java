package com.github.turchev.carrepairshop.view.client;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.turchev.carrepairshop.MainLayout;
import com.github.turchev.carrepairshop.dao.ClientDao;
import com.github.turchev.carrepairshop.dao.DaoFactory;
import com.github.turchev.carrepairshop.domain.person.Client;
import com.github.turchev.carrepairshop.ds.DsType;
import com.github.turchev.carrepairshop.view.AbstractView;
import com.github.turchev.carrepairshop.view.UiException;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@SuppressWarnings("serial")
@Route(value = ClientView.NAME, layout = MainLayout.class)
@PageTitle(ClientView.NAME)
public class ClientView extends AbstractView {
	private static final Logger LOG = LogManager.getLogger();
	public static final String NAME = "client";
	private DaoFactory hsqlDaoFactory;
	private ClientDao clientDao;
	private Grid<Client> grid = new Grid<>();

	public ClientView() throws UiException {
		try {
			hsqlDaoFactory = DaoFactory.getFactory(DsType.HSQLDB);
			clientDao = hsqlDaoFactory.getClientDao();			
			grid.setSelectionMode(SelectionMode.SINGLE);
			grid.addColumn(Client::getId).setHeader("Id").setId("id");
			grid.addColumn(Client::getLastName).setHeader("Фамилия").setId("lastName");
			grid.addColumn(Client::getFirstName).setHeader("Имя").setId("firstName");
			grid.addColumn(Client::getPatronnymic).setHeader("Отчество").setId("patronnymic");
			grid.addColumn(Client::getPhone).setHeader("Телефон").setId("phone");			
			this.add(grid);
			showAll();
		} catch (Exception e) {
			throw new UiException(e);
		}
	}

	private void showAll() throws UiException {
		try {
			Notification.show("Client!!!!!");
			List<Client> clients = clientDao.findAll();
			grid.setItems(clients);
		} catch (Exception e) {
			throw new UiException(e);
		}
	}

	@Override
	protected void btnAddClick() {
		try {
			ClientWindowAdd subWindowAdd = new ClientWindowAdd();
//			UI.getCurrent().addWindow(subWindowAdd);
			subWindowAdd.open();
		} catch (Exception e) {
			LOG.error(e);
			Notification.show("Ошибка диалогового окна создания записи");
		}
	}

	@Override
	protected void btnChangeClick() {
		try {
			if (grid.asSingleSelect().isEmpty()) {
				Notification.show("Выберите клиента из списка");
				return;
			}
			Client selectedClient = grid.asSingleSelect().getValue();
			ClientWindowEdit subWindowEdit = new ClientWindowEdit(selectedClient.getId());
//			UI.getCurrent().addWindow(subWindowEdit);
		} catch (Exception e) {
			LOG.error(e);
			Notification.show("Ошибка диалогового окна редактирования");
		}
	}

	@Override
	protected void btnDeleteClick() {
//		try {
//			if (grid.asSingleSelect().isEmpty()) {
//				Notification.show("Выберите клиента из списка");
//				return;
//			}
//			Client selectedClient = grid.asSingleSelect().getValue();
//			final String MESSAGE_1 = "Удаление записи " + selectedClient.getLastName() + " "
//					+ selectedClient.getFirstName() + " " + selectedClient.getPatronnymic() + "?";
//
//			ConfirmDialog.show(getUI(), "Внимание", MESSAGE_1, "Подтвердить", "Отменить", dialog -> {
//				if (dialog.isConfirmed()) {
//					try {
//						clientDao.delete(selectedClient.getId());
//						showAll();
//					} catch (DaoException ex) {
//						LOG.debug(ex);
//						Notification.show(ex.getMessage());
//					} catch (UiException xe) {
//						LOG.error(xe);
//						Notification.show("Не удалось выполнить удаление");
//					}
//				} else {
//					return;
//				}
//			});
//
//		} catch (Exception e) {
//			LOG.error(e);
//			Notification.show("Не удалось выполнить удаление");
//		}
	}

//	@Override
//	public void enter(ViewChangeEvent event) {
//		LOG.debug("Welcome to Client View");
//	}
}

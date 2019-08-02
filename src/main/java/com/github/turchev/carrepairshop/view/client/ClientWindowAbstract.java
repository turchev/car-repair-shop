package com.github.turchev.carrepairshop.view.client;

import com.github.turchev.carrepairshop.dao.ClientDao;
import com.github.turchev.carrepairshop.dao.DaoFactory;
import com.github.turchev.carrepairshop.domain.person.Client;
import com.github.turchev.carrepairshop.ds.DsType;
import com.github.turchev.carrepairshop.view.UiException;
import com.vaadin.data.Binder;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
abstract class ClientWindowAbstract extends Window {

	protected TextField txtLastName, txtFirstName, txtPatronnymic, txtPhone;
	protected ClientDao clientDao;
	protected Binder<Client> binder;	
	
	protected ClientWindowAbstract() throws UiException {
		try {
			clientDao = DaoFactory.getFactory(DsType.HSQLDB).getClientDao();
			binder = new Binder<>(Client.class);
			binder.setBean(new Client());

			txtFirstName = new TextField("Имя");
			binder.forField(txtFirstName)
					.withValidator(new RegexpValidator("Допустимы только символы русского алфавита и дефис ",
							"[а-яА-Я]+-?[а-яА-Я]+"))
					.withValidator(new StringLengthValidator("Максимум 40 символов", 0, 40))
					.bind(Client::getFirstName, Client::setFirstName);
			txtFirstName.setSizeFull();

			txtLastName = new TextField("Фамилия");
			binder.forField(txtLastName)
					.withValidator(new RegexpValidator("Допустимы только символы русского алфавита и дефис ",
							"[а-яА-Я]+-?[а-яА-Я]+"))
					.withValidator(new StringLengthValidator("Максимум 40 символов", 0, 40))
					.bind(Client::getLastName, Client::setLastName);
			txtLastName.setSizeFull();

			txtPatronnymic = new TextField("Отчество");
			binder.forField(txtPatronnymic)
					.withValidator(new RegexpValidator("Допустимы только символы русского алфавита и дефис ",
							"[а-яА-Я]+-?[а-яА-Я]+"))
					.withValidator(new StringLengthValidator("Максимум 40 символов", 0, 40))
					.bind(Client::getPatronnymic, Client::setPatronnymic);
			txtPatronnymic.setSizeFull();

			txtPhone = new TextField("Телефон");
			txtPhone.setValueChangeMode(ValueChangeMode.EAGER);
			binder.forField(txtPhone)
					.withValidator(new RegexpValidator("Введите номер в формате +7(XXX)XXX-XX-XX",
							"^\\+7\\([0-9]{3}\\)[0-9]{3}\\-[0-9]{2}\\-[0-9]{2}$"))
					.bind(Client::getPhone, Client::setPhone);
			txtPhone.setSizeFull();

			VerticalLayout vlLayout = new VerticalLayout(txtLastName, txtFirstName, txtPatronnymic, txtPhone);
			this.setWidth(600.0f, Unit.PIXELS);
			this.setModal(true);
			this.setResizable(false);
			Button btnApple = new Button("Ok");
			btnApple.addClickListener(event -> {
				btnAppleClick();
			});
			Button btnCancel = new Button("Отменить");
			btnCancel.addClickListener(event -> {
				btnCancelClick();
			});
			HorizontalLayout hLayout = new HorizontalLayout(btnApple, btnCancel);
			vlLayout.addComponent(hLayout);
			this.setContent(vlLayout);
		} catch (Exception e) {
			throw new UiException(e);
		}
	}

	protected abstract void btnCancelClick();

	protected abstract void btnAppleClick();

}

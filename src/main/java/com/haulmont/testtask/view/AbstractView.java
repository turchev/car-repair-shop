package com.haulmont.testtask.view;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public abstract class AbstractView extends VerticalLayout {	
	
	HorizontalLayout groupButtons;	
	
	protected AbstractView() {		
		this.setMargin(true);	
		groupButtons = new HorizontalLayout();
		final Button btnAdd = new Button("Добавить");
		final Button btnChange = new Button("Изменить");
		final Button btnDelete = new Button("Удалить");
		btnAdd.addClickListener(event -> {
			Notification.show("TODO", "Добавить", Notification.Type.HUMANIZED_MESSAGE);
		});
		btnChange.addClickListener(event -> {
			Notification.show("TODO", "Изменить", Notification.Type.HUMANIZED_MESSAGE);
		});
		btnDelete.addClickListener(event -> {
			Notification.show("TODO", "Удалить", Notification.Type.HUMANIZED_MESSAGE);
		});
		groupButtons.addComponent(btnAdd);
		groupButtons.addComponent(btnChange);
		groupButtons.addComponent(btnDelete);
		this.addComponent(getLowerGroupButtons());
	}
	
	private HorizontalLayout getLowerGroupButtons() {
		groupButtons = new HorizontalLayout();
		final Button btnAdd = new Button("Добавить");
		final Button btnChange = new Button("Изменить");
		final Button btnDelete = new Button("Удалить");
		btnAdd.addClickListener(event -> {
			Notification.show("TODO", "Добавить", Notification.Type.HUMANIZED_MESSAGE);
			btnAddClick();
		});
		btnChange.addClickListener(event -> {
			Notification.show("TODO", "Изменить", Notification.Type.HUMANIZED_MESSAGE);
			btnChangeClick();
		});
		btnDelete.addClickListener(event -> {
			Notification.show("TODO", "Удалить", Notification.Type.HUMANIZED_MESSAGE);
			btnDeleteClick();
		});
		groupButtons.addComponent(btnAdd);
		groupButtons.addComponent(btnChange);
		groupButtons.addComponent(btnDelete);
		return groupButtons;
	}
	
	abstract void btnAddClick();
	
	abstract void btnChangeClick();
	
	abstract void btnDeleteClick();
}

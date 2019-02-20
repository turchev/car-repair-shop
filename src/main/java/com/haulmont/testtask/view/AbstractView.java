package com.haulmont.testtask.view;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
abstract class AbstractView extends VerticalLayout {	
	
	HorizontalLayout groupButtons;	
	
	protected AbstractView() {		
		this.setMargin(true);	
		this.addComponent(getLowerGroupButtons());
	}
	
	private HorizontalLayout getLowerGroupButtons() {
		groupButtons = new HorizontalLayout();
		Button btnAdd = new Button("Добавить");
		Button btnChange = new Button("Изменить");
		Button btnDelete = new Button("Удалить");
		btnAdd.addClickListener(event -> {		
			btnAddClick();
		});
		btnChange.addClickListener(event -> {
			btnChangeClick();
		});
		btnDelete.addClickListener(event -> {
			btnDeleteClick();
		});
		groupButtons.addComponent(btnAdd);
		groupButtons.addComponent(btnChange);
		groupButtons.addComponent(btnDelete);
		return groupButtons;
	}
	
	protected abstract void btnAddClick();
	
	protected abstract void btnChangeClick();
	
	protected abstract void btnDeleteClick();
}

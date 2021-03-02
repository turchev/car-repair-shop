package com.github.turchev.carrepairshop.view;

import com.github.turchev.carrepairshop.MainLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = ErrorView.NAME, layout = MainLayout.class)
@PageTitle(ErrorView.NAME)
public class ErrorView extends VerticalLayout {
	public static final String NAME = "error";

	public ErrorView() {
		this.setSizeFull();
		this.setAlignItems(Alignment.CENTER);
		Label lblError = new Label("ERROR");
		this.add(lblError);
	}
}

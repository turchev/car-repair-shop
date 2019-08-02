package com.github.turchev.carrepairshop;

import com.github.turchev.carrepairshop.view.AbstractView;
import com.github.turchev.carrepairshop.view.client.ClientView;
import com.github.turchev.carrepairshop.view.mechanic.MechanicView;
import com.github.turchev.carrepairshop.view.orders.OrdersView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;

public class Menu extends FlexLayout {

	private static final long serialVersionUID = 1946084039439880360L;
	private static final String SHOW_TABS = "show-tabs";
	private Tabs tabs;

	public Menu() {
		setClassName("menu-bar");

		Button btnOrders = new Button("Заказы");
		btnOrders.addClickListener(e -> {
			btnOrders.getUI().ifPresent(ui -> ui.navigate(OrdersView.NAME));
		});
		btnOrders.getElement().setAttribute("theme", "contrast primary");

		Button btnClient = new Button("Клиент");
		btnClient.addClickListener(e -> {
			btnClient.getUI().ifPresent(ui -> ui.navigate(ClientView.NAME));
		});
		btnClient.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_PRIMARY);

		Button btnMechanic = new Button("Механик");
		btnMechanic.addClickListener(e -> {
			btnMechanic.getUI().ifPresent(ui -> ui.navigate(MechanicView.NAME));
		});
		btnMechanic.addThemeVariants(ButtonVariant.LUMO_CONTRAST, ButtonVariant.LUMO_ERROR);

		add(btnOrders, btnClient, btnMechanic);		
		this.setSizeUndefined();		
	}

	/**
	 * Add a view to the navigation menu
	 *
	 * @param viewClass that has a {@code Route} annotation
	 * @param caption   view caption in the menu
	 * @param icon      view icon in the menu
	 */
	public void addView(Class<? extends AbstractView> viewClass, String caption, Icon icon) {
		Tab tab = new Tab();
		RouterLink routerLink = new RouterLink(null, viewClass);
		routerLink.setClassName("menu-link");
		routerLink.add(icon);
		routerLink.add(new Span(caption));
		tab.add(routerLink);
		tabs.add(tab);
	}
}

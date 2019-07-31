//package com.github.turchev.carrsh.view.orders;
//
//import java.awt.Window;
//import java.math.BigDecimal;
//import java.text.DecimalFormat;
//import java.util.List;
//import java.util.Locale;
//
//import com.github.turchev.carrsh.dao.ClientDao;
//import com.github.turchev.carrsh.dao.DaoFactory;
//import com.github.turchev.carrsh.dao.MechanicDao;
//import com.github.turchev.carrsh.dao.OrdersDao;
//import com.github.turchev.carrsh.domain.ShortName;
//import com.github.turchev.carrsh.domain.orders.OrderStatusType;
//import com.github.turchev.carrsh.domain.orders.OrdersWithFio;
//import com.github.turchev.carrsh.domain.person.Client;
//import com.github.turchev.carrsh.domain.person.Mechanic;
//import com.github.turchev.carrsh.ds.DsType;
//import com.github.turchev.carrsh.view.UiException;
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.combobox.ComboBox;
//import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.component.textfield.TextArea;
//import com.vaadin.flow.component.textfield.TextField;
//import com.vaadin.flow.data.binder.Binder;
//import com.vaadin.flow.data.converter.StringToBigDecimalConverter;
//import com.vaadin.flow.data.validator.BigDecimalRangeValidator;
//import com.vaadin.flow.data.validator.StringLengthValidator;
//import com.vaadin.flow.data.value.ValueChangeMode;
//import com.vaadin.ui.DateTimeField;
//import com.vaadin.ui.NativeSelect;
//
//import elemental.css.CSSStyleDeclaration.Unit;
//
//@SuppressWarnings("serial")
//abstract class OrdersWindowAbstract extends Window {
//	protected ComboBox<ShortName<Client>> cmbClient;
//	protected ComboBox<ShortName<Mechanic>> cmbMechanic;
//	protected NativeSelect<OrderStatusType> ntsStatus;
//	protected DateTimeField dtfDateCreat, dtfCompletionDate;
//	protected TextField txtPrice;
//	protected TextArea txrDescription;
//	protected DecimalFormat dcf = new DecimalFormat();
//	protected OrdersDao ordersDao;
//	protected MechanicDao mechanicDao;
//	protected ClientDao clientDao;
//	protected List<ShortName<Mechanic>> mechanicShortName;
//	protected List<ShortName<Client>> clientShortName;
//	protected Binder<OrdersWithFio> binder;
//
//	protected OrdersWindowAbstract() throws UiException {
//		try {
//			binder = new Binder<>(OrdersWithFio.class);
//			binder.setBean(new OrdersWithFio());
//			mechanicDao = DaoFactory.getFactory(DsType.HSQLDB).getMechanicDao();
//			ordersDao = DaoFactory.getFactory(DsType.HSQLDB).getOrdersDao();
//			clientDao = DaoFactory.getFactory(DsType.HSQLDB).getClientDao();
//			mechanicShortName = mechanicDao.findAllShortName();
//			clientShortName = clientDao.findAllShortName();
//			ntsStatus = new NativeSelect<>("Статус");
//			ntsStatus.setItems(OrderStatusType.values());
//			cmbClient = new ComboBox<>("Клиент ФИО");
//			cmbClient.setItems(clientShortName);
//			cmbClient.setWidth(300.0f, Unit.PIXELS);
//			cmbMechanic = new ComboBox<>("Механик ФИО");
//			cmbMechanic.setWidth(300.0f, Unit.PIXELS);
//			cmbMechanic.setItems(mechanicShortName);
//			dtfDateCreat = new DateTimeField("Дата создания заявки");
//			dtfCompletionDate = new DateTimeField("Дата окончания работ");
//
//			txrDescription = new TextArea("Описание заявки");
//			binder.forField(txrDescription)
//					.withValidator(new StringLengthValidator("Минимум 10, максимум 4500 символов", 10, 4500))
//					.bind(OrdersWithFio::getDescription, OrdersWithFio::setDescription);
//			txrDescription.setSizeFull();			
//
//			txtPrice = new TextField("Цена");
//			txtPrice.setLocale(new Locale("en", "US"));
//			txtPrice.setValueChangeMode(ValueChangeMode.EAGER);
//			binder.forField(txtPrice).withNullRepresentation("")
//					.withConverter(new StringToBigDecimalConverter("Введите сумму в формате 00000.00"))
//					.withValidator(new BigDecimalRangeValidator("Таких цен не бывает:)", new BigDecimal(0),
//							new BigDecimal(100000000)))
//					.bind(OrdersWithFio::getPrice, OrdersWithFio::setPrice);
//
//			HorizontalLayout hltStatusPrice = new HorizontalLayout(ntsStatus, txtPrice);
//			HorizontalLayout hltDate = new HorizontalLayout(dtfDateCreat, dtfCompletionDate);
//			HorizontalLayout hltClientMechanic = new HorizontalLayout(cmbClient, cmbMechanic);
//			VerticalLayout vlLayout = new VerticalLayout(txrDescription, hltStatusPrice, hltDate, hltClientMechanic);
//			this.setWidth(800.0f, Unit.PIXELS);
//			this.setModal(true);
//			this.setResizable(false);
//			Button btnApple = new Button("Ok");
//			btnApple.addClickListener(event -> {
//				btnAppleClick();
//			});
//			Button btnCancel = new Button("Отменить");
//			btnCancel.addClickListener(event -> {
//				btnCancelClick();
//			});
//			HorizontalLayout hltButton = new HorizontalLayout(btnApple, btnCancel);
//			vlLayout.addComponent(hltButton);
//			this.setContent(vlLayout);
//		} catch (Exception e) {
//			throw new UiException(e);
//		}
//	}
//
//	protected abstract void btnCancelClick();
//
//	protected abstract void btnAppleClick();
//}

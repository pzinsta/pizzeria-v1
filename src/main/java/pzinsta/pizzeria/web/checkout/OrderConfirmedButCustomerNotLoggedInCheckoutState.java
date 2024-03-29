package pzinsta.pizzeria.web.checkout;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import pzinsta.pizzeria.model.Customer;

class OrderConfirmedButCustomerNotLoggedInCheckoutState implements CheckoutState {

	private final static OrderConfirmedButCustomerNotLoggedInCheckoutState INSTANCE = new OrderConfirmedButCustomerNotLoggedInCheckoutState();
	private final static String PAGE_ID = "logInOrOrderAsGuest";

	private OrderConfirmedButCustomerNotLoggedInCheckoutState() {
	}

	@Override
	public String getView() {
		return "WEB-INF/checkout/logInOrOrderAsGuest.jsp";
	}

	@Override
	public CheckoutState handlePost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!isRequestComingFromValidPage(request)) {
			return handleGet(request, response);
		}

		String action = request.getParameter("action");

		if (StringUtils.equalsIgnoreCase(action, "order as a guest")) {
			Customer customer = new Customer();
			customer.setFirstName(request.getParameter("first-name"));
			customer.setLastName(request.getParameter("last-name"));
			customer.setAddress(request.getParameter("address"));
			customer.setEmail(request.getParameter("email"));
			customer.setPhoneNumber(request.getParameter("phone-number"));
			request.getSession().setAttribute("unregisteredCustomer", customer);
			return CustomerInformationNotConfirmedCheckoutState.getInstance();
		}

		return this;
	}

	private boolean isRequestComingFromValidPage(HttpServletRequest request) {
		return StringUtils.equals(PAGE_ID, request.getParameter("page"));
	}

	@Override
	public CheckoutState handleGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object customer = request.getSession().getAttribute("customer");
		Object unregisteredCustomer = request.getSession().getAttribute("unregisteredCustomer");
		return ObjectUtils.firstNonNull(customer, unregisteredCustomer) == null ? this : CustomerInformationNotConfirmedCheckoutState.getInstance();
	}

	public static OrderConfirmedButCustomerNotLoggedInCheckoutState getInstance() {
		return INSTANCE;
	}

	@Override
	public boolean isReturnToPreviousStateAllowed(HttpServletRequest request) {
		return isRequestComingFromValidPage(request);
	}
}

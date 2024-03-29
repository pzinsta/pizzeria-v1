<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<c:url value="/css/main.css"/>">
<title>Confirm the customer information</title>
</head>
<body>

    Please check the following information. If it's not accurate, update the values.

    <c:set scope="page" value="${empty customer ? unregisteredCustomer : customer}" var="customer" />

    <form method="POST" action="<c:url value="/checkout"/>">
        <div>
            <label for="email">Email address</label> <input type="text" name="email" id="email" value="${customer.email}"> <span>${validationErrors.email}</span>
        </div>
        <div>
            <label for="first-name">First name</label> <input type="text" name="first-name" id="first-name" required="required" value="${customer.firstName}"> <span>${validationErrors.firstName}</span>
        </div>
        <div>
            <label for="last-name">Last name</label> <input type="text" name="last-name" id="last-name" value="${customer.lastName}"> <span>${validationErrors.lastName}</span>
        </div>
        <div>
            <label for="phone-number">Phone number</label> <input type="tel" name="phone-number" id="phone-number" required="required" value="${customer.phoneNumber}"> <span>${validationErrors.phoneNumber}</span>
        </div>
        <div>
            <label for="address">Address</label> <input type="text" name="address" id="address" required="required" value="${customer.address}"> <span>${validationErrors.address}</span>
        </div>
        <div>
            <input type="hidden" name="page" value="customerInformationConfirmation"> <input type="submit" name="action" value="Continue" class="btn">
        </div>
    </form>
    
</body>
</html>
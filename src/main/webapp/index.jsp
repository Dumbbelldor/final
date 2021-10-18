<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang" value="${sessionScope.currentLang}" scope="page" />
<c:set var="context" value="${pageContext.request.contextPath}" scope="page" />
<c:set var="user" value="${sessionScope.currentUser}" scope="page" />

<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="localization.index" />

<!doctype html>
<html lang=${lang}>
<head>
    <meta charset="utf-8">
    <title><fmt:message key="welcome.text.title" /></title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-colors-food.css">
</head>
<body class="w3-image" style="background-image: url(images/def_background.jpg)">

    <c:import url="/WEB-INF/fragment/header_nav.jsp" />

    <header class="w3-container w3-center" style="padding: 144px 16px">
        <h1 class="w3-margin w3-jumbo w3-text-light-grey"><fmt:message key="welcome.text.h1" /></h1>
        <p class="w3-xlarge w3-text-light-grey"><fmt:message key="welcome.text.p" /></p>
        <a href="${context}/categories"
           class="w3-button w3-padding-large w3-black w3-hover-deep-purple w3-large"><fmt:message key="welcome.button" />
        </a>
    </header>

    <section class="w3-cell-row w3-row-padding w3-food-aubergine w3-padding-64 w3-container">
        <div class="w3-cell w3-padding-large w3-cell-middle">
            <div class="w3-third w3-center">
                <em class="fa fa-line-chart w3-padding-64 w3-text-orange w3-margin-right"
                    style="font-size: 200px"></em>
            </div>

            <div class="w3-twothird">
                <h1><fmt:message key="section1.text.h1" /></h1>
                <h3 class="w3-padding-32"><fmt:message key="section1.text.h3" /></h3>

                <p><fmt:message key="section1.text.p.1" /></p>
                <p><fmt:message key="section1.text.p.2" /></p>
            </div>
        </div>
    </section>

    <section class="w3-cell-row w3-container w3-food-grape w3-row-padding w3-padding-64">
        <div class="w3-cell w3-padding-large w3-cell-middle">
            <div class="w3-twothird w3-center">
                <h1><fmt:message key="section2.text.h1" /></h1>
                <p class="w3-padding-16"><fmt:message key="section2.text.p.1" /></p>
                <p><fmt:message key="section2.text.p.2" /></p>
                <p class="w3-padding-16"><fmt:message key="section2.text.p.3" /></p>
            </div>
            <div class="w3-third w3-center">
                <em class="fa fa-star w3-padding-64 w3-text-red" style="font-size: 200px"></em>
            </div>
        </div>
    </section>

    <section class="w3-cell-row w3-row-padding w3-food-plum w3-padding-64 w3-container">
        <div class="w3-cell w3-padding-large w3-cell-middle">
            <div class="w3-third w3-center">
                <em class="fa fa-address-book w3-padding-64 w3-text-orange w3-margin-right"
                    style="font-size: 200px"></em>
            </div>

            <div class="w3-twothird">
                <h1><fmt:message key="section3.text.h1" /></h1>
                <h4 class="w3-padding-16"><fmt:message key="section3.text.h4" /></h4>

                <p class="w3-padding-16"><fmt:message key="section3.text.p" /></p>
                <c:choose>
                    <c:when test="${user != null}">
                        <p><fmt:message key="section3.text.logged.p" /></p>
                        <div class="w3-padding-32">
                            <a href="${context}/profile"
                               class="w3-button w3-padding-large w3-black w3-hover-deep-purple w3-large" style="width: 33%"><fmt:message key="section3.button.logged" />
                            </a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <p><fmt:message key="section3.text.not_logged.p" /></p>
                        <div class="w3-padding-32">
                            <a href="${context}/authorization"
                               class="w3-button w3-padding-large w3-black w3-hover-deep-purple w3-large" style="width: 33%"><fmt:message key="section3.button.not_logged.log_in" />
                            </a>
                            <a href="${context}/registration"
                               class="w3-button w3-padding-large w3-black w3-hover-deep-purple w3-large" style="width: 33%"><fmt:message key="section3.button.not_logged.reg" />
                            </a>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </section>

</body>
</html>
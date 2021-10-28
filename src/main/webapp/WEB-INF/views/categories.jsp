<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang" value="${sessionScope.currentLang}" scope="page" />
<c:set var="allCategories" value="${requestScope.allCategories}" scope="page" />
<c:set var="context" value="${pageContext.request.contextPath}" scope="page" />

<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="localization.categories" />

<!doctype html>
<html lang="${lang}">
<head>
    <meta charset="utf-8">
    <title><fmt:message key="text.title" /></title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <style>
        body {
            background-image: url(/images/backgrounds/profile_background.jpg);
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
        }
    </style>
</head>
<body>
    <c:import url="/WEB-INF/fragments/header_nav.jsp" />

    <header class="w3-container w3-light-grey">
        <h1><fmt:message key="text.title" /></h1>
        <p><fmt:message key="text.header.p" /></p>
    </header>

    <section class="w3-row">
        <c:forEach var="elem" items="${allCategories}">
            <form action="${context}/category" method="get">

                <div class="w3-container w3-col s4">
                    <div class="w3-card-4 w3-section w3-white">
                        <div class="w3-padding" style="height: 250px">
                            <header class="w3-container" style="height: 25%">
                                <h1>${elem.name()}</h1>
                            </header>
                            <div class="w3-container" style="height: 45%">
                                <p>${elem.flavor()}</p>
                            </div>
                            <div class="w3-container w3-section" style="height: 30%">
                                <button class="w3-button w3-black w3-hover-deep-purple"
                                        name="category"
                                        value="${elem.category().name().toLowerCase()}"
                                        type="submit">
                                    <fmt:message key="button.get_started" />
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </c:forEach>
    </section>

</body>
</html>

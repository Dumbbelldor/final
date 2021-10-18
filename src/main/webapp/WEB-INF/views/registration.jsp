<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang" value="${sessionScope.currentLang}" scope="page" />
<c:set var="context" value="${pageContext.request.contextPath}" scope="page" />

<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="localization.registration" />

<!doctype html>
<html lang="${lang}">
<head>
    <meta charset="utf-8">
    <title><fmt:message key="text.registration" /></title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body class="w3-image" style="background-image: url(/images/reg_background.jpg);">
    <c:import url="/WEB-INF/fragment/header_nav.jsp" />

    <div class="w3-display-container w3-card-4 w3-display-middle w3-white" style="width: 40%">
        <header class="w3-container w3-deep-purple">
            <h2><fmt:message key="text.registration" /></h2>
        </header>
        <form action="${context}/registration" method="post">
            <div class="w3-container">
                <label class="w3-container w3-text-deep-purple"><fmt:message key="input.login.label" />
                    <input class="w3-input w3-border"
                           name="login" type="text" placeholder="<fmt:message key="input.login.placeholder" />">
                </label>
                <label class="w3-container w3-text-deep-purple"><fmt:message key="input.password.label" />
                    <input class="w3-input w3-border"
                           name="password" type="password" placeholder="<fmt:message key="input.password.placeholder" />">
                </label>
                <label class="w3-container w3-text-deep-purple"><fmt:message key="input.email.label" />
                    <input class="w3-input w3-border"
                           name="email" type="email" placeholder="<fmt:message key="input.email.placeholder" />">
                </label>
                <footer class="w3-cell-row w3-padding-16">
                    <div class="w3-cell">
                        <button class="w3-button w3-green w3-large w3-hover-deep-orange"
                                type="submit"><fmt:message key="button.register" />
                        </button>
                    </div>
                    <div class="w3-cell">
                        <a href="${context}/authorization"
                           class="w3-text w3-cell-middle w3-right"><fmt:message key="button.already_registered" />
                        </a>
                    </div>
                </footer>
            </div>
        </form>
    </div>
</body>
</html>
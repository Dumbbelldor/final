<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang" value="${sessionScope.currentLang}" scope="page" />
<c:set var="context" value="${pageContext.request.contextPath}" scope="page" />
<c:set var="user" value="${sessionScope.currentUser}" scope="page" />

<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="localization.header_nav" />

<!doctype html>
<html lang="${lang}">
<head>
    <meta charset="utf-8">
    <title>Header</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>

    <nav aria-label="auth_nav" class="w3-bar w3-black w3-large">
        <a class="w3-bar-item"><fmt:message key="text.logo" /></a>
        <a href="${context}/"
        class="w3-bar-item w3-button w3-hover-deep-purple"><fmt:message key="button.home" /></a>
        <a href="${context}/categories"
        class="w3-bar-item w3-button w3-hover-deep-purple"><fmt:message key="button.categories" /></a>
        <a href="${context}/about"
        class="w3-bar-item w3-button w3-hover-deep-purple"><fmt:message key="button.about" /></a>

            <div class="w3-dropdown-hover w3-right">
                <button class="w3-button w3-black w3-hover-deep-purple">
                    <em class="fa fa-globe"></em>
                </button>

                <form action="${context}/header_nav" method="post">

                    <div class="w3-dropdown-content w3-bar-block w3-card-4 w3-black" style="right: 0">
                        <button class="w3-bar-item w3-button w3-hover-purple"
                                type="submit"
                                value="ru"
                                name="lang">
                            <fmt:message key="button.locale.ru" />
                        </button>
                        <button class="w3-bar-item w3-button w3-hover-purple"
                                type="submit"
                                value="en"
                                name="lang">
                            <fmt:message key="button.locale.en" />
                        </button>
                        <button class="w3-bar-item w3-button w3-hover-purple"
                                type="submit"
                                value="de"
                                name="lang">
                            <fmt:message key="button.locale.de" />
                        </button>
                    </div>
                </form>
            </div>

        <c:choose>
            <c:when test="${user != null}">

                <div class="w3-dropdown-hover w3-right">
                    <button class="w3-button w3-black w3-hover-deep-purple">${user.email}</button>
                    <form action="${context}/header_nav" method="post">
                        <div class="w3-dropdown-content w3-bar-block w3-card-4 w3-black">
                            <a href="${context}/profile" class="w3-bar-item w3-button w3-hover-purple"><fmt:message key="button.profile" /></a>
                            <button class="w3-bar-item w3-button w3-hover-purple"
                                    type="submit"
                                    value="exit"
                                    name="exit">
                                <fmt:message key="button.exit" />
                            </button>
                        </div>
                    </form>
                </div>

                <div class="w3-clear"></div>
            </c:when>
            <c:otherwise>
                <a href="${context}/registration"
                class="w3-bar-item w3-button w3-food-plum w3-right w3-hover-deep-purple"><fmt:message key="button.registration" /></a>
                <a href="${context}/authorization"
                class="w3-bar-item w3-text w3-right"><fmt:message key="button.login" /></a>
                <div class="w3-clear"></div>
            </c:otherwise>
        </c:choose>
    </nav>

</body>
</html>

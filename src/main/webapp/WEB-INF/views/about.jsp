<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang" value="${sessionScope.currentLang}" scope="page" />
<c:set var="context" value="${pageContext.request.contextPath}" scope="page" />

<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="localization.about" />

<!doctype html>
<html lang="${lang}">
<head>
    <meta charset="utf-8">
    <title><fmt:message key="text.title" /></title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <style>
        body {
            background-image: url(/images/backgrounds/about_background.jpg);
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
        }
    </style>
</head>
<body>

    <c:import url="/WEB-INF/fragments/header_nav.jsp" />

    <div style="height: 100px"></div>
    <section class="w3-container w3-card-4 w3-light-grey w3-center" style="margin: auto; width: 70%">
        <h1 class="w3-padding-24">
            <fmt:message key="text.about_project" />
        </h1>
        <h3 class="w3-topbar w3-bottombar w3-border-black w3-padding-16" style="margin: auto; width: 50%">
            <fmt:message key="text.general" />
        </h3>
        <h5 class="w3-padding">
            <fmt:message key="text.general.h5.1" />
        </h5>
        <h5 class="w3-padding">
            <fmt:message key="text.general.h5.2" />
        </h5>
        <h3 class="w3-topbar w3-bottombar w3-border-black w3-padding-16" style="margin: auto; width: 50%">
            <fmt:message key="text.func" />
        </h3>
        <h4 class="w3-padding">
            <fmt:message key="text.func.before_ul" />
        </h4>
        <ul class="w3-ul" style="margin: auto; width: 85%">
            <li class="w3-bottombar w3-topbar w3-border-black">
                <fmt:message key="text.func.li.1" />
            </li>
            <li class="w3-bottombar w3-border-black">
                <fmt:message key="text.func.li.2" />
            </li>
            <li class="w3-bottombar w3-border-black">
                <fmt:message key="text.func.li.3" />
            </li>
            <li class="w3-bottombar w3-border-black">
                <fmt:message key="text.func.li.4" />
            </li>
            <li class="w3-bottombar w3-border-black">
                <fmt:message key="text.func.li.5" />
            </li>
            <li class="w3-bottombar w3-border-black">
                <fmt:message key="text.func.li.6" />
            </li>
            <li class="w3-bottombar w3-border-black">
                <fmt:message key="text.func.li.7" />
            </li>
        </ul>
        <h5 class="w3-padding">
            <fmt:message key="text.func.footer" />
        </h5>
        <h3 class="w3-topbar w3-bottombar w3-border-black w3-padding-16" style="margin: auto; width: 50%">
            <fmt:message key="text.inner" />
        </h3>
        <h4 class="w3-padding">
            <fmt:message key="text.inner.frontend" />
        </h4>
        <p>
            <fmt:message key="text.inner.frontend.p" />
        </p>
        <h4 class="w3-padding">
            <fmt:message key="text.inner.backend" />
        </h4>
        <ul class="w3-ul" style="margin: auto; width: 85%">
            <li class="w3-bottombar w3-topbar w3-border-black">
                <h5 class="w3-padding w3-grey">
                    <fmt:message key="text.inner.backend.li.con" />
                </h5>
                <p>
                    <fmt:message key="text.inner.backend.li.con.p" />
                </p>
            </li>
            <li class="w3-bottombar w3-border-black">
                <h5 class="w3-padding w3-grey">
                    <fmt:message key="text.inner.backend.li.db" />
                </h5>
                <p>
                    <fmt:message key="text.inner.backend.li.db.p" />
                </p>
            </li>
            <li class="w3-bottombar w3-border-black">
                <h5 class="w3-padding w3-grey">
                    <fmt:message key="text.inner.backend.li.wdb" />
                </h5>
                <p>
                    <fmt:message key="text.inner.backend.li.wdb.p.1" />
                </p>
                <p>
                    <fmt:message key="text.inner.backend.li.wdb.p.2" />
                </p>
                <p>
                    <fmt:message key="text.inner.backend.li.wdb.p.3" />
                </p>
                <p>
                    <fmt:message key="text.inner.backend.li.wdb.p.4" />
                </p>
            </li>
            <li class="w3-bottombar w3-border-black">
                <h5 class="w3-padding w3-grey">
                    <fmt:message key="text.filter" />
                </h5>
                <p>
                    <fmt:message key="text.filter.p" />
                </p>
            </li>
            <li class="w3-bottombar w3-border-black w3-margin-bottom">
                <h5 class="w3-padding w3-grey">
                    <fmt:message key="text.local" />
                </h5>
                <p>
                    <fmt:message key="text.local.p.1" />
                </p>
                <p>
                    <fmt:message key="text.local.p.2" />
                </p>
            </li>
        </ul>
        <h3 class="w3-topbar w3-bottombar w3-border-black w3-padding-16" style="margin: auto; width: 50%">
            <fmt:message key="text.add" />
        </h3>
        <img class="w3-padding-16 w3-image"
             src="${context}/images/uml_database.png"
             alt="UML schema" style="margin: auto; width: 85%">
        <p>
            <fmt:message key="text.add.p" />
        </p>
    </section>
    <div style="height: 100px"></div>

</body>
</html>

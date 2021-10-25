<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang" value="${sessionScope.currentLang}" scope="page" />

<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="tasks.economics.econ7" />

<!doctype html>
<html lang="${lang}">
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<p><fmt:message key="p.1" /></p>
<blockquote class="w3-panel w3-leftbar w3-border-deep-purple w3-light-grey w3-padding">
    <p><em><fmt:message key="em.1" /></em></p>
    <p><em><fmt:message key="em.2" /></em></p>
</blockquote>
    <p><fmt:message key="p.2" /></p>
    <p><fmt:message key="p.3" /></p>
    <p><fmt:message key="p.4" /></p>
    <p><fmt:message key="p.5" /></p>
    <p><fmt:message key="p.6" /></p>
</body>
</html>
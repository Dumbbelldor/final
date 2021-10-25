<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang" value="${sessionScope.currentLang}" scope="page" />

<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="tasks.geography.geo4" />

<!doctype html>
<html lang="${lang}">
<head>
    <meta charset="utf-8">
    <title></title>
</head>
<body>
    <p><fmt:message key="p.1" /></p>
    <p><fmt:message key="p.2" /></p>
    <h4><fmt:message key="h4.1" /></h4>
    <h5><fmt:message key="h5.1" /></h5>
    <h5><fmt:message key="h5.2" /></h5>
    <h4><fmt:message key="h4.2" /></h4>
    <p><fmt:message key="p.3" /></p>
    <p><fmt:message key="p.4" /></p>
    <p><fmt:message key="p.5" /></p>
    <p><fmt:message key="p.6" /></p>
    <p><fmt:message key="p.7" /></p>
</body>
</html>
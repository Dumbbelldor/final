<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang" value="${sessionScope.currentLang}" scope="page" />

<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="tasks.geography.geo8" />

<!doctype html>
<html lang="${lang}">
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
    <h5><fmt:message key="h5" /></h5>
    <blockquote class="w3-panel w3-leftbar w3-border-deep-purple w3-light-grey w3-padding">
        <em><fmt:message key="em" /></em>
</blockquote>
</body>
</html>
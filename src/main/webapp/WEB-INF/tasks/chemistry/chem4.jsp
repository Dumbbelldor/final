<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang" value="${sessionScope.currentLang}" scope="page" />

<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="tasks.chemistry.chem4" />

<!doctype html>
<html lang="${lang}">
<head>
    <meta charset="utf-8">
    <title></title>
</head>
<body>
    <p><fmt:message key="p.1" /></p>
    <p><fmt:message key="p.2" /></p>
    <p><fmt:message key="p.3" /></p>
    <p><fmt:message key="p.4" /></p>
    <p><fmt:message key="p.5" /></p>
    <p><fmt:message key="p.6" /></p>
</body>
</html>
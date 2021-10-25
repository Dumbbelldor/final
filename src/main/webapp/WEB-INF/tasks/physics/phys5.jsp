<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang" value="${sessionScope.currentLang}" scope="page" />

<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="tasks.physics.phys5" />

<!doctype html>
<html lang="${lang}">
<head>
    <meta charset="utf-8">
    <title></title>
</head>
<body>
<p><fmt:message key="p.1" /></p>
<h5><fmt:message key="h5.1" /></h5>
<p><fmt:message key="p.2" /></p>
<p><fmt:message key="p.3" /></p>
<h5><fmt:message key="h5.2" /></h5>
<p><fmt:message key="p.4" /></p>
<p><fmt:message key="p.5" /></p>
<p><fmt:message key="p.6" /></p>
<p><fmt:message key="p.7" /></p>
<p><fmt:message key="p.8" /></p>
</body>
</html>
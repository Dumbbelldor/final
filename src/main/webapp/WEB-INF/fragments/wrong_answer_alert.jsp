<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang" value="${sessionScope.currentLang}" scope="page" />

<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="localization.wrong_answer_alert" />

<!doctype html>
<html lang="${lang}">
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
    <div class="w3-panel w3-section w3-leftbar w3-border-red w3-display-container w3-white" style="width: 80%; margin: auto;">
            <span onclick="this.parentElement.style.display='none'"
                  class="w3-button w3-large w3-display-topright">&times;</span>
        <h3><fmt:message key="h3" /></h3>
        <p><fmt:message key="p" /></p>
    </div>
</body>
</html>
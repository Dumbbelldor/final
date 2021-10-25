<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang" value="${sessionScope.currentLang}" scope="page" />
<c:set var="tasks" value="${sessionScope.currentTasks}" scope="page" />
<c:set var="category" value="${sessionScope.currentLocalizedCategory}" scope="page" />
<c:set var="context" value="${pageContext.request.contextPath}" scope="page" />

<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="localization.spec_category" />

<!doctype html>
<html lang="${lang}">
    <head>
        <meta charset="utf-8">
        <title>${category}</title>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    </head>
    <style>
        body {
            background-image: url(/images/backgrounds/reg_background.jpg);
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
        }
    </style>
<body>
    <c:import url="/WEB-INF/fragment/header_nav.jsp" />

    <header class="w3-container w3-light-grey">
        <h2>${category}</h2>
        <p><fmt:message key="text.header.p" /></p>
    </header>

    <section class="w3-container">
        <form action="${context}/task" method="get">
            <ul class="w3-ul w3-hoverable w3-section w3-white w3-xlarge" style="width: 70%; margin: auto">
                <c:forEach var="elem" items="${tasks}">
                    <li class="w3-container w3-cell-row">
                        <h3 class="w3-container">${elem.task().name}</h3>
                        <div class="w3-bar">
                            <div class="w3-bar-item w3-small"><fmt:message key="text.difficulty" />: ${elem.task().difficulty}</div>
                            <div class="w3-bar-item w3-small"><fmt:message key="text.status" />: ${elem.status().description}</div>
                        </div>
                        <div class="w3-cell w3-cell-middle w3-center" style="width: 15%">
                            <button class="w3-button w3-green w3-hover-deep-orange"
                                    name="task"
                                    value="${elem.task().taskId}"
                                    type="submit">
                                <fmt:message key="button.solve" />
                            </button>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </form>
    </section>

</body>
</html>
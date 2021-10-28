<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang" value="${sessionScope.currentLang}" scope="page" />
<c:set var="task" value="${sessionScope.currentTask}" scope="page" />
<c:set var="context" value="${pageContext.request.contextPath}" scope="page" />

<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="localization.task" />

<!doctype html>
<html lang="${lang}">
<head>
    <meta charset="utf-8">
    <title><fmt:message key="text.title" /></title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <script>
        function validateForm() {
            let x = document.forms["answerForm"]["answer"].value.toLowerCase();
            const rightAnswer = "${task.answer.toLowerCase()}";
            if (x === "" || x !== rightAnswer) {
                alert(<fmt:message key="text.warning" />);
                return false;
            }
            return true;
        }
    </script>
    <style>
        body {
            background-image: url(/images/backgrounds/def_background.jpg);
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
        }
    </style>
</head>
<body>

    <c:import url="/WEB-INF/fragments/header_nav.jsp" />

    <header class="w3-container w3-light-grey">
        <h1>${task.name}</h1>
        <p><fmt:message key="text.difficulty" />: ${task.difficulty}</p>
    </header>
    
    <c:if test="${requestScope.taskAlert != null}">
        <c:import url="/WEB-INF/fragments/wrong_answer_alert.jsp" />
    </c:if>

    <form name="answerForm"
          action="${context}/task"
          onsubmit="return validateForm()"
          method="post">
        <section class="w3-container w3-card-4 w3-white w3-section" style="width: 80%; margin: auto;">
            <div class="w3-container">
                <c:import url="${task.description}" />
                <label>
                    <input class="w3-input w3-border-1 w3-border-black"
                           name="answer"
                           type="text"
                           placeholder="<fmt:message key="text.placeholder" />"
                           required style="width: 30%">
                </label>
                <button class="w3-button w3-green w3-hover-deep-orange w3-section"
                        type="submit"><fmt:message key="button.submit" />
                </button>
            </div>
        </section>
    </form>
    </body>
</html>
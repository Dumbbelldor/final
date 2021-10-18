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
            let x = document.forms["answerForm"]["answer"].value;
            const rightAnswer = "${task.answer}";
            if (x === "" || x !== rightAnswer) {
                alert("Your answer is incorrect!");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <c:import url="/WEB-INF/fragment/header_nav.jsp" />

    <header class="w3-container">
        <h1>${task.name}</h1>
        <div class="w3-bar">
            <div class="w3-bar-item w3-small"><fmt:message key="text.difficulty" />: ${task.difficulty}</div>
        </div>
    </header>
    
    <c:if test="${requestScope.taskAlert != null}">
        <c:import url="/WEB-INF/fragment/wrong_answer_alert.jsp" />
    </c:if>

    <form name="answerForm"
          action="${context}/task"
          onsubmit="return validateForm()"
          method="post">
        <section class="w3-container w3-card-4 w3-white w3-section" style="width: 80%; margin: auto;">
            <section class="w3-container">
                <p>${task.description}</p>
                <label>
                    <input class="w3-input w3-border-1 w3-border-black"
                           name="answer"
                           type="text"
                           placeholder="Your answer"
                           required style="width: 30%">
                    <button class="w3-button w3-green w3-hover-deep-orange w3-section"
                            type="submit"><fmt:message key="button.submit" />
                    </button>
                </label>
            </section>
        </section>
    </form>
    </body>
</html>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>

<c:set var="context" value="${pageContext.request.contextPath}" scope="page" />

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Error</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body style="background-image: url(/images/backgrounds/reg_background.jpg); background-attachment: fixed">

    <c:import url="/WEB-INF/fragment/header_nav.jsp" />

    <div class="w3-container" style="padding: 100px 75px 100px 75px">
        <section class="w3-container w3-card-4 w3-white">
            <div class="w3-cell-row">
                <div class="w3-cell w3-padding" style="width: 70%">
                    <h1 class="w3-jumbo w3-padding">
                        Ops!
                    </h1>
                    <h1 class="w3-xxlarge w3-padding">
                        Something gone wrong...
                    </h1>
                    <h3 class="w3-padding">
                        You probably went astray, because you shouldn't
                        see this page.
                    </h3>
                </div>
                <div class="w3-cell w3-cell-middle w3-center" style="width: 30%">
                    <img class="w3-section"
                         src="${context}/images/confusing.png"
                         alt="Mmm?" style="width: 300px">
                </div>
            </div>
        </section>
    </div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang" value="${sessionScope.currentLang}" scope="page" />
<c:set var="user" value="${sessionScope.currentUser}" scope="page" />
<c:set var="since" value="${user.created.toLocalDateTime().toLocalDate()}" scope="page" />
<c:set var="level" value="${sessionScope.currentLocalizedLevel}" scope="page" />
<c:set var="context" value="${pageContext.request.contextPath}" scope="page" />
<c:set var="countedTasks" value="${sessionScope.currentCountedTasks}" scope="page" />

<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="localization.profile" />

<!doctype html>
<html lang="${lang}">
<head>
    <meta charset="utf-8">
    <title><fmt:message key="text.title" /></title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
    <c:import url="/WEB-INF/fragment/header_nav.jsp" />

    <div class="w3-container w3-white">

        <section class="w3-cell-row w3-grey w3-margin-top">

            <div class="w3-container w3-cell w3-purple" style="width: 300px;">
                <div class="w3-card-4 w3-section w3-white" style="width: 300px">
                    <c:choose>
                        <c:when test="${user.picture == null}">
                            <c:set var="profileImage"
                                   value="${context}/images/default_avatar.jpg"
                                   scope="page" />
                        </c:when>
                        <c:otherwise>
                            <c:set var="profileImage"
                                   value="${context}${user.picture}"
                                   scope="page" />
                        </c:otherwise>
                    </c:choose>
                    <img src="${profileImage}" alt="You" style="width: 300px"/>
                </div>
            </div>

            <div class="w3-container w3-cell w3-orange">
                <div class="w3-container w3-white w3-section">
                    <h3 class="w3-padding-16">
                        <fmt:message key="text.name" />: ${user.login}
                    </h3>
                    <h3 class="w3-padding-16">
                        <fmt:message key="text.email" />: ${user.email}
                    </h3>
                    <h3 class="w3-padding-16">
                        <fmt:message key="text.since" />: ${since}
                    </h3>

                    <form action="${context}/upload_image"
                          enctype="multipart/form-data"
                          method="post">
                        <c:choose>
                            <c:when test="${user.picture == null}">
                                <div class="w3-cell-row w3-padding-16">
                                    <div class="w3-cell">
                                        <h3><fmt:message key="text.upload_image" />:</h3>
                                    </div>
                                    <div class="w3-cell">
                                        <label>
                                            <input type="file"
                                                   name="uploadedPicture"
                                                   accept="image/jpeg, image/png, image/bmp">
                                        </label>
                                    </div>
                                    <div class="w3-cell">
                                        <label>
                                            <input type="submit"
                                                   name="upload"
                                                   value="upload">
                                        </label>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="w3-cell-row w3-padding-16">
                                    <div class="w3-cell">
                                        <h3><fmt:message key="text.delete_image" />:</h3>
                                    </div>
                                    <div class="w3-cell">
                                        <input type="submit"
                                               name="deleteImage"
                                               value="${user.userId}">
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </form>

                </div>
            </div>
        </section>

        <section class="w3-container w3-section w3-blue">

            <div class="w3-card-4 w3-orange w3-section">

                <header class="w3-container w3-red">
                    <h2><fmt:message key="text.level_progression" /></h2>
                </header>

                <div class="w3-container w3-black">
                    <h3>${level.name()}</h3>
                    <progress value="${user.experience}"
                              max="${level.exp()}"
                              style="width: 100%; height: 40px"></progress>
                    <p>${user.experience}/${level.exp()} XP</p>
                </div>

            </div>
        </section>

        <section class="w3-container w3-section w3-purple">

            <div class="w3-card-4 w3-orange w3-section">

                <header class="w3-container w3-red">
                    <h2><fmt:message key="text.achievements" /></h2>
                </header>

                <div class="w3-container w3-white">
                    <div class="w3-row w3-black w3-section">
                        <c:forEach var="elem" items="${sessionScope.currentUserAch}">
                            <div class="w3-col s2 w3-blue">
                                <div class="w3-container w3-center w3-section">
                                    <img src="${context}${elem.picture}"
                                         alt="Ach" style="width: 100%" />
                                    <div class="w3-container w3-red">
                                        <h3>${elem.flavor}</h3>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>

            </div>

        </section>

        <section class="w3-container w3-section w3-purple">

            <div class="w3-card-4 w3-orange w3-section">

                <header class="w3-container w3-red">
                    <h2><fmt:message key="text.task_progression" /></h2>
                </header>

                <div class="w3-container w3-white">
                    <div class="w3-row w3-black w3-section">
                        <c:forEach var="elem" items="${countedTasks}">
                            <div class="w3-col s4 w3-blue">
                                <div class="w3-container">
                                    <h3>${elem.category()}</h3>
                                    <progress value="${elem.solved()}"
                                              max="${elem.all()}"
                                              style="width: 100%; height: 40px"></progress>
                                    <p>${elem.solved()}/${elem.all()}</p>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>

            </div>

        </section>

    </div>
</body>
</html>
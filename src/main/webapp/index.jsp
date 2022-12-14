<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<!doctype html>
<html>
<body>
<c:set var="title" value="Страница входа" scope="page"/>
<%@ include file="/WEB-INF/templates/_head.jsp"%>
<%@ include file="/WEB-INF/templates/_menu.jsp"%>
<div class="container">
    <div class="row  mt-5">
        <div class="col">
            <div class="d-flex justify-content-center">
                <img src="img/media.jpg" style="width: 100%">
            </div>
        </div>
    </div>
    <div class="row mt-5">
        <div class="col">
            <img src="img/inet-ru.png">
        </div>
        <div class="col">
            <img src="img/tv-ru.png">
        </div>
        <div class="col">
            <img src="img/tel-ru.png">
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/templates/_scripts.jsp"/>
</body>
</html>
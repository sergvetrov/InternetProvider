<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<!doctype html>
<html>
<body>
<c:set var="title" value="Ошибка" scope="page"/>
<%@ include file="/WEB-INF/templates/_head.jsp"%>
<%@ include file="/WEB-INF/templates/_menu.jsp"%>
<div class="container">
    <div class="row  mt-5">
        <div class="col">
            <div class="d-flex justify-content-center">
                <img src="../../img/error-pages-gregshuster.png" style="width: 100%">
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/templates/_scripts.jsp"/>
</body>
</html>
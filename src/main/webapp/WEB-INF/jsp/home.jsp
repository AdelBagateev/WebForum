<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<script src="<c:url value="/js/jquery-3.6.1.min.js"/>"></script>
    <title>Title</title>
</head>
<body>
    <jsp:include page="/jsp/navbar.jsp" />
</body>
	<div>
		<div id="message" class="ui message">
			<div class="header">
				Привет!
			</div>
			<p>Вы попали на самую свободную новостную площадку в рунете!</p>
			<button id="close" class="ui button">
				<i style="font-size: 20px" class="bi bi-x"></i>
			</button>
		</div>
	</div>

	<script>
		$("#close").click(function () {
            $("#message").fadeOut("slow");
		});
	</script>

</html>

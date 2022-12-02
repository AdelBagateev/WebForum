<%@ page import="util.Utils" %>
<%@ page import="services.UserService" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Posts</title>
</head>
<body>
<jsp:include page="/jsp/navbar.jsp"/>

<h3 class="ui top attached header">
	Посты
</h3>
<div class="ui attached segment">
	<p>
	<div style="display: flex; flex-wrap: wrap; justify-content: space-evenly; align-items: center">
		<c:forEach var="post" items="${posts}">
			<div class="ui card" style="margin: 0">
				<div class="content">
					<a href="/posts/${post.getId()}" class="header">${post.getTitle()}</a>
				</div>
				<c:if test="${UserService.isPostBelongsToUser(post.getUserId())}">
					<div class="description">
						<form action="<c:url value="/posts/edit"/>" method="get">
							<button style="font-size: 10px; margin-left: 5px;" name="postId" value="${post.getId()}" class="ui button" type="submit">Редактировать</button>
						</form>

						<form action="<c:url value="/posts/delete"/>" method="post">
							<button style="font-size: 10px; margin-left: 5px;" name="postId" value="${post.getId()}" class="ui button" type="submit">Удалить</button>
						</form>
					</div>
				</c:if>

				<div class="extra content">
					<a>${Utils.getUsernameById(post.getUserId())}</a>
				</div>
			</div>
		</c:forEach>
	</div>
	</p>
</div>


</body>
</html>

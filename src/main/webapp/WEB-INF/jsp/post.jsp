<%@ page import="util.Utils" %>
<%@ page import="services.UserService" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>${post.getTitle()}</title>
</head>
<body>
	<jsp:include page="/jsp/navbar.jsp" />
	<h3 class="ui top attached header">
		${post.getTitle()}
	</h3>
	<div class="ui attached segment">
		<p>
			${post.getText()}
		</p>
	</div>

	<div class="ui comments" style="margin-left: 2rem">
		<c:forEach var="comment" items="${comments}">
			<div class="comment" style="line-height: unset; margin-bottom: 1rem;">
				<a class="avatar">
					<img style="width: auto; height: auto"  src="<c:url value="/img/avatar.jpg"/>">
				</a>
				<div class="content" style="margin-left: 4.5rem;">
					<a class="author">${Utils.getUsernameById(comment.getUserID())}</a>
					<div class="text">
						${comment.getText()}
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	<c:if test="${UserService.getAuthUser().isPresent()}">
		<form action="" method="post" class="ui reply form" style="margin-left: 2rem; margin-right: 2rem; margin-top: 2rem">
			<div class="field">
				<textarea name="text"></textarea>
			</div>
			<button class="ui primary submit labeled icon button">
				Add Comment
			</button>
		</form>
	</c:if>
</body>
</html>

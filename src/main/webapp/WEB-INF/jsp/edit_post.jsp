<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Edit</title>
</head>
<body>
	<jsp:include page="/jsp/navbar.jsp" />
	<h3 class="ui top attached header">
		Редактировать пост
	</h3>
	<div class="ui attached segment">
		<p>
		<div>
			<form action="" method="post" class="ui form">
				<div class="field">
					<label>Название</label>
					<input value="${post.getTitle()}" type="text" name="title">
				</div>
				<div class="field">
					<label>Текст</label>
					<textarea name="text" rows="2">${post.getText()}</textarea>
				</div>
				<button class="ui button" type="submit">Submit</button>
			</form>
		</div>
		</p>
	</div>
</body>
</html>

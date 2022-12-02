<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Title</title>
</head>
<body>
<jsp:include page="/jsp/navbar.jsp"/>
<div class="ui placeholder segment">
	<div class="ui two column very relaxed stackable grid">
		<div class="column">
			<form action="" method="post">
				<div class="ui form">
					<div class="field">
						<label>Username</label>
						<div class="ui left icon input">
							<input required name="name" type="text" placeholder="Username">
						</div>
					</div>
					<div class="field">
						<label>Password</label>
						<div class="ui left icon input">
							<input required name="password" type="password">
						</div>
					</div>
					<button class="ui blue submit button">Login</button>
				</div>
			</form>

		</div>
		<div class="middle aligned column">
			<form action="${pageContext.request.contextPath}/reg" method="get">
				<button class="ui big button">
					Sign Up
				</button>
			</form>

		</div>
	</div>
	<div class="ui vertical divider">
		Or
	</div>
</div>
</body>
</html>

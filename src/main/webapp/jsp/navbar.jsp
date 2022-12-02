<%@ page import="services.UserService" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/semantic.min.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css" integrity="sha384-xeJqLiuOvjUBq3iGOjvSQSIlwrpqjSHXpduPd6rQpuiM3f5/ijby8pCsnbu5S81n" crossorigin="anonymous">
<script src="${pageContext.request.contextPath}/js/semantic.min.js"></script>

<div class="ui secondary  menu" style="padding-right: 2rem; padding-left: 2rem">
    <a class="item" href="<c:url value="/home"/>">
        Home
    </a>
    <a class="item" href="<c:url value="/auth"/>">
        Auth
    </a>
    <a class="item" href="<c:url value="/posts"/>">
        Posts
    </a>

	<a class="item" href="<c:url value="/posts/add"/>">
		Add post
	</a>

    <div class="right menu">
        <div class="item">
            <div>
				<form action="<c:url value="/posts/find"/>" method="get">
					<div class="ui input">
						<input name="query" type="text" placeholder="Search...">
					</div>
				</form>
            </div>
        </div>
        <div class="item" style="color: lightskyblue">
			<b>${UserService.getAuthUser().isPresent() ? UserService.getAuthUser().get().getName() : ""}</b>
		</div>
    </div>
</div>
<div class="ui clearing divider"></div>



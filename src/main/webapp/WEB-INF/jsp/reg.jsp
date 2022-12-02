<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <jsp:include page="/jsp/navbar.jsp" />

    <form action="" method="post" class="ui form" style="width: 25%; margin: 0 auto">
        <div class="field">
            <label>Username</label>
            <input type="text" name="name" placeholder="Username">
        </div>
        <div class="field">
            <label>Password</label>
            <input type="password" name="password" placeholder="Password">
        </div>

        <button class="ui button" type="submit">Зарегестрироваться</button>
    </form>
</body>
</html>

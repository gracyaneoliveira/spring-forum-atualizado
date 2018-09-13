<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="<c:url value="/login"/>" method="post">
	<input type="text" name="login"/>
	<input type="password" name="senha"/>
	<input type="submit" value="Entrar"/>
</form>
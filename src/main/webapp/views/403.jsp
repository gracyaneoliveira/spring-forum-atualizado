<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>  
	    <meta content="text/html; charset=UTF-8">  
	    <title>Access denied</title>  
    </head>  
	<body>
	<h2>Sorry, you do not have permission to view this page.</h2>
	<p>${msg}</p>
	 
	Click <a href="<c:url value="/" /> ">here</a>
	to go back to the Homepage.
	</body>
</html>
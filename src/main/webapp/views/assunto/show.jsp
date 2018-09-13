<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div style="background: #48762A;">
<div class="chamada row">
		
	<div class="twelve columns">
		<h3 style="color:white;">${assunto.nome}</h3>
		<h5 style="color:white;">
			<c:forEach items="${topicos}" var="top">
				<div>
					${top.titulo}
				</div>
			</c:forEach>
		</h5>
	</div>
		
</div>
</div>
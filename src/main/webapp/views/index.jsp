<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<div style="background: #48762A;">
		<div class="row">
			<div class="twelve columns">
				<img src="./recursos/images/chamada.png" />
			</div>
		</div>
	</div>
	
	<div class="row">
		<div class="two columns">
			<h5>Assuntos</h5>
			<ul class="side-nav">
				<c:forEach items="${assuntos}" var="assunto">
					<li>${assunto.nome}</li>
				</c:forEach>
			</ul>
		</div>
		<hr>
		<div class="eight columns">
			<h5>O que � isto?</h5>
			<p>O projeto Spring F�rum � um gerenciador de comunidades virtuais (a la GUJ) baseado nos conceitos apresentados no livro <i>Virando o Jogo com Spring Framework</i> de <i>Henrique Lobo Weissmann</i>.</p>
			<p>Seu c�digo fonte � totalmente aberto e voc� � livre para explor�-lo da maneira que achar melhor.</p>
		</div>

		<div class="two columns">
			<h5>�ltimos membros</h5>
			<ul class="side-nav">
				<c:forEach items="${usuarios}" var="usuario">
					<li>${usuario.nome}</li>
				</c:forEach>
			</ul>
		</div>
		
	</div>
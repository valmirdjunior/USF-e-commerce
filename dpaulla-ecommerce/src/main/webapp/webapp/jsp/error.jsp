<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
	  <meta charset="UTF-8">
	  <meta http-equiv="X-UA-Compatible" content="IE=edge">
	  <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1">
	  <link rel="shortcut icon" href="assets/images/logo-90x66.png" type="image/x-icon">
	  <meta name="description" content="">
  	  <link rel="stylesheet" href="assets/style.css">
      <title>P�gina n�o encontrada</title>
	  <script type="text/javascript" charset="UTF-8" src="assets/dpaulla-scripts.js"></script>  	  
</head>

<body class="body-left">
	<div class="section-body">
		<div class="section-menu-principal-view">
			<div class="section-menu-principal-view-logo">
				<a href="/"><img src="assets/images/logo_transparent.png" alt="DPaulla" class="section-menu-principal-view-logo-logo"></img></a>
			</div>
			<div class="section-menu-principal-view-login">
					<img src="assets/icons/icons-female-user.png" alt="icons-female-user" class="icone-imagem"></img>
					 
						<c:if test="${pageContext.request.userPrincipal.name != null}">
							<a href="/meu-perfil" class="icone-legenda">
		        				Ol�, ${user.nome}
		        			</a>	
						</c:if> 
						<c:if test="${pageContext.request.userPrincipal.name == null}">
							<a href="/meu-perfil" class="icone-legenda">		
			        			Login
	        				</a>
			    		</c:if>
			</div>
			<div class="section-menu-principal-view-meuspedidos">
				<img src="assets/icons/icons-address-book.png" alt="icons-book" class="icone-imagem"></img>
				<a href="/meu-perfil" class="icone-legenda">
					Meus Pedidos
				</a> 
			</div>
			<div class="section-menu-principal-view-faleconosco">
				<img src="assets/icons/icons-call-female.png" alt="icons-call-female" class="icone-imagem"></img>
				<a href="/fale-conosco" class="icone-legenda">
					Fale Conosco
				</a>
			</div>
			<div class="section-menu-principal-view-sairoculto">
				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<form id="logoutForm" method="GET" action="${contextPath}/logout">
            			<button type="submit" name="username">
            				<img src="assets/icons/icons-exit.png" alt="icons-exit" class="icone"></img>
           				</button>
        			</form>
				</c:if>
			</div>
			<div class="section-menu-principal-view-listadesejos-meucarrinho">
				<div class="section-menu-principal-view-listadesejos">
					<img src="assets/icons/icons-page.png" alt="icons-shopping-cart" class="icone-imagem"></img>
					<a href="/lista-desejos" class="icone-legenda white-link">
						Lista de Desejos
					</a>
				</div>		
				<div class="section-menu-principal-view-meucarrinho">
					<img src="assets/icons/icons-shopping-cart.png" alt="icons-shopping-cart" class="icone-imagem"></img>
					<a href="/meu-carrinho" class="icone-legenda white-link">
						Meu Carrinho (${quantidadeProduto})
					</a>
				</div>		
			</div>
			<c:if test="${userRole.roles_role_id == 1}">
				<div class="section-menu-principal-view-menusecundario">
					<a class="white-link" href="/acesso-restrito"> Acesso Restrito </a>
				</div>
    		</c:if>
			<c:if test="${userRole.roles_role_id == 3}">
				<div class="section-menu-principal-view-menusecundario">
					<a class="white-link" href="/meu-perfil"> Minha Conta </a>
					<a class="white-link"> Quem Somos </a>
					<a class="white-link"> Pol�tica do Site</a>
					<a class="white-link"> FAQ</a>
				</div>
			</c:if> 
    		
			<div class="section-menu-principal-view-busca">
				<input name="buscar" type="text" placeholder="Buscar" class="text-buscar"/>
				<button type="submit" class="btn-dpaulla button-buscar" name="name">Buscar</button>
			</div>

			<div class="menuCategoriasPrincipal section-menu-principal-view-menucategorias" onmouseleave="mouseout()">
				<div class="menuCategoriasPrincipal-grupoitem1">
					<div class="menuCategoriasPrincipal-item" onclick="redirectPage('page1')" onmouseenter="mousein('divMenu1')">
						TIPOS DE CABELO
					</div>
					<div class="menuCategoriasPrincipal-item" onclick="redirectPage('page2')" onmouseenter="mousein('divMenu2')">
						PARA SEU CABELO
					</div>
					<div class="menuCategoriasPrincipal-item" onclick="redirectPage('page3')" onmouseenter="mousein('divMenu3')">
						MARCAS
					</div>
					<div class="menuCategoriasPrincipal-item" onclick="redirectPage('page4')" onmouseenter="mousein('divMenu4')">
						PROMO��O
					</div>
					<div class="menuCategoriasPrincipal-item" onclick="redirectPage('page5')" onmouseenter="mousein('divMenu5')">
						DICAS
					</div>
				</div>

				<div class="menuCategoriasPrincipal-grupoitem2 section-menu-principal-view-menusubcategorias">
					<div class="menuCategoriasPrincipal-item-oculto">
						<div class="divMenu1" id="divMenu1" name="divMenu1">
							<div class="divMenu-sub-item1" id="divMenu-sub-item1" onclick="redirectSubPage('subpage1')"></div>
							<div class="divMenu-sub-item2" id="divMenu-sub-item2" onclick="redirectSubPage('subpage2')"></div>
							<div class="divMenu-sub-item3" id="divMenu-sub-item3" onclick="redirectSubPage('subpage3')"></div>
							<div class="divMenu-sub-item4" id="divMenu-sub-item4" onclick="redirectSubPage('subpage4')"></div>
							<div class="divMenu-sub-item5" id="divMenu-sub-item5" onclick="redirectSubPage('subpage5')"></div>
						</div>
					</div>
				</div>
			</div>		
		</div>
		
	<div class="section-home">
		<div class="slideshow-container">
			<h1 class="titulo-DPaulla">P�gina n�o encontrada</h1>
			<h4>Desculpe nos pelo transtorno, por�m essa p�gina n�o pode ser encontrada. 
			Por favor, retorne ao menu inicial.</h4>
 		</div>
	</div>

	<div class="section-footer">
		<div class="section-footer-logo">
			<a href="/"><img src="assets/images/logo_transparent.png" alt="DPaulla" class="section-footer-logo-logo"></img></a>
		</div>
		<div class="section-footer-grid-informacoes">
			<h1 class="white-link">Novidades</h1>
			<h1 class="white-link">Categorias</h1>
			<h1 class="white-link">Siga</h1>
			<h6 class="white-link">Linha Pr�pria</h6>
			<h6 class="white-link">Tipos de Cabelo</h6>
			<h6 class="white-link">Instagram</h6>
			<h6 class="white-link">Nos indique</h6>
			<h6 class="white-link">Para Cabelo</h6>
			<h6 class="white-link">Youtube</h6>
			<h6 class="white-link">Promo��o</h6>
			<h6 class="white-link">Marcas</h6>
			<h6 class="white-link">Facebook</h6>
		</div>
	</div>
</div>

<script>
var slideIndex = 0;
showSlides();

function showSlides() {
  var i;
  var slides = document.getElementsByClassName("mySlides");
  var dots = document.getElementsByClassName("dot");
  for (i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";  
  }
  slideIndex++;
  if (slideIndex > slides.length) {slideIndex = 1}    
  for (i = 0; i < dots.length; i++) {
    dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex-1].style.display = "block";  
  dots[slideIndex-1].className += " active";
  setTimeout(showSlides, 2000); // Change image every 2 seconds
}

function redirectPage(page){
	if(page == "page1"){
		window.location.replace("/tipos_de_cabelo");	
	}else if (page == "page2"){
		window.location.replace("/para_seu_cabelo");	
	}else if (page == "page3"){
		window.location.replace("/marcas");	
	}else if (page == "page4"){
		window.location.replace("/promocoes");	
	}else if (page == "page5"){
		window.location.replace("/dicas");	
	}
}

</script>
</body>
								
</html>
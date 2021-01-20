<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="es_ES" />
<!doctype html>
<html lang="es">
<head>

<%-- <base href="/mf0227_3/" /> --%>
<base href="${pageContext.request.contextPath}/" />
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- FontAwesome -->
<link rel="stylesheet" href="css/all.min.css">
<!-- Datatables -->
<link rel="stylesheet" href="css/dataTables.bootstrap4.min.css" />
<!-- Hoja de estilos personalizada -->
<link rel="stylesheet" href="css/supermercado.css">

<title>Supermercado</title>
</head>
<body>
	<header class="sticky-top">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="#"><i class="d-inline-block align-middle pr-2 fa fa-shopping-cart"></i>Supermercado</a>
			<!--  <a class="navbar-brand" href="#">Supermercado</a> <i class="fa fa-shopping-cart" aria-hidden="true"></i> -->
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#menuprincipal"
				aria-controls="menuprincipal" aria-expanded="false"
				aria-label="Menu principal">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item active"><a class="nav-link" href="#">Inicio
							<span class="sr-only">(current)</span>
					</a></li>
					<c:if test="${sessionScope.usuario != null}">
					<!--
						<li class="nav-item"><a class="nav-link" href="admin/index">Mantenimiento
								Productos</a></li>
						<li class="nav-item"><a class="nav-link" href="admin/maestro">Mantenimiento
								Clientes</a></li>
					
					-->
					  	<li class="nav-item dropdown">
        					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          					Mantenimientos ...
        					</a>
       						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
          						<a class="dropdown-item" href="admin/index">Productos</a>
          						<a class="dropdown-item" href="admin/maestro">Clientes</a>
         				 		<div class="dropdown-divider"></div>
          						<a class="dropdown-item" href="admin/indexDpto">Departamentos</a>
        					</div>
      					</li>
      				</c:if>
				</ul>
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="carrito">Ver
							carrito</a> 
						<c:choose>
							<c:when test="${sessionScope.usuario == null}">
								<li class="nav-item"><a class="nav-link" href="login">Iniciar
										sesión</a></li>
							</c:when>
							<c:otherwise>
								<li class="nav-item"><a class="navbar-text">${sessionScope.usuario.email}</a>
								</li>
								<li class="nav-item"><a class="nav-link" href="logout">Cerrar
										sesión</a></li>
							</c:otherwise>
						</c:choose></li>
				</ul>
			</div>
		</nav>
		<c:if test="${alertaTexto != null}">
			<div class="alert alert-${alertaNivel} alert-dismissible fade show"
				role="alert">
				${alertaTexto}
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</c:if>
	</header>
	<main class="container-fluid pt-3">
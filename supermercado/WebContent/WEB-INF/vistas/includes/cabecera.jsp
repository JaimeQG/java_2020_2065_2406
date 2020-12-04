<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="es_ES" />
<!doctype html>
<html lang="es">
<head>

<%-- <base href="/supermercado/" /> --%>
<base href="${pageContext.request.contextPath}/" />
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- FontAwesome -->
<link rel="stylesheet" href="css/all.min.css">
<!-- Hoja de estilos personalizada -->
<link rel="stylesheet" href="css/supermercado.css">

<title>Supermercado</title>
</head>
<body>
	<header class="sticky-top">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<a class="navbar-brand" href="#">Supermercado</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#menuprincipal"
				aria-controls="menuprincipal" aria-expanded="false"
				aria-label="Menu principal">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="menuprincipal">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item active"><a class="nav-link" href="#">Inicio
							<span class="sr-only">(current)</span>
					</a></li>
				</ul>
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="carrito">Ver carrito</a>
					<c:choose>
						<c:when test="${sessionScope.usuario == null}">
							<li class="nav-item"><a class="nav-link" href="login">Iniciar sesión</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="nav-item"><a class="navbar-text">${sessionScope.usuario.email}</a>
							</li>
							<li class="nav-item"><a class="nav-link" href="logout">Cerrar sesión</a>
							</li>
						</c:otherwise>
					</c:choose>
				</ul>

			</div>
		</nav>
	</header>
	<main class="container-fluid pt-3">
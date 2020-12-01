<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<c:forEach items="${productos}" var="producto">
	<div>
		<p>${producto.id}</p>
		<p>${producto.nombre}</p>
		<p>${producto.descripcion}</p>
		<p>${producto.precio}</p>
		<p>${producto.descuento}</p>
		<p>${producto.precioUnidadMedida}</p>
		<p>${producto.unidadMedida}</p>
		<p>${producto.urlImagen}</p>
		<p>${producto.cantidad}</p>

	</div>
	<hr />
</c:forEach>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
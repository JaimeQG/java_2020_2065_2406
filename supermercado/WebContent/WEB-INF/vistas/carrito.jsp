<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<h1>Carrito de la compra</h1>
<!-- ${carrito} -->
<table class="table table-striped table-bordered table-hover table-sm">
<caption>Lista de la compra</caption>
	<thead class="thead-light">
		<tr>
			<th>Id</th>
			<th>Nombre</th>
			<th>Precio</th>
			<th>Cantidad</th>
			<th>Importe</th>
		</tr>
	</thead>
	<tbody>
		<c:set var="importe" value="${0}"/>
		<c:set var="total" value="${0}"/>
		<c:forEach items="${carrito}" var="producto">
			<tr>
				<th>${producto.value.id}</th>
				<th>${producto.value.nombre}</th>
				<th><fmt:formatNumber type="currency" value="${producto.value.precioConDescuento}" /></th>
				<th>${producto.value.cantidad}</th>
				<c:set var="importe" value="${(producto.value.cantidad * producto.value.precioConDescuento)}" />
				<th><fmt:formatNumber type="currency" value="${importe}"/></th>
				<c:set var="total" value="${(total + importe)}" />
				<!-- ${producto} -->
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
	 		<tr>
				<th colspan="4" class="text-right">Importe total</th>
				<th><fmt:formatNumber type="currency" value="${total}"/></th>
	 		</tr>
	 	</tfoot>	
</table>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
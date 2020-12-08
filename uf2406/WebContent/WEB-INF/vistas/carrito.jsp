<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<h1>Your choice was</h1>
<!-- ${carrito} -->
<table class="table table-striped table-bordered table-hover table-sm">
<caption>Lista de la compra</caption>
	<thead class="thead-light">
		<tr>
			<th>Product Number</th>
			<th>Name</th>
			<th>Price</th>
			<th>Number</th>
			<th>Total</th>
		</tr>
	</thead>
	<tbody>
		<c:set var="importe" value="${0}"/>
		<c:set var="total" value="${0}"/>
		<c:forEach items="${carrito}" var="producto">
			<tr>
				<th>${producto.value.id}</th>
				<th>${producto.value.nombre}</th>
				<th><fmt:formatNumber type="currency" value="${producto.value.precio}" /></th>
				<th>${producto.value.cantidad}</th>
				<c:set var="importe" value="${(producto.value.cantidad * producto.value.precio)}" />
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
<div>
	<button class="btn btn-primary btn-block mb-3">Pay</button>
</div>
<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>


<h1>Make your order</h1>
<form action="agregartodocarrito" method="get">
	
	<table class="table table-striped table-bordered table-hover table-sm">
	<caption>Lista de la compra</caption>
		<thead class="thead-light">
			<tr>
				<th>Product Number</th>
				<th>Name</th>
				<th>Price</th>
				<th>Number</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${productos}" var="producto">
				<tr>
					<th>${producto.id}</th>
					<th>${producto.nombre}</th>
					<th><fmt:formatNumber type="currency" value="${producto.precio}" /></th>
					<th><input type="number" placeholder=""
									aria-label="Cantidad" value="${producto.cantidad}" name="${producto.id}"
									min="0" /></th>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
	 		<tr>
				
	 		</tr>
	 	</tfoot>	
	</table>
	<div>
		<button class="btn btn-primary btn-block mb-3">Process Request</button>
	</div>
</form>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
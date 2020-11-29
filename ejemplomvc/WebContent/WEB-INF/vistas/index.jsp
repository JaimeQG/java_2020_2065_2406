
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<h1>Listado de perros</h1>

<table>
	<thead>
		<tr> 
			<th scope="col">Id</th>
			<th scope="col">Nombre</th>
			<th scope="col">Raza</th>
			<th scope="col">Peso</th>
			<th scope="col">Vacunado</th>
		</tr>
	</thead>
	<tbody>
		<%-- for(Perro perro: perros) --%>
		<c:forEach items="${perros}" var="perro">
			<tr>
				<%-- perro.getId() --%>
				<th scope="row">${perro.id}</th>
				<td>${perro.nombre}</td>
				<td>${perro.raza}</td>
				<td>${perro.peso}</td>
				<td>${perro.peso}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>


<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
	
		
		
		


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<div class="table-responsive mb-3">
	<table class="table table-striped table-bordered table-hover table-sm">
		<caption>Listado de libros</caption>
		<thead class="thead-dark">
			<tr>
				<th scope="col">Id</th>
				<th scope="col">Nombre</th>
				<th scope="col">Imagen</th>	
				<th scope="col">Precio</th>
				<th scope="col">Descuento</th>
				<th scope="col">Autor</th>
				<th scope="col">Opciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${libreria}" var="libro">
				<tr>
					<th scope="row">${libro.id}</th>
					<td>${libro.nombre}</td>
					<td><img src="${libro.urlImagen}" alt="" style="height: 64px; width: 44px;" /></td>	
					<td><fmt:formatNumber type="currency" value="${libro.precio}" /></td>
					<td><fmt:formatNumber type="percent" value="${libro.descuento / 100}" /></td>
					<td>${libro.autor}</td>	
					<td>
						<div class="btn-group" role="group" aria-label="Opciones">
							<a class="btn btn-primary btn-sm" href="admin/libro?id=${libro.id}">Editar</a>
							<a onclick="return confirm('¿Estás seguro?')" class="btn btn-danger btn-sm" href="admin/borrar?id=${libro.id}">Borrar</a>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<p>
		<a class="btn btn-primary" href="admin/libro">Añadir libro</a> 
	</p>
</div>
<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
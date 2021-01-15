<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<h3>Mantenimiento Clientes</h3>

<div class="table-responsive">
	<table class="table table-striped table-bordered table-hover table-sm">
		<caption>Listado de productos</caption>
		<thead class="thead-dark">
			<tr>
				<th scope="col">Id</th>
				<th scope="col">Nombre</th>
				<th scope="col">Apellidos</th>
				<th scope="col">CIF</th>
				<th scope="col">Fecha de nacimiento</th>
				<th scope="col">Opciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${clientes}" var="cliente">
				<tr>
					<th scope="row">${cliente.id}</th>
					<td>${cliente.nombre}</td>
					<td>${cliente.apellidos}</td>
					<td>${cliente.cif}</td>
					<td>${cliente.fechaNacimiento}</td>
					<td>
						<div class="btn-group" role="group" aria-label="Opciones">
							<a class="btn btn-primary btn-sm" href="admin/cliente?id=${cliente.id}">Editar</a>
							<a onclick="return confirm('¿Estás seguro?')" class="btn btn-danger btn-sm" href="admin/borrarCliente?id=${cliente.id}">Borrar</a>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<p>
		<a class="btn btn-primary" href="admin/cliente">Añadir cliente</a> 
	</p>
</div>
<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
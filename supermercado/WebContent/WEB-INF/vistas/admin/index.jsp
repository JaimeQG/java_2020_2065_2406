<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<h3>Mantenimiento Productos</h3>

<div class="table-responsive">
	<table class="table table-striped table-bordered table-hover table-sm">
		<caption>Listado de productos</caption>
		<thead class="thead-dark">
			<tr>
				<th scope="col">Id</th>
				<th scope="col">Nombre</th>
				<th scope="col">Departamento</th>
				<th scope="col">Imagen</th>
				<th scope="col">Descripción</th>
				<th scope="col">Precio</th>
				<th scope="col">Cantidad</th>
				<th scope="col">Unidad de medida</th>
				<th scope="col">Precio por unidad de medida</th>
				<th scope="col">Descuento</th>
				<th scope="col">Opciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${productos}" var="producto">
				<tr>
					<th scope="row">${producto.id}</th>
					<td>${producto.nombre}</td>
					<td>${producto.departamento.nombre}</td>
					<td><img src="product_imgs/${producto.urlImagen}" alt="" style="height: 3em" /></td>
					<td>${producto.descripcion}</td>
					<td><fmt:formatNumber type="currency" value="${producto.precio}" /></td>
					<td>${producto.cantidad}</td>
					<td>${producto.unidadMedida}</td>
					<td><fmt:formatNumber type="currency" value="${producto.precioUnidadMedida}" /></td>
					<td><fmt:formatNumber type="percent" value="${producto.descuento / 100}" /></td>
					<td>
						<div class="btn-group" role="group" aria-label="Opciones">
							<c:if test="${borrados == null}">
								<a class="btn btn-primary btn-sm"
									href="admin/producto?id=${producto.id}">Editar</a> <a
									onclick="return confirm('¿Estás seguro?')"
									class="btn btn-danger btn-sm"
									href="admin/borrar?id=${producto.id}">Borrar</a>
							</c:if>
							<c:if test="${borrados != null}">
								<a class="btn btn-primary btn-sm"
									href="admin/borrar?id=${producto.id}&deshacer">Recuperar</a>
							</c:if>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<a class="btn btn-primary" href="admin/producto">Añadir producto</a>
	<form action="admin/index">
		<div class="form-check">
			<input class="form-check-input" type="checkbox" ${borrados != null ? 'checked' : ''}
				id="borrados" name="borrados" onchange="submit()"> <label
				class="form-check-label" for="borrados"> Mostrar sólo borrados </label>
		</div>
	</form>
</div>
<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
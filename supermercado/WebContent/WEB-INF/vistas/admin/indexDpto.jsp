<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<h3>Mantenimiento Departamentos</h3>

<div class="table-responsive">
	<table class="table table-striped table-bordered table-hover table-sm">
		<caption>Listado de productos</caption>
		<thead class="thead-dark">
			<tr>
				<th scope="col">Id</th>
				<th scope="col">Nombre</th>
				<th scope="col">Descripción</th>
				<th scope="col">Opciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${departamentos}" var="departamento">
				<tr>
					<th scope="row">${departamento.id}</th>
					<td>${departamento.nombre}</td>
					<td>${departamento.descripcion}</td>
					<td>
						<div class="btn-group" role="group" aria-label="Opciones">
							<c:if test="${borrados == null}">
								<a class="btn btn-primary btn-sm"
									href="admin/departamento?id=${departamento.id}">Editar</a> <a
									onclick="return confirm('¿Estás seguro?')"
									class="btn btn-danger btn-sm"
									href="admin/borrarDpto?id=${departamento.id}">Borrar</a>
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
	
	<p>
		<a class="btn btn-primary" href="admin/departamento">Añadir departamento</a>
	</p>
	<!--  
	<c:choose>
		<c:when test="${sessionScope.usuario != null}">
			<form action="admin/index">
				<div class="form-check">
					<input class="form-check-input" type="checkbox" ${borrados != null ? 'checked' : ''}
					id="borrados" name="borrados" onchange="submit()"> <label
					class="form-check-label" for="borrados"> Mostrar sólo borrados </label>
				</div>
			</form>					
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>
	-->
</div>
<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
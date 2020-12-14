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
				<th scope="col">Precio</th>
				<th scope="col">Descuento</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${libreria}" var="libro">
				<tr>
					<th scope="row">${libro.id}</th>
					<td>${libro.nombre}</td>
					<td><fmt:formatNumber type="currency" value="${libro.precio}" /></td>
					<td><fmt:formatNumber type="percent" value="${libro.descuento / 100}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<hr>
	<h2>Edición de libro</h2>

<form action="libro" method="post" class="needs-validation" novalidate>
	<%--<input type="hidden" name="id" value="" />--%>

	<!-- Id -->
	<div class="form-group row">
		<label for="id" class="col-md-4 col-lg-3 col-form-label">Id</label>
		<div class="col">
			<input type="number" class="form-control ${libro.errorId != null ? 'is-invalid' : '' }" id="id" name="id" value="${libro.id}"
				readonly placeholder="Id del libro">
			<div class="valid-feedback">Id correcto</div>
			<div class="invalid-feedback">${libro.errorId}</div>
		</div>
	</div>
	
	<!-- Nombre -->
	<div class="form-group row">
		<label for="nombre" class="col-md-4 col-lg-3  col-form-label">Nombre</label>
		<div class="col">
			<input type="text" class="form-control ${libro.errorNombre != null ? 'is-invalid' : '' }" id="nombre" name="nombre" value="${libro.nombre}"
				required minlength="2" autofocus pattern="^[A-Za-z0-9 -]+$"
				placeholder="Debe introducir un nombre con solo letras y mayúscula la primera. Mínimo 2 caracteres (Máx. 150).">
			<div class="valid-feedback">Nombre correcto</div>
			<div class="invalid-feedback">${libro.errorNombre != null ? libro.errorNombre : 'Debe introducir un nombre con
				 mínimo 2 caracteres - máximo 150, y solo letras y mayúscula la primera'}</div>
		</div>
	</div>

	<!-- Precio -->
	<div class="form-group row">
		<label for="precio" class="col-md-4 col-lg-3 col-form-label">Precio</label>
		<div class="input-group col">
			<input type="number" step=".01" min="0" class="form-control ${libro.errorPrecio != null ? 'is-invalid' : '' }"
				id="precio" name="precio" value="${libro.precio}" required>

			<div class="input-group-append">
				<span class="input-group-text rounded-right">€</span>
			</div>

			<div class="valid-feedback">Precio correcto</div>
			<div class="invalid-feedback">${libro.errorPrecio != null ? libro.errorPrecio : 'Debe rellenarse y ser mayor que 0'}</div>
		</div>
	</div>
	
	<!-- Descuento -->
	<div class="form-group row">
		<label for="descuento" class="col-md-4 col-lg-3 col-form-label">Descuento</label>
		<div class="input-group col">
			<input type="number" class="form-control ${libro.errorDescuento != null ? 'is-invalid' : '' }" id="descuento" name="descuento" value="${libro.descuento}"
				name="descuento" min="0" max="100" />
			<div class="input-group-append">
				<span class="input-group-text rounded-right">%</span>
			</div>
			
			<div class="valid-feedback">Descuento correcto</div>
			<div class="invalid-feedback">${libro.errorDescuento != null ? libro.errorDescuento : 'Debe ser mayor que 0'}</div>
		</div>
	</div>
	
	<div class="form-group row">
		<div class="offset-md-4 offset-lg-3 col">
			<button type="submit" class="btn btn-primary">Añadir Libro</button>
		</div>
	</div>

</form>
	
	
</div>
<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
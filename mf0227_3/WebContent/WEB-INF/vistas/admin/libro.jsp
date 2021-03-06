<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<h1>Edición de libro</h1>

<form action="" method="post" class="needs-validation" novalidate>
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

	<!-- urlImagen -->
	<div class="form-group row">
		<label for="urlImagen" class="col-md-4 col-lg-3 col-form-label">Imagen</label>
		<div class="col">
			<input type="text" class="form-control" id="urlImagen" name="urlImagen"
				placeholder="URL de la imagen a mostrar del producto" value="${libro.urlImagen}">
			<div class="valid-feedback">Imagen correcta</div>
			<div class="invalid-feedback"></div>
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
	
	<!-- Autor -->
	<div class="form-group row">
		<label for="autor" class="col-md-4 col-lg-3  col-form-label">Autor</label>
		<div class="col">
			<input type="text" class="form-control" id="autor" name="autor" value="${libro.autor}"
				pattern="^[A-Za-z -]+$"
				placeholder="Debe introducir un autor con solo letras y mayúscula la primera">
			<div class="valid-feedback">Autor correcto</div>
			<div class="invalid-feedback"></div>
		</div>
	</div>
	
	<div class="form-group row">
		<div class="offset-md-4 offset-lg-3 col">
			<button type="submit" class="btn btn-primary">Aceptar</button>
		</div>
	</div>

</form>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
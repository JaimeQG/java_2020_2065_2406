<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<h1>Edición de producto</h1>

<form action="" method="post" class="needs-validation" novalidate>
	<%--<input type="hidden" name="id" value="" />--%>

	<div class="form-group row">
		<label for="id" class="col-md-4 col-lg-3 col-form-label">Id</label>
		<div class="col">
			<input type="number" class="form-control ${producto.errorId != null ? 'is-invalid' : '' }" id="id" name="id" value="${producto.id}"
				readonly placeholder="Id del producto">
			<div class="valid-feedback">Id correcto</div>
			<div class="invalid-feedback">${producto.errorId}</div>
		</div>
	</div>

	<div class="form-group row">
		<label for="nombre" class="col-md-4 col-lg-3  col-form-label">Nombre</label>
		<div class="col">
			<input type="text" class="form-control ${producto.errorNombre != null ? 'is-invalid' : '' }" id="nombre" name="nombre" value="${producto.nombre}"
				required minlength="3" pattern="\p{Lu}\p{Ll}{2}[\p{Ll} ]*"
				placeholder="Debe introducir un nombre con solo letras y mayúscula la primera. Mínimo tres caracteres.">
			<div class="valid-feedback">Nombre correcto</div>
			<div class="invalid-feedback">${producto.errorNombre != null ? producto.errorNombre : 'Debe introducir un nombre con
				como mínimo 3 letras, y solo letras y mayúscula la primera'}</div>
		</div>
	</div>

	<%--
	<div class="form-group row">
		<label for="imagen" class="col-md-4 col-lg-3 col-form-label">Imagen</label>
		<div class="col">
			<div class="custom-file">
				<input type="file" class="custom-file-input" id="imagen" value="${producto.urlImagen}"
					name="imagen" lang="es"> <label class="custom-file-label"
					for="imagen" data-browse="Elegir">${producto.urlImagen != null ? producto.urlImagen : 'Seleccionar Archivo' }</label>
			</div>
			<div class="valid-feedback">Imagen correcta</div>
			<div class="invalid-feedback"></div>
		</div>
	</div>
	--%>
	
	<div class="form-group row">
		<label for="imagen" class="col-md-4 col-lg-3 col-form-label">Imagen</label>
		<div class="col">
			<input type="url" class="form-control" id="imagen" name="imagen"
				placeholder="URL de la imagen a mostrar del producto" value="${producto.urlImagen}">
			<div class="valid-feedback">Imagen correcta</div>
			<div class="invalid-feedback"></div>
		</div>
	</div>
	
	<div class="form-group row">
		<label for="descripcion" class="col-md-4 col-lg-3 col-form-label">Descripción</label>
		<div class="col">
			<textarea class="form-control" id="descripcion" name="descripcion"
				placeholder="Descripción del producto">${producto.descripcion}</textarea>
			<div class="valid-feedback">Descripción correcta</div>
			<div class="invalid-feedback"></div>
		</div>
	</div>

	<div class="form-group row">
		<label for="precio" class="col-md-4 col-lg-3 col-form-label">Precio</label>
		<div class="input-group col">
			<input type="number" step=".01" min="0" class="form-control ${producto.errorPrecio != null ? 'is-invalid' : '' }"
				id="precio" name="precio" value="${producto.precio}" required>

			<div class="input-group-append">
				<span class="input-group-text rounded-right">€</span>
			</div>

			<div class="valid-feedback">Precio correcto</div>
			<div class="invalid-feedback">${producto.errorPrecio != null ? producto.errorPrecio : 'Debe rellenarse y ser mayor que 0' }</div>
		</div>
	</div>

	<div class="form-group row">
		<label for="cantidad" class="col-md-4 col-lg-3 col-form-label">Cantidad</label>
		<div class="col">
			<input type="number" class="form-control ${producto.errorCantidad != null ? 'is-invalid' : '' }" id="cantidad" value="${producto.cantidad}"
				name="cantidad" required min="0" />
			<div class="valid-feedback">Cantidad correcta</div>
			<div class="invalid-feedback">${producto.errorCantidad != null ? producto.errorCantidad : 'Debe rellenarse y ser mayor que 0' }</div>
		</div>
	</div>

	<div class="form-group row">
		<label for="unidad-medida" class="col-md-4 col-lg-3 col-form-label">Unidad de medida</label>
		<div class="col">
			<input type="text" class="form-control" id="unidad-medida" name="unidad-medida" value="${producto.unidadMedida}" />
			<div class="valid-feedback">Unidad de medida</div>
			<div class="invalid-feedback"></div>
		</div>
	</div>
	
	<div class="form-group row">
		<label for="precio-unidad-medida" class="col-md-4 col-lg-3 col-form-label">Precio por unidad de medida</label>
		<div class="input-group col">
			<input type="number" step=".01" min="0" class="form-control ${producto.errorPrecioUnidadMedida != null ? 'is-invalid' : ''}" value="${producto.precioUnidadMedida}"
				id="precio-unidad-medida" name="precio-unidad-medida" />

			<div class="input-group-append">
				<span class="input-group-text rounded-right">€</span>
			</div>

			<div class="valid-feedback">Precio por unidad de medida correcto</div>
			<div class="invalid-feedback">${producto.errorPrecioUnidadMedida != null ? producto.errorPrecioUnidadMedida : 'Debe ser mayor que 0'}</div>
		</div>
	</div>
	
	<div class="form-group row">
		<label for="descuento" class="col-md-4 col-lg-3 col-form-label">Descuento</label>
		<div class="input-group col">
			<input type="number" class="form-control ${producto.errorDescuento != null ? 'is-invalid' : '' }" id="descuento" name="descuento" value="${producto.descuento}"
				name="cantidad" min="0" max="100" />
			<div class="input-group-append">
				<span class="input-group-text rounded-right">%</span>
			</div>
			
			<div class="valid-feedback">Descuento correcto</div>
			<div class="invalid-feedback">${producto.errorDescuento != null ? producto.errorDescuento : 'Debe ser mayor que 0'}</div>
		</div>
	</div>
	
	<div class="form-group row">
		<div class="offset-md-4 offset-lg-3 col">
			<button type="submit" class="btn btn-primary">Aceptar</button>
		</div>
	</div>

</form>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
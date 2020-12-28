<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<form action="admin/cliente" method="post" class="needs-validation"
	novalidate>

	<div class="form-group row">
		<label for="id" class="col-md-5 col-lg-4 col-xl-3 col-form-label">Id (opcional)</label>
		<div class="col">
			<input class="form-control ${cliente != null ? (cliente.errorId != null ? 'is-invalid' : 'is-valid') : ''}" type="number" min="1" name="id"
				placeholder="Id" value="${cliente.id}" />
			<div class="valid-feedback">Id correcto</div>
			<div class="invalid-feedback">El id debe ser superior o igual a 1</div>
		</div>
	</div>

	<div class="form-group row">
		<label for="nombre" class="col-md-5 col-lg-4 col-xl-3 col-form-label">Nombre</label>
		<div class="col">
			<input class="form-control ${cliente != null ? (cliente.errorNombre != null ? 'is-invalid' : 'is-valid') : ''}" type="text" name="nombre" id="nombre"
				placeholder="Nombre" value="${cliente.nombre}" required
				pattern="\p{Lu}\p{Ll}{2}[ \p{L}]*" />
			<div class="valid-feedback">Nombre correcto</div>
			<div class="invalid-feedback">El nombre es obligatorio y debe tener al menos 3 caracteres y comenzar en mayúscula</div>
		</div>
	</div>
	<div class="form-group row">
		<label for="apellidos" class="col-md-5 col-lg-4 col-xl-3 col-form-label">Apellidos (opcionales)</label>
		<div class="col">
			<input type="text" class="form-control ${cliente != null ? (cliente.errorApellidos != null ? 'is-invalid' : 'is-valid') : ''}" id="apellidos"
				name="apellidos" placeholder="Apellidos"
				value="${cliente.apellidos}" pattern="\p{Lu}\p{Ll}{2}[ \p{L}]*" />
			<div class="valid-feedback">Apellidos correctos</div>
			<div class="invalid-feedback">Los apellidos deben tener al menos 3 caracteres y comenzar en mayúscula</div>
		</div>
	</div>
	<div class="form-group row">
		<label for="cif" class="col-md-5 col-lg-4 col-xl-3 col-form-label">CIF</label>
		<div class="col">
			<input class="form-control ${cliente != null ? (cliente.errorCif != null ? 'is-invalid' : 'is-valid') : ''}" id="cif" type="text" name="cif"
				placeholder="CIF" value="${cliente.cif}" required
				pattern="[ABCDEFGHJPQRSUVNW]\d{8}|[XYZ]\d{7}[A-Z]|\d{8}[A-Z]" />
			<div class="valid-feedback">CIF correcto</div>
			<div class="invalid-feedback">El CIF debe tener un formato 12345678A, B12345678 ó X1234567A</div>
		</div>
	</div>
	<div class="form-group row">
		<label for="fecha-nacimiento" class="col-md-5 col-lg-4 col-xl-3 col-form-label">Fecha
			de nacimiento (opcional)</label>
		<div class="col">
			<input class="form-control ${cliente != null ? (cliente.errorFechaNacimiento != null ? 'is-invalid' : 'is-valid') : ''}" id="fecha-nacimiento" type="date"
				max="<%=java.time.LocalDate.now().minusYears(18)%>"
				name="fecha-nacimiento" placeholder="Fecha de nacimiento"
				value="${cliente.fechaNacimiento}" />
			<div class="valid-feedback">Fecha de nacimiento correcta</div>
			<div class="invalid-feedback">Debes ser mayor de edad para registrarte</div>
		</div>
	</div>

	<div class="form-group row">
		<div class="offset-md-5 offset-lg-4 offset-xl-3 col">
			<button type="submit" class="btn btn-primary">Aceptar</button>
		</div>
	</div>

</form>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
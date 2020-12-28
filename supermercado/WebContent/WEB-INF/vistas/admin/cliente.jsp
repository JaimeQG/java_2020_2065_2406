<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp" %>

<!-- ${cliente} -->

<h1>Edición de cliente</h1>

<form action="admin/cliente" method="post">

	<!-- id -->
	<div class="form-group row">
		<label for="id" class="col-md-4 col-lg-3 col-form-label">Id</label>
		<div class="col">
			<input type="number" class="form-control ${cliente.errorId != null ? 'is-invalid' : '' }" id="id" name="id" value="${cliente.id}"
				readonly placeholder="Id del cliente">
			<div class="valid-feedback">Id correcto</div>
			<div class="invalid-feedback">${cliente.errorId}</div>
		</div>
	</div>
	
	<!-- nombre -->
	<div class="form-group row">
		<label for="nombre" class="col-md-4 col-lg-3  col-form-label">Nombre</label>
		<div class="col">
			<input type="text" class="form-control ${cliente.errorNombre != null ? 'is-invalid' : '' }" id="nombre" name="nombre" value="${cliente.nombre}"
				placeholder="Debe introducir un nombre con solo letras y mayúscula la primera. Mínimo tres caracteres.">
			<div class="valid-feedback">Nombre correcto</div>
			<div class="invalid-feedback">${cliente.errorNombre != null ? cliente.errorNombre : 'El nombre es obligatorio y debe tener al menos 3 caracteres y comenzar en mayúscula'}</div>
		</div>
	</div>
	
	<!-- apellidos -->
	<div class="form-group row">
		<label for="apellidos" class="col-md-4 col-lg-3  col-form-label">Apellidos</label>
		<div class="col">
			<input type="text" class="form-control ${cliente.errorApellidos != null ? 'is-invalid' : '' }" id="apellidos" name="apellidos" value="${cliente.apellidos}"
				placeholder="Apellidos">
			<div class="valid-feedback">Apellidos correcto</div>
			<div class="invalid-feedback">${cliente.errorApellidos != null ? cliente.errorApellidos : 'Los apellidos no son obligatorios, pero deben tener al menos 3 letras y comenzar en mayúscula'}</div>
		</div>
	</div>
	
	<!-- cif -->
	<div class="form-group row">
		<label for="cif" class="col-md-4 col-lg-3  col-form-label">CIF</label>
		<div class="col">
			<input type="text" class="form-control ${cliente.errorCif != null ? 'is-invalid' : '' }" id="cif" name="cif" value="${cliente.cif}"
				placeholder="CIF">
			<div class="valid-feedback">CIF correcto</div>
			<div class="invalid-feedback">${cliente.errorCif != null ? cliente.errorCif : 'El CIF debe tener uno de los siguientes formatos: B12345678 X1234567A 12345678Z'}</div>
		</div>
	</div>
	
	<!-- fecha nacimiento -->
	<div class="form-group row">
		<label for=fecha-nacimiento" class="col-md-4 col-lg-3  col-form-label">Fecha de nacimiento</label>
		<div class="col">
			<input type="date" class="form-control ${cliente.errorFechaNacimiento != null ? 'is-invalid' : '' }" id="fecha-nacimiento" name="fecha-nacimiento" value="${cliente.fechaNacimiento}"
				placeholder="Fecha de nacimiento">
			<div class="valid-feedback">Fecha de nacimiento correcta</div>
			<div class="invalid-feedback">${cliente.errorFechaNacimiento != null ? cliente.errorFechaNacimiento : 'Debes ser mayor de edad y no haber nacido en el futuro'}</div>
		</div>
	</div>
	
	<!-- Botón Aceptar -->
	<div class="form-group row">
		<div class="offset-md-4 offset-lg-3 col">
			<button type="submit" class="btn btn-primary">Aceptar</button>
		</div>
	</div>
	
<!--  
	<input type="number" name="id" placeholder="Id" value="${cliente.id}" />
	<div>${cliente.errorId}</div>
	<input type="text" name="nombre" placeholder="Nombre" value="${cliente.nombre}" />
	<div>${cliente.errorNombre}</div>
	<input type="text" name="apellidos" placeholder="Apellidos" value="${cliente.apellidos}" />
	<div>${cliente.errorApellidos}</div>
	<input type="text" name="cif" placeholder="CIF" value="${cliente.cif}" />
	<div>${cliente.errorCif}</div>
	<input type="date" name="fecha-nacimiento" placeholder="Fecha de nacimiento" value="${cliente.fechaNacimiento}" />
	<div>${cliente.errorFechaNacimiento}</div>
	<button>Aceptar</button>
	-->
</form>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp" %>
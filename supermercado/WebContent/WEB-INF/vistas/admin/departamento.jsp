<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp" %>

<!-- ${departamento} -->

<h1>Edición de departamento</h1>

<form action="admin/departamento" method="post">

	<!-- id -->
	<div class="form-group row">
		<label for="id" class="col-md-4 col-lg-3 col-form-label">Id</label>
		<div class="col">
			<input type="number" class="form-control ${cliente.errorId != null ? 'is-invalid' : '' }" id="id" name="id" value="${cliente.id}"
				min="1" readonly placeholder="Id del cliente">
			<div class="valid-feedback">Id correcto</div>
			<div class="invalid-feedback">${cliente.errorId}</div>
		</div>
	</div>
	
	<div class="form-group row departamento">
		<label for="departamento-nombre" class="col-md-4 col-lg-3  col-form-label">Nombre de Departamento</label>
		<div class="col">
			<input type="text" class="form-control" id="departamento-nombre" name="departamento-nombre"
				minlength="3" pattern="\p{Lu}\p{Ll}{2}[\p{Ll}]*"
				placeholder="Debe introducir un nombre con solo letras y mayúscula la primera. Mínimo tres caracteres.">
			<div class="valid-feedback">Nombre correcto</div>
			<div class="invalid-feedback">Debe introducir un nombre con
				como mínimo 3 letras, y solo letras y mayúscula la primera</div>
		</div>
	</div>
	
	<div class="form-group row departamento">
		<label for="departamento-descripcion" class="col-md-4 col-lg-3  col-form-label">Descripción de Departamento</label>
		<div class="col">
			<textarea class="form-control" id="departamento-descripcion" name="departamento-descripcion"></textarea>
			<div class="valid-feedback">Descripción correcta</div>
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
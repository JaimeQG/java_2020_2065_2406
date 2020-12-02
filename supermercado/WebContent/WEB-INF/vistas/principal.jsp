<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<div
	class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 row-cols-xl-5">
	<c:forEach items="${productos}" var="producto">
		<div class="col mb-3">
			<div class="card h-100">
				<c:if test="${producto.descuento != null}">
					<p class="bg-danger text-white position-absolute px-2 mt-1 ml-1 rounded-pill">-${producto.descuento}%</p>
				</c:if>
				<img src="${producto.urlImagen}" class="card-img-top" alt="">
				<%-- <c:if test="${producto.descuento != null}">
					<div class="card-header">${producto.descuento != null ? ''.concat(producto.descuento).concat('% descuento') : ''}</div>
				</c:if> --%>
				<div class="card-body">
					<h5 class="card-title">${producto.nombre}</h5>
					<p class="card-text">${producto.descripcion}</p>
					<p class="card-text lead row">
						<strong class="col">
							<c:if test="${producto.descuento != null}">
								<del>
							</c:if>
							${producto.precio}€
							<c:if test="${producto.descuento != null}">
								</del>
							</c:if>
						</strong>
						<c:if test="${producto.descuento != null}">
							<strong class="col text-danger">${producto.precioConDescuento}€</strong>
						</c:if>
						<small class="text-muted col text-center">(${producto.precioUnidadMedida}€&nbsp;/&nbsp;${producto.unidadMedida})</small>
						<small class="text-muted col text-right">${producto.cantidad} en stock</small>
					</p>
					<p class="card-text">
						<div class="input-group mx-auto" style="width: 9rem">
							<div class="input-group-prepend">
    							<button class="btn btn-secondary" type="button"
								id="button-addon1"><i class="fas fa-minus"></i></button>
  							</div>
  							<input type="text" class="form-control text-center font-weight-bold" placeholder=""
							aria-label="Example text with button addon"
							aria-describedby="button-addon1" value="1" />
							<div class="input-group-append">
    							<button class="btn btn-secondary" type="button" id="button-addon2"><i class="fas fa-plus"></i></button>
  							</div>
						</div>
					</p>
					
				</div>
				<div class="card-footer bg-primary p-0">
					<a href="#" class="btn btn-block text-white">Añadir al carrito</a>
				</div>
			</div>
		</div>

	</c:forEach>
</div>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
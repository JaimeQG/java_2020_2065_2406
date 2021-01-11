<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<form action="agregartodocarrito" method="get">
	<button class="btn btn-primary btn-block mb-3">Agregar todas las cantidades al carrito</button>

<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 row-cols-xl-5">
	<c:forEach items="${productos}" var="producto">
		<div class="col mb-3">
			<div class="card h-100">
				<c:if test="${producto.descuento != null}">
					<p class="bg-danger h4 text-white position-absolute px-2 mt-1 ml-1 rounded-pill">-${producto.descuento}%</p>
				</c:if>
				<img src="product_imgs/${producto.urlImagen}" class="card-img-top" alt="">
				<%-- <c:if test="${producto.descuento != null}">
					<div class="card-header">${producto.descuento != null ? ''.concat(producto.descuento).concat('% descuento') : ''}</div>
				</c:if> --%>
				<div class="card-body">
						<h5 class="card-title">${producto.nombre}</h5>
						<p class="card-text">${producto.descripcion}</p>
						<p class="card-text lead row">
							<strong
								class="${producto.descuento != null ? 'col-6' : 'col-12'}">
								<c:if test="${producto.descuento != null}">
									<del>
								</c:if> <fmt:formatNumber type="currency" value="${producto.precio}" />
								<c:if test="${producto.descuento != null}">
									</del>
								</c:if>
							</strong>
							<c:if test="${producto.descuento != null}">
								<strong class="col-6 text-danger text-right"> <fmt:formatNumber
										type="currency" value="${producto.precioConDescuento}" />
								</strong>
							</c:if>
							<small class="text-muted col-12">(<fmt:formatNumber
									type="currency" value="${producto.precioUnidadMedida}" />&nbsp;/&nbsp;${producto.unidadMedida})
							</small> <small class="text-muted col-12">${producto.cantidad} en
								stock</small>
						</p>
						<p class="card-text">
							<div class="input-group mx-auto" style="width: 9rem">
								<div class="input-group-prepend">
									<button class="btn btn-secondary btn-menos" type="button">
										<i class="fas fa-minus"></i>
									</button>
								</div>
								<input type="number"
									class="form-control text-center font-weight-bold" placeholder=""
									aria-label="Cantidad" value="0" name="${producto.id}"
									min="0" />
								<div class="input-group-append">
									<button class="btn btn-secondary btn-mas" type="button">
										<i class="fas fa-plus"></i>
									</button>
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
</form>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<!-- ${libreria} -->

<div class="row row-cols-1 row-cols-md-3 g-4 mx-2">
	<c:forEach items="${libreria}" var="libro">
  		<div class="col py-2">
    		<div class="card h-100 border-0">
    			<div class="card-body">
      				<img src="${libro.urlImagen}" class="imagen card-img-top mx-auto d-block" alt="...">
      
      				<hr>
      				<div class="row">
      					<div class="col-10">
       						<p class="card-text text-muted">${libro.nombre}</p>
       						<p class="card-text text-uppercase fw-bold"><strong>${libro.autor}</strong></p>
      					</div>
       
      					<div class="col-2 text-right">
      						<i class="far fa-heart"></i>
      					</div>
     				</div>
      			</div>
    		</div>
  		</div>
 	</c:forEach>
 </div>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
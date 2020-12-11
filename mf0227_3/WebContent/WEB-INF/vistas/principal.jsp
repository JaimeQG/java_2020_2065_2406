<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<!-- ${libreria} -->

<div class="row row-cols-1 row-cols-md-3 g-4 mx-0">
	<c:forEach items="${libreria}" var="libro">
  		<div class="col py-2">
    		<div class="card h-200 border-0">
    			<div class="card-body">
      				<img src="${libro.urlImagen}" class="imagen card-img-top mx-auto d-block" alt="...">
      
      				<hr>
      				<div class="row">
      					<div class="col-10">
       						<p class="card-text text-muted">${libro.nombre}</p>
       						<p class="card-text text-uppercase">${libro.autor}</p>
      					</div>
       
      					<div class="col-2 text-right">
      						<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-heart" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  								<path fill-rule="evenodd" d="M8 2.748l-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"/>
							</svg>
      					</div>
     				</div>
      			</div>
    		</div>
  		</div>
 	</c:forEach>
 </div>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
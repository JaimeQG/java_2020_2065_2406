<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp" %>

<div class="jumbotron">
	<p class="display-1">Ha habido un error en la página</p>
	<small><%= request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE) %></small>
	<small><%= request.getAttribute(RequestDispatcher.ERROR_MESSAGE) %></small>
</div>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp" %>
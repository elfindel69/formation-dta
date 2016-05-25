<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored='false'%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liste des pizzas</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<%@ page import="fr.pizzeria.model.Pizza"%>
<%@ page import="java.util.List"%>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
<body>
	<h1>Liste des pizzas</h1>

	<c:set var="pizzas" value="${list}" />

	<a class="btn btn-success"
		href="<c:url value="/pizzas/new"/>">nouvelle pizza</a>
	<table class="table table-striped">
		<thead>
			<tr>
				<td>ID</td>
				<td>CODE</td>
				<td>NOM</td>
				<td>CATEGORIE</td>
				<td>PRIX</td>
				<td>IMAGE</td>
			</tr>
		</thead>
	<c:out value="${param.list}"></c:out>
		<c:forEach var="p" items="${pizzas}">

			<tr id="tr-<c:out value='${p.code}'/>">
				<td><c:out value='${p.id}' /></td>
				<td><c:out value='${p.code}' /></td>
				<td><c:out value='${p.nom}' /></td>
				<td><c:out value='${p.cat.name()}' /></td>
				<td><c:out value='${p.prix}' /></td>
				<td><img src="http://placehold.it/150x150" /></td>
				<td><a class="btn btn-success"
					href="<c:url value="/pizzas/edit?code=${p.code}"/>">Editer</a>
				<td><button class="btn btn-danger" type='button'
						onclick="supprimer('<c:out value='${p.code}'/>')">Supprimer</button>
			</tr>
		</c:forEach>
	</table>
	<c:remove var="pizzas" />
	<script>
		function supprimer(code_pizz){
			url = '<%=request.getContextPath()%>/pizzas/edit?code='+code_pizz;
			$.ajax({
	 			  type: "DELETE",
	 		  url: url,
				  success: function(){
					  $("#tr-"+code_pizz).remove();
				  }
	 		});
		}
	
	</script>
</body>
</html>
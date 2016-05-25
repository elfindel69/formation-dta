
<%@page import="fr.pizzeria.model.CategoriePizza"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page isELIgnored='false' %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edition des pizzas</title>
<%@ page import="fr.pizzeria.model.Pizza"%>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>
	<c:set var='pizza' value="${pizza}"/>
	<form method="post" class="form-horizontal" role="form">
		<fieldset>
			<legend>edition d'une pizza</legend>
			<div class="form-group">
				<label for="code" class="col-md-2 control-label">Code</label>
				<div class="col-md-10">
					<input type="text" name="code" id="code"
						class="form-control input-md" value="<c:out value='${pizza.code}'/>">
				</div>

			</div>
			<div class="form-group">
				<label for="nom" class="col-md-2 control-label">Nom</label>
				<div class="col-md-10">
					<input type="text" name="nom" id="nom" class="form-control"
						 value="<c:out value='${pizza.nom}'/>">
				</div>
			</div>
			<div class="form-group">
				<label for="prix" class="col-md-2 control-label">Prix</label>
				<div class="col-md-10">
					<input type="number" name="prix" id="prix" class="form-control"
						value="<c:out value='${pizza.prix}'/>">
				</div>
			</div>
			<div class="form-group">
				<label for="cat" class="col-md-2 control-label">Categorie</label>
				<div class="col-md-10">
					<select name="cat" id="cat" class="form-control">
						<option <c:if test="${pizza.cat.equals(CategoriePizza.VIANDE)}">selected</c:if> value="VIANDE">Viande</option>
						<option <c:if test="${pizza.cat.equals(CategoriePizza.SANS_VIANDE)}">selected</c:if> value="SANS_VIANDE">Sans Viande</option>
						<option <c:if test="${pizza.cat.equals(CategoriePizza.POISSON)}">selected</c:if> value="POISSON">Poisson</option>
					</select>
				</div>
			</div>
			<div class="col-md-2 col-md-offset-2">
				<input type="hidden" name="id"  value="<c:out value='${pizza.id}'/>">

				<button class="btn btn-success"type="submit">Valider</button>
			</div>
		</fieldset>


	</form>

<c:remove var='pizza'/>

</body>
</html>
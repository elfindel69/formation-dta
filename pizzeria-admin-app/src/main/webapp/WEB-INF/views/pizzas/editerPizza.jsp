
<%@page import="fr.pizzeria.model.CategoriePizza"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edition des pizzas</title>
<%@ page import="fr.pizzeria.model.Pizza"%>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>
<body>

	<%
		Pizza pizza = (Pizza) request.getAttribute("pizza");
	%>
	<form method="post" class="form-horizontal" role="form">
		<fieldset>
			<legend>edition d'une pizza</legend>
			<div class="form-group">
				<label for="code" class="col-md-2 control-label">Code</label>
				<div class="col-md-10">
					<input type="text" name="code" id="code"
						class="form-control input-md" value="<%=pizza.getCode()%>">
				</div>

			</div>
			<div class="form-group">
				<label for="nom" class="col-md-2 control-label">Nom</label>
				<div class="col-md-10">
					<input type="text" name="nom" id="nom" class="form-control"
						value="<%=pizza.getNom()%>">
				</div>
			</div>
			<div class="form-group">
				<label for="prix" class="col-md-2 control-label">Prix</label>
				<div class="col-md-10">
					<input type="number" name="prix" id="prix" class="form-control"
						value="<%=pizza.getPrix()%>">
				</div>
			</div>
			<div class="form-group">
				<label for="cat" class="col-md-2 control-label">Categorie</label>
				<div class="col-md-10">
					<select name="cat" id="cat" class="form-control"
						value="<%=pizza.getCat().name()%>">
						<%for(CategoriePizza cat : CategoriePizza.values()){
							%>
							<option value="<%=cat.name()%>"><%=cat.getLibelle() %></option>
						<%} %>
						
						
					</select>
				</div>
			</div>
			<div class="col-md-2 col-md-offset-2">
				<input type="hidden" name="id" value="<%=pizza.getId()%>">

				<button class="btn btn-success"type="submit">Valider</button>
			</div>
		</fieldset>


	</form>



</body>
</html>
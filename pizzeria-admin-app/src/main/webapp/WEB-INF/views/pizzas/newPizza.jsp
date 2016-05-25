<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nouvelle pizza</title>
<%@page import="fr.pizzeria.model.CategoriePizza"%>
<%@page import="fr.pizzeria.model.Pizza"%>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>
</head>

<body>




	<!-- Thumbnails -->
	<div class="page-header">
		<h1>Pizza</h1>
	</div>

	<%
		Pizza pizza = new Pizza();
	%>
	<form class="form-horizontal"
		action="<%=request.getContextPath()%>/pizzas/new" method="post">
		<fieldset>

			<!-- Form Name -->
			<legend>Nouvelle pizza</legend>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="code">Code</label>
				<div class="col-md-4">
					<input id="code" name="code" type="text" placeholder="code pizza"
						class="form-control input-md" required="">

				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="nom">Nom</label>
				<div class="col-md-4">
					<input id="nom" name="nom" type="text" placeholder="nom pizza"
						class="form-control input-md" required="">

				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="prix">Prix</label>
				<div class="col-md-4">
					<input id="prix" name="prix" type="text" placeholder="prix pizza"
						class="form-control input-md" required="">

				</div>
			</div>

			<!-- Select Basic -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="categorie">Categorie</label>
				<div class="col-md-4">
					<select id="categorie" name="categorie" class="form-control">
						<option value="VIANDE">Viande</option>
						<option value="SANS_VIANDE">Sans Viande</option>
						<option value="POISSON">Poisson</option>
					</select>
				</div>
			</div>

			<!-- Button (Double) -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="modifier"></label>
				<div class="col-md-8">
					<button id="modifier" class="btn btn-success" type="submit">Ajouter</button>
				</div>
			</div>

		</fieldset>
	</form>
	<div class="clearfix"></div>
</body>
</html>
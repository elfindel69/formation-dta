<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page isELIgnored='false' %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nouvelle pizza</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
</head>

<body>
	<!-- Thumbnails -->
	<div class="page-header">
		<h1>Pizza</h1>
	</div>
	<form class="form-horizontal" method="post">
		<fieldset>

			<!-- Form Name -->
			<legend>Nouvelle pizza</legend>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="code">Code</label>
				<div class="col-md-4">
					<input id="code" name="code" type="text" placeholder="code pizza"
						class="form-control input-md" required>

				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="nom">Nom</label>
				<div class="col-md-4">
					<input id="nom" name="nom" type="text" placeholder="nom pizza"
						class="form-control input-md" required>

				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="prix">Prix</label>
				<div class="col-md-4">
					<input id="prix" name="prix" type="text" placeholder="prix pizza"
						class="form-control input-md" required>

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
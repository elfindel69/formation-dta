<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page isELIgnored='false'%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Authentification</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>
</head>

<body>
	<!-- Thumbnails -->
	<div class="page-header">
		<h1>Gestion de la pizzeria</h1>
	</div>
	<c:if test="${message != null}">
	<div class="alert alert-danger">
	<c:out value="${message}"/>
	</div>
	</c:if>
	

	<form class="form-horizontal" method="post">
		<fieldset>

			<!-- Form Name -->
			<legend>Authentification</legend>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="email">e-mail</label>
				<div class="col-md-4">
					<input id="email" name="email" type="email" placeholder="e-mail"
						class="form-control input-md" required>

				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="password">Mot de passe</label>
				<div class="col-md-4">
					<input id="password" name="password" type="text" placeholder="mot de passe"
						class="form-control input-md" required>

				</div>
			</div>
			<!-- Button (Double) -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="modifier"></label>
				<div class="col-md-8">
					<button id="modifier" class="btn btn-success" type="submit">Se connecter</button>
				</div>
			</div>

		</fieldset>
	</form>
	<div class="clearfix"></div>
</body>
</html>
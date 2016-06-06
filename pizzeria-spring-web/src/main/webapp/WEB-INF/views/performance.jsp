<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored='false'%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Performance</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<%@ page import="fr.pizzeria.model.Performance"%>
<%@ page import="java.util.List"%>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
<body>
	<h1>Performance</h1>

	<c:set var="perfs" value="${performance}" />

	<table class="table table-striped">
		<thead>
			<tr>
				<td>ID</td>
				<td>SERVICE</td>
				<td>DATE MESURE</td>
				<td>EXECUTION</td>
			</tr>
		</thead>
	<c:out value="${param.list}"></c:out>
		<c:forEach var="p" items="${perfs}">

			<tr>
				<td><c:out value='${p.id}' /></td>
				<td><c:out value='${p.service}' /></td>
				<td><c:out value='${p.dateMesure}' /></td>
				<td><c:out value='${p.execution}' /></td>
			</tr>
		</c:forEach>
	</table>
	<c:remove var="perfs" />
	
</body>
</html>
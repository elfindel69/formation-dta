<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
</head>
<body>
	<h1>Liste des pizzas</h1>
	<%
		List<Pizza> pizzas = (List<Pizza>) request.getAttribute("list");
	%>
	<a class="btn btn-success" href="<%=request.getContextPath() %>/pizzas/new">nouvelle pizza</a>
	<table class="table table-striped">
		<thead>
			<tr>
				<td>ID</td>
				<td>CODE</td>
				<td>NOM</td>
				<td>PRIX</td>
				<td>IMAGE</td>
			</tr>
		</thead>
		<%
			for (Pizza p : pizzas) {
		%>
		<tr id="tr-<%=p.getCode()%>">
			<td><%=p.getId()%></td>
			<td><%=p.getCode()%></td>
			<td><%=p.getNom()%></td>
			<td><%=p.getPrix()%></td>
			<td><img src="http://placehold.it/150x150" /></td>
			<td><a class="btn btn-primary"
				href='<%=request.getContextPath()%>/pizzas/edit?code=<%=p.getCode()%>'>Editer</a>
			<td><button class="btn btn-primary" type='button'
					onclick="supprimer('<%=p.getCode()%>')">Supprimer</button>
		</tr>

		<%
			}
		%>
	</table>
	<script>
	function supprimer(code_pizz){
				url = '<%=request.getContextPath()%>/pizzas/edit?code='+ code_pizz;
			$.ajax({
				type : "DELETE",
				url : url,
				success : function() {
					$("#tr-" + code_pizz).remove();
				}
			});
		}
	</script>

</body>
</html>
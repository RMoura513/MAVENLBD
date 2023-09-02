<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/styles.css">
<title>Cliente</title>
</head>
<body>
	<div>
		<jsp:include page="menu.jsp" />
	</div>
	<br />
	<div align="center" class="container">
		<form action="cliente" method="post">
		<p class="title">
			<b>Cliente</b>
		</p>
		<table>
			<tr>
				<td colspan="3">
				<input class="input_data" type="number" id="CPF" name="CPF" placeholder="CPF"
				 value='<c:out value="${cliente.cpf }"></c:out>'>
				</td>
				<td>
					<input type="submit" id="botao" name="botao" value="Buscar">
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<input class="input_data" type="text" id="nome" name="nome" placeholder="Nome"
					 value='<c:out value="${cliente.nome }"></c:out>'>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<input class="input_data" type="text" id="email" name="email" placeholder="E-Mail"
					value='<c:out value="${cliente.email }"></c:out>'>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<input class="input_data" type="number" id="limite_de_credito" name="limite_de_credito" placeholder="Limite de Cr�dito"
					value='<c:out value="${cliente.limite_de_credito }"></c:out>'>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<input class="input_data" type="date" id="dt_nascimento" name="dt_nascimento" placeholder="Data de Nascimento"
					value='<c:out value="${cliente.dt_nascimento }"></c:out>'>
				</td>
			<tr>
				<td>
					<input type="submit" id="botao" name="botao" value="Cadastrar">
				</td>
				<td>
					<input type="submit" id="botao" name="botao" value="Alterar">
				</td>
				<td>
					<input type="submit" id="botao" name="botao" value="Excluir">
				</td>
				<td>
					<input type="submit" id="botao" name="botao" value="Listar">
				</td>
			</tr>
			
		</table>
		</form>
	</div>
	<br />
	<div align="center">
		<c:if test="${not empty erro }">
			<H2><b><c:out value="${erro}" /></b></H2>
		</c:if>
	</div>
		<div align="center">
		<c:if test="${not empty saida }">
			<H3><b><c:out value="${saida}" /></b></H3>
		</c:if>
	</div>
	<br />
	<c:if test="${not empty clientes }">
		<table class="table_round">
			<thead>
				<tr>
					<th>CPF</th>
					<th>Nome</th>
					<th>E-Mail</th>
					<th>Limite de Cr�dito</th>
					<th>Data de nascimento</th>
				</tr>
			</thead>
			<tbody>
				
				<c:forEach var="c" items="${clientes }">
					<tr>
						<td><c:out value="${c.cpf}" /></td>
						<td><c:out value="${c.nome}" /></td>
						<td><c:out value="${c.email}" /></td>
						<td><c:out value="${c.limite_de_credito}" /></td>
						<td><c:out value="${c.dt_nascimento}" /></td>
					</tr>
		
				</c:forEach>
				
			</tbody>
		</table>
	</c:if>
</body>
</html>
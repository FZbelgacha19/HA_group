<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="r" uri="http://www.rapid-framework.org.cn/rapid"%>
<!DOCTYPE html>
<html lang="en">
<head>
</head>
<body>
	<!-- Section-->
	<r:override name="adminContainer">
		<div class="d-flex justify-content-center">
			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">email</th>
						<th scope="col">nom_complete</th>
						<th scope="col">Adress</th>
						<th scope="col">Tele</th>
						<th scope="col">dateNaissance</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listAdmin}" var="a">
						<tr>
							<th scope="row">${a.getId() }</th>
							<td>${a.getEmail() }</td>
							<td>${a.getNom_complete() }</td>
							<td>${a.getAdress() }</td>
							<td>${a.getTele() }</td>
							<td>${a.getDateNaissance() }</td>
							<td class="d-flex justify-content-around" id="BtnEditing"><a
								href="Modifier_admin?id=${a.getId()}"
								class="btn btn-outline-primary m-2 btn-sm"><i
									class="bi bi-person-lines-fill"></i></a> <a
								href="Supprime_admin?id=${a.getId()}"
								class="btn btn-outline-danger m-2 btn-sm"><i
									class="bi bi-person-x-fill"></i></a></td>
						</tr>

					</c:forEach>
				</tbody>
			</table>
		</div>
	</r:override>
	<jsp:include page="/admin_views/accueil.jsp" />
</body>
</html>

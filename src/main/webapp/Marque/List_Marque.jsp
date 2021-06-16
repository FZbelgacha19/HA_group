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
		<div class="d-flex justify-content-center container">
			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">Nom Marque</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listMarq}" var="m">
						<tr>
							<th scope="row">${m.getId_marque()}</th>
							<td>${m.getLib_marque()}</td>
							<td class="d-flex justify-content-around" id="BtnEditing"><a
								href="Modifier_Marque?id=${m.getId_marque()}"
								class="btn btn-outline-primary m-2 btn-sm"><i
									class="bi bi-pencil-square"></i></a> <a
								href="Supp_Marque?id=${m.getId_marque()}"
								class="btn btn-outline-danger m-2 btn-sm"><i
									class="bi bi-trash"></i></a></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</r:override>
	<jsp:include page="/admin_views/accueil.jsp" />
</body>
</html>

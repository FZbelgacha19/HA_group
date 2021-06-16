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
						<th scope="col">Reference</th>
						<th scope="col">Nom</th>
						<th scope="col">Description</th>
						<th scope="col">Prix U</th>
						<th scope="col">Quantite</th>
						<th scope="col">Marque</th>
						<th scope="col">Categorie</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listprod}" var="p">
						<tr>
							<th scope="row">${p.getRef_pr()}</th>
							<td>${p.getNom()}</td>
							<td>${p.getDescription()}</td>
							<td>${p.getPrix()} DH</td>
							<td>${p.getQte()}</td>
							<td>${m.getMarqueName(p.getMarque())}</td>
							<td>${c.getCatName(p.getCat())}</td>
							<td class="d-flex justify-content-around" id="BtnEditing"><a
								href="Modifier_Produit?id=${p.getRef_pr()}"
								class="btn btn-outline-primary m-2 btn-sm"><i
									class="bi bi-pencil-square"></i></a> <a
								href="Supp_Produit?id=${p.getRef_pr()}"
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

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="r" uri="http://www.rapid-framework.org.cn/rapid" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<%@ page import="visiteur.models.Panier"%>
<%@ page import="visiteur.dao.PanierDao"%>
<%@ page import="admin.dao.ProduitDao"%>
<%@ page import="visiteur.dao.imp.PanierDaoImp"%>
<%@ page import="admin.dao.imp.ProduitDaoImp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<script type="text/javascript">
function scroll(id) {
    var el = document.getElementById(id);
    el.scrollIntoView(true);
}
</script>
</head>
<body onload="scroll('${id}')">
<c:set var="id" value="0" scope="session"/>
	<!-- Section-->
	<r:override name="contenu">

		<div class="container px-4 px-lg-5 mt-5 row">
		<div class="col-2">
		<form action="filterparMarque?id_c=${id_c}" method="post">

		<c:forEach items="${ListM}" var="m">
		<div class="d-flex justify-content-between">
		<c:set var="idM" value="${m.getId_marque()}"></c:set>
		<input type="checkbox" name="marques" value="${m.getId_marque()}"
		<c:if test="${fn:contains(marq, idM)}">checked</c:if> />
		<label>${m.getLib_marque()}</label>
		</div>

		</c:forEach>
				<div class="d-flex justify-content-end m-4">
				<input type="submit" class="btn btn-outline-dark" value="Filtre">
				</div>
		</form>
		</div>

			<div
				class="col row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
				<c:forEach items="${listProd}" var="p">
				<c:if test="${p.getQte() gt 0}">
				<div class="col mb-5" id="${p.getRef_pr()}">
					<div class="card h-100">
						<!-- Product image-->
						<img class="card-img-top"
							src="Produit/img/${p.getImage()}" alt="..." />
						<!-- Product details-->
						<div class="card-body p-4">
							<div class="text-center">
								<!-- Product name-->
								<h5 class="fw-bolder">${p.getNom()}</h5>
								<!-- Product price-->
								${p.getPrix()} DH
							</div>
						</div>
						<!-- Product actions-->
						
						<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
							<div class="text-center">
								<a class="btn btn-outline-dark mt-auto" href="Ajouter_Panier?id=${p.getRef_pr()}"><i class="bi bi-cart-plus"></i></a>
							</div>
						</div>
					</div>
				</div>
				</c:if>
				</c:forEach>

			</div>
			</div>
	
	</r:override>
<jsp:include page="/base.jsp"/>
</body>
</html>

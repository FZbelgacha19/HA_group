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
	<r:override name="contenu">

		<div class="container px-4 px-lg-5 mt-5">
			<div class="container m-2 d-flex justify-content-end">
				<a href="Retour_panier?id_lp=${id_lp}&id_user=${id_user}" class="btn btn-outline-dark m-2">Retour au
					panier</a> <a href="Finir_Commande?id_lp=${id_lp}&id_user=${id_user}" class="btn btn-outline-dark m-2">Finir
					la commande</a>
			</div>
			<div
				class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
				<table class="table table-hover">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Produit</th>
							<th scope="col">Quantite</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="i" value="0"/>
						<c:forEach items="${listCmd}" var="lp">
							<tr>
								<th scope="row">${i=i+1}</th>
								<td>${p_dao.GetNameProduit(lp.getRef_prod())}</td>
								<td>${lp.getQte_cmd()}</td>

							</tr>

						</c:forEach>
					</tbody>
				</table>
			</div>

		</div>
	</r:override>
	<jsp:include page="/base.jsp" />
</body>
</html>

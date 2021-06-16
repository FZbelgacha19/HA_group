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
			<form class="shadow-lg p-3 mb-5 bg-body rounded"
				action="Modifier_Produit?id=${p.getRef_pr()}" enctype="multipart/form-data" method="post">
				<h2 class="fw-bold">Ajouter Nouveau produit</h2>
				<div class="form-group mb-1">
					<div class="input-group">
						<span class="input-group-text"><i class="bi bi-link-45deg"></i></span>
						<input id="Ref_pr" name="Ref_pr" type="number" required="required"
							class="form-control" placeholder="Reference de produit" value="${p.getRef_pr()}" disabled="disabled">

					</div>
				</div>
				<div class="form-group mb-1">
					<div class="input-group">
						<span class="input-group-text"><i class="bi bi-box-seam"></i></i></span> <input
							id="Nom" name="Nom" type="text" required="required"
							class="form-control" placeholder="Nom du produit" value="${p.getNom()}">

					</div>
				</div>
				<div class="form-group mb-1">
					<div class="input-group">
						<span class="input-group-text"><i class="bi bi-box-seam"></i></span> <input
							id="Description" name="Description" type="text"
							required="required" class="form-control"
							placeholder="Description" value="${p.getDescription()}">

					</div>
				</div>
				<div class="form-group mb-1">
					<div class="input-group">
						<span class="input-group-text"><i class="bi bi-currency-dollar"></i></span> <input
							id="Prix" name="Prix" type="number" required="required"
							class="form-control" placeholder="Prix unitair de produit" value="${p.getPrix()}">

					</div>
				</div>
				<div class="form-group mb-1">
					<div class="input-group">
						<span class="input-group-text"><i class="bi bi-grid"></i></span> <input
							id="qte" name="qte" type="number" required="required"
							class="form-control" placeholder="Quantite disponible de produit" value="${p.getQte()}">

					</div>
				</div>
				<div class="form-group mb-1">
					<div class="input-group">
						<span class="input-group-text"><i class="bi bi-shop"></i></span> <select
							class="form-select" aria-label="Default select example"
							name="marque">
							<option selected disabled="disabled">Marque de produit</option>
							<c:forEach items="${listMarq}" var="m">
								<option value="${m.getId_marque()}" ${ p.getMarque() == m.getId_marque() ? 'selected': '' }>${m.getLib_marque()}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group mb-1">
					<div class="input-group">
						<span class="input-group-text"><i class="bi bi-shop"></i></span> <select
							class="form-select" aria-label="Default select example"
							name="cat">
							<option selected disabled="disabled">Marque de categorie</option>
							<c:forEach items="${listCat}" var="c">
								<option value="${c.getCode_cat()}" ${ p.getCat() == c.getCode_cat() ? 'selected': '' }>${c.getNom_cat()}</option>
							</c:forEach>
						</select>

					</div>
				</div>
				<div class="form-group mb-1">
					<div class="input-group">
						<span class="input-group-text"><i class="bi bi-grid"></i></span> <input
							id="Image" name="Image" type="file"
							class="form-control" placeholder="Nom du marque"
							onchange="document.getElementById('preview').src = window.URL.createObjectURL(this.files[0])">
					</div>
					<img src="Produit/img/${p.getImage()}" width="500"
						height="500" id="preview"/>
				</div>
				<br>
				<div class="form-group mb-1 d-flex justify-content-end">
					<button name="submit" type="submit"
						class="btn btn-outline-primary my-3 signin">Modifier</button>
				</div>
			</form>
		</div>
	</r:override>

	<jsp:include page="/admin_views/accueil.jsp" />

</body>
</html>

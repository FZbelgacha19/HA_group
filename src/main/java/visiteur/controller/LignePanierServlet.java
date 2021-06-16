package visiteur.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.dao.CategoriDao;
import admin.dao.ProduitDao;
import admin.dao.imp.CategorieDaoImp;
import admin.dao.imp.ProduitDaoImp;
import admin.models.Produit;
import visiteur.dao.LignepanierDao;
import visiteur.dao.PanierDao;
import visiteur.dao.VisiteurDao;
import visiteur.dao.imp.LignePanierDaoImp;
import visiteur.dao.imp.PanierDaoImp;
import visiteur.dao.imp.VisiteurDaoImp;
import visiteur.models.Lignepanier;
import visiteur.models.Visiteur;


/**
 * Servlet implementation class LignePanierServlet
 */
@WebServlet(urlPatterns = {"/valideCommande","/Retour_panier"})
public class LignePanierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    PanierDao pn_dao = new PanierDaoImp();
    ProduitDao p_dao = new ProduitDaoImp();
    LignepanierDao lp_dao = new LignePanierDaoImp();
    VisiteurDao v_dao = new VisiteurDaoImp();
    CategoriDao c_dao = new CategorieDaoImp();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LignePanierServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().equals("/valideCommande")) {
			int id_vis =1+v_dao.maxid();
			Visiteur v = new Visiteur(id_vis);
			int id_panier = 1+lp_dao.maxid();
			HashMap<Integer, Integer> listC = pn_dao.GetTotalCommande();
			for(Map.Entry<Integer, Integer> e : listC.entrySet()) {
				Produit p = p_dao.GetProduit(e.getKey());
	
				Lignepanier lp = new Lignepanier(id_panier, p.getRef_pr(), e.getValue(),v.getId_Visiteur());
				lp_dao.AjouteLignepanier(lp);
			}
			v_dao.AjouteVisiteur(v);
			request.setAttribute("listCmd", lp_dao.ListLignepanier(v.getId_Visiteur(), id_panier));
			request.setAttribute("p_dao", p_dao);
			request.setAttribute("id_user", id_vis);
			request.setAttribute("id_lp", id_panier);
			request.setAttribute("nbAchat", pn_dao.ListPanier().size());
			request.setAttribute("listC", c_dao.ListCategorie());
			request.getRequestDispatcher("visiteur/Commande.jsp").forward(request, response);
			
			
		}else if(request.getServletPath().equals("/Retour_panier")) {
			int id_lp = Integer.parseInt(request.getParameter("id_lp"));
			int id_user = Integer.parseInt(request.getParameter("id_user"));
			v_dao.SuppVisiteur(id_user);
			lp_dao.SuppLignepanier(id_lp, id_user);
			response.sendRedirect(request.getContextPath()+"/List_Achat");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

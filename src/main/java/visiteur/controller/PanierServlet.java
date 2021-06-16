package visiteur.controller;

import java.io.IOException;

import java.util.HashMap;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.dao.CategoriDao;
import admin.dao.ProduitDao;
import admin.dao.imp.CategorieDaoImp;
import admin.dao.imp.ProduitDaoImp;
import admin.models.Produit;
import visiteur.dao.PanierDao;
import visiteur.dao.imp.PanierDaoImp;
import visiteur.models.Panier;

/**
 * Servlet implementation class PanierServlet
 */
@WebServlet(urlPatterns = {"/Ajouter_Panier","/List_Achat","/Supp_panier"})
public class PanierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    PanierDao pn_dao = new PanierDaoImp();
    ProduitDao p_dao = new ProduitDaoImp();
    CategoriDao c_dao = new CategorieDaoImp();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PanierServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().equals("/Ajouter_Panier")) {
			int Ref_pr = Integer.parseInt(request.getParameter("id"));
			int id_p = 1+pn_dao.maxid();
			Panier pn = new Panier(id_p, Ref_pr);
			pn_dao.AjoutePanier(pn);
			HttpSession se = request.getSession();
			se.setAttribute("id", Ref_pr);
			String referer = request.getHeader("Referer");
			response.sendRedirect(referer);
			
			
		}else if(request.getServletPath().equals("/List_Achat")) {
			
			HashMap<Integer, Produit> listAchat = new HashMap<Integer, Produit>();
			List<Panier> panier = pn_dao.ListPanier();
			for (Panier p : panier) {
				Produit pr = p_dao.GetProduit(p.getRef_pr());
				listAchat.put(p.getId_panier(), pr);
			}
			request.setAttribute("listAchat",listAchat);
			request.setAttribute("nbAchat", pn_dao.ListPanier().size());
			request.setAttribute("listC", c_dao.ListCategorie());
			request.getRequestDispatcher("visiteur/List_Achat.jsp").forward(request, response);
			
			
		}else if(request.getServletPath().equals("/Supp_panier")) {
			int id = Integer.parseInt(request.getParameter("id"));
			pn_dao.SuppPanier(id);
			response.sendRedirect(request.getContextPath() + "/List_Achat");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}

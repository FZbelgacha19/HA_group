package admin.controller;


import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.dao.CommandeDao;
import admin.dao.FactureDao;
import admin.dao.ProduitDao;
import admin.dao.imp.CommandeDaoImp;
import admin.dao.imp.FactureDaoImp;
import admin.dao.imp.ProduitDaoImp;
import admin.models.Commande;
import admin.models.Facture;
import admin.models.Produit;
import visiteur.dao.LignepanierDao;
import visiteur.dao.PanierDao;
import visiteur.dao.VisiteurDao;
import visiteur.dao.imp.LignePanierDaoImp;
import visiteur.dao.imp.PanierDaoImp;
import visiteur.dao.imp.VisiteurDaoImp;
import visiteur.models.Lignepanier;

/**
 * Servlet implementation class CommandeServlet
 */
@WebServlet(urlPatterns = {"/Finir_Commande"})
public class CommandeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PanierDao pn_dao = new PanierDaoImp();
	ProduitDao p_dao = new ProduitDaoImp();
	LignepanierDao lp_dao = new LignePanierDaoImp();
	VisiteurDao v_dao = new VisiteurDaoImp();
	CommandeDao c_dao = new CommandeDaoImp();
	FactureDao f_dao = new FactureDaoImp();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommandeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().equals("/Finir_Commande")) {
			int id_lp = Integer.parseInt(request.getParameter("id_lp"));
			int id_user = Integer.parseInt(request.getParameter("id_user"));
			Date d = new Date();
			SimpleDateFormat f = new SimpleDateFormat("dd/MM/YYYY");
			List<Lignepanier> lps = lp_dao.ListLignepanier(id_user, id_lp);
			double prix = 0;
			int qte = 0;
			for (Lignepanier l : lps) {
				Produit p = p_dao.GetProduit(l.getRef_prod());
				prix+= l.getQte_cmd() * p.getPrix();
				qte += l.getQte_cmd();
				p.setQte(p.getQte()-l.getQte_cmd());
				p_dao.ModifProduit(p);
			}
			int id_cmd = 1+c_dao.maxid();
			Commande c = new Commande(id_cmd,f.format(d), prix, id_lp,qte);
			c_dao.AjouteCommande(c);
			int f_id = 1+f_dao.maxid();
			Facture fact = new Facture(f_id, id_cmd, id_user, "no_paye");
			f_dao.AjouteFacture(fact);
			FactureServlet fs = new FactureServlet();
			String filepath = fs.ExportFacture(id_lp, id_user, id_cmd, f_id, request, response);
			pn_dao.SuppPanier();
			HttpSession se = request.getSession();
			se.setAttribute("filepath", filepath);
			response.sendRedirect(request.getContextPath()+"/Imprimer_Facture");
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

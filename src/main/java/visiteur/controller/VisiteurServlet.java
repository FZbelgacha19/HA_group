package visiteur.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.dao.CategoriDao;
import admin.dao.MarqueDao;
import admin.dao.ProduitDao;
import admin.dao.imp.CategorieDaoImp;
import admin.dao.imp.MarqueDaoImp;
import admin.dao.imp.ProduitDaoImp;
import admin.models.Produit;
import visiteur.dao.PanierDao;
import visiteur.dao.VisiteurDao;
import visiteur.dao.imp.PanierDaoImp;
import visiteur.dao.imp.VisiteurDaoImp;

/**
 * Servlet implementation class VisiteurServlet
 */
@WebServlet(urlPatterns = {"", "/List_visiteur","/Supp_visiteur","/Filter_Produit","/filterparMarque"})
public class VisiteurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProduitDao p_dao = new ProduitDaoImp();
	CategoriDao c_dao = new CategorieDaoImp();
	MarqueDao m_dao = new MarqueDaoImp();
	VisiteurDao v_dao = new VisiteurDaoImp();
	PanierDao pn_dao = new PanierDaoImp();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisiteurServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().equals("")) {
			request.setAttribute("listProd", p_dao.ListProduit());
			request.setAttribute("nbAchat", pn_dao.ListPanier().size());
			request.setAttribute("listC", c_dao.ListCategorie());
			HttpSession se = request.getSession();
			se.setAttribute("marq", "");
			request.getRequestDispatcher("visiteur/Accueil.jsp").forward(request, response);
			
			
		}else if(request.getServletPath().equals("/Filter_Produit")) {
			int id_c = Integer.parseInt(request.getParameter("id_c"));
			request.setAttribute("id_c", id_c);
			request.setAttribute("nbAchat", pn_dao.ListPanier().size());
			request.setAttribute("listC", c_dao.ListCategorie());
			request.setAttribute("listProd", p_dao.ListProduitParCat(id_c));
			request.setAttribute("ListM", m_dao.ListMarque());
			request.getRequestDispatcher("visiteur/Filter_Produit.jsp").forward(request, response);
			
			
		}else if(request.getServletPath().equals("/filterparMarque")) {
			int id_c = Integer.parseInt(request.getParameter("id_c"));
			String[] checkMarques = request.getParameterValues("marques");
			List<Produit> prods = new ArrayList<Produit>();
			String marqstr ="";
			if(checkMarques != null) {
				for (String s : checkMarques) {
					int id = Integer.parseInt(s);
					List<Produit> p = p_dao.ListProduit(id_c, id);
					prods.addAll(p);
					marqstr+=s;
				}
				HttpSession se = request.getSession();
				se.setAttribute("marq", marqstr);
			}else {
			prods = p_dao.ListProduitParCat(id_c);
			}
			
		
			request.setAttribute("id_c", id_c);
			request.setAttribute("listProd", prods);
			request.setAttribute("nbAchat", pn_dao.ListPanier().size());
			request.setAttribute("listC", c_dao.ListCategorie());
			request.setAttribute("ListM", m_dao.ListMarque());
			request.getRequestDispatcher("visiteur/Filter_Produit.jsp").forward(request, response);
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

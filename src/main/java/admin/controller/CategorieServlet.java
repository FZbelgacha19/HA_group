package admin.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.dao.CategoriDao;
import admin.dao.imp.CategorieDaoImp;
import admin.models.Categorie;

/**
 * Servlet implementation class CategorieServlet
 */
@WebServlet(urlPatterns = {"/Ajouter_Categorie","/List_Categorie", "/Modifier_categorie","/Supp_Categorie"})
public class CategorieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    CategoriDao c_dao = new CategorieDaoImp();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategorieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession se = request.getSession(false);
		if (se != null) {
			if (se.getAttribute("admin") != null) {
				if (request.getServletPath().equals("/Ajouter_Categorie")) {
					
					request.getRequestDispatcher("Categorie/Ajouter_Categorie.jsp").forward(request, response);
					
					
				}else if (request.getServletPath().equals("/List_Categorie")) {
					request.setAttribute("listCat", c_dao.ListCategorie());
					request.getRequestDispatcher("Categorie/List_Categorie.jsp").forward(request, response);
					
					
					
				}else if (request.getServletPath().equals("/Modifier_categorie")) {
					int id =Integer.parseInt(request.getParameter("id"));
					Categorie c  = c_dao.GetCategorie(id);
					request.setAttribute("c", c);
					request.getRequestDispatcher("Categorie/Modifier_categorie.jsp").forward(request, response);
					
					
					
				}else if (request.getServletPath().equals("/Supp_Categorie")) {
					int id =Integer.parseInt(request.getParameter("id"));
					c_dao.SuppCategorie(id);		
					response.sendRedirect(request.getContextPath() + "/List_Categorie");
				}
				
			}else {
					response.sendRedirect(request.getContextPath() + "/Accueil");
				}
					
			}else {
				response.sendRedirect(request.getContextPath() + "/Accueil");
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getServletPath().equals("/Ajouter_Categorie")) {
			int id =1+c_dao.maxid();
			String Nom = request.getParameter("Nom_cat");
			Categorie cat = new Categorie(id, Nom);
			c_dao.AjouteCategorie(cat);
			request.getRequestDispatcher("Categorie/Ajouter_Categorie.jsp").forward(request, response);
			
			
			
		}else if (request.getServletPath().equals("/Modifier_categorie")) {
			int id =Integer.parseInt(request.getParameter("id"));
			String Nom = request.getParameter("Nom_cat");
			Categorie cat = new Categorie(id, Nom);
			c_dao.ModifCategorie(cat);
			response.sendRedirect(request.getContextPath() + "/List_Categorie");
			
		}
	}

}

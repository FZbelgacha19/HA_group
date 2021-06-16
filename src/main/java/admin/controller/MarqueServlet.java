package admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.dao.MarqueDao;
import admin.dao.imp.MarqueDaoImp;
import admin.models.Marque;


/**
 * Servlet implementation class MarqueServlet
 */
@WebServlet(urlPatterns = {"/Ajouter_Marque", "/List_Marque","/Modifier_Marque","/Supp_Marque"})
public class MarqueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    MarqueDao m_dao = new MarqueDaoImp();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarqueServlet() {
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
				if (request.getServletPath().equals("/Ajouter_Marque")) {
					
					request.getRequestDispatcher("Marque/Ajouter_Marque.jsp").forward(request, response);
					
					
				}else if (request.getServletPath().equals("/List_Marque")) {
					request.setAttribute("listMarq", m_dao.ListMarque());
					request.getRequestDispatcher("Marque/List_Marque.jsp").forward(request, response);
					
					
					
				}else if (request.getServletPath().equals("/Modifier_Marque")) {
					int id =Integer.parseInt(request.getParameter("id"));
					Marque m  = m_dao.GetMarque(id);
					request.setAttribute("m", m);
					request.getRequestDispatcher("Marque/Modifier_Marque.jsp").forward(request, response);
					
					
					
				}else if (request.getServletPath().equals("/Supp_Marque")) {
					int id =Integer.parseInt(request.getParameter("id"));
					m_dao.SuppMarque(id);		
					response.sendRedirect(request.getContextPath() + "/List_Marque");
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
		if (request.getServletPath().equals("/Ajouter_Marque")) {
			int id =1+m_dao.maxid();
			String Nom = request.getParameter("Nom_Marq");
			Marque Marq = new Marque(id, Nom);
			m_dao.AjouteMarque(Marq);
			request.getRequestDispatcher("Marque/Ajouter_Marque.jsp").forward(request, response);
			
			
			
		}else if (request.getServletPath().equals("/Modifier_Marque")) {
			int id =Integer.parseInt(request.getParameter("id"));
			String Nom = request.getParameter("Nom_Marq");
			Marque Marq = new Marque(id, Nom);
			m_dao.ModifMarque(Marq);
			response.sendRedirect(request.getContextPath() + "/List_Marque");
			
		}
	}

}

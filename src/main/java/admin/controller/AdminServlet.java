package admin.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.dao.AdminDao;
import admin.dao.imp.AdminDaoImp;
import admin.models.Admin;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet(urlPatterns = { "/Accueil", "/Ajouter_admin", "/List_admin", "/Modifier_admin", "/Supprime_admin","/Deconnecter" })
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AdminDao a_dao = new AdminDaoImp();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getServletPath().equals("/Accueil")) {
			request.getRequestDispatcher("admin_views/accueil.jsp").forward(request, response);
		}
		HttpSession se = request.getSession(false);
		if (se != null) {
			if (se.getAttribute("admin") != null) {
				if (request.getServletPath().equals("/Ajouter_admin")) {
					request.getRequestDispatcher("admin_views/Ajouter_admin.jsp").forward(request, response);
					
					
					
				} else if (request.getServletPath().equals("/List_admin")) {
					List<Admin> admins = a_dao.ListAdmin();
					request.setAttribute("listAdmin", admins);
					request.getRequestDispatcher("admin_views/List_admin.jsp").forward(request, response);
					
					
				} else if (request.getServletPath().equals("/Modifier_admin")) {
					int id = Integer.parseInt(request.getParameter("id"));
					Admin a = a_dao.GetAdmin(id);
					request.setAttribute("a", a);
					request.getRequestDispatcher("admin_views/Modifier_admin.jsp").forward(request, response);
					
					
					
				} else if (request.getServletPath().equals("/Supprime_admin")) {
					int id = Integer.parseInt(request.getParameter("id"));
					a_dao.SuppAdmin(id);
					response.sendRedirect(request.getContextPath() + "/List_admin");
					
				}else if(request.getServletPath().equals("/Deconnecter")){
					HttpSession session = request.getSession();
					session.invalidate();
					response.sendRedirect(request.getContextPath()+"/Accueil");
				}
				
				
			}else {
				response.sendRedirect(request.getContextPath() + "/Accueil");
			}
				
		}else {
			response.sendRedirect(request.getContextPath() + "/Accueil");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getServletPath().equals("/Accueil")) {
			String email = request.getParameter("email");
			String motpass = request.getParameter("motdpass");
			Admin u = a_dao.EstValide(email, motpass, "Admin");
			if (u != null) {
				HttpSession se = request.getSession();
				se.setAttribute("admin", u);
			}
			response.sendRedirect(request.getContextPath()+"/Accueil");
			
			
		} else if (request.getServletPath().equals("/Ajouter_admin")) {
			SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
			Admin a;
			try {
				Date d = f.parse(request.getParameter("dateNaissance"));
				a = new Admin(request.getParameter("email"), request.getParameter("motdpass"),
						request.getParameter("nom_complete"), request.getParameter("adress"),
						request.getParameter("tele"), new java.sql.Date(d.getTime()));
				a_dao.AjouteAdmin(a);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("admin_views/Ajouter_admin.jsp").forward(request, response);
			
			
		} else if (request.getServletPath().equals("/Modifier_admin")) {
			SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
			Admin a ;
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				Date d = f.parse(request.getParameter("dateNaissance"));
				a = new Admin(id,request.getParameter("email"), request.getParameter("motdpass"),
						request.getParameter("nom_complete"), request.getParameter("adress"),
						request.getParameter("tele"), new java.sql.Date(d.getTime()));
				a_dao.ModifAdmin(a);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			response.sendRedirect(request.getContextPath() + "/List_admin");
			
			
			
		}

	}

}

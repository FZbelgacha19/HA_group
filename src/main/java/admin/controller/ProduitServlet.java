package admin.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import admin.dao.CategoriDao;
import admin.dao.MarqueDao;
import admin.dao.ProduitDao;
import admin.dao.imp.CategorieDaoImp;
import admin.dao.imp.MarqueDaoImp;
import admin.dao.imp.ProduitDaoImp;
import admin.models.Produit;

/**
 * Servlet implementation class ProduitServlet
 */
@WebServlet(urlPatterns = { "/Ajouter_Produit", "/List_Produit", "/Modifier_Produit", "/Supp_Produit" })
@MultipartConfig
public class ProduitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private static final String SAVE_DIRECTORY = "\\img";
	ProduitDao p_dao = new ProduitDaoImp();
	CategoriDao c_dao = new CategorieDaoImp();
	MarqueDao m_dao = new MarqueDaoImp();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProduitServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession se = request.getSession(false);

		if (se != null) {
			if (se.getAttribute("admin") != null) {
				if (request.getServletPath().equals("/Ajouter_Produit")) {
					request.setAttribute("listCat", c_dao.ListCategorie());
					request.setAttribute("listMarq", m_dao.ListMarque());
					request.getRequestDispatcher("Produit/Ajouter_Produit.jsp").forward(request, response);

				} else if (request.getServletPath().equals("/List_Produit")) {
					request.setAttribute("listprod", p_dao.ListProduit());
					request.setAttribute("c", c_dao);
					request.setAttribute("m", m_dao);
					request.getRequestDispatcher("Produit/List_Produit.jsp").forward(request, response);

				} else if (request.getServletPath().equals("/Modifier_Produit")) {
					int id = Integer.parseInt(request.getParameter("id"));
					request.setAttribute("listCat", c_dao.ListCategorie());
					request.setAttribute("listMarq", m_dao.ListMarque());
					Produit p = p_dao.GetProduit(id);
					String appPath = request.getServletContext().getRealPath("");
					System.out.println(appPath);
					/*
					 * String[] path = appPath.split("\\\\"); String imgpath = path[0]+ "/"
					 * +path[1]+ "/" +path[2]+"/"+path[8]
					 * +"/src/main/webapp/"+path[9]+SAVE_DIRECTORY.replace("\\", "/")+"/"+p.getImage
					 * ();
					 */

					appPath = appPath.replace("\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps",
							"");
					appPath = appPath.replace("\\", "/");
					String imgpath = appPath + "/src/main/webapp/Produit/img/" + p.getImage();
					System.out.println("++" + imgpath);
					request.setAttribute("path", imgpath);
					request.setAttribute("p", p);
					request.getRequestDispatcher("Produit/Modifier_Produit.jsp").forward(request, response);

					
					
					
				} else if (request.getServletPath().equals("/Supp_Produit")) {
					int id = Integer.parseInt(request.getParameter("id"));
					p_dao.SuppProduit(id);
					response.sendRedirect(request.getContextPath() + "/List_Produit");

				}
			} else {
				response.sendRedirect(request.getContextPath() + "/Accueil");
			}

		} else {
			response.sendRedirect(request.getContextPath() + "/Accueil");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getServletPath().equals("/Ajouter_Produit")) {

			int Ref_pr = Integer.parseInt(request.getParameter("Ref_pr").toString());
			String Nom = request.getParameter("Nom");
			String Description = request.getParameter("Description");
			double Prix = Double.parseDouble(request.getParameter("Prix").toString());

			int qte = Integer.parseInt(request.getParameter("qte").toString());
			int marque = Integer.parseInt(request.getParameter("marque").toString());
			int cat = Integer.parseInt(request.getParameter("cat").toString());

			Part ImagePart = request.getPart("Image");
			String ImageName = ImagePart.getSubmittedFileName();
			String appPath = request.getServletContext().getRealPath("");

			/*
			 * String[] path = appPath.split("\\\\"); appPath = path[0]+ File.separator
			 * +path[1]+ File.separator +path[2]+ File.separator
			 * +path[8]+"\\src\\main\\webapp"+ File.separator +path[9]; String savePath =
			 * appPath + SAVE_DIRECTORY+ File.separator + ImageName;
			 */
			appPath = appPath.replace("\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps", "");
			String imgpath = appPath + "\\src\\main\\webapp\\Produit\\img" + File.separator + ImageName;
			ImagePart.write(imgpath);
			Produit p = new Produit(Ref_pr, Nom, Description, Prix, ImageName, qte, marque, cat);
			p_dao.AjouteProduit(p);

			response.sendRedirect(request.getContextPath() + "/Ajouter_Produit");

			
			
		} else if (request.getServletPath().equals("/Modifier_Produit")) {

			int Ref_pr = Integer.parseInt(request.getParameter("id"));
			Produit p = p_dao.GetProduit(Ref_pr);
			String Nom = request.getParameter("Nom");
			String Description = request.getParameter("Description");
			double Prix = Double.parseDouble(request.getParameter("Prix").toString());

			int qte = Integer.parseInt(request.getParameter("qte").toString());
			int marque = Integer.parseInt(request.getParameter("marque").toString());
			int cat = Integer.parseInt(request.getParameter("cat").toString());
			String ImageName = p.getImage();
			Part ImagePart = request.getPart("Image");
			if (ImagePart.getSize() > 0) {
				ImageName = ImagePart.getSubmittedFileName();
				String appPath = request.getServletContext().getRealPath("");

				/*
				 * String[] path = appPath.split("\\\\");
				 * 
				 * appPath = path[0]+ File.separator +path[1]+ File.separator +path[2]+
				 * File.separator +path[8]+"\\src\\main\\webapp"+ File.separator +path[9];
				 * String savePath = appPath + SAVE_DIRECTORY+ File.separator + ImageName;
				 * ImagePart.write(savePath);
				 */
				
				
				appPath = appPath.replace("\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps", "");
				String imgpath = appPath + "\\src\\main\\webapp\\Produit\\img" + File.separator + ImageName;
				ImagePart.write(imgpath);
			}

			Produit p1 = new Produit(Ref_pr, Nom, Description, Prix, ImageName, qte, marque, cat);
			p_dao.ModifProduit(p1);

			response.sendRedirect(request.getContextPath() + "/List_Produit");

		}
	}

}

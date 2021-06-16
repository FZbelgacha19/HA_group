package admin.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
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

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import admin.dao.CommandeDao;
import admin.dao.FactureDao;
import admin.dao.ProduitDao;
import admin.dao.imp.CommandeDaoImp;
import admin.dao.imp.FactureDaoImp;
import admin.dao.imp.ProduitDaoImp;
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
 * Servlet implementation class FactureServlet
 */
@WebServlet(urlPatterns = {"/Imprimer_Facture","/Valide_Facture","/Recherche_Facture"})
public class FactureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PanierDao pn_dao = new PanierDaoImp();
	ProduitDao p_dao = new ProduitDaoImp();
	LignepanierDao lp_dao = new LignePanierDaoImp();
	VisiteurDao v_dao = new VisiteurDaoImp();
	CommandeDao c_dao = new CommandeDaoImp();
	FactureDao f_dao = new FactureDaoImp();
	private String filepath;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FactureServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected String ExportFacture(int id_lp, int id_user, int id_cmd, int f_id, HttpServletRequest request,
			HttpServletResponse response) {
		try {

			String docName = id_user+"&"+id_lp+"&"+id_cmd+"&"+f_id+".pdf";
			String appPath = request.getServletContext().getRealPath("");
			/*String[] path = appPath.split("\\\\");
			appPath = path[0] + File.separator + path[1] + File.separator + path[2] + File.separator + path[8]
					+ "\\src\\main\\webapp";*/
			
			
			appPath = appPath.replace("\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps", "");
			appPath = appPath + "\\src\\main\\webapp";
			String imgPath = appPath+File.separator+"assets\\img\\ha-logo.png";
			String savePath = appPath + File.separator + "\\Facture"+ File.separator + docName;
			this.filepath = savePath;
			
			Document doc = new Document(PageSize.A4);
			PdfWriter.getInstance(doc, new FileOutputStream(savePath));
			doc.open();
			
			/*Logo*/
			Image logo = Image.getInstance(imgPath);
			logo.setAlignment(Element.ALIGN_LEFT);
			logo.setSpacingAfter(30f);
			logo.scaleToFit(90,110);
			
			Paragraph[] P = new Paragraph[3];
			
			/*Titre*/
			Font font = new Font(FontFamily.HELVETICA,16, Font.BOLD);
			Chunk text = new Chunk("Facture N° "+f_id, font);
			
			Paragraph Titre = new Paragraph(text);
			Titre.setAlignment(Element.ALIGN_CENTER);
			Titre.setSpacingAfter(25f);
			
			/*Date*/
			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
			Date d = new Date();
			P[0] = new Paragraph("Marrakech le : "+f.format(d));
			P[0].setSpacingAfter(15f);
			
			
			/*Client*/
			P[1] = new Paragraph("Client N° : "+id_user);
			P[1].setSpacingAfter(15f);
			
			/*Commande*/
			P[2] = new Paragraph("Commande N° : "+id_cmd);
			P[2].setSpacingAfter(15f);
			
			
			//Stock paragraphs
			PdfPTable pr = new PdfPTable(2);			
			PdfPCell[] prCell = new PdfPCell[2];
			
			for(int k=0; k<3 ; k++) {
				prCell[0] = new PdfPCell(new Paragraph("        "));
				prCell[1] = new PdfPCell(P[k]);
				for(int j=0; j<2 ; j++) {
					prCell[j].setBorder(Rectangle.NO_BORDER);
					pr.addCell(prCell[j]);
				}		
					
			}
			
			//Set space between table
			Paragraph space = new Paragraph(" ");
			space.setSpacingAfter(25f);
			
			
			
			/*Table - 6 Col*/
			PdfPTable t = new PdfPTable(6);
		    t.setWidthPercentage(100);
		    
			PdfPCell[] c = new PdfPCell[6];
			c[0] = new PdfPCell(new Paragraph("Reference & Produit"));
			c[1] = new PdfPCell(new Paragraph("Quantité"));
			c[2] = new PdfPCell(new Paragraph("PUT"));
			c[3] = new PdfPCell(new Paragraph("Total HT"));
			c[4] = new PdfPCell(new Paragraph("TVA 20%"));
			c[5] = new PdfPCell(new Paragraph("TTC"));
			
			for(int i=0 ; i<6 ; i++)
				t.addCell(c[i]);
			
			List<Lignepanier> lps = lp_dao.ListLignepanier(id_user,id_lp);
			double total = 0;
			for (Lignepanier l : lps) {
				Produit p = p_dao.GetProduit(l.getRef_prod());
				int qte = l.getQte_cmd();
				double pu = p.getPrix();
				double pht = qte*pu;
				double tva = 0.2*pht;
				double ttc = pht+tva;
				total += ttc;
				
				c[0] = new PdfPCell(new Paragraph(p.getRef_pr()+" - "+p.getNom()));
				c[1] = new PdfPCell(new Paragraph(""+qte));
				c[2] = new PdfPCell(new Paragraph(pu+" DH"));
				c[3] = new PdfPCell(new Paragraph(pht+" DH"));
				c[4] = new PdfPCell(new Paragraph(tva+" DH"));
				c[5] = new PdfPCell(new Paragraph(ttc+" DH"));
				
				for(int i=0 ; i<6 ; i++) {
					c[i].setPaddingBottom(4f);
					c[i].setPaddingTop(4f);
					t.addCell(c[i]);
				}
					
			
			}
			
			PdfPCell last = new PdfPCell(new Paragraph("Total TTC"));
			last.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			last.setColspan(5);
			PdfPCell tot = new PdfPCell(new Paragraph(total+" DH"));
			t.addCell(last);
			t.addCell(tot);
			
			//add elements to doc
			doc.add(logo);
			doc.add(Titre);
			doc.add(pr);
			doc.add(space);
			doc.add(t);
			
			doc.close();
			return savePath;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getServletPath().equals("/Imprimer_Facture")) {
			HttpSession se = request.getSession(false);

			this.filepath = (String) se.getAttribute("filepath");
		
			File myFile = new File(this.filepath);
	        Desktop.getDesktop().open(myFile);
	        response.sendRedirect(request.getContextPath()+"");
		}
		HttpSession se = request.getSession(false);

		if (se != null) {
			if (se.getAttribute("admin") != null) {
		if(request.getServletPath().equals("/Valide_Facture")) {
			
			request.getRequestDispatcher("Facture/views/Valide_Facture.jsp").forward(request, response);
		}
			}else
				response.sendRedirect(request.getContextPath() + "/Accueil");
			
		}else
			response.sendRedirect(request.getContextPath() + "/Accueil");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getServletPath().equals("/Valide_Facture")) {
			int id_f = Integer.parseInt(request.getParameter("id_f"));
			Facture f = f_dao.GetFacture(id_f);
			f.setEtat_fact("paye");
			f_dao.ModifFacture(f);
			request.setAttribute("fact", f);
			request.getRequestDispatcher("Facture/views/Valide_Facture.jsp").forward(request, response);
		}else if(request.getServletPath().equals("/Recherche_Facture")) {
			int id_f = Integer.parseInt(request.getParameter("num_facture"));
			Facture f = f_dao.GetFacture(id_f);
			request.setAttribute("fact", f);
			request.getRequestDispatcher("Facture/views/Valide_Facture.jsp").forward(request, response);
		}
	}

}

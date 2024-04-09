package web;

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
import org.apache.catalina.connector.Response;
import dao.TypeDaoImpl;
import dao.ITypeDao;
import metier.entities.Type;

@WebServlet (name="typServ",urlPatterns= {"/types","/saisieType",
"/saveType","/supprimerTyp","/editerTyp","/updateTyp"})
public class TypeServlet extends HttpServlet{
	ITypeDao metier;
	@Override
	public void init() throws ServletException {
	metier = new TypeDaoImpl();
	}
	@Override
	protected void doGet(HttpServletRequest request,
	HttpServletResponse response)
	throws ServletException, IOException {
	String path=request.getServletPath();
	System.out.println("PATH "+path);
	if (path.equals("/types"))
	{
		TypeModel model= new TypeModel();
	List<Type> typs = metier.getAllTypes();
	model.setTypes(typs);
	request.setAttribute("model", model);
	request.getRequestDispatcher("types.jsp").forward(request,response);
	}
	else if (path.equals("/saisieType") )
	{
	request.getRequestDispatcher("saisieType.jsp").forward(request,response
	);
	}
	else if (path.equals("/saveType") &&

			request.getMethod().equals("POST"))

			{
			Date dateTyp= new Date();
			String nom=request.getParameter("nom");
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new

			SimpleDateFormat(pattern);
			try {

			dateTyp =

			simpleDateFormat.parse(request.getParameter("dateTyp"));

			} catch (ParseException e) {
			e.printStackTrace();
			}
			Type typ = metier.save(new Type(nom,dateTyp));
			request.setAttribute("type", typ);
			response.sendRedirect("types");
			}
			else if (path.equals("/supprimerTyp"))
			{
			Long id= Long.parseLong(request.getParameter("id"));
			metier.deleteType(id);
			response.sendRedirect("types");
			}
			else if (path.equals("/editerTyp") )
			{
			Long id= Long.parseLong(request.getParameter("id"));
			Type typ = metier.getType(id);
			request.setAttribute("type", typ);
			request.getRequestDispatcher("editerType.jsp").forward(request,response
			);
			}
			else if (path.equals("/updateTyp") )
			{
			Date dateTyp= new Date();
			Long id = Long.parseLong(request.getParameter("id"));
			String nom=request.getParameter("nom");
			Type typ = new Type();
			typ.setIdTyp(id);
			typ.setNomTyp(nom);
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new

			SimpleDateFormat(pattern);
			try {

			dateTyp =

			simpleDateFormat.parse(request.getParameter("dateCreation"));

			} catch (ParseException e) {
			e.printStackTrace();
			}
			typ.setDateCreation(dateTyp);
			metier.updateType(typ);
			response.sendRedirect("types");
			}
			else
			{
			response.sendError(Response.SC_NOT_FOUND);
			}
			}
			@Override
			protected void doPost(HttpServletRequest request,

			HttpServletResponse response) throws

			ServletException, IOException {
			doGet(request,response);
			}
}

package web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import dao.IInstrumentDao;
import dao.InstrumentDaoImpl;
import metier.entities.Instrument;

@WebServlet(name = "cs", urlPatterns = { "/controleur", "*.do" })
public class ControleurServlet extends HttpServlet {
	IInstrumentDao metier;

	@Override
	public void init() throws ServletException {
		metier = new InstrumentDaoImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		if (path.equals("/index.do")) {
			request.getRequestDispatcher("instruments.jsp").forward(request, response);
		} else if (path.equals("/chercher.do")) {
			String motCle = request.getParameter("motCle");
			InstrumentModele model = new InstrumentModele();
			model.setMotCle(motCle);
			List<Instrument> instrs = metier.instrumentsParMC(motCle);
			model.setInstruments(instrs);
			request.setAttribute("model", model);
			request.getRequestDispatcher("instruments.jsp").forward(request, response);
		}
		else if (path.equals("/saisie.do") )
		{
		request.getRequestDispatcher("saisieInstrument.jsp").forward(request,response);
		}
		else if (path.equals("/save.do") && request.getMethod().equals("POST"))
		{
		 String nom=request.getParameter("nom");
		double prix = Double.parseDouble(request.getParameter("prix"));
		Instrument i = metier.save(new Instrument(nom,prix));
		request.setAttribute("instrument", i);
		request.getRequestDispatcher("confirmation.jsp").forward(request,response);
		}
		else if (path.equals("/supprimer.do"))
		{
		 Long id= Long.parseLong(request.getParameter("id"));
		 metier.deleteInstrument(id);
		 response.sendRedirect("chercher.do?motCle=");
		}
		else if (path.equals("/editer.do") )
		{
		Long id= Long.parseLong(request.getParameter("id"));
		Instrument i = metier.getInstrument(id);
		 request.setAttribute("instrument", i);
		request.getRequestDispatcher("editerInstrument.jsp").forward(request,response);
		}
		else if (path.equals("/update.do") )
		{
		Long id = Long.parseLong(request.getParameter("id"));
		String nom=request.getParameter("nom");
		double prix = 
		Double.parseDouble(request.getParameter("prix"));
		Instrument i = new Instrument();
		i.setIdInstrument(id);
		i.setNomInstrument(nom);
		i.setPrix(prix);
		metier.updateInstrument(i);
		request.setAttribute("instrument", i);
		request.getRequestDispatcher("confirmation.jsp").forward(request,response);
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
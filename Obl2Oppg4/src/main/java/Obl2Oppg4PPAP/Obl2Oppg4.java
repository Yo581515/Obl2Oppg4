package Obl2Oppg4PPAP;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/konger")
public class Obl2Oppg4 extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String aarString = request.getParameter("aar");
			//Valider årstall og gi alternativ respons hvis ugyldig.
			if (erGyldigHeltall(aarString)) {
				int aar = Integer.parseInt(aarString);
				
				//Finn et passende Konge-objekt fra listen Konger.norske
				//ved å bruke streams og lambda-uttrykk.
				Konge konge = Konger.norske.stream()
						.filter(k -> k.getKongeFraAar() <= aar && k.getKongeTilAar() >= aar )
						.findAny().orElse(null);
				
				String kongeNavn = konge.getNavn();
				int fodselesDato= konge.getFodtAar();
				int kongeFra = konge.getKongeFraAar();
				int kongeTil = konge.getKongeTilAar();
				String bilde = konge.getBilde();
				
				
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				
				out.println("<html>");
				out.println("<body>");
				out.println("<image src="+bilde+" />");
				out.println("<h1>");
				out.println("Kongen i aar "+ aar+" var "+kongeNavn+ ", født "+fodselesDato+", </br>konge fra "+kongeFra+ " til "+kongeTil );
				out.println("</h1>");
				out.println("<h1>");
				out.println("<a href='http://localhost:8080/LOL/index.html'>Nytt sook </a>");
				out.println("</h1>");
				out.println("</body>");
				out.println("</html>");
				
			}
			
			
			
			


	}
	
	
	private boolean erGyldigHeltall(String talletSomString) {
		return talletSomString.matches("^\\d+$");
	}

}

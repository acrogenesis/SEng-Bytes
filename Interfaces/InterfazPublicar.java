package interfaces;
import controles.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import controles.ControlPublicar;

public class InterfazPublicar extends HttpServlet {
  HttpServletResponse thisResponse;
  HttpServletRequest thisRequest;
  PrintWriter out;
  ControlPublicar cp;

  //Es importante observar que todos los metodos definen la operacion GET para
  //que el metodo doGet sea el que se ejecuta al presionar el boton "Enviar". 
  public void doGet(HttpServletRequest request,
        HttpServletResponse response)
        throws IOException {
    thisResponse = response;
    thisRequest = request;
    thisResponse.setContentType("text/html");
    out = thisResponse.getWriter();
    //Preparar el encabezado de la pagina Web de respuesta
    out.println("<!DOCTYPE HTML>");
    out.println("<HTML>");
    out.println("<HEAD>");
    out.println("<TITLE>Publicar Articulo</TITLE>");
    out.println("<META http-equiv=Content-Type content=\"text/html\">");
    out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />");
    out.println("<link rel=\"stylesheet\" href=\"Interfaces/css/foundation.min.css\" />");
    out.println("<script src=\"Interfaces/js/modernizr.js\"></script>");
    out.println("<script src=\"Interfaces/js/jquery.js\"></script>");

    out.println("</HEAD>");
    out.println("<BODY>");
    out.println("<nav class=\"top-bar\" data-topbar>");
    out.println("<ul class=\"title-area\">");
    out.println("<li class=\"name\">");
    out.println("<h1><a href=\"/SEng\">SEng Bytes</a></h1>");
    out.println("</li>");
    out.println("</ul>");
    out.println("</nav>");
    out.println("<div class=\"row\">");
    out.println("<h2>Publicar Articulo</h2>");
    out.println("</div>");
    out.println("<div class=\"row\">");

    String operacion = request.getParameter("operacion");
    if(operacion == null){ // El menu nos envia un parametro para indicar el inicio de una transaccion
      iniciarPublicacion(false);
    }else if(operacion.equals("validar")){
       validarPublicacion(false);
    } else if (operacion.equals("publicando")){
      mandarPubclicacion();
    }
  }
  public void iniciarPublicacion(boolean error){
    out.println("<p>Indique el idAutor y idEdicion </p>");
    if (error){
      out.println("<div data-alert class=\"alert-box warning radius\">");
      out.println("Error en idAutor y/o idEdicion");
      out.println("<a href=\"#\" class=\"close\">&times;</a>");
      out.println("</div>");
    }
    out.println("<form method=\"GET\" action=\"Publicar\">");
    out.println("<p> Autor <input type=\"text\" name=\"autor\" size=\"15\"></p>");
    out.println("<p> Edicion <input type=\"text\" name=\"edicion\" size=\"15\"></p>");
    out.println("<input type=\"hidden\" name=\"operacion\" value=\"validar\"/>");
    out.println("<input type=\"submit\" value=\"Enviar\"name=\"B1\" class=\"button radius success\">");
    out.println("<a href=\"index.html\" class=\"button radius\">Cancelar</a>");
    out.println("</form>");

    out.println("</div>");

    out.println("</BODY>");
    out.println("</HTML>");
  }

  public void validarPublicacion(boolean error){
    cp = new ControlPublicar();
    //La funcion trim() elimina espacios antes y despues del valor
    int autor = Integer.parseInt(thisRequest.getParameter("autor").trim());
    int edicion = Integer.parseInt(thisRequest.getParameter("edicion").trim());
    boolean existente = cp.puedoPublicar(autor,edicion);
    if (existente){
      out.println("<p>Articulo</p>");
      if (error){
        out.println("<div data-alert class=\"alert-box warning radius\">");
        out.println("Error, no se pudo publicar");
        out.println("<a href=\"#\" class=\"close\">&times;</a>");
        out.println("</div>");
      }
      out.println("<form method=\"GET\" action=\"Publicar\">");
      out.println("<input type=\"hidden\" name=\"operacion\" value=\"publicando\"/>");
      out.println("<input type=\"hidden\" name=\"autor\" value=\"" + autor + "\"/>");
      out.println("<input type=\"hidden\" name=\"edicion\" value=\"" + edicion + "\"/>");
      out.println("<p> idArticulo <input type=\"text\" name=\"articulo\" size=\"15\"></p>");
      out.println("<p> Titulo <input type=\"text\" name=\"titulo\" size=\"15\"></p>");
      out.println("<p> Fecha <input type=\"date\" name=\"fecha\" size=\"15\"></p>");
      out.println("<p> Contenido </p>");
      out.println("<textarea rows=\"4\" cols=\"50\" name=\"contenido\"></textarea>");
      out.println("<input type=\"submit\" value=\"Enviar\"name=\"B1\" class=\"button radius success\">");
      out.println("<a href=\"index.html\" class=\"button radius\">Cancelar</a>");
      out.println("</form>");

      out.println("</div>");

      out.println("</BODY>");
      out.println("</HTML>");
    } else {
       iniciarPublicacion(true);
    }
  }

  public void mandarPubclicacion(){
    int autor = Integer.parseInt(thisRequest.getParameter("autor").trim());
    int articulo = Integer.parseInt(thisRequest.getParameter("articulo").trim());
    String titulo = thisRequest.getParameter("titulo").trim();
    String fecha = thisRequest.getParameter("fecha").trim();
    int edicion = Integer.parseInt(thisRequest.getParameter("edicion").trim());
    String contenido = thisRequest.getParameter("contenido").trim();
    boolean resultado = cp.publicar(autor,articulo,titulo,fecha,edicion,contenido);
    if (resultado) {
      out.println("<p>Publicado</p>");
      out.println("<div data-alert class=\"alert-box radius success\">");
      out.println("Fue un place servirlo.");
      out.println("</div>");
      out.println("<a href=\"index.html\" class=\"button radius\">Terminar</a>");
      out.println("</div>");
      out.println("</BODY>");
      out.println("</HTML>");
    }else {
      validarPublicacion(true);
    }
  }
}

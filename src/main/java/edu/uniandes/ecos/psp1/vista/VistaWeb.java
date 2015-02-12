package edu.uniandes.ecos.psp1.vista;

import edu.uniandes.ecos.psp1.modelo.PuntoDosDimensiones;
import edu.uniandes.ecos.psp1.modelo.ResultadoRegrecionCorrelacion;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Vista web del programa.
 * @author JohnDany
 */
public class VistaWeb {

    /**
     * Métod que muestra la página de inicio de la aplicación.
     *
     * @param req llamado de la página
     * @param resp respuesta de la página
     * @throws ServletException
     * @throws IOException
     */
    public static void mostrarFormunlarioInicial(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        PrintWriter pw = resp.getWriter();
        pw.write("<html>");
        pw.println("<h1>Programa PSP1</h1>");
        pw.write("<form  method=\"post\">\n"
                + "<div><h2>Cálculo de la regresión lineal y correlación</h2></div> \n"
                + "<div><input id=\"btnTest\" name=\"btnTest\" type=\"submit\" value=\"Calcular desde archivos Test\"></div>\n"
                + "<div><input id=\"btnEntrada\" name=\"btnEntrada\" type=\"submit\" value=\"Calcular datos personalizados\"></div>\n"
                + "</form> ");
        pw.write("</html>");
    }

    /**
     * Método que muestra los resultados del conteo
     *
     * @param req llamado de la página
     * @param resp respuesta de la página
     * @param resultado del conteo de líneas de código fuente
     * @throws ServletException
     * @throws IOException
     */
    public static void mostrarResultados(HttpServletRequest req, HttpServletResponse resp, ResultadoRegrecionCorrelacion resultado, LinkedList<PuntoDosDimensiones> puntos, String titulo)
            throws ServletException, IOException {

        String b0 = String.format("%.3f", resultado.getB0());
        String b1 = String.format("%.3f", resultado.getB1());
        String rxy = String.format("%.3f", resultado.getRxy());
        String x2 = String.format("%.3f", resultado.getrCuadrado());
        String yk = String.format("%.3f", resultado.getYk());
        String xk = String.format("%.3f", resultado.getXk());

        PrintWriter r = resp.getWriter();
        r.println("<br><h3>" + titulo + "</h3>");
        r.println("<html>");
        r.write("<head>");
        r.write("<meta http-equiv=\"Content-Language\" >");
        r.write("<meta http-equiv=\"Content-Type\" content=\"text/html;\">");
        r.println("<style>");
        r.println("table, th, td");
        r.print("{");
        r.println("border: 1px solid black;");
        r.println("border-collapse: collapse;");
        r.println("}");
        r.println("th, td");
        r.print("{");
        r.println("padding: 5px;");
        r.println("text-align: left;");
        r.print("}");
        r.println("</style>");
        r.write("</head>");
        r.write("<body>");
        r.println("<table>");
        r.println("<tr>");
        r.println("<th>B0</th>");
        r.println("<th>B1</th>");
        r.println("<th>rxy</th>");
        r.println("<th>r2</th>");
        r.println("<th>yk</th>");
        r.println("<th>xk</th>");
        r.println("</tr>");
        r.println("<tr>");
        r.println("<td><label>" + b0 + " </label>");
        r.println("</td>");
        r.println("<td><label>" + b1 + " </label>");
        r.println("</td>");
        r.println("<td><label>" + rxy + " </label>");
        r.println("</td>");
        r.println("<td><label>" + x2 + "</label>");
        r.println("</td>");
        r.println("<td><label>" + yk + "</label>");
        r.println("</td>");
        r.println("<td><label>" + xk + "</label>");
        r.println("</td>");
        r.println("</tr>");
        r.println("<tr>");
        r.println("</tr>");
        r.println("<td colspan=\"6\">");
         r.println("<h4>Puntos: </h4><label>" + puntos + "</label>");
        r.println("</td>");
        r.println("</table>");
        r.println("</body>");
        r.println("</html>");
        r.flush();
    }

    public static void mostrarVentanaEntrada(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter pw = resp.getWriter();
        pw.write("<html>");
        pw.println("<h1>Captura de datos</h1>");
        pw.write("<form  method=\"post\">\n"
                + "<div><h2>Cálculo de la regresión lineal y correlación</h2></div> \n"
                + "<div><label>Tamaño del proxy(xk):</label><input type=\"text\" name=\"proxy\" id=\"proxy\"></div> \n"
                + "<div><label>Datos X:</label><input type=\"text\" name=\"datosx\" id=\"datosx\"></div> \n"
                + "<div><label>Datos Y:</label><input type=\"text\" name=\"datosy\" id=\"datosy\"</div> \n"
                + "<div><input id=\"btnCalcularEntrada\" name=\"btnCalcularEntrada\" type=\"submit\" value=\"Calcular\"></div>\n"
                + "</form> ");
        pw.write("</html>");
    }

    /**
     * Método que controla la presentación de errores.
     *
     * @param req llamado de la página
     * @param resp respuesta de la página
     * @param ex excepción capturada
     * @throws ServletException
     * @throws IOException
     */
    public static void error(HttpServletRequest req, HttpServletResponse resp, Exception ex)
            throws ServletException, IOException {
        resp.getWriter().println("Vaya se ha presentado un error!" + ex.getMessage());
    }

}

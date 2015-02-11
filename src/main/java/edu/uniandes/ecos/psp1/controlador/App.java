package edu.uniandes.ecos.psp1.controlador;

import edu.uniandes.ecos.psp0.Fuente;
import edu.uniandes.ecos.psp0.Modelo.PuntoDosDimensiones;
import edu.uniandes.ecos.psp1.modelo.CalculadorRegresionYCorrelacion;
import edu.uniandes.ecos.psp1.modelo.ResultadoRegrecionCorrelacion;
import edu.uniandes.ecos.psp1.vista.VistaConsola;
import edu.uniandes.ecos.psp1.vista.VistaWeb;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 *
 * @author JohnDany
 */
public class App extends HttpServlet {

    public static void main(String[] args) {
        Server server = new Server(Integer.valueOf(System.getenv("PORT")));
        //Server server = new Server(5100);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new App()), "/*");
        try {
            server.start();
            server.join();
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        VistaWeb.mostrarFormunlarioInicial(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getParameter("btnTest") != null) {
                calculoArchivosTest(req, resp);
            } else if (req.getParameter("btnEntrada") != null) {
                VistaWeb.mostrarVentanaEntrada(req, resp);
            } else if (req.getParameter("btnCalcularEntrada") != null) {
                calculoArchivoEntrada(req, resp);
            }
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void calculoArchivosTest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Fuente fuente = new Fuente();
            LinkedList<PuntoDosDimensiones> puntos = null;
            CalculadorRegresionYCorrelacion calculador = new CalculadorRegresionYCorrelacion();

            String rutaArvhivo = App.class.getClassLoader().getResource("test1.txt").getFile();
            puntos = fuente.obtenerPuntosDosDimensionesDeArchivo(rutaArvhivo);

            VistaConsola.mostrarResultado("Puntos de Test 1: " + puntos);
            ResultadoRegrecionCorrelacion respuesta = calculador.calcularRegrecionCorrelacion(puntos, 386.0);
            VistaConsola.mostrarResultado("Datos calculados de Test 1:\n" + respuesta);
            VistaWeb.mostrarResultados(req, resp, respuesta, puntos, "Respuesta de Test 1");

            rutaArvhivo = App.class.getClassLoader().getResource("test2.txt").getFile();
            puntos = fuente.obtenerPuntosDosDimensionesDeArchivo(rutaArvhivo);

            VistaConsola.mostrarResultado("Puntos de Test 2: " + puntos);
            respuesta = calculador.calcularRegrecionCorrelacion(puntos, 386.0);
            VistaConsola.mostrarResultado("Datos calculados de Test 2:\n" + respuesta);
            VistaWeb.mostrarResultados(req, resp, respuesta, puntos, "Respuesta de Test 2");

            rutaArvhivo = App.class.getClassLoader().getResource("test3.txt").getFile();
            puntos = fuente.obtenerPuntosDosDimensionesDeArchivo(rutaArvhivo);
            VistaConsola.mostrarResultado("Puntos de Test 3: " + puntos);
            respuesta = calculador.calcularRegrecionCorrelacion(puntos, 386.0);
            VistaConsola.mostrarResultado("Datos calculados de Test 3:\n" + respuesta);
            VistaWeb.mostrarResultados(req, resp, respuesta, puntos, "Respuesta de Test 3");

            rutaArvhivo = App.class.getClassLoader().getResource("test4.txt").getFile();
            puntos = fuente.obtenerPuntosDosDimensionesDeArchivo(rutaArvhivo);
            VistaConsola.mostrarResultado("Puntos de Test 4: " + puntos);
            respuesta = calculador.calcularRegrecionCorrelacion(puntos, 386.0);
            VistaConsola.mostrarResultado("Datos calculados de Test 4:\n" + respuesta);
            VistaWeb.mostrarResultados(req, resp, respuesta, puntos, "Respuesta de Test 4");
        } catch (Exception ex) {
            VistaWeb.error(req, resp, ex);
        }
    }

    private static void calculoArchivoEntrada(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            CalculadorRegresionYCorrelacion calculador = new CalculadorRegresionYCorrelacion();
            ResultadoRegrecionCorrelacion respuesta = null;
            LinkedList<PuntoDosDimensiones> puntos = new LinkedList<PuntoDosDimensiones>();
            Double proxy;
            try {
                proxy = Double.parseDouble(req.getParameter("proxy"));
            } catch (Exception ex) {
                throw new Exception("Tamaño de proxy incorrecto. " + ex.getMessage());
            }

            String[] datosx = req.getParameter("datosx").split(",");
            String[] datosy = req.getParameter("datosy").split(",");

            if (datosx == null || datosx.length == 0) {
                throw new Exception("No se ingresaron datos para las X");
            }
            if (datosy == null || datosy.length == 0) {
                throw new Exception("No se ingresaron datos para las Y");
            }

            if (datosx.length != datosy.length) {
                throw new Exception("No se ingresaron la misma cantidad de datos en X y Y");
            }

            for (int i = 0; i < datosx.length; i++) {
                puntos.add(new PuntoDosDimensiones(Double.parseDouble(datosx[i]), Double.parseDouble(datosy[i])));
            }
            VistaConsola.mostrarResultado("Puntos Entrados: " + puntos);
            respuesta = calculador.calcularRegrecionCorrelacion(puntos, proxy);
            VistaConsola.mostrarResultado("Datos calculados de datos entrados:\n" + respuesta);
            VistaWeb.mostrarResultados(req, resp, respuesta, puntos, "Respuesta de Entrada");

        } catch (Exception ex) {
            VistaWeb.error(req, resp, ex);
        }
    }
}

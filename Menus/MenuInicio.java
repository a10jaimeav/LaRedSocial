package LaRedSocial.Menus;

import LaRedSocial.Usuarios.AltaNueva;
import LaRedSocial.Usuarios.InicioSesion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class MenuInicio {
    private static final Logger logger = LoggerFactory.getLogger(MenuInicio.class);
    public void menuInicio() {

        int opcion;

        do{
            System.out.println();
            logger.info("Introduce la opción deseada:\n\t1 - Iniciar Sesión\n" +
                    "\t2 - Darme de alta\n\t3 - Finalizar Programa");

            Scanner tecladoMenu = new Scanner(System.in);
            opcion = tecladoMenu.nextInt();

            switch (opcion){
                case 1:
                    InicioSesion iniciar = new InicioSesion();
                    iniciar.iniciar();
                    break;
                case 2:
                    AltaNueva alta = new AltaNueva(null);
                    alta.darDeAlta();
                    break;
                case 3:
                    logger.info("Programa finalizado");
                    break;
                default:
                    logger.error("Selecciona una opción correcta");
                    break;
            }
        } while(opcion != 3);
    }
}

package LaRedSocial.Menus;

import LaRedSocial.Usuarios.MostrarEnPantalla;
import LaRedSocial.Usuarios.AccionesUsuarios;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class MenuUsuarios {
    private static final Logger logger = LoggerFactory.getLogger(MenuUsuarios.class);
    public void menuUsuario(String nombreUsuario) {

        int opcion;
        String nombreIntroducido;

        do{
            System.out.println();
            logger.info(nombreUsuario + ", ¿qué quieres hacer ahora?:\n\t1 - Ver todos los usuarios\n\t2 - Ver los usuarios que sigo\n" +
                    "\t3 - Seguir un usuario\n\t4 - Dejar de seguir a un usuario\n\t5 - Eliminar mi usuario\n\t6 - Volver atrás");

            Scanner tecladoMenu = new Scanner(System.in);
            opcion = tecladoMenu.nextInt();
            MostrarEnPantalla mostrarEnPantalla = new MostrarEnPantalla();
            Scanner tecladoNombre = new Scanner(System.in);

            switch (opcion){
                case 1:
                    mostrarEnPantalla.mostrarEnPantalla("Usuarios.txt", nombreUsuario);
                    break;
                case 2:
                    mostrarEnPantalla.mostrarEnPantalla("Seguidos.txt", nombreUsuario);
                    break;
                case 3:
                    mostrarEnPantalla.comparar(nombreUsuario);
                    logger.info("¿A quién quieres seguir?");
                    nombreIntroducido = tecladoNombre.nextLine();
                    AccionesUsuarios follow = new AccionesUsuarios(nombreUsuario, null, null, null, null, null);
                    follow.reescribirFollow(nombreUsuario,nombreIntroducido);
                    break;
                case 4:
                    mostrarEnPantalla.mostrarEnPantalla("Seguidos.txt", nombreUsuario);
                    logger.info("Estos son los usuarios que sigues. ¿A quién quieres dejar de seguir?");
                    nombreIntroducido = tecladoNombre.nextLine();
                    AccionesUsuarios unFollow = new AccionesUsuarios(nombreUsuario, null, null, null, null, null);
                    unFollow.reescribirFollow(nombreUsuario,nombreIntroducido);
                    break;
                case 5:
                    break;
                case 6:
                    MenuBasico menuBasico = new MenuBasico();
                    menuBasico.menuBasico(nombreUsuario);
                    break;
                default:
                    logger.error("Selecciona una opción correcta");
                    break;
            }
        } while(opcion != 6);
    }
}

package LaRedSocial.Menus;

import LaRedSocial.Objetos.Comentarios;
import LaRedSocial.Usuarios.AccionesUsuarios;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class MenuBasico {
    private static final Logger logger = LoggerFactory.getLogger(MenuBasico.class);
    public void menuBasico(String nombreUsuario) {
        AccionesUsuarios auditoria = new AccionesUsuarios(nombreUsuario, null, null, null, null, null);
        auditoria.iniciarAuditoria(true);

        int opcion;

        do{
            System.out.println();
            logger.info("Bienvenido " + nombreUsuario + ". ¿Qué quieres hacer?:\n\t1 - Perfil\n" +
                    "\t2 - Entradas\n\t3 - Comentarios baneados\n\t4 - Cerrar sesión");

            Scanner tecladoMenu = new Scanner(System.in);
            opcion = tecladoMenu.nextInt();

            switch (opcion){
                case 1:
                    MenuUsuarios menuUsuarios = new MenuUsuarios();
                    menuUsuarios.menuUsuario(nombreUsuario);
                    break;
                case 2:
                    MenuEntradas menuEntradas = new MenuEntradas();
                    menuEntradas.menuEntradas(nombreUsuario);
                    break;
                case 3:
                    Comentarios listarComentarios = new Comentarios(nombreUsuario,null,null,null,"Auditando","1");
                    listarComentarios.mostrarComentario("Auditados");
                    break;
                case 4:
                    logger.info("Sesión cerrada");
                    MenuInicio menuInicio = new MenuInicio();
                    menuInicio.menuInicio();
                    break;
                default:
                    logger.error("Selecciona una opción correcta");
                    break;
            }
        } while(opcion != 4);
    }
}

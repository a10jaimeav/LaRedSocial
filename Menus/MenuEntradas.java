package LaRedSocial.Menus;

import LaRedSocial.Objetos.AccionesObjetos;
import LaRedSocial.Objetos.Imagenes;
import LaRedSocial.Objetos.Posts;
import LaRedSocial.Objetos.Videos;
import LaRedSocial.Usuarios.Auditor;
import LaRedSocial.Usuarios.MostrarEnPantalla;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class MenuEntradas {
    private static final Logger logger = LoggerFactory.getLogger(MenuEntradas.class);
    public void menuEntradas(String nombreUsuario) {

        int opcion;

        do{
            System.out.println();
            logger.info(nombreUsuario + ", ¿qué quieres hacer ahora?:\n\t1 - Ver mis entradas\n\t2 - Ver entradas de gente a la que sigo\n" +
                    "\t3 - Añadir una entrada\n\t4 - Ver comentarios sobre una entrada\n\t5 - Eliminar una entrada\n\t6 - Volver atrás");

            Scanner tecladoMenu = new Scanner(System.in);
            opcion = tecladoMenu.nextInt();

            String tipo;
            Scanner tecladoOpcion = new Scanner(System.in);
            List<String> tipoEntrada = Arrays.asList("Post", "Imagen", "Video");

            switch (opcion){
                case 1:
                    do {
                        logger.info("{}", tipoEntrada);
                        logger.info("¿Qué tipo de entradas quieres ver?");
                        tipo = tecladoOpcion.nextLine().toLowerCase();

                        switch (tipo){
                            case "post":
                                AccionesObjetos verPosts = new AccionesObjetos(nombreUsuario, null, null, null, "Posts.txt");
                                verPosts.listarElementos(nombreUsuario);
                                break;
                            case "imagen":
                                AccionesObjetos verImagenes = new AccionesObjetos(nombreUsuario, null, null, null,"Imagenes.txt");
                                verImagenes.listarElementos(nombreUsuario);
                                break;
                            case "video":
                                AccionesObjetos verVideos = new AccionesObjetos(nombreUsuario, null, null, null,"Videos.txt");
                                verVideos.listarElementos(nombreUsuario);
                                break;
                        }
                    } while (!tipoEntrada.contains(tipo));
                    break;

                case 2:
                    String otroUsuario;
                    MostrarEnPantalla mostrarUsuariosSeguidos = new MostrarEnPantalla();
                    mostrarUsuariosSeguidos.mostrarEnPantalla("Seguidos.txt", nombreUsuario);
                    logger.info("¿De qué usuario quieres ver?");
                    otroUsuario = tecladoOpcion.nextLine();
                    AccionesObjetos listarEntradasOtros = new AccionesObjetos(nombreUsuario, null, null, null, "Posts.txt");
                    listarEntradasOtros.listarElementos(otroUsuario);
                    break;
                case 3:
                    //Aquí hacer para que se repita si no se introduce algo de lo que está en el ArrayList
                    do {
                        logger.info("{}", tipoEntrada);
                        logger.info("¿Qué tipo de entrada quieres realizar?");
                        tipo = tecladoOpcion.nextLine().toLowerCase();

                        switch (tipo){
                            case "post":
                                Posts escribirPost = new Posts(nombreUsuario, null, null, null, "Posts.txt");
                                escribirPost.infoElemento(nombreUsuario);
                                break;
                            case "imagen":
                                Imagenes escribirImagen = new Imagenes(nombreUsuario, null, null, null,"Imagenes.txt", null);
                                escribirImagen.infoElemento(nombreUsuario);
                                break;
                            case "video":
                                Videos escribirVideo = new Videos(nombreUsuario, null, null, null,"Videos.txt", null, null);
                                escribirVideo.infoElemento(nombreUsuario);
                                break;
                        }
                    } while (!tipoEntrada.contains(tipo));
                    break;

                case 4:
                    break;

                case 5:
                    //Aquí hacer para que se repita si no se introduce algo de lo que está en el ArrayList
                    do {
                        logger.info("{}", tipoEntrada);
                        logger.info("¿Qué tipo de entradas eliminar?");
                        tipo = tecladoOpcion.nextLine().toLowerCase();

                        switch (tipo){
                            case "post":
                                AccionesObjetos eliminarPosts = new AccionesObjetos(nombreUsuario, null, null, null, "Posts.txt");
                                eliminarPosts.eliminarElemento();
                                break;
                            case "imagen":
                                AccionesObjetos eliminarImagenes = new AccionesObjetos(nombreUsuario, null, null, null,"Imagenes.txt");
                                eliminarImagenes.eliminarElemento();
                                break;
                            case "video":
                                AccionesObjetos eliminarVideos = new AccionesObjetos(nombreUsuario, null, null, null,"Videos.txt");
                                eliminarVideos.eliminarElemento();
                                break;
                        }
                    } while (!tipoEntrada.contains(tipo));
                    break;
                case 6:
                    MenuBasico menuBasico = new MenuBasico();
                    menuBasico.menuBasico(nombreUsuario);
                    break;
                case 7:
                    Auditor auditarComentarios = new Auditor(nombreUsuario);
                    auditarComentarios.revisarComentario();
                    break;
                default:
                    logger.error("Selecciona una opción correcta");
                    break;
            }
        } while(opcion != 7);
    }
}

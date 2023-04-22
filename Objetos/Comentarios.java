package LaRedSocial.Objetos;

import LaRedSocial.Usuarios.AccionesUsuarios;
import LaRedSocial.Menus.MenuEntradas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.Scanner;

public class Comentarios extends AccionesObjetos {
    private static final Logger logger = LoggerFactory.getLogger(AccionesUsuarios.class);
    private static final String RUTACOMENTARIOS = "src/main/resources/ArchivosRedSocial/Comentarios.txt";

    protected String numEntrada;
    public Comentarios(String propietario, String titulo, String cuerpo, Date fecha, String documento, String numEntrada) {
        super(propietario, titulo, cuerpo, fecha, documento);
        this.numEntrada = numEntrada;
    }

    public void escribirComentario() {//Tengo que llamarla desde listarElementos, al final de todo
        logger.info("¿Quieres añadir un comentario?(y/n)");
        Scanner tecladoComentario = new Scanner(System.in);
        String respuesta = tecladoComentario.nextLine();
        if (respuesta.equals("y")) {
            logger.info("¿En qué entrada? (Escribe el número o 0 para salir)");
            respuesta = tecladoComentario.nextLine();
        }
        if(!respuesta.equals("0")){
            logger.info("Introduce el texto del comentario:");
            respuesta = tecladoComentario.nextLine();
            //Crear la línea a introducir en el documento de comentarios
            String lineaParaIntroducir = (propietario + ";" + "Usuario post" + ";" + documento + ";" + numEntrada + ";" + respuesta);
        }
        MenuEntradas menuEntradas = new MenuEntradas();
        menuEntradas.menuEntradas(propietario);
    }
    public void mostrarComentario(){
        if(!numEntrada.equals("0")) {
            File fileComentarios = new File(RUTACOMENTARIOS);

            try (FileReader fr = new FileReader(fileComentarios); BufferedReader br = new BufferedReader(fr)) {
                String linea = "";

                while ((linea = br.readLine()) != null) {
                    String[] lineaLeida = linea.split(";");
                    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    if(documento.equals(lineaLeida[2]) && numEntrada.equals(lineaLeida[3])) {
                        System.out.println("Comentario de " + lineaLeida[1] + "\n" + lineaLeida[4]);
                        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            logger.info("No hay comentarios");
        }
    }

    public void eliminarComentario(){

    }

}

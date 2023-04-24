package LaRedSocial.Objetos;

import LaRedSocial.Usuarios.AccionesUsuarios;
import LaRedSocial.Menus.MenuEntradas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Date;
import java.util.Scanner;

public class Comentarios extends AccionesObjetos {
    private static final Logger logger = LoggerFactory.getLogger(AccionesUsuarios.class);
    private static final String RUTACOMENTARIOS = "src/main/resources/ArchivosRedSocial/Comentarios.txt";
    private static final String RUTATEMPORAL = "src/main/resources/ArchivosRedSocial/Temporal.txt";

    protected String numEntrada;
    public Comentarios(String propietario, String titulo, String cuerpo, Date fecha, String documento, String numEntrada) {
        super(propietario, titulo, cuerpo, fecha, documento);
        this.numEntrada = numEntrada;
    }

    public void mostrarComentario(String propietario){
        if(!numEntrada.equals("0")) {
            File fileComentarios = new File(RUTACOMENTARIOS);

            try (FileReader fr = new FileReader(fileComentarios); BufferedReader br = new BufferedReader(fr)) {
                String linea;

                while ((linea = br.readLine()) != null) {
                    String[] lineaLeida = linea.split(";");
                    if(documento.equals(lineaLeida[2]) && numEntrada.equals(lineaLeida[3]) && propietario.equals(lineaLeida[0])) {
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


    public void escribirComentario(String propietario) {
        Scanner tecladoComentario = new Scanner(System.in);
        String respuesta;
        logger.info("Introduce el texto del comentario:");
        respuesta = tecladoComentario.nextLine();

        try(FileReader fr = new FileReader(RUTATEMPORAL); BufferedReader br = new BufferedReader(fr); PrintWriter pw = new PrintWriter(new FileOutputStream(RUTACOMENTARIOS, true))){
            String linea, entrada = "";
            while ((linea = br.readLine()) != null){
                if(linea.equals("Entrada " + numEntrada + " de " + propietario)){
                    br.readLine();
                    linea = br.readLine();
                    entrada = linea;//No me coge el valor de cada entrada
                }
            }
            //Crear la línea a introducir en el documento de comentarios
            String lineaParaIntroducir = ("\n" + propietario + ";" + this.propietario + ";" + documento + ";" + entrada + ";" + respuesta);
            pw.write(lineaParaIntroducir);

            logger.info("Se ha añadido tu comentario");
        } catch (Exception e){
            e.printStackTrace();
        }

        MenuEntradas menuEntradas = new MenuEntradas();
        menuEntradas.menuEntradas(propietario);
    }

    public void eliminarComentario(){

    }

}

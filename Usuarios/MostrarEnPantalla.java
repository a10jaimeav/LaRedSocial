package LaRedSocial.Usuarios;

import LaRedSocial.Menus.MenuBasico;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class MostrarEnPantalla {
    private static final Logger logger = LoggerFactory.getLogger(MenuBasico.class);

    //Listo todos los usuarios o solamente los que sigo
    public void mostrarEnPantalla(String opcion, String usuarioPropio){
        //Crear el tipo archivo para indicarle la ruta
        File file = new File("src/main/resources/ArchivosRedSocial/" + opcion);

        //Lectura del archivo con el try
        try(FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)){
            String linea = " ";

            if(opcion.equals("Usuarios.txt")) {
                //Imprimo por pantalla todos los usuarios
                while ((linea = br.readLine()) != null) {
                    String[] nombreUsuario = linea.split(";");
                    logger.info("{}", nombreUsuario[0]);
                }
            } else if(opcion.equals("Seguidos.txt")) {
                //Imprimo por pantalla los usuarios que sigo
                while ((linea = br.readLine()) != null) {
                    String[] nombreUsuario = linea.split(";");
                    if(nombreUsuario[0].equals(usuarioPropio)){
                        for(int i = 1; i < nombreUsuario.length; i++) {
                            logger.info("{}", nombreUsuario[i]);
                        }
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //Comparo los usuarios que sigo con los que hay en el documento para listarlos en pantalla
    public void comparar(String usuarioPropio){
        //Crear el tipo archivo para indicarle la ruta
        File file = new File("src/main/resources/ArchivosRedSocial/Usuarios.txt");
        File file2 = new File("src/main/resources/ArchivosRedSocial/Seguidos.txt");

        ArrayList<String> listaComparar = new ArrayList<>();

        //Lectura del archivo con el try
        try(FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)){
            String linea = " ";

            //Añado al arrayList todos los usuarios menos el propio
            while ((linea = br.readLine()) != null) {
                String[] nombreUsuario = linea.split(";");
                if(!nombreUsuario[0].equals(usuarioPropio)){
                    listaComparar.add(nombreUsuario[0]);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        //Lectura del archivo con el try
        try(FileReader fr = new FileReader(file2); BufferedReader br = new BufferedReader(fr)){
            String linea = " ";

            //Comparo cada nombre de los usuarios que sigue el usuario activo con la lista
            while ((linea = br.readLine()) != null) {
                String[] nombreUsuario = linea.split(";");
                if(nombreUsuario[0].equals(usuarioPropio)){
                    for(int i = 1; i < nombreUsuario.length; i++){
                        if(listaComparar.contains(nombreUsuario[i])){
                            listaComparar.remove(nombreUsuario[i]);
                        }
                    }
                }
            }
            logger.info("{}", listaComparar);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

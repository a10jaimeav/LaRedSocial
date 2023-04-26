package LaRedSocial.Usuarios;

import LaRedSocial.Menus.MenuBasico;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Scanner;

public class InicioSesion {
    private static final Logger logger = LoggerFactory.getLogger(InicioSesion.class);
    public void iniciar(){
        String nombreUsuario, password;
        Scanner teclado = new Scanner(System.in);

        //Comprobamos si lo que nos introducen se corresponde con lo almacenado en el archivo
        do {
            logger.info("Usuario:");
            nombreUsuario = teclado.nextLine();
            logger.info("Contraseña:");
            password = teclado.nextLine();
        } while (!comprobarInicio(nombreUsuario, password));

        //Llamo al menú de usuario
        MenuBasico menuBasico = new MenuBasico();
        menuBasico.menuBasico(nombreUsuario);
    }

    public boolean comprobarInicio(String nombreUsuario, String password){
        //Puntero para el archivo de usuarios
        File fileUsuarios = new File("src/main/resources/ArchivosRedSocial/Usuarios.txt");

        //Try para leer el archivo
        try (FileReader fr = new FileReader(fileUsuarios); BufferedReader br = new BufferedReader(fr)){
            //Compruebo si existe el fichero y sino lo creo
            if(!fileUsuarios.exists()){
                fileUsuarios.createNewFile();
                logger.info("No existen usuarios");//Tengo que hacer que vuelva para el menú principal
                return false;
            }

            //Comprobación de si el usuario existe
            String linea;
            while((linea = br.readLine()) != null){
                String[] comparaUsuario = linea.split(";");
                if(comparaUsuario[0].equals(nombreUsuario) && comparaUsuario[1].equals(password) && Integer.parseInt(comparaUsuario[5]) < 5){
                    logger.info("Inicio de sesión correcto");
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.error("El nombre de usuario o contraseña no son correctos o has sido bloqueado");
        return false;
    }
}

package LaRedSocial.Usuarios;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Scanner;

public class AltaNueva extends AccionesUsuarios{
    private static final Logger logger = LoggerFactory.getLogger(AltaNueva.class);

    private String nombreUsuario;
    private static final String RUTAUSUARIOS = "src/main/resources/ArchivosRedSocial/Usuarios.txt";

    public AltaNueva(String nombreUsuario) {
        super(nombreUsuario, null,null,null,null,null);
    }

    public void darDeAlta(){
        Scanner teclado = new Scanner(System.in);
        StringBuilder lineaIntroducir = new StringBuilder();

        do {
            logger.info("Introduce tu nombre de usuario:");
            nombreUsuario = teclado.nextLine();
        } while (!this.comprobarUsuario(RUTAUSUARIOS));
        lineaIntroducir.append(nombreUsuario + ";");
        logger.info("Introduce tu contraseña:");
        lineaIntroducir.append(teclado.nextLine() + ";");
        logger.info("Introduce tu nombre:");
        lineaIntroducir.append(teclado.nextLine() + ";");
        logger.info("Introduce tu primer apellido:");
        lineaIntroducir.append(teclado.nextLine() + ";");
        logger.info("Introduce tu segundo apellido:");
        lineaIntroducir.append(teclado.nextLine() + "\n");

        //Puntero para el archivo de usuarios
        File fileUsuarios = new File(RUTAUSUARIOS);

        //Try para escribir la línea nueva
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(fileUsuarios, true))){
            pw.write(lineaIntroducir + ";0");
            logger.info("Has sido dado de alta correctamente");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

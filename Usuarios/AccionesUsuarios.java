package LaRedSocial.Usuarios;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class AccionesUsuarios {
    private static final Logger logger = LoggerFactory.getLogger(AccionesUsuarios.class);
    protected String nombreUsuario;
    protected static final String RUTASEGUIDOS = "src/main/resources/ArchivosRedSocial/Seguidos.txt";
    protected static final String RUTATEMPORAL = "src/main/resources/ArchivosRedSocial/Temporal.txt";

    public AccionesUsuarios(String nombreUsuario){
        this.nombreUsuario = nombreUsuario;
    }

    public boolean comprobarUsuario(String ruta){//Reutilizar para seguir a alguien o dejar de seguir
        //Puntero para el archivo de usuarios
        File fileUsuarios = new File(ruta);

        //Try para leer el archivo
        try (FileReader fr = new FileReader(fileUsuarios); BufferedReader br = new BufferedReader(fr)){
            //Compruebo si existe el fichero y sino lo creo
            if(!fileUsuarios.exists()){
                fileUsuarios.createNewFile();
            }

            //Comprobación de si el usuario existe
            String linea;
            while((linea = br.readLine()) != null){
                String[] comparaUsuario = linea.split(";");
                if(comparaUsuario[0].equals(nombreUsuario)){
                    logger.error("Este nombre de usuario ya existe");
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public void reescribirFollow(String nombreUsuario, String nombreSeguido){
        //Crear el tipo archivo para indicarle la ruta
        File file = new File(RUTASEGUIDOS);
        File fileTemporal = new File(RUTATEMPORAL);

        //Lectura del archivo con el try
        try(FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr);PrintWriter pw = new PrintWriter(fileTemporal)){
            boolean Follow = true;
            String linea;
            StringBuilder nuevaLinea = new StringBuilder();
            //Busco los usuarios que sigo
            while ((linea = br.readLine()) != null) {
                String[] lineaLeida = linea.split(";");
                if (lineaLeida[0].equals(nombreUsuario)) {
                    nuevaLinea.append(lineaLeida[0]+";");
                    for(int i = 1; i < lineaLeida.length; i++){
                        //UnFollow = Solo incluyo los seguidores que son diferentes a los del nombre que quiero dejar de seguir
                        if(!lineaLeida[i].equals(nombreSeguido)) {
                            nuevaLinea.append(lineaLeida[i] + ";");
                        } else {//Si llega aquí quiere decir que realizó el UnFollow
                            Follow = false;
                        }
                    }
                    if(Follow){//Si no realizó el UnFollow, la bandera sigue siendo verdadera, por lo que entrará aquí
                        nuevaLinea.append(nombreSeguido + ";");
                        Follow = false;
                    }
                    nuevaLinea.append("\n");
                    pw.write(nuevaLinea.toString());
                } else {
                    //Escribo la línea completa en el temporal si no es la del usuario activo
                    pw.write(linea + "\n");
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        //Aquí reescribo el fichero original con lo que contiene el temporal
        try(FileReader fr = new FileReader(fileTemporal); BufferedReader br = new BufferedReader(fr);PrintWriter pw = new PrintWriter(file)){
            String linea;
            while ((linea = br.readLine()) != null) {
                pw.write(linea + "\n");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
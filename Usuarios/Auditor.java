package LaRedSocial.Usuarios;

/*
Modificar el ejercicio de la Red social para incluir Hilos.

Ahora la red social contará con auditores!
Los auditores tienen las siguientes características:
    Un auditor es un Usuario que es Runnable y extiende de Usuario por lo que se ejecutará en un hilo.
    El auditor tendrá un atributo con una Lista de strings que representa sus palabras clave que va a censurar.
    El auditor tendrá un atributo con la lista de post y comentarios que ha censurado
    El auditor revisará todos los post y comentarios buscando entre una lista de palabras claves.
    Cuando encuentre una palabra clave en el post o en el comentario lo modificará cambiando esa palabra por ****
    El auditor tendrá un método que imprima todos los post que ha censurado
    El auditor tendrá un método que contabilice cuantas veces censuro a un usuario

Ahora los usuarios tendrán un nuevo atributo, estado, que podrá ser activo o bloqueado.
Cuando un auditor censure 5 comentarios de un usuario, este usuario pasará a estar bloqueado.
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

public class Auditor extends AccionesUsuarios implements Runnable{
    private static final String RUTAPOSTS = "src/main/resources/ArchivosRedSocial/Posts.txt";
    private static final String RUTACOMENTARIOS = "src/main/resources/ArchivosRedSocial/Comentarios.txt";
    private static final String RUTATEMPCOMENTARIOS = "src/main/resources/ArchivosRedSocial/TemporalComentarios.txt";
    private static final String RUTATEMPORAL = "src/main/resources/ArchivosRedSocial/Temporal.txt";

    ArrayList<String> palabrasBaneadas = new ArrayList<>();
    ArrayList<String> postBaneados = new ArrayList<>();
    ArrayList<String> comentariosBaneados = new ArrayList<>();

    public Auditor(String nombreUsuario, String password, String nombre, String apellido1, String apellido2, Integer warnings) {
        super(nombreUsuario, password, nombre, apellido1, apellido2, warnings);
    }

    public void revisarPost(){
        ArrayList<String> postRevisar = new ArrayList<>();
        try(FileReader fr = new FileReader(RUTAPOSTS); BufferedReader br = new BufferedReader(fr); PrintWriter pw = new PrintWriter(RUTATEMPORAL)){
            String linea;
            while ((linea = br.readLine()) != null){
                for(int i = 0; i < 5; i++){
                    postRevisar.add(linea + "\n");
                    linea = br.readLine();
                }
                for(int j = 0; j < palabrasBaneadas.size(); j++){
                    if(postRevisar.get(4).contains(palabrasBaneadas.get(j))){
                        postRevisar.set(4, postRevisar.get(4).replace(palabrasBaneadas.get(j),"****"));
                    }
                }
                for (String s : postRevisar) {
                    pw.write(s);
                }
                pw.write("\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        reescribirDocumento(RUTAPOSTS);
    }

    //Tengo que pausar al auditor o utilizar otros documentos
    public void revisarComentario(){
        palabrasBaneadas.add("about4");
        try(FileReader fr = new FileReader(RUTACOMENTARIOS); BufferedReader br = new BufferedReader(fr); PrintWriter pw = new PrintWriter(RUTATEMPCOMENTARIOS)){
            String linea;
            while ((linea = br.readLine()) != null){
                int warning = 0;
                String[] comentarioSeparado = linea.split(";");
                for(int j = 0; j < palabrasBaneadas.size(); j++){
                    if(comentarioSeparado[4].contains(palabrasBaneadas.get(j))){
                        comentarioSeparado[4] = comentarioSeparado[4].replace(palabrasBaneadas.get(j),"****");
                        warning += 1;
                    }
                }

                for (String s : comentarioSeparado) {
                    pw.write(s + ";");
                }
                pw.write("\n");

                this.reescribirUsuario(comentarioSeparado[1], warning);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        reescribirDocumento(RUTACOMENTARIOS);
    }

    public void reescribirDocumento(String ruta){
        try(FileReader fr = new FileReader(RUTATEMPCOMENTARIOS); BufferedReader br = new BufferedReader(fr); PrintWriter pw = new PrintWriter(ruta)){
            String linea;
            boolean primeralinea = true;
            while ((linea = br.readLine()) != null){
                if(primeralinea){
                    pw.write(linea);
                    primeralinea = false;
                } else {
                    pw.write("\n" + linea);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

    }
}

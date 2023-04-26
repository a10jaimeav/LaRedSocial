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
import java.util.ArrayList;

public class Auditor extends AccionesUsuarios implements Runnable{
    private static final String RUTAPOSTS = "src/main/resources/ArchivosRedSocial/Posts.txt";
    private static final String RUTACOMENTARIOS = "src/main/resources/ArchivosRedSocial/Comentarios.txt";
    private static final String RUTATEMPORAL = "src/main/resources/ArchivosRedSocial/Temporal.txt";

    ArrayList<String> palabrasBaneadas = new ArrayList<>();
    ArrayList<String> postBaneados = new ArrayList<>();
    ArrayList<String> comentariosBaneados = new ArrayList<>();

    public Auditor(String nombreUsuario) {
        super(nombreUsuario);
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
    public void revisarComentario(){
        palabrasBaneadas.add("sobre");
        try(FileReader fr = new FileReader(RUTACOMENTARIOS); BufferedReader br = new BufferedReader(fr); PrintWriter pw = new PrintWriter(RUTATEMPORAL)){
            String linea;
            while ((linea = br.readLine()) != null){
                String[] comentarioSeparado = linea.split(";");
                for(int j = 0; j < palabrasBaneadas.size(); j++){
                    if(comentarioSeparado[4].contains(palabrasBaneadas.get(j))){
                        comentarioSeparado[4] = comentarioSeparado[4].replace(palabrasBaneadas.get(j),"****");
                    }
                }

                for (String s : comentarioSeparado) {
                    pw.write(s + ";");
                }
                pw.write("\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        reescribirDocumento(RUTACOMENTARIOS);
    }

    public void reescribirDocumento(String ruta){
        try(FileReader fr = new FileReader(RUTATEMPORAL); BufferedReader br = new BufferedReader(fr); PrintWriter pw = new PrintWriter(ruta)){
            String linea;
            while ((linea = br.readLine()) != null){
                pw.write(linea + "\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void run() {

    }
}

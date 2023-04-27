package LaRedSocial.Objetos;

import LaRedSocial.Menus.MenuEntradas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

public class AccionesObjetos {
    private static final Logger logger = LoggerFactory.getLogger(AccionesObjetos.class);
    protected String propietario;
    protected String titulo;
    protected String cuerpo;
    protected Date fecha;
    protected String documento;
    private static final String RUTATEMPORAL  = "src/main/resources/ArchivosRedSocial/Temp/Temporal.txt";
    private static final String RUTAALTERNATIVO  = "src/main/resources/ArchivosRedSocial/Temp/TemporalAlternativo.txt";
    Map<String, Integer> documentosValores = new HashMap<>() {{
        put("Posts.txt", 4);
        put("Imagenes.txt", 5);
        put("Videos.txt", 6);
    }};

    public AccionesObjetos(String propietario, String titulo, String cuerpo, Date fecha, String documento){
        this.propietario = propietario;
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.fecha = fecha;
        this.documento = documento;
    }

    public void escribirElemento(String documento, String elementoParaIntroducir){
        File filePost = new File("src/main/resources/ArchivosRedSocial/" + documento);
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(filePost, true))){
            pw.write(elementoParaIntroducir);
        } catch (Exception e){
            e.printStackTrace();
        }

        MenuEntradas menuEntradas = new MenuEntradas();
        menuEntradas.menuEntradas(propietario);
    }

    public void eliminarElemento(){
        int numeroElemento;
        Scanner tecladoEliminar = new Scanner(System.in);

        //Crear el tipo archivo para indicarle la ruta
        File file = new File("src/main/resources/ArchivosRedSocial/" + documento);
        File fileTemporal = new File(RUTATEMPORAL);
        File fileAlternativo = new File(RUTAALTERNATIVO);
        listarElementos(propietario);

        logger.info("¿Qué entrada quieres eliminar? (Introduce numero)");
        numeroElemento = tecladoEliminar.nextInt();

        //Lectura del archivo Temporal
        try(FileReader fr = new FileReader(fileTemporal); BufferedReader br = new BufferedReader(fr);PrintWriter pw = new PrintWriter(file)){
            String linea;
            StringBuilder nuevaLinea = new StringBuilder();
            //Busco mis elementos en el documento temporal
            while ((linea = br.readLine()) != null) {
                if(linea.equals("----------")){
                    linea = br.readLine();
                    if(!linea.equals("Entrada " + numeroElemento + " de " + propietario)){
                        nuevaLinea.append("----------\n" + br.readLine() + "\n");
                        for(int i = 0; i < documentosValores.get(documento); i++) {
                            nuevaLinea.append(br.readLine() + "\n");
                        }
                    }
                }
            }
            pw.write(nuevaLinea.toString());
        } catch (Exception e){
            e.printStackTrace();
        }

        //Completa el archivo original con lo que hay en el alternativo para disponer de todos los elementos
        try(FileReader fr = new FileReader(fileAlternativo); BufferedReader br = new BufferedReader(fr);PrintWriter pw = new PrintWriter(new FileOutputStream(file, true))){
            String linea;
            while ((linea = br.readLine()) != null){
                pw.write(linea + "\n");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        MenuEntradas menuEntradas = new MenuEntradas();
        menuEntradas.menuEntradas(propietario);
    }

    public void listarElementos(String propietario){
        File file = new File("src/main/resources/ArchivosRedSocial/" + documento);
        File fileTemporal = new File(RUTATEMPORAL);
        File fileAlternativo = new File(RUTAALTERNATIVO);

        try(FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)){
            int numeroOrden = 1;
            String numEntrada = "0";
            boolean autonumerico = false;
            String linea;

            if(fileTemporal.exists()){
                fileTemporal.delete();
            }
            fileTemporal.createNewFile();

            if(fileAlternativo.exists()){
                fileAlternativo.delete();
            }
            fileAlternativo.createNewFile();


            //Leo línea por línea y añado algo nuevo al temporal y al temporal auxiliar
            while ((linea = br.readLine()) != null) {
                if(linea.equals("----------")) {
                    linea = br.readLine();

                    if (linea.equals(propietario)) {
                        try(PrintWriter pw = new PrintWriter(new FileOutputStream(fileTemporal, true))) {

                            pw.write("----------\n");
                            pw.write("Entrada " + numeroOrden + " de " + propietario + "\n");
                            pw.write(propietario + "\n");
                            logger.info("Entrada " + numeroOrden);
                            numeroOrden += 1;
                            for (int i = 0; i < documentosValores.get(documento); i++) {
                                pw.write(linea = br.readLine());
                                if (!autonumerico) {
                                    numEntrada = linea;
                                    autonumerico = true;
                                } else {
                                    logger.info(linea);
                                }
                                pw.write("\n");
                            }
                            autonumerico = false;

                            Comentarios mostrarComentarios = new Comentarios(propietario, null, null, null, documento, numEntrada);
                            mostrarComentarios.mostrarComentario(propietario);

                            logger.info("-------------------------");
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    } else {
                        try(PrintWriter pwAux = new PrintWriter(new FileOutputStream(fileAlternativo, true))) {
                            pwAux.write("----------\n" + linea + "\n");
                            for (int i = 0; i < documentosValores.get(documento); i++) {
                                pwAux.write(br.readLine() + "\n");
                            }
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }

            logger.info("¿Quieres añadir algún comentario?(y/n)");
            Scanner tecladoDecision = new Scanner(System.in);
            if((tecladoDecision.nextLine()).equalsIgnoreCase("y")){
                logger.info("¿Sobre qué número de entrada?");
                Comentarios escribirComentario = new Comentarios(this.propietario,null, null, null, documento, tecladoDecision.nextLine());
                escribirComentario.escribirComentario(propietario);
            } else {
                MenuEntradas volverAlMenu = new MenuEntradas();
                volverAlMenu.menuEntradas(this.propietario);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //Esto me sirve para saber cuál es el último número de entrada según su tipo y poder seguir generando el autonumérico
    public int ultimoElemento(String documento){
        int ultimo = 0;
        File file = new File("src/main/resources/ArchivosRedSocial/" + documento);

        try(FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)){
            String linea;

            if(!file.exists()){
                file.createNewFile();
            }

            //Leo línea por línea y sumo 1 al contador del último
            while ((linea = br.readLine()) != null) {
                if(linea.equals(propietario)) {
                    ultimo = (Integer.parseInt(br.readLine())+1);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return ultimo;
    }

    public void verMuro(){
        //Tengo que coger las últimas entradas de cada uno de los archivos y comparar las fechas para irlas ordenando
        //Cada entrada la meto en un array y comparo el último campo
        //Si una es muy nueva, la meto a un archivo temporal y voy generando el muro poco a poco para mostrarlo después
    }

    public void getComentarios(){

    }
}

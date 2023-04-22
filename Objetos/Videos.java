package LaRedSocial.Objetos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Videos extends AccionesObjetos {
    private static final Logger logger = LoggerFactory.getLogger(Videos.class);
    protected String calidad;
    protected String duracion;
    public Videos(String propietario, String titulo, String texto, Date fecha, String documento, String calidad, String duracion) {
        super(propietario, titulo, texto, fecha, documento);
        this.calidad = calidad;
        this.duracion = duracion;
    }

    public void infoElemento(String propietario) {
        Scanner tecladoPost = new Scanner(System.in);
        StringBuilder postParaIntroducir = new StringBuilder();

        postParaIntroducir.append("\n----------\n" + propietario + "\n" + ultimoElemento(documento) + "\nTítulo: ");
        logger.info("Introduce un título para el vídeo:");
        postParaIntroducir.append(tecladoPost.nextLine() + "\nTexto: ");
        logger.info("Introduce una descripción del vídeo:");
        postParaIntroducir.append(tecladoPost.nextLine());
        fecha = new Date();
        postParaIntroducir.append("\nFecha: " + fecha + "\n");
        List<String> tipoVideo = Arrays.asList("HD", "Screener", "8K", "4K", "SD");
        calidad = (tipoVideo.get((int)(Math.random()*(tipoVideo.size()-1))+1));
        postParaIntroducir.append("Calidad: " + calidad + "\nDuración : " + ((int)(Math.random()*10)+1) + " minutos");

        this.escribirElemento(documento, postParaIntroducir.toString());
    }
}

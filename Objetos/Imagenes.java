package LaRedSocial.Objetos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Scanner;

public class Imagenes extends AccionesObjetos {
    private static final Logger logger = LoggerFactory.getLogger(Imagenes.class);
    protected String dimensiones;
    public Imagenes(String propietario, String titulo, String texto, Date fecha, String documento, String dimensiones) {
        super(propietario, titulo, texto, fecha, documento);
        this.dimensiones =  dimensiones;
    }

    public void infoElemento(String propietario) {
        Scanner tecladoPost = new Scanner(System.in);
        StringBuilder postParaIntroducir = new StringBuilder();

        postParaIntroducir.append("\n----------\n" + propietario + "\n" + ultimoElemento(documento) + "\nTítulo: ");
        logger.info("Introduce un título para la imagen:");
        postParaIntroducir.append(tecladoPost.nextLine() + "\nTexto: ");
        logger.info("Introduce un texto motivador o influencer:");
        postParaIntroducir.append(tecladoPost.nextLine());
        fecha = new Date();
        postParaIntroducir.append("\nFecha: " + fecha + "\n");
        dimensiones = (((int)(Math.random()*200)+1) + "X" + ((int)(Math.random()*200)+1));
        postParaIntroducir.append("Dimensiones: " + dimensiones);

        this.escribirElemento(documento, postParaIntroducir.toString());
    }
}

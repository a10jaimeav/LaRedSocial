package LaRedSocial.Objetos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Scanner;

public class Posts extends AccionesObjetos {
    private static final Logger logger = LoggerFactory.getLogger(AccionesObjetos.class);

    public Posts(String propietario, String titulo, String texto, Date fecha, String documento) {
        super(propietario, titulo, texto, fecha, documento);
    }
    
    public void infoElemento(String propietario) {
        Scanner tecladoPost = new Scanner(System.in);
        StringBuilder postParaIntroducir = new StringBuilder();

        postParaIntroducir.append("\n----------\n" + propietario + "\n" + ultimoElemento(documento) + "\nTítulo: ");
        logger.info("Introduce el título del post:");
        postParaIntroducir.append(tecladoPost.nextLine() + "\nTexto: ");
        logger.info("Introduce el texto del post:");
        postParaIntroducir.append(tecladoPost.nextLine());
        fecha = new Date();
        postParaIntroducir.append("\nFecha: " + fecha);

        this.escribirElemento(documento, postParaIntroducir.toString());
    }
}

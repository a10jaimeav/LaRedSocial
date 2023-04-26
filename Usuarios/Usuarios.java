package LaRedSocial.Usuarios;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Usuarios {
    private static final Logger logger = LoggerFactory.getLogger(Usuarios.class);
    protected String nUsuario;
    protected String password;
    protected String nombre;
    protected String apellido1;
    protected String apellido2;
    protected Integer warnings;

    //Constructor
    public Usuarios(String nUsuario, String password, String nombre, String apellido1, String apellido2, Integer warnings){
        this.nUsuario = nUsuario;
        this.password = password;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.warnings = warnings;
    }

    //Getters
    public String getNombre() {
        return nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public String getnUsuario() {
        return nUsuario;
    }

    public String getPassword() {
        return password;
    }

    public Integer getWarnings(){ return warnings; }
}

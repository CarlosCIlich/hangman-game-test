package co.edu.univalle.poe.mvc.model;

import java.util.Collections;
import java.util.Locale;
import java.util.Set;

public class Partida {

    private Set<Character> letrasProbadas;
    private EstadoPartida estadoPartida;
    private String palabraSecreta;
    private int errores;
    private Jugador jugador;

    private static final int MAX_ERRORES = 6;

    public Partida(String nombreJugador, String palabraSecreta){

        this.jugador = new Jugador(nombreJugador);
        this.errores = 0;
        estadoPartida = EstadoPartida.EN_CURSO;

    }

    public boolean esValida(String auxEntrada){
        return auxEntrada != null && auxEntrada.toLowerCase().matches("^[a-zñ]$");
    }

    public int getErrores(){
        return errores;
    }

    public int getIntentosRestantes(){
        return MAX_ERRORES - errores;
    }

    public Set<Character> getLetrasProbadas(){
        return letrasProbadas;
    }

}

package co.edu.univalle.poe.mvc.model;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Set;

public class Partida {

    private Set<Character> letrasProbadas;
    private EstadoPartida estadoPartida;
    private String palabraSecreta;
    private int errores;
    private Jugador jugador;
    private char[] progreso;

    private static final int MAX_ERRORES = 6;

    public Partida(String nombreJugador, String palabraSecreta){

        this.jugador = new Jugador(nombreJugador);
        this.errores = 0;
        estadoPartida = EstadoPartida.EN_CURSO;
        this.palabraSecreta = palabraSecreta;
        progreso = new char[this.palabraSecreta.length()];
        Arrays.fill(this.progreso, '_');
    }

    public ResultadoIntento probarLetra(String entrada){

        if (estaTerminada()) {
            throw new IllegalStateException("La partida ya terminó. Inicia una nueva partida.");
        }

        if (esValida(entrada)){
            return ResultadoIntento.LETRA_INVALIDA;
        }

        char letra = entrada.charAt(0);

        if (letrasProbadas.contains(letra)){
            return ResultadoIntento.LETRA_REPETIDA;
        }

        letrasProbadas.add(letra);

        // Check Working
        boolean acierto = false;
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == letra) {
                progreso[i] = letra;
                acierto = true;
            }
        }

        if (acierto) {
            if (palabraCompleta()) {
                estadoPartida = EstadoPartida.GANADA;
                return ResultadoIntento.PALABRA_COMPLETADA;
            }
            return ResultadoIntento.LETRA_CORRECTA;
        }

        errores++;
        if (errores >= MAX_ERRORES) {
            estadoPartida = EstadoPartida.PERDIDA;
        }
        return ResultadoIntento.LETRA_INCORRECTA;
    }

    public boolean esValida(String auxEntrada){
        return auxEntrada != null && auxEntrada.toLowerCase().matches("^[a-zñ]$");
    }

    public boolean estaTerminada() {
        return estadoPartida != EstadoPartida.EN_CURSO;
    }

    public boolean palabraCompleta(){
        for (char c: progreso){
            if (c == '_') {
                return false;
            }
        }
        return true;
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

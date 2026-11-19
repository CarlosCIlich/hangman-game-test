package co.edu.univalle.poe.mvc.model;

public class Jugador {

    private String nombreJugador;

    public Jugador(String nombreJugador){
        if(
                nombreJugador == null ||
                nombreJugador.trim().isEmpty() ||
                nombreJugador.contains(","))
        {
            throw new IllegalArgumentException("El nombre debe tener contenido y no incluir comas.");
        }
        this.nombreJugador = nombreJugador.trim();
    }

    public String getNombreJugador() {
        return nombreJugador;
    }
}

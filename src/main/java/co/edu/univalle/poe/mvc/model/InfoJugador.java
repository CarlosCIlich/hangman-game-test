package co.edu.univalle.poe.mvc.model;

public class InfoJugador {

    private Jugador jugador;
    private String palabraSecreta;
    private String resultado;
    private int errores;

    public InfoJugador(String nombreJugador, String palabraSecreta, String resultado, int errores){
        this.jugador = new Jugador(nombreJugador);
        this.palabraSecreta = palabraSecreta;
        this.resultado = resultado;
        this.errores = errores;
    }


    // method to instanciate objects from file.
    public static InfoJugador desdeArchivo(String linea){

        String[] parametros = linea.split(",");
        if (parametros.length != 4){
            throw new IllegalArgumentException("Formato Invalido en el Historial: " + linea);
        }

        return new InfoJugador(
                parametros[0],
                parametros[1],
                parametros[2],
                Integer.parseInt(parametros[3].trim())
        );
    }

    @Override
    public String toString(){
        return jugador.getNombreJugador() + "," + palabraSecreta + "," + resultado + "," + errores;
    }

    public int getErrores() {
        return errores;
    }

    public String getPalabraSecreta(){
        return palabraSecreta;
    }

    public String getResultado() {
        return resultado;
    }

//    public void setErrores(int errores) {
//        this.errores = errores;
//    }
//
//    public void setResultado(String resultado) {
//        this.resultado = resultado;
//    }
}

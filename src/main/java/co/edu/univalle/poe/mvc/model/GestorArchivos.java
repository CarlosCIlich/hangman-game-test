package co.edu.univalle.poe.mvc.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GestorArchivos {

    private static final Path RUTA_ARCHIVO = Path.of("src", "main", "java", "co",
            "edu", "univalle", "poe", "mvc", "model", "palabras.txt");

    private static final Path RUTA_HISTORIAL = Path.of("src", "main", "java", "co",
            "edu", "univalle", "poe", "mvc", "model", "historial.txt");

    Random random = new Random();
    private BufferedWriter writer;
    private BufferedReader reader;
    private EstadoArchivo estadoArchivo;

    // Manejamos la excepcion en el controlador.
    public String seleccionarPalabraAleatoria() throws IOException {

        // Creamos una lista para guardar las palabras del archivo.
        List<String> palabras = new ArrayList<>();

        try {
            reader = Files.newBufferedReader(RUTA_ARCHIVO);
            String linea;

            while ((linea = reader.readLine()) != null) {
                String palabra = linea.trim();
                if (!palabra.isEmpty()) {
                    palabras.add(palabra);
                }
            }
            estadoArchivo = EstadoArchivo.CARGADO_OK;
            // Este bloque se asegura de cerrar el reader siempre.
        } catch (IOException e){
            // Does this really make sense?
            estadoArchivo = EstadoArchivo.CARGADO_ERROR;
            throw e;
        } finally {
            if (reader != null) {
                reader.close();
                reader = null;
            }
        }

        if (palabras.isEmpty()) {
            throw new IOException("El arhivo " + RUTA_ARCHIVO.getFileName() + " esta vacio.");
        }

        int indiceRandom = random.nextInt(palabras.size());
        return palabras.get(indiceRandom);

    }

    public EstadoArchivo guardarPuntaje(InfoJugador infoJugador) {
        // Verificar que el nombre del usuario no tenga ","
        String linea = infoJugador.toString();

        try {
            /* Pasamos StandardOpenOption.CREATE Y APPEND para que el historial no sea borrado cada vez que se
               guarda el puntaje */
            writer = Files.newBufferedWriter(RUTA_HISTORIAL,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
            writer.write(linea);
            writer.newLine();
            estadoArchivo = EstadoArchivo.GUARDADO_OK;
        } catch (IOException e) {
            estadoArchivo = EstadoArchivo.GUARDADO_ERROR;
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    estadoArchivo = EstadoArchivo.GUARDADO_ERROR;
                } finally {
                    writer = null;
                }
            }
        }

        return estadoArchivo;

    }

    /**
     * Metodo para leer el archivo de historial con los puntajes anteriores.
     * @return Devuelve una lista de objetos tipo InfoJugador.
     */
    public List<InfoJugador> leerHistorial() {

        List<InfoJugador> historial = new ArrayList<InfoJugador>();

        if (Files.notExists(RUTA_HISTORIAL)) {
            estadoArchivo = EstadoArchivo.CARGADO_OK;
            // gives back a [0] elements array.
            return historial;
        }

        try {
            reader = Files.newBufferedReader(RUTA_HISTORIAL);
            String linea;

            while ((linea = reader.readLine()) != null) {

                // Revisar si esto hace alguna diferencia.
                if (linea.isBlank()){
                    continue;
                }

                InfoJugador infoJugador = InfoJugador.desdeArchivo(linea);
                historial.add(infoJugador);
            }

            estadoArchivo = EstadoArchivo.CARGADO_OK;

        } catch (IOException e) {
            estadoArchivo = EstadoArchivo.CARGADO_ERROR;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException | IllegalArgumentException e) {
                    // temporal solution to catch errors.
                    estadoArchivo = EstadoArchivo.CARGADO_ERROR;
                } finally {
                    reader = null;
                }
            }
        }
        return historial;
    }

    public EstadoArchivo getEstado() {
        return estadoArchivo;
    }
}

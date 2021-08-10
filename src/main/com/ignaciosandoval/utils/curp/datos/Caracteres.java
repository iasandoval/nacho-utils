package com.ignaciosandoval.utils.curp.datos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase que representa el diccionario de caracteres a remplazar. También contiene una lista de las vocales.
 * <p>
 * Si la letra inicial de alguno de los apellidos o del nombre es Ñ, se asignará una "X" en su lugar.
 * <p>
 * Si en los apellidos o en el nombre aparecieran caracteres especiales como diagonal (/), guión (-), o punto (.),
 * se asignará una "X" en caso de que esa posición intervenga para la conformación de la clave.
 * <p>
 * Cuando se presentan diéresis (¨) en nombres o apellidos, habrá que omitirlas.
 *
 * @author iasandoval
 * @version 1.0
 * @see <a href="http://www.ordenjuridico.gob.mx/Federal/PE/APF/APC/SEGOB/Instructivos/InstructivoNormativo.pdf" target="_blank">INSTRUCTIVO Normativo para la asignación de la Clave Única de Registro de Población (PDF - Ver. 2006).</a>
 */
public final class Caracteres {

    /**
     * Diccionario de Caracteres a Remplazar.
     */
    public static final Map<Character, Character> CARACTERES_DICCIONARIO = new HashMap<>();

    /**
     * Listado de Vocales.
     */
    public static final List<Character> VOCALES = new ArrayList<>();

    static {
        CARACTERES_DICCIONARIO.put('Á', 'A');
        CARACTERES_DICCIONARIO.put('Ä', 'A');
        CARACTERES_DICCIONARIO.put('À', 'A');

        CARACTERES_DICCIONARIO.put('É', 'E');
        CARACTERES_DICCIONARIO.put('Ë', 'E');
        CARACTERES_DICCIONARIO.put('È', 'E');

        CARACTERES_DICCIONARIO.put('Í', 'I');
        CARACTERES_DICCIONARIO.put('Ï', 'I');
        CARACTERES_DICCIONARIO.put('Ì', 'I');

        CARACTERES_DICCIONARIO.put('Ó', 'O');
        CARACTERES_DICCIONARIO.put('Ö', 'O');
        CARACTERES_DICCIONARIO.put('Ò', 'O');

        CARACTERES_DICCIONARIO.put('Ú', 'U');
        CARACTERES_DICCIONARIO.put('Ü', 'U');
        CARACTERES_DICCIONARIO.put('Ù', 'U');

        CARACTERES_DICCIONARIO.put('Ñ', 'X');
        CARACTERES_DICCIONARIO.put('/', 'X');
        CARACTERES_DICCIONARIO.put('-', 'X');
        CARACTERES_DICCIONARIO.put('.', 'X');

        VOCALES.add('A');
        VOCALES.add('E');
        VOCALES.add('I');
        VOCALES.add('O');
        VOCALES.add('U');
    }

    /**
     * Constructor Privado.
     */
    private Caracteres() {
        // Private Constructor
    }
}

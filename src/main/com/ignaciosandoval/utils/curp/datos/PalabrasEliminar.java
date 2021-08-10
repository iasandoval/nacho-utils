package com.ignaciosandoval.utils.curp.datos;

import java.util.HashSet;
import java.util.Set;

/**
 * Clase que representa el listado de palabras a eliminar.
 * <p>
 * Cuando alguno de los apellidos o nombre es compuesto y la primera palabra de esta composición es una preposición,
 * conjunción, contracción (DA, DAS, DE, DEL, DER, DI, DIE, DD, EL, LA, LOS, LAS, LE, LES, MAC, MC, VAN, VON, Y). Se
 * elimina la primera palabra y utiliza la siguiente del apellido o nombre.
 * <p>
 * Cuando el nombre sea compuesto (formado por dos o más palabras), la clave se construye con la letra inicial de la
 * primera palabra, siempre que no sea MARIA, MA., MA, o JOSE, J, J. en cuyo caso se utilizará la segunda palabra.
 *
 * @author iasandoval
 * @version 1.0
 * @see <a href="http://www.ordenjuridico.gob.mx/Federal/PE/APF/APC/SEGOB/Instructivos/InstructivoNormativo.pdf" target="_blank">INSTRUCTIVO Normativo para la asignación de la Clave Única de Registro de Población (PDF - Ver. 2006).</a>
 */
public final class PalabrasEliminar {

    public static final Set<String> PALABRAS_A_ELIMINAR = new HashSet<>();

    static {
        PALABRAS_A_ELIMINAR.add("MARIA");
        PALABRAS_A_ELIMINAR.add("MA.");
        PALABRAS_A_ELIMINAR.add("MA");
        PALABRAS_A_ELIMINAR.add("JOSE");
        PALABRAS_A_ELIMINAR.add("J");
        PALABRAS_A_ELIMINAR.add("J.");
        PALABRAS_A_ELIMINAR.add("DA");
        PALABRAS_A_ELIMINAR.add("DAS");
        PALABRAS_A_ELIMINAR.add("DE");
        PALABRAS_A_ELIMINAR.add("DEL");
        PALABRAS_A_ELIMINAR.add("DER");
        PALABRAS_A_ELIMINAR.add("DI");
        PALABRAS_A_ELIMINAR.add("DIE");
        PALABRAS_A_ELIMINAR.add("DD");
        PALABRAS_A_ELIMINAR.add("EL");
        PALABRAS_A_ELIMINAR.add("LA");
        PALABRAS_A_ELIMINAR.add("LOS");
        PALABRAS_A_ELIMINAR.add("LAS");
        PALABRAS_A_ELIMINAR.add("LE");
        PALABRAS_A_ELIMINAR.add("LES");
        PALABRAS_A_ELIMINAR.add("MAC");
        PALABRAS_A_ELIMINAR.add("MC");
        PALABRAS_A_ELIMINAR.add("VAN");
        PALABRAS_A_ELIMINAR.add("VON");
        PALABRAS_A_ELIMINAR.add("Y");
    }

    /**
     * Constructor privado.
     */
    private PalabrasEliminar() {
        // Private Constructor
    }
}

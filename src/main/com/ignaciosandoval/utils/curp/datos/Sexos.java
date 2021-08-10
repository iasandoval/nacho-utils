package com.ignaciosandoval.utils.curp.datos;

import com.ignaciosandoval.utils.curp.datos.vo.Sexo;

/**
 * Clase que representa el listado de Sexos (Mujer y Hombre).
 *
 * @author iasandoval
 * @version 1.0
 * @see <a href="http://www.ordenjuridico.gob.mx/Federal/PE/APF/APC/SEGOB/Instructivos/InstructivoNormativo.pdf" target="_blank">INSTRUCTIVO Normativo para la asignación de la Clave Única de Registro de Población (PDF - Ver. 2006).</a>
 */
public final class Sexos {

    public static final Sexo MUJER = new Sexo("M", "MUJER");
    public static final Sexo HOMBRE = new Sexo("H", "HOMBRE");

    /**
     * Constructor privado.
     */
    private Sexos() {
        // Private Constructor
    }
}

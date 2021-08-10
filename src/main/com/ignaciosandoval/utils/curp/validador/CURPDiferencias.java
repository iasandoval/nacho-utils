package com.ignaciosandoval.utils.curp.validador;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa las diferencias entre una CURP proporcionada y una CURP generada.
 *
 * @author iasandoval
 * @version 1.0
 */
public class CURPDiferencias {

    public static final String DATOS_INSUFICIENTES = "DATOS INSUFICIENTES";
    public static final String ESPERADO_INVALIDO = "CURP ESPERADO INVALIDO";
    public static final String GENERADO_INVALIDO = "CURP GENERADO INVALIDO";
    public static final String VALIDO = "CURP VALIDO";
    public static final String INICIALES = "INICIALES";
    public static final String FECHA_DE_NACIMIENTO = "FECHA DE NACIMIENTO";
    public static final String SEXO = "SEXO";
    public static final String ENTIDAD_FEDERATIVA = "ENTIDAD FEDERATIVA";
    public static final String CONSONANTES = "CONSONANTES";
    public static final String HOMOCLAVE = "HOMOCLAVE";

    /**
     * CURP Esperado.
     */
    private final String curpEsperado;

    /**
     * CURP Generado.
     */
    private final String curpGenerado;

    /**
     * Listado de diferencias entre la CURP proporcionada y la CURP generada.
     */
    private final List<String> diferencias;

    /**
     * Constructor por defecto.
     *
     * @param curpEsperado CURP esperado.
     * @param curpGenerado CURP generado.
     */
    CURPDiferencias(String curpEsperado, String curpGenerado) {
        this.curpEsperado = curpEsperado;
        this.curpGenerado = curpGenerado;
        this.diferencias = new ArrayList<>();
    }

    /**
     * Getter de la CURP esperada.
     *
     * @return La CURP proporcionada.
     */
    public String getCurpEsperado() {
        return curpEsperado;
    }

    /**
     * Getter de la CURP generada.
     *
     * @return La CURP generada.
     */
    public String getCurpGenerado() {
        return curpGenerado;
    }

    /**
     * Getter del listado de diferencias.
     *
     * @return Listado de diferencias.
     */
    public List<String> getDiferencias() {
        return diferencias;
    }

    /**
     * Muestra la representación del Objeto en String.
     *
     * @return La representación del Objeto en String.
     */
    @Override
    public String toString() {
        return "CURPDiferencias: {" +
                "\n\tcurpEsperado: \"" + curpEsperado + "\"," +
                "\n\tcurpGenerado: \"" + curpGenerado + "\"," +
                "\n\tdiferencias: [\"" + String.join("\", \"", diferencias) + "\"]" +
                "\n}";
    }
}


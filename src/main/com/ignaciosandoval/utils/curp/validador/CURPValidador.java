package com.ignaciosandoval.utils.curp.validador;

import com.ignaciosandoval.utils.curp.builder.CURPBuilder;
import com.ignaciosandoval.utils.curp.Persona;
import com.ignaciosandoval.utils.curp.builder.CURP;

/**
 * Clase utilizada para validar la CURP y mostrar las diferencias entre la CURP esperada y la CURP actual.
 *
 * @author iasandoval
 * @version 1.0
 */
public class CURPValidador {

    /**
     * La CURP esperada.
     */
    private String curpEsperado;

    /**
     * La CURP actual.
     */
    private String curpActual;

    /**
     * El Objeto Persona.
     */
    private Persona persona;

    /**
     * Constructor por Objeto Persona.
     *
     * @param persona El Objeto Persona.
     */
    public CURPValidador(final Persona persona) {
        this.persona = persona;
    }

    /**
     * Constructor por CURP Esperada y CURP Actual.
     *
     * @param curpEsperado La CURP Esperada.
     * @param curpActual   La CURP Actual.
     */
    public CURPValidador(final String curpEsperado, final String curpActual) {
        this.curpEsperado = curpEsperado;
        this.curpActual = curpActual;
    }

    /**
     * Método utilizado para validar las CURP proporcionadas.
     *
     * @return Objeto con las diferencias entre las CURP.
     */
    public CURPDiferencias validate() {
        if (this.persona != null) {
            return this.validatePersona();
        } else {
            return this.validateCURP();
        }
    }

    /**
     * Genera los Objetos CURP y compara para obtener las diferencias.
     *
     * @return Objeto con las diferencias entre las CURP.
     */
    private CURPDiferencias validateCURP() {
        CURP curpExpected = new CURPBuilder(this.curpEsperado).get();
        CURP curpActual = new CURPBuilder(this.curpActual).get();

        return this.validate(curpExpected, curpActual);
    }

    /**
     * Genera los Objetos CURP dado el Objeto Persona y compara para obtener las diferencias.
     *
     * @return Objeto con las diferencias entre las CURP.
     */
    private CURPDiferencias validatePersona() {
        CURP curpPersona = new CURPBuilder(this.persona.getCurp()).get();
        CURP curpGenerado = new CURPBuilder(this.persona).get();

        this.curpEsperado = curpPersona.get();
        this.curpActual = curpGenerado.get();

        CURPDiferencias curpDiferencias = this.validate(curpPersona, curpGenerado);

        if (!this.persona.isValid()) {
            curpDiferencias.getDiferencias().add(CURPDiferencias.DATOS_INSUFICIENTES);
        }

        return curpDiferencias;
    }

    /**
     * Obtiene el listado de diferencias entre la CURP esperada y la CURP Actual.
     *
     * @param esperado El Objeto CURP esperado.
     * @param actual   El Objeto CURP actual.
     * @return Objeto con las diferencias entre las CURP.
     */
    private CURPDiferencias validate(CURP esperado, CURP actual) {
        CURPDiferencias curpDiferencias = new CURPDiferencias(esperado.get(), actual.get());

        if (!esperado.isValid()) {
            curpDiferencias.getDiferencias().add(CURPDiferencias.ESPERADO_INVALIDO);
        } else if(!actual.isValid()) {
            curpDiferencias.getDiferencias().add(CURPDiferencias.GENERADO_INVALIDO);
        } else if (actual.isValid()) {
            if (!esperado.getIniciales().equals(actual.getIniciales())) {
                curpDiferencias.getDiferencias().add(CURPDiferencias.INICIALES);
            }

            if (!esperado.getFechaDeNacimiento().equals(actual.getFechaDeNacimiento())) {
                curpDiferencias.getDiferencias().add(CURPDiferencias.FECHA_DE_NACIMIENTO);
            }

            if (!esperado.getSexo().equals(actual.getSexo())) {
                curpDiferencias.getDiferencias().add(CURPDiferencias.SEXO);
            }

            if (!esperado.getEntidadFederativa().equals(actual.getEntidadFederativa())) {
                curpDiferencias.getDiferencias().add(CURPDiferencias.ENTIDAD_FEDERATIVA);
            }

            if (!esperado.getConsonantes().equals(actual.getConsonantes())) {
                curpDiferencias.getDiferencias().add(CURPDiferencias.CONSONANTES);
            }

            if (!esperado.getHomoclave().equals(actual.getHomoclave())) {
                curpDiferencias.getDiferencias().add(CURPDiferencias.HOMOCLAVE);
            }
        }

        if (esperado.isValid() && actual.isValid()
                && esperado.get16().equals(actual.get16())) {
            curpDiferencias.getDiferencias().add(CURPDiferencias.VALIDO);
        }

        return curpDiferencias;
    }

    /**
     * Getter de la CURP Esperada.
     *
     * @return La CURP Esperada.
     */
    public String getCurpEsperado() {
        return curpEsperado;
    }

    /**
     * Getter de la CURP Actual.
     *
     * @return La CURP Actual.
     */
    public String getCurpActual() {
        return curpActual;
    }

}

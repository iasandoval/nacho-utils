package com.ignaciosandoval.utils.curp.datos.vo;

/**
 * Clase que representa el Objeto Estado.
 *
 * @author iasandoval
 * @version 1.0
 */
public class Estado extends CatalogoAbstract {

    /**
     * La abreviatura del Estado.
     */
    private final String abreviatura;

    /**
     * Constructor por Clave, Descrpción y Abreviatura.
     *
     * @param clave       La Clave del Estado.
     * @param descripcion La Descripción del Estado.
     * @param abreviatura La Abreviatura del Estado.
     */
    public Estado(String clave, String descripcion, String abreviatura) {
        super(clave, descripcion);
        this.abreviatura = abreviatura;
    }

    /**
     * Getter de la Abreviatura del Estado.
     *
     * @return La Abreviatura del Estado.
     */
    public String getAbreviatura() {
        return abreviatura;
    }
}

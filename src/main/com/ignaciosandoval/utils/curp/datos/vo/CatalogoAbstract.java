package com.ignaciosandoval.utils.curp.datos.vo;

/**
 * Clase que representa un Objeto Abstracto para un Catálogo con Clave y Descripción.
 *
 * @author iasandoval
 * @version 1.0
 */
abstract class CatalogoAbstract {

    /**
     * La clave del catálogo.
     */
    private final String clave;

    /**
     * La descripción del catálogo.
     */
    private final String descripcion;

    /**
     * Constructor por Clave y Descripción.
     *
     * @param clave       La Clave.
     * @param descripcion La Descripción.
     */
    public CatalogoAbstract(String clave, String descripcion) {
        this.clave = clave;
        this.descripcion = descripcion;
    }

    /**
     * Getter de la Clave.
     *
     * @return La Clave.
     */
    public String getClave() {
        return clave;
    }

    /**
     * Getter de la Descripción.
     *
     * @return La Descripción.
     */
    public String getDescripcion() {
        return descripcion;
    }
}

package com.ignaciosandoval.utils.curp;

import com.ignaciosandoval.utils.curp.datos.vo.Estado;
import com.ignaciosandoval.utils.curp.datos.vo.Sexo;

import java.time.LocalDate;

/**
 * Clase que representa un Objeto Persona.
 * <p>
 * Esa clase es utilizada para generar la CURP basada en los datos de una Persona.
 *
 * @author iasandoval
 * @version 1.0
 */
public class Persona {

    /**
     * Primer Apellido de la Persona.
     */
    private String primerApellido;

    /**
     * Segundo Apellido de la Persona.
     */
    private String segundoApellido;

    /**
     * Nombre(s) de la Persona.
     */
    private String nombre;

    /**
     * Fecha de Nacimiento de la Persona.
     */
    private LocalDate fechaDeNacimiento;

    /**
     * Sexo de la Persona (Hombre o Mujer).
     */
    private Sexo sexo;

    /**
     * Entidad Federativa (Estado) de Nacimiento de la Persona.
     */
    private Estado estado;

    /**
     * La Clave Única de Registro de Población de la Persona.
     */
    private String curp;

    /**
     * Constructor por Defecto.
     */
    public Persona() {
        this.primerApellido = "";
        this.segundoApellido = "";
        this.nombre = "";
        this.curp = "";
    }

    /**
     * Getter del Primer Apellido de la Persona.
     *
     * @return El Primer Apellido de la Persona.
     */
    public String getPrimerApellido() {
        return primerApellido;
    }

    /**
     * Setter del Primer Apellido de la Persona.
     *
     * @param primerApellido El Primer Apellido de la Persona.
     */
    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    /**
     * Getter del Segundo Apellido de la Persona.
     *
     * @return El Segundo Apellido de la Persona.
     */
    public String getSegundoApellido() {
        return segundoApellido;
    }

    /**
     * Setter del Segundo Apellido de la Persona.
     *
     * @param segundoApellido El Segundo Apellido de la Persona.
     */
    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    /**
     * Getter del Nombre de la Persona.
     *
     * @return El Nombre de la Persona.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter del Nombre de la Persona.
     *
     * @param nombre El Nombre de la Persona.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Getter de la Fecha de Nacimiento de la Persona.
     *
     * @return La Fecha de Nacimiento de la Persona.
     */
    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    /**
     * Setter de la Fecha de Nacimiento de la Persona.
     *
     * @param anio El año de nacimiento de la Persona.
     * @param mes  El mes de nacimiento de la Persona.
     * @param dia  El día de nacimiento de la Persona.
     */
    public void setFechaDeNacimiento(int anio, int mes, int dia) {
        this.fechaDeNacimiento = LocalDate.of(anio, mes, dia);
    }

    /**
     * Getter del Sexo de la Persona.
     *
     * @return El Sexo de la Persona.
     */
    public Sexo getSexo() {
        return sexo;
    }

    /**
     * Setter del Sexo de la Persona.
     *
     * @param sexo El Sexo de la Persona.
     */
    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    /**
     * Getter del Estado de Nacimiento de la Persona.
     *
     * @return El Estado de Nacimiento de la Persona.
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Setter del Estado de Nacimiento de la Persona.
     *
     * @param estado El Estado de Nacimiento de la Persona.
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /**
     * Getter de la Clave Única de Registro de Población (CURP) de la Persona.
     *
     * @return La Clave Única de Registro de Población (CURP) de la Persona.
     */
    public String getCurp() {
        return curp;
    }

    /**
     * Setter de la Clave Única de Registro de Población (CURP) de la Persona.
     *
     * @param curp La Clave Única de Registro de Población (CURP) de la Persona.
     */
    public void setCurp(String curp) {
        this.curp = curp;
    }

    /**
     * Indica si todos los datos de la persona fueron proporcionados para generar la CURP.
     * <p>
     * Nota: El Segundo Apellido es Opcional.
     *
     * @return True, si todos los datos de la persona fueron proporcionados para generar la CURP.
     */
    public boolean isValid() {
        boolean hasPrimerApellido = this.primerApellido != null && this.primerApellido.length() > 0;
        boolean hasNombre = this.nombre != null && this.nombre.length() > 0;
        boolean hasFechaDeNacimiento = this.fechaDeNacimiento != null;
        boolean hasSexo = this.sexo != null;
        boolean hasEstado = this.estado != null;

        return hasPrimerApellido && hasNombre && hasFechaDeNacimiento && hasSexo && hasEstado;
    }

    /**
     * Muestra la representación de la Persona en String.
     *
     * @return La representación de la Persona en String.
     */
    @Override
    public String toString() {
        return "Persona: {" +
                "\n\tvalid: \"" + this.isValid() + "\", " +
                "\n\tprimerApellido: \"" + primerApellido + "\", " +
                "\n\tsegundoApellido: \"" + segundoApellido + "\", " +
                "\n\tnombre: \"" + nombre + "\", " +
                "\n\tfechaDeNacimiento: \"" + (fechaDeNacimiento != null ? fechaDeNacimiento.toString() : "") + "\", " +
                "\n\tsexo: \"" + (sexo != null ? sexo.getDescripcion() : "") + "\", " +
                "\n\testado: \"" + (estado != null ? estado.getDescripcion() : "") + "\"," +
                "\n\tcurp: \"" + curp + "\"" +
                "\n}";
    }
}

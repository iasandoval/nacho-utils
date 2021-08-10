package com.ignaciosandoval.utils.curp.builder;

/**
 * Clase que representa la Clave Única de Registro de Población (CURP) y sus meta datos.
 *
 * @author iasandoval
 * @version 1.0
 * @see <a href="http://www.ordenjuridico.gob.mx/Federal/PE/APF/APC/SEGOB/Instructivos/InstructivoNormativo.pdf" target="_blank">INSTRUCTIVO Normativo para la asignación de la Clave Única de Registro de Población (PDF - Ver. 2006).</a>
 */
public class CURP {

    /**
     * Longitud mínima de la CURP.
     */
    public static final int SIZE_16 = 16;

    /**
     * Longitud máxima de la CURP.
     */
    public static final int SIZE_18 = 18;

    /**
     * Expresión regular para validar la CURP.
     * <p>
     * Primer Apellido
     * Inicial:                        [A-Z]
     * Primer Vocal Interna:           [AEIOUX]
     * Inicial Segundo Apellido
     * e Inicial Nombre:               [A-Z]{2}
     * Fecha de Nacimiento
     * Año, dos dígitos:               \d{2}
     * Mes, dos dígitos:               (0[1-9]|1[0-2])
     * Día, dos dígitos:               (0[1-9]|[12]\d|3[01])
     * Indicador de Sexo:              [HM]
     * Entidad Federativa:             (NE|AS|BC|BS|CC|CL|CM|CS|CH|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS)
     * Tres consonantes:               [^AEIOU]{3}
     * Opcional
     * Diferenciador de homonimia:     [A-Z0-9]
     * Dígito Verificador:             \d
     */
    private static final String CURP_PATTERN = "^([A-Z][AEIOUX][A-Z]{2}\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])[HM](NE|AS|BC|BS|CC|CL|CM|CS|CH|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NA|SI)[^AEIOU]{3}([A-Z0-9]\\d)?)$";

    /**
     * Representa el primer segmento de la CURP, formado por las iniciales del Primer Apellido,
     * Segundo Apellido y Primer Nombre.
     */
    private String iniciales;

    /**
     * Representa el segundo segmento de la CURP, formado por la fecha de nacimiento
     * en formato AAMMDD (Año, Mes, Día).
     */
    private String fechaDeNacimiento;

    /**
     * Representa el tercer segmento de la CURP, formado por el sexo de la persona.
     * H para Hombre y M para Mujer.
     */
    private String sexo;

    /**
     * Representa el cuarto segmento de la CURP, formado por la abreviatura de la
     * Entidad Federativa de nacimiento (Ver ANEXO 4).
     *
     * @see <a href="http://www.ordenjuridico.gob.mx/Federal/PE/APF/APC/SEGOB/Instructivos/InstructivoNormativo.pdf" target="_blank">INSTRUCTIVO Normativo para la asignación de la Clave Única de Registro de Población (PDF - Ver. 2006).</a>
     */
    private String entidadFederativa;

    /**
     * Representa el quinto segmento de la CURP, formado por las consonantes internas
     * del Primer Apellido, Segundo Apellido y Primer Nombre.
     */
    private String consonantes;

    /**
     * Representa el último segmento del CURP, formado por el Diferenciador de Homonimia
     * y el Dígito Verificador.
     * <p>
     * Estos dos últimos caracteres no son generados y son proporcionados por
     * la <a href="https://www.gob.mx/segob/renapo" target="_blank">RENAPO</a>.
     */
    private String homoclave;

    /**
     * Constructor por defecto.
     * <p>
     * Solo puede ser instanciado por {@link CURPBuilder}.
     */
    CURP() {
        this.iniciales = "";
        this.fechaDeNacimiento = "";
        this.sexo = "";
        this.entidadFederativa = "";
        this.consonantes = "";
        this.homoclave = "";
    }

    /**
     * Getter para las iniciales de la CURP.
     *
     * @return Las iniciales de la CURP.
     */
    public String getIniciales() {
        return iniciales;
    }

    /**
     * Setter para las iniciales de la CURP.
     *
     * @param iniciales Las Iniciales de la CURP.
     */
    void setIniciales(String iniciales) {
        this.iniciales = iniciales;
    }

    /**
     * Getter para la Fecha de Nacimiento en formato AAMMDD.
     *
     * @return La Fecha de Nacimiento en formato AAMMDD.
     */
    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    /**
     * Setter para la Fecha de Nacimiento en formato AAMMDD.
     *
     * @param fechaDeNacimiento La Fecha de Nacimiento en formato AAMMDD.
     */
    void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    /**
     * Getter para la clave del Sexo (H o M).
     *
     * @return Clave del Sexo (H ó M).
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Setter para la clave del Sexo (H o M).
     *
     * @param sexo La clave del Sexo (H o M).
     */
    void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * Getter para la abreviatura de la Entidad Federativa.
     *
     * @return Abreviatura de la Entidad Federativa.
     */
    public String getEntidadFederativa() {
        return entidadFederativa;
    }

    /**
     * Setter para la abreviatura de la Entidad Federativa.
     *
     * @param entidadFederativa La abreviatura de la Entidad Federativa.
     */
    void setEntidadFederativa(String entidadFederativa) {
        this.entidadFederativa = entidadFederativa;
    }

    /**
     * Getter para las Consonantes de la CURP.
     *
     * @return Las Consonantes de la CURP.
     */
    public String getConsonantes() {
        return consonantes;
    }

    /**
     * Setter para las Consonantes de la CURP.
     *
     * @param consonantes Las Consonantes de la CURP.
     */
    void setConsonantes(String consonantes) {
        this.consonantes = consonantes;
    }

    /**
     * Getter para la Homoclave de la CURP.
     *
     * @return La homoclave de la CURP.
     */
    public String getHomoclave() {
        return homoclave;
    }

    /**
     * Método Setter para la Homoclave del CURP.
     *
     * @param homoclave La Homoclave del CURP.
     */
    void setHomoclave(String homoclave) {
        this.homoclave = homoclave;
    }

    /**
     * Este método sirve para validar la CURP proporcionada para ver si cumple con la Longitud (CURP16 o CURP18)
     * y con la expresión regular.
     *
     * @param curp El CURP a validar.
     * @return True, si la CURP proporcionado cumple con las características.
     */
    public static boolean isValid(final String curp) {
        // CURP solo puede ser de 16 o 18 caracteres
        boolean curpSize = curp != null && (curp.length() == SIZE_16 || curp.length() == SIZE_18);
        // Patrón del CURP
        boolean matchPattern = curp != null && curp.matches(CURP_PATTERN);

        // TODO Validar Diferenciador de homonimia
        // 1-9 para fechas de nacimiento hasta el año 1999
        // A-Z para fechas de nacimiento a partir de 2000

        return curpSize && matchPattern;
    }

    /**
     * Este método sirve para validar la Longitud (CURP16 o CURP18) y la expresión regular de la CURP.
     *
     * @return True, si la CURP proporcionado cumple con la longitud y la expresión regular.
     */
    public boolean isValid() {
        return CURP.isValid(this.get());
    }

    /**
     * Regresa la CURP formada por todos sus segmentos.
     *
     * @return Si la CURP contiene Homoclave, regresará CURP18, si no, regresará CURP16.
     */
    public String get() {
        return this.iniciales +
                this.fechaDeNacimiento +
                this.sexo +
                this.entidadFederativa +
                this.consonantes +
                this.homoclave;
    }

    /**
     * Regresa la CURP formada por sus segmentos, excluyendo la homoclave.
     *
     * @return CURP en formato CURP16.
     */
    public String get16() {
        return this.iniciales +
                this.fechaDeNacimiento +
                this.sexo +
                this.entidadFederativa +
                this.consonantes;
    }

    /**
     * Muestra el Objeto CURP.
     *
     * @return El Objeto CURP.
     */
    @Override
    public String toString() {
        if (this.isValid()) {
            return "CURP: {" +
                    "\n\tcurp: \"" + this.get() + "\"," +
                    "\n\tisValid: " + this.isValid() + "," +
                    "\n\tsize: " + this.get().length() + "," +
                    "\n\tiniciales: \"" + this.iniciales + "\"," +
                    "\n\tfechaDeNacimiento: \"" + this.fechaDeNacimiento + "\"," +
                    "\n\tsexo: \"" + this.sexo + "\"," +
                    "\n\tentidadFederativa: \"" + this.entidadFederativa + "\"," +
                    "\n\tconsonantes: \"" + this.consonantes + "\"," +
                    "\n\thomoclave: \"" + this.homoclave + "\"" +
                    "\n}";
        } else {
            return "CURP: {" +
                    "\n\tisValid: " + this.isValid() +
                    "\n}";
        }
    }
}

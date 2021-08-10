package com.ignaciosandoval.utils.curp.builder;

import com.ignaciosandoval.utils.curp.Persona;

import java.time.format.DateTimeFormatter;

import static com.ignaciosandoval.utils.curp.datos.Caracteres.CARACTERES_DICCIONARIO;
import static com.ignaciosandoval.utils.curp.datos.Caracteres.VOCALES;
import static com.ignaciosandoval.utils.curp.datos.PalabrasEliminar.PALABRAS_A_ELIMINAR;
import static com.ignaciosandoval.utils.curp.datos.PalabrasInconvenientes.PALABRAS_INCONVENIENTES;

/**
 * Clase utilizada para construir la Clave Única de Registro de Población (CURP).
 *
 * @author iasandoval
 * @version 1.0
 * @see <a href="http://www.ordenjuridico.gob.mx/Federal/PE/APF/APC/SEGOB/Instructivos/InstructivoNormativo.pdf" target="_blank">INSTRUCTIVO Normativo para la asignación de la Clave Única de Registro de Población (PDF - Ver. 2006).</a>
 */
public final class CURPBuilder {

    /**
     * Formato de Fecha de Nacimiento (yyMMDD).
     */
    private static final String FECHA_NACIMIENTO_FORMAT = "yyMMdd";

    /**
     * La Clave Única de Registro de POblación (CURP).
     */
    private final CURP curp;

    /**
     * Constructor por Persona.
     *
     * @param persona Objeto Persona para generar la CURP.
     */
    public CURPBuilder(final Persona persona) {
        this.curp = this.buildCurp(persona);
    }

    /**
     * Constructor pot String.
     *
     * @param curpString String representando la CURP.
     */
    public CURPBuilder(final String curpString) {
        this.curp = this.buildCurp(curpString);
    }

    /**
     * Gets el Objeto CURP generado.
     *
     * @return El Objeto CURP generado.
     */
    public CURP get() {
        return this.curp;
    }

    /**
     * Construye el Objeto CURP dada una Persona.
     *
     * @param persona El Objeto Persona para generar la CURP.
     * @return La CURP dada una Persona.
     */
    private CURP buildCurp(final Persona persona) {

        if (persona != null && persona.isValid()) {
            CURP curp = this.buildLetras(persona);
            String fechaDeNacimiento = this.buildFechaDeNacimiento(persona);
            String sexo = this.buildSexo(persona);
            String entidadFederativa = this.buildEntidadFederativa(persona);

            curp.setFechaDeNacimiento(fechaDeNacimiento);
            curp.setSexo(sexo);
            curp.setEntidadFederativa(entidadFederativa);

            return curp;
        }

        return new CURP();
    }

    /**
     * Construye el Objeto CURP dado un String.
     *
     * @param curp La CURP en String.
     * @return La CURP dada un String.
     */
    private CURP buildCurp(final String curp) {

        if (CURP.isValid(curp)) {
            return this.stripCURP(curp);
        }

        return new CURP();
    }

    /**
     * Desglosa la CURP proporcionada en String para generar el Objeto CURP.
     *
     * @param curpString La CURP en String.
     * @return Objeto CURP dado un String.
     */
    private CURP stripCURP(final String curpString) {

        StringBuilder curpSB = new StringBuilder(curpString);

        // 1-4 Iniciales
        String iniciales = curpSB.substring(0, 4);
        // 5-10 Fecha de nacimiento
        String fechaDeNacimiento = curpSB.substring(4, 10);
        // 11 Sexo
        String sexo = curpSB.substring(10, 11);
        // 12-13 Entidad Federativa
        String entidadFederativa = curpSB.substring(11, 13);
        // 14-16 Consonantes
        String consonantes = curpSB.substring(13, 16);

        String homoclave = "";
        if (curpSB.length() == CURP.SIZE_18) {
            // 17-18 Homoclave
            homoclave = curpSB.substring(16, 18);
        }

        // Crea el Objeto CURP
        CURP curp = new CURP();
        curp.setIniciales(iniciales);
        curp.setFechaDeNacimiento(fechaDeNacimiento);
        curp.setSexo(sexo);
        curp.setEntidadFederativa(entidadFederativa);
        curp.setConsonantes(consonantes);
        curp.setHomoclave(homoclave);

        return curp;
    }

    /**
     * Construye las Iniciales y Consonantes de la CURP.
     * <p>
     * Iniciales: La letra inicial y la primera vocal interna del primer apellido, la letra
     * inicial del segundo apellido y la primera letra del nombre.
     * <p>
     * Consonantes: Integradas por las primeras consonantes internas del primer
     * apellido, segundo apellido y nombre.
     *
     * @param persona El Objeto Persona a obtener las Iniciales y Consonantes.
     * @return Objeto CURP con Iniciales y Consonantes.
     */
    private CURP buildLetras(final Persona persona) {
        // Inicial del primer apellido
        String primerApellido = this.cleanName(persona.getPrimerApellido());
        char inicialPrimerApellido = this.cleanCharacter(primerApellido.charAt(0));

        // Primer Vocal del primer apellido
        char primerVocal = this.findFirstVowel(primerApellido);

        // Inicial del segundo apellido
        String segundoApellido = this.cleanName(persona.getSegundoApellido());
        // Si no hay segundo apellido, asignar X
        segundoApellido = segundoApellido.length() > 0 ? segundoApellido : "X";
        char inicialSegundoApellido = this.cleanCharacter(segundoApellido.charAt(0));

        // Inicial del nombre
        String nombre = this.cleanName(persona.getNombre());
        char inicialNombre = this.cleanCharacter(nombre.charAt(0));

        String iniciales = String.valueOf(inicialPrimerApellido) +
                primerVocal +
                inicialSegundoApellido +
                inicialNombre;

        String cleanIniciales = this.cleanIniciales(iniciales);

        // Build Consonantes
        char consonantePrimerApellido = this.findFirstConsonant(primerApellido);
        char consonanteSegundoApellido = this.findFirstConsonant(segundoApellido);
        char consonanteNombre = this.findFirstConsonant(nombre);

        String consonantes = String.valueOf(consonantePrimerApellido) +
                consonanteSegundoApellido +
                consonanteNombre;

        CURP curp = new CURP();
        curp.setIniciales(cleanIniciales);
        curp.setConsonantes(consonantes);

        return curp;
    }

    /**
     * Construye la Fecha de Nacimiento en formato YYMMDD (Año, Mes, Día).
     *
     * @param persona El Objeto Persona a obtener la Fecha de Nacimiento.
     * @return La Fecha de Nacimiento en formato YYMMDD (Ex. 910120).
     */
    private String buildFechaDeNacimiento(final Persona persona) {
        return persona.getFechaDeNacimiento()
                .format(DateTimeFormatter.ofPattern(FECHA_NACIMIENTO_FORMAT));
    }

    /**
     * Construye el Sexo (Hombre o Mujer).
     *
     * @param persona El Objeto Persona a obtener el Sexo (Hombre o Mujer).
     * @return H para Hombre o M para Mujer.
     * @see com.ignaciosandoval.utils.curp.datos.Sexos
     */
    private String buildSexo(final Persona persona) {
        return persona.getSexo().getClave();
    }

    /**
     * Construye la Abreviatura de la Entidad Federativa de Nacimiento.
     *
     * @param persona El Objeto Persona a obtener la Entidad Federativa de Nacimiento.
     * @return La Abreviatura de la Entidad Federativa de Nacimiento.
     * @see com.ignaciosandoval.utils.curp.datos.Estados
     */
    private String buildEntidadFederativa(final Persona persona) {
        return persona.getEstado().getAbreviatura();
    }

    /**
     * Busca la primer vocal interna.
     *
     * @param text Apellido o Nombre a buscar la primera vocal interna.
     * @return La primer vocal interna, si no hay vocales, regresa 'X'.
     */
    private char findFirstVowel(String text) {
        // Si no hay vocales, se usa X
        char vowel = 'X';

        // Se empieza a partir de la segunda letra
        for (int i = 1; i < text.length(); i++) {
            char letter = this.cleanCharacter(text.charAt(i));

            if (VOCALES.contains(letter)) {
                return letter;
            }
        }

        return vowel;
    }

    /**
     * Buscar la primer consonante interna.
     *
     * @param text Apellido o Nombre a buscar la primera consonante interna.
     * @return La primer consonante interna, si no hay consonantes, regresa 'X'.
     */
    private char findFirstConsonant(String text) {
        // Si no hay consonantes, se usa X
        char consonant = 'X';

        for (int i = 1; i < text.length(); i++) {
            char letter = this.cleanCharacter(text.charAt(i));

            if (!VOCALES.contains(letter)) {
                return letter;
            }
        }
        return consonant;
    }

    /**
     * Reemplaza el carácter dependiendo de las reglas para generar la CURP.
     * <p>
     * Elimina los acentos o diéresis (¨) de las Vocales (Ex. 'Á', 'À' o 'Ä' pasará a "A").
     * <p>
     * Si el carácter es Ñ, o si aparecieran caracteres especiales como diagonal (/), guión (-), o punto (.),
     * se asignará una "X".
     *
     * @param dirtyChar Carácter a limpiar.
     * @return Carácter limpio.
     * @see com.ignaciosandoval.utils.curp.datos.Caracteres#CARACTERES_DICCIONARIO
     */
    private char cleanCharacter(char dirtyChar) {
        if (CARACTERES_DICCIONARIO.containsKey(dirtyChar)) {
            // Swap with clean CHARACTER
            return CARACTERES_DICCIONARIO.get(dirtyChar);
        }

        return dirtyChar;
    }

    /**
     * Método recursivo para eliminar las palabras de un Nombre o Apellido de la Persona.
     * <p>
     * 1. Elimina espacios, tabuladores y retornos al inicio y al final.
     * 2. Si el nombre es compuesto, elimina las primeras palabras que coincidan con las "Palabras a Eliminar".
     * <p>
     * Palabras a Eliminar:
     * <p>
     * Cuando alguno de los apellidos o nombre es compuesto y la primera palabra de esta composición es una preposición,
     * conjunción, contracción (DA, DAS, DE, DEL, DER, DI, DIE, DD, EL, LA, LOS, LAS, LE, LES, MAC, MC, VAN, VON, Y). Se
     * elimina la primera palabra y utiliza la siguiente del apellido o nombre.
     * <p>
     * Cuando el nombre sea compuesto (formado por dos o más palabras), la clave se construye con la letra inicial de la
     * primera palabra, siempre que no sea MARIA, MA., MA, o JOSE, J, J. en cuyo caso se utilizará la segunda palabra.
     *
     * @param name Nombre o Apellido a limpiar.
     * @return El Nombre o Apellido Limpio.
     * @see com.ignaciosandoval.utils.curp.datos.PalabrasEliminar#PALABRAS_A_ELIMINAR
     */
    private String cleanName(String name) {
        // Elimina espacios, tabuladores y retornos al inicio y al final
        String nameTrim = (name != null ? name : "").toUpperCase().replaceAll("^\\s*", "");

        if (this.isCompuesto(nameTrim)) {
            // Separa el nombre en palabras
            String[] words = nameTrim.split("\\W+");

            // Elimina la primera palabra
            // que coincidan con PALABRAS_A_ELIMINAR
            if (PALABRAS_A_ELIMINAR.contains(words[0])) {
                return cleanName(nameTrim.replace(words[0], ""));
            }
        }

        return nameTrim;
    }

    /**
     * Método utilizado para determinar si un Nombre o Apellido es Compuesto.
     *
     * @param name Nombre o Apellido de la Persona.
     * @return True, si el Nombre o Apellido de la Persona es compuesto.
     */
    private boolean isCompuesto(String name) {
        return name.split("\\W+").length > 1;
    }

    /**
     * Limpia las iniciales generadas.
     * <p>
     * Si de las cuatro letras resulta una palabra altisonante. La segunda letra será sustituida por una "X".
     *
     * @param iniciales Iniciales generadas para la CURP.
     * @return Iniciales limpias, si resulta en una palabra altisonante.
     * @see com.ignaciosandoval.utils.curp.datos.PalabrasEliminar#PALABRAS_A_ELIMINAR
     */
    private String cleanIniciales(String iniciales) {
        if (PALABRAS_INCONVENIENTES.containsKey(iniciales)) {
            return PALABRAS_INCONVENIENTES.get(iniciales);
        }

        return iniciales;
    }

}

package com.ignaciosandoval.utils.curp.datos;

import com.ignaciosandoval.utils.curp.datos.vo.Estado;

/**
 * Clase que representa Catálogo de Entidades Federativas y sus abreviaturas (Ver ANEXO 4).
 *
 * @author iasandoval
 * @version 1.0
 * @see <a href="http://www.ordenjuridico.gob.mx/Federal/PE/APF/APC/SEGOB/Instructivos/InstructivoNormativo.pdf" target="_blank">INSTRUCTIVO Normativo para la asignación de la Clave Única de Registro de Población (PDF - Ver. 2006).</a>
 */
public final class Estados {

    public static final Estado NACIDO_EXTRANJERO = new Estado("00", "NACIDO EN EL EXTRANJERO", "NE");
    public static final Estado AGUASCALIENTES = new Estado("01", "AGUASCALIENTES", "AS");
    public static final Estado BAJA_CALIFORNIA = new Estado("02", "BAJA CALIFORNIA", "BC");
    public static final Estado BAJA_CALIFORNIA_SUR = new Estado("03", "BAJA CALIFORNIA SUR", "BS");
    public static final Estado CAMPECHE = new Estado("04", "CAMPECHE", "CC");
    public static final Estado COAHUILA = new Estado("05", "COAHUILA DE ZARAGOZA", "CL");
    public static final Estado COLIMA = new Estado("06", "COLIMA", "CM");
    public static final Estado CHIAPAS = new Estado("07", "CHIAPAS", "CS");
    public static final Estado CHIHUAHUA = new Estado("08", "CHIHUAHUA", "CH");
    public static final Estado CDMX = new Estado("09", "CIUDAD DE MÉXICO", "DF");
    public static final Estado DURANGO = new Estado("10", "DURANGO", "DG");
    public static final Estado GUANAJUATO = new Estado("11", "GUANAJUATO", "GT");
    public static final Estado GUERRERO = new Estado("12", "GUERRERO", "GR");
    public static final Estado HIDALGO = new Estado("13", "HIDALGO", "HG");
    public static final Estado JALISCO = new Estado("14", "JALISCO", "JC");
    public static final Estado MEXICO = new Estado("15", "MÉXICO", "MC");
    public static final Estado MICHOACAN = new Estado("16", "MICHOACÁN DE OCAMPO", "MN");
    public static final Estado MORELOS = new Estado("17", "MORELOS", "MS");
    public static final Estado NAYARIT = new Estado("18", "NAYARIT", "NT");
    public static final Estado NUEVO_LEON = new Estado("19", "NUEVO LEÓN", "NL");
    public static final Estado OAXACA = new Estado("20", "OAXACA", "OC");
    public static final Estado PUEBLA = new Estado("21", "PUEBLA", "PL");
    public static final Estado QUERETARO = new Estado("22", "QUERÉTARO", "QT");
    public static final Estado QUINTANA_ROO = new Estado("23", "QUINTANA ROO", "QR");
    public static final Estado SAN_LUIS_POTOSI = new Estado("24", "SAN LUIS POTOSÍ", "SP");
    public static final Estado SINALOA = new Estado("25", "SINALOA", "SL");
    public static final Estado SONORA = new Estado("26", "SONORA", "SR");
    public static final Estado TABASCO = new Estado("27", "TABASCO", "TC");
    public static final Estado TAMAULIPAS = new Estado("28", "TAMAULIPAS", "TS");
    public static final Estado TLAXCALA = new Estado("29", "TLAXCALA", "TL");
    public static final Estado VERACRUZ = new Estado("30", "VERACRUZ DE IGNACIO DE LA LLAVE", "VZ");
    public static final Estado YUCATAN = new Estado("31", "YUCATÁN", "YN");
    public static final Estado ZACATECAS = new Estado("32", "ZACATECAS", "ZS");

    /**
     * Constructor privado.
     */
    private Estados() {
        // Private Constructor
    }
}

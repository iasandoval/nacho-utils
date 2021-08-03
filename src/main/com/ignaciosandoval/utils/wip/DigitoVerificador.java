package com.ignaciosandoval.utils.wip;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase encargada de regresar el Dígito Verificador dependiendo el Banco.
 * @author iasandoval
 * @version 1.0
 */
public class DigitoVerificador {

    private static final int BANAMEX = 1;
    private static final int REF_LEN_BMX = 18;


    public String getDV() {
        int dv = 0;
        String dvs = "";

        //if (vo.getIdBanco() == BANAMEX) {
            dv = this.getDvBanamex(123456789,22226669945611L,"TEST");

            dvs = Integer.toString(dv);

            if (dv < 10) {
                dvs = "0" + dv;
            }
        //}

        return dvs;
    }

    /**
     * Método encargado de obtener el Digito Verificador del Banco Banamex
     *
     * @return Digito Verificador.
     */
    private int getDvBanamex(long numSucursal, long numCuenta, String numReferencia ) {

        //TODO: Hacer constantes
        int[] sucursal = {23, 29, 31, 37};
        int[] cuenta = {13, 17, 19, 23, 29, 31, 37};
        int[] referencia = {19, 23, 29, 31, 37, 1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37};
        int ponResiduo = 97;
        int ponResta = 99;

        int suc = this.getMultiplicaArray(sucursal, this.getIntArray(numSucursal));
        int cta = this.getMultiplicaArray(cuenta, this.getIntArray(numCuenta));
        int ref = this.getMultiplicaArray(referencia, this.getStringArray(numReferencia));

        int suma = suc + cta + ref;
        int residuo = suma % ponResiduo;
        int dv = ponResta - residuo;

        return dv;
    }

    /**
     * Método encargado de crear la tabla de referencia para Banamex
     * @return Mapa con la tabla de referencia
     */
    private Map<String, Integer> getTablaBanamex() {
        //TODO: Hacer constantes
        Map<String, Integer> tc = new HashMap<String, Integer>();
        tc.put("A", 2);
        tc.put("B", 2);
        tc.put("C", 2);
        tc.put("D", 3);
        tc.put("E", 3);
        tc.put("F", 3);
        tc.put("G", 4);
        tc.put("H", 4);
        tc.put("I", 4);
        tc.put("J", 5);
        tc.put("K", 5);
        tc.put("L", 5);
        tc.put("M", 6);
        tc.put("Ñ", 0);
        tc.put("N", 6);
        tc.put("0", 6);
        tc.put("P", 7);
        tc.put("Q", 7);
        tc.put("R", 7);
        tc.put("S", 8);
        tc.put("T", 8);
        tc.put("U", 8);
        tc.put("V", 9);
        tc.put("W", 9);
        tc.put("X", 9);
        tc.put("Y", 0);
        tc.put("Z", 0);
        tc.put("0", 0);
        tc.put("1", 1);
        tc.put("2", 2);
        tc.put("3", 3);
        tc.put("4", 4);
        tc.put("5", 5);
        tc.put("6", 6);
        tc.put("7", 7);
        tc.put("8", 8);
        tc.put("9", 9);

        return tc;
    }

    /**
     * Método encargado de Multiplicar dos arrays y sumar los resultados
     * @param a Arreglo 1
     * @param b Arreglo 2
     * @return Sumatoria del resultado de la multiplicación de los arreglos.
     */
    private int getMultiplicaArray(int a[], int b[]) {

        int valor = 0;

        //Comparar que los arrays sean iguales
        if (a.length == b.length) {

            int am[] = new int[a.length];

            for (int i = 0; i < a.length; i++) {
                int m = a[i] * b[i];

                valor += m;
            }
        }

        return valor;
    }

    /**
     * Método encargado de convertir un numero en un arreglo de enteros
     * @param numero a convertir en arreglo de numeros
     * @return Arreglo de numeros
     */
    private int[] getIntArray(long numero) {

        String n = String.valueOf(numero);

        char[] a = n.toCharArray();
        int[] b = new int[a.length];

        for (int i = 0; i < a.length; i++) {
            b[i] = Integer.parseInt("" + a[i]);
        }

        return b;
    }

    /**
     * Método encargado de crear un arreglo de enteros dada la referencia y
     * la tabla de referencia del banco Banamex.
     * @param referencia Referencia en bruto, sin ceros a la izquierda.
     * @return Arreglo de numeros.
     */
    private int[] getStringArray(String referencia) {
        Map<String, Integer> tablaConversion = this.getTablaBanamex();

        String codigo = referencia;

        while (codigo.length() < REF_LEN_BMX) {
            codigo = "0" + codigo;
        }

        char[] a = codigo.toCharArray();
        int[] b = new int[a.length];

        for (int i = 0; i < a.length; i++) {
            b[i] = tablaConversion.get("" + a[i]);
        }

        return b;
    }
}

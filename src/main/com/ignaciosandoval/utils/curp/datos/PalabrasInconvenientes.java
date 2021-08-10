package com.ignaciosandoval.utils.curp.datos;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase que representa el listado de palabras a eliminar.
 * <p>
 * Si de las cuatro letras resulta una palabra altisonante (Ver ANEXO 2). La segunda letra será sustituida por una "X".
 *
 * @author iasandoval
 * @version 1.0
 * @see <a href="http://www.ordenjuridico.gob.mx/Federal/PE/APF/APC/SEGOB/Instructivos/InstructivoNormativo.pdf" target="_blank">INSTRUCTIVO Normativo para la asignación de la Clave Única de Registro de Población (PDF - Ver. 2006).</a>
 */
public final class PalabrasInconvenientes {

    public static final Map<String, String> PALABRAS_INCONVENIENTES = new HashMap<>();

    static {
        PALABRAS_INCONVENIENTES.put("BACA", "BXCA");
        PALABRAS_INCONVENIENTES.put("BAKA", "BXKA");
        PALABRAS_INCONVENIENTES.put("BUEI", "BXEI");
        PALABRAS_INCONVENIENTES.put("BUEY", "BXEY");
        PALABRAS_INCONVENIENTES.put("CACA", "CXCA");
        PALABRAS_INCONVENIENTES.put("CACO", "CXCO");
        PALABRAS_INCONVENIENTES.put("CAGA", "CXGA");
        PALABRAS_INCONVENIENTES.put("CAGO", "CXGO");
        PALABRAS_INCONVENIENTES.put("CAKA", "CXKA");
        PALABRAS_INCONVENIENTES.put("CAKO", "CXKO");
        PALABRAS_INCONVENIENTES.put("COGE", "CXGE");
        PALABRAS_INCONVENIENTES.put("COGI", "CXGI");
        PALABRAS_INCONVENIENTES.put("COJA", "CXJA");
        PALABRAS_INCONVENIENTES.put("COJE", "CXJE");
        PALABRAS_INCONVENIENTES.put("COJI", "CXJI");
        PALABRAS_INCONVENIENTES.put("COJO", "CXJO");
        PALABRAS_INCONVENIENTES.put("COLA", "CXLA");
        PALABRAS_INCONVENIENTES.put("CULO", "CXLO");
        PALABRAS_INCONVENIENTES.put("FALO", "FXLO");
        PALABRAS_INCONVENIENTES.put("FETO", "FXTO");
        PALABRAS_INCONVENIENTES.put("GETA", "GXTA");
        PALABRAS_INCONVENIENTES.put("GUEI", "GXEI");
        PALABRAS_INCONVENIENTES.put("GUEY", "GXEY");
        PALABRAS_INCONVENIENTES.put("JETA", "JXTA");
        PALABRAS_INCONVENIENTES.put("JOTO", "JXTO");
        PALABRAS_INCONVENIENTES.put("KACA", "KXCA");
        PALABRAS_INCONVENIENTES.put("KACO", "KXCO");
        PALABRAS_INCONVENIENTES.put("KAGA", "KXGA");
        PALABRAS_INCONVENIENTES.put("KAGO", "KXGO");
        PALABRAS_INCONVENIENTES.put("KAKA", "KXKA");
        PALABRAS_INCONVENIENTES.put("KAKO", "KXKO");
        PALABRAS_INCONVENIENTES.put("KOGE", "KXGE");
        PALABRAS_INCONVENIENTES.put("KOGI", "KXGI");
        PALABRAS_INCONVENIENTES.put("KOJA", "KXJA");
        PALABRAS_INCONVENIENTES.put("KOJE", "KXJE");
        PALABRAS_INCONVENIENTES.put("KOJI", "KXJI");
        PALABRAS_INCONVENIENTES.put("KOJO", "KXJO");
        PALABRAS_INCONVENIENTES.put("KOLA", "KXLA");
        PALABRAS_INCONVENIENTES.put("KULO", "KXLO");
        PALABRAS_INCONVENIENTES.put("LILO", "LXLO");
        PALABRAS_INCONVENIENTES.put("LOCA", "LXCA");
        PALABRAS_INCONVENIENTES.put("LOCO", "LXCO");
        PALABRAS_INCONVENIENTES.put("LOKA", "LXKA");
        PALABRAS_INCONVENIENTES.put("LOKO", "LXKO");
        PALABRAS_INCONVENIENTES.put("MAME", "MXME");
        PALABRAS_INCONVENIENTES.put("MAMO", "MXMO");
        PALABRAS_INCONVENIENTES.put("MEAR", "MXAR");
        PALABRAS_INCONVENIENTES.put("MEAS", "MXAS");
        PALABRAS_INCONVENIENTES.put("MEON", "MXON");
        PALABRAS_INCONVENIENTES.put("MIAR", "MXAR");
        PALABRAS_INCONVENIENTES.put("MION", "MXON");
        PALABRAS_INCONVENIENTES.put("MOCO", "MXCO");
        PALABRAS_INCONVENIENTES.put("MOKO", "MXKO");
        PALABRAS_INCONVENIENTES.put("MULA", "MXLA");
        PALABRAS_INCONVENIENTES.put("MULO", "MXLO");
        PALABRAS_INCONVENIENTES.put("NACA", "NXCA");
        PALABRAS_INCONVENIENTES.put("NACO", "NXCO");
        PALABRAS_INCONVENIENTES.put("PEDA", "PXDA");
        PALABRAS_INCONVENIENTES.put("PEDO", "PXDO");
        PALABRAS_INCONVENIENTES.put("PENE", "PXNE");
        PALABRAS_INCONVENIENTES.put("PIPI", "PXPI");
        PALABRAS_INCONVENIENTES.put("PITO", "PXTO");
        PALABRAS_INCONVENIENTES.put("POPO", "PXPO");
        PALABRAS_INCONVENIENTES.put("PUTA", "PXTA");
        PALABRAS_INCONVENIENTES.put("PUTO", "PXTO");
        PALABRAS_INCONVENIENTES.put("QULO", "QXLO");
        PALABRAS_INCONVENIENTES.put("RATA", "RXTA");
        PALABRAS_INCONVENIENTES.put("ROBA", "RXBA");
        PALABRAS_INCONVENIENTES.put("ROBE", "RXBE");
        PALABRAS_INCONVENIENTES.put("ROBO", "RXBO");
        PALABRAS_INCONVENIENTES.put("RUIN", "RXIN");
        PALABRAS_INCONVENIENTES.put("SENO", "SXNO");
        PALABRAS_INCONVENIENTES.put("TETA", "TXTA");
        PALABRAS_INCONVENIENTES.put("VACA", "VXCA");
        PALABRAS_INCONVENIENTES.put("VAGA", "VXGA");
        PALABRAS_INCONVENIENTES.put("VAGO", "VXGO");
        PALABRAS_INCONVENIENTES.put("VAKA", "VXKA");
        PALABRAS_INCONVENIENTES.put("VUEI", "VXEI");
        PALABRAS_INCONVENIENTES.put("VUEY", "VXEY");
        PALABRAS_INCONVENIENTES.put("WUEI", "WXEI");
        PALABRAS_INCONVENIENTES.put("WUEY", "WXEY");
    }

    /**
     * Constructor privado.
     */
    private PalabrasInconvenientes() {
        // Private Constructor
    }
}

package com.ignaciosandoval.utils.test;

import com.ignaciosandoval.utils.curp.builder.CURPBuilder;
import com.ignaciosandoval.utils.curp.datos.Estados;
import com.ignaciosandoval.utils.curp.datos.Sexos;
import com.ignaciosandoval.utils.curp.validador.CURPDiferencias;
import com.ignaciosandoval.utils.curp.validador.CURPValidador;
import com.ignaciosandoval.utils.curp.builder.CURP;
import com.ignaciosandoval.utils.curp.Persona;
import org.junit.Assert;
import org.junit.Test;

public class CURPTest {

    @Test
    public void testCURPPersona() {
        Persona persona = new Persona();
        persona.setPrimerApellido("ÑANDO");
        persona.setSegundoApellido("DE LA O");
        persona.setNombre("JOSE MARIA");
        persona.setFechaDeNacimiento(1980, 12, 23);
        persona.setSexo(Sexos.HOMBRE);
        persona.setEstado(Estados.CDMX);

        CURP curp = new CURPBuilder(persona).get();

        System.out.println(persona);
        System.out.println(curp);

        Assert.assertTrue(curp.isValid());
    }

    @Test
    public void testCURPPersonaIncompleta() {
        Persona persona = new Persona();
        persona.setPrimerApellido("DE LA PUERTA");
        persona.setSegundoApellido("TORRES");
        persona.setNombre("OFELIA");
        persona.setFechaDeNacimiento(1990, 1, 5);
        persona.setSexo(Sexos.MUJER);
        //persona.setEstado(Estados.AGUASCALIENTES);

        CURP curp = new CURPBuilder(persona).get();

        System.out.println(persona);
        System.out.println(curp);

        Assert.assertFalse(curp.isValid());
    }

    @Test
    public void testValidCURPString() {
        String curpString = "PXTO900105MASRRF01";

        CURP curp = new CURPBuilder(curpString).get();
        System.out.println(curp);

        Assert.assertTrue(curp.isValid());
    }

    @Test
    public void testInvalidCURPString() {
        String curpString = "PEPE999999JSINAS1X";

        CURP curp = new CURPBuilder(curpString).get();
        System.out.println(curp);

        Assert.assertFalse(curp.isValid());
    }

    @Test
    public void testValidateCURPPersona() {
        Persona persona = new Persona();
        persona.setPrimerApellido("ÑANDO");
        persona.setSegundoApellido("DE LA O");
        persona.setNombre("JOSE MARIA");
        persona.setFechaDeNacimiento(1980, 12, 23);
        persona.setSexo(Sexos.HOMBRE);
        persona.setEstado(Estados.CDMX);
        persona.setCurp("XAOM801223HDFNXR01");

        CURPDiferencias curpDiferencias = new CURPValidador(persona).validate();

        System.out.println(curpDiferencias);
    }

    @Test
    public void testValidateCURPString() {
        CURPDiferencias curpDiferencias = new CURPValidador("XAOA801223HDFNXL00", "XAOA801223MDFNXL").validate();

        System.out.println(curpDiferencias);
    }
}





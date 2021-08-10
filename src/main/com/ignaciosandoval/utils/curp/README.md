## CURP Utils

Sorry, not in English.

### Referencias
1. [RENAPO](https://www.gob.mx/segob/renapo)
2. [Wikipedia - Clave Única de Registro de Población](https://es.wikipedia.org/wiki/Clave_%C3%9Anica_de_Registro_de_Poblaci%C3%B3n)
3. [INSTRUCTIVO Normativo para la asignación de la Clave Única de Registro de Población (DOF)](https://www.dof.gob.mx/nota_detalle.php?codigo=5526717&fecha=18/06/2018)
4. [INSTRUCTIVO Normativo para la asignación de la Clave Única de Registro de Población (PDF - Ver. 2006)](http://www.ordenjuridico.gob.mx/Federal/PE/APF/APC/SEGOB/Instructivos/InstructivoNormativo.pdf)

### Casos de Uso

1. **¿Tienes los datos de una Persona, pero no tienes la CURP?** Puedes Generar la CURP a 16 dígitos.
2. **¿Tienes los datos incompletos de una Persona, pero tienes una CURP válida?** Puedes complementar los datos de una
   persona dada su CURP, extrayendo Fecha de Nacimiento, Sexo y Entidad Federativa de Nacimiento.
3. **¿Los datos de la Persona no coinciden con la CURP?** Puedes generar la CURP a 16 dígitos y compararlas para ver
   las diferencias.
4. **¿Quieres realizar un proceso de limpieza y calidad de datos?** Estas utilerías te pueden ayudar.

**Nota:** La Homoclave no es generada, esta es asignada por la Secretaría de Gobernación.

### CURP Builder

* Version 1.0
* Su función es la de Generar la Clave Única de Registro de Población (CURP), dado como parámetro los datos de una Persona.
* La CURP es generada utilizando el Instructivo Normativo proporcionado por la Secretaría de Gobernación <sup>4</sup>.
* [Ver código fuente](builder/CURPBuilder.java).
* [Ver javadoc](https://iasandoval.github.io/nacho-utils/com/ignaciosandoval/utils/curp/builder/CURPBuilder.html).

**Ejemplo 1: Generar el Objeto CURP con los datos de una Persona**
```java
    Persona persona = new Persona();
    persona.setPrimerApellido("ÑANDO");
    persona.setSegundoApellido("DE LA O");
    persona.setNombre("JOSE MARIA");
    persona.setFechaDeNacimiento(1980, 12, 23);
    persona.setSexo(Sexos.HOMBRE);
    persona.setEstado(Estados.CDMX);

    CURP curp = new CURPBuilder(persona).get();
    
    System.out.println(curp);
```

**Ejemplo 1 - Resultando en:**
```json lines
    CURP: {
        curp: "XAOM801223HDFNXR",
        isValid: true,
        size: 16,
        iniciales: "XAOM",
        fechaDeNacimiento: "801223",
        sexo: "H",
        entidadFederativa: "DF",
        consonantes: "NXR",
        homoclave: ""
    }
```

**Ejemplo 2: Generar el Objeto CURP con datos incompletos de una Persona**

**Nota:** El Segundo Apellido es opcional, y se puede generar la CURP si es omitido.

```java
    Persona persona = new Persona();
    persona.setPrimerApellido("DE LA PUERTA");
    persona.setSegundoApellido("TORRES");
    persona.setNombre("OFELIA");
    persona.setFechaDeNacimiento(1990, 1, 5);
    persona.setSexo(Sexos.MUJER);
    // persona.setEstado(Estados.AGUASCALIENTES);

    CURP curp = new CURPBuilder(persona).get();
```

**Ejemplo 2 - Resultando en:**
```json lines
    CURP: {
      isValid: false
    }
```

**Ejemplo 3: Generar el Objeto CURP dada una cadena de texto válida (CURP Válida)**
```java
    String curpString = "PXTO900105MASRRF01";

    CURP curp = new CURPBuilder(curpString).get();
    System.out.println(curp);
```

**Ejemplo 3 - Resultando en:**
```json lines
    CURP: {
        curp: "PXTO900105MASRRF01",
        isValid: true,
        size: 18,
        iniciales: "PXTO",
        fechaDeNacimiento: "900105",
        sexo: "M",
        entidadFederativa: "AS",
        consonantes: "RRF",
        homoclave: "01"
    }
```

**Ejemplo 4: Generar el Objeto CURP dada una cadena de texto inválida (CURP inválida)**
```java
    String curpString = "PEPE999999JSINAS1X";

    CURP curp = new CURPBuilder(curpString).get();
    System.out.println(curp);
```

**Ejemplo 4 - Resultando en:**
```json lines
    CURP: {
        isValid: false
    }
```

### CURP Validador

* Version 1.0
* Encuentra las diferencias entre la CURP de una persona y sus datos.
* Encuentra las diferencias entre dos CURP proporcionadas.
* [Ver código fuente](validador/CURPValidador.java).
* [Ver javadoc](https://iasandoval.github.io/nacho-utils/com/ignaciosandoval/utils/curp/validador/CURPValidador.html).

**Ejemplo 1: Genera la CURP basada en los datos de la Persona, y lo compara con su CURP**
```java
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
```

**Ejemplo 1 - Resultando en:**
```json lines
    CURPDiferencias: {
        curpEsperado: "XAOM801223HDFNXR01",
        curpGenerado: "XAOM801223HDFNXR",
        diferencias: ["HOMOCLAVE", "CURP VALIDO"]
    }
```

**Ejemplo 2: Encuentra las diferencias entre 2 CURP**
```java
    CURPDiferencias curpDiferencias = new CURPValidador("XAOA801223HDFNXL00", "XAOA801223MDFNXL").validate();

    System.out.println(curpDiferencias);
```
**Ejemplo 2 - Resultando en:**
```json lines
    CURPDiferencias: {
        curpEsperado: "XAOA801223HDFNXL00",
        curpGenerado: "XAOA801223MDFNXL",
        diferencias: ["SEXO", "HOMOCLAVE"]
    }
```
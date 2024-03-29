# Nacho Utils

Read in [English](README.md).

## Acerca de

La idea de este repositorio, es la de publicar utilerías (Java) 
que he aplicado en proyectos reales, esperando que les 
puedan ser de "utilidad" xD.

Iré actualizando este proyecto poco a poco en mi tiempo libre.

# Utilerías

1. [Date of Birth](#date-of-birth).
2. [Social Security Number](#social-security-number).
3. [Encrypt Util](#encrypt-util).
4. [CURP Utils](#curp-utils).
5. [Contact Utils](#contact-utils). ** NEW **

## Date Utils

Read in [English](README.md#date-utils).

### Date of Birth

* Versión 1.0
* Esta es la primera utilería agregada al repositorio. 
* El objetivo es extraer "meta datos" de la fecha de nacimiento.

* [Ver código fuente](src/main/com/ignaciosandoval/utils/date/DateOfBirth.java).
* [Ver javadoc](https://iasandoval.github.io/nacho-utils/com/ignaciosandoval/utils/date/DateOfBirth.html).

**Ejemplo:**
```java
    DateOfBirth dob = new DateOfBirth(1983, 12, 3);
    System.out.println(dob.toString());
``` 

**Resultando en:**
```java
    DateOfBirth{
        dob=1983-12-03
        age=36
        day=3
        month=12
        year=1983
        monthText=December
        dayOfWeek=Saturday
        isWeekend=true
        isNewborn=false
        isChild=false
        isTeen=false
        isAdult=true
        isSenior=false
        category=ADULT
        ageRange=18+
        date=Sat Dec 03 00:00:00 EST 1983
        timestamp=1983-12-03 00:00:00.0
    }
```

## SSN Utils

Read in [English](README.md#ssn-utils).

### Social Security Number

* Versión 1.0
* Su función es la de validar y dar formato al número de seguro social de los Estados Unidos ("Social Security Number") 
o SSN.
* El método para validar el SSN utiliza la expresión regular "Over-The-Top Validation" publicada en el artículo
["Validating Social Security Numbers through Regular Expressions"](http://rion.io/2013/09/10/validating-social-security-numbers-through-regular-expressions-2/) de Rion Williams.

* [Ver código fuente](src/main/com/ignaciosandoval/utils/ssn/SocialSecurityNumber.java).
* [Ver javadoc](https://iasandoval.github.io/nacho-utils/com/ignaciosandoval/utils/ssn/SocialSecurityNumber.html).


**Ejemplo:**
```java
    SocialSecurityNumber ssn = new SocialSecurityNumber(52063811);
    System.out.println(ssn.toString());
```
**Resultando en:**
```
    SocialSecurityNumber{
        ssn=52063811
        isValid=true
        rawSSN=52063811
        stringSSN=052063811
        formattedSSN=052-06-3811
        maskedSSN=XXX-XX-3811
    }
```

## Security Utils

Read in [English](README.md#security-utils).

### Encrypt Util

* Versión 2.0 **Actualizado al 2021-05-05:**
* La Clase y la implementación has sido actualizada para permitir el Cifrado de texto y archivos.
* Ahora utiliza la transformación "Advanced Encryption Standard using the Galois/Counter Mode and No Padding" (AES/GCM/NoPadding).

* [Ver código fuente](src/main/com/ignaciosandoval/utils/security/EncryptUtil.java).
* [Ver javadoc](https://iasandoval.github.io/nacho-utils/com/ignaciosandoval/utils/security/EncryptUtil.html).

**Ejemplo 1: Cifrado de texto**
```java
    // Cifra el texto "nacho" con la llave "secret". 
    String encrypted = new EncryptUtil("secret").encrypt("nacho").toString();
    // Desencripta el texto con la llave "secret"
    String decrypted = new EncryptUtil("secret").decrypt(encrypted).toString();

    System.out.println("Encrypted Text: " + encrypted);
    System.out.println("Decrypted Text: " + decrypted);
```

**Ejemplo 1 - Resultado:**

**Nota:** El resultado puede variar, ya que se utiliza "sal" al realizar el cifrado.
```shell
    Encrypted Text: GDa5uPI/Sx6cH52xciJzqRg2ubjyP0senB+dsXIic6k9HVHPipG7GhzyuSS2sF5v0XOnOqY=
    Decrypted Text: nacho
```

**Ejemplo 2: Cifrar archivos**

**Nota:** Puedes pasar el mismo archivo para cifrarlo, pero este ejemplo muestra como un archivo es cifrado, manteniendo 
el archivo original y creando dos archivos nuevos: encriptado y desencriptado respectivamente.

```java
    // Mantener el archivo original
    File file = new File ("files/chicken.pdf");
    
    // Copia el archivo a Cifrar, agregando "_encrypted" al nombre del archivo.
    File fileEncrypted = new File ("files/chicken_encrypted.pdf");
    Files.copy(file.toPath(), fileEncrypted.toPath(), StandardCopyOption.REPLACE_EXISTING);
    
    // Cifra y escribe el archivo. Genera el archivo "files/chicken_encrypted.pdf". 
    new EncryptUtil("secret").encrypt(fileEncrypted).writeFile();

    // Copia el archivo a desencriptar, agregando "_decrypted" al nombre del archivo.
    File fileDecrypted = new File ("files/chicken_decrypted.pdf");
    Files.copy(fileEncrypted.toPath(), fileDecrypted.toPath(), StandardCopyOption.REPLACE_EXISTING);
    
    // Desencripta y escribe el archivo. Genera el archivo "files/chicken_decrypted.pdf".
    new EncryptUtil("secret").decrypt(fileDecrypted).writeFile();
```

**Ejemplo 2 - Resultado:**

* El código generará dos archivos, ```chicken_encrypted.pdf``` y ```chicken_decrypted.pdf```.

**Ejemplo 3: Cifrar un archivo en Base64**

```java
    // Archivo a cifrar
    File file = new File ("files/file.txt");
    
    // Cifra el archivo y regresa su representación en Base64
    String encrypted = new EncryptUtil("secret").encrypt(file).toString();
    
    // Desencripta el archivo en Base64
    String decrypted = new EncryptUtil("secret").decrypt(encrypted).toString();
    
    System.out.println("Encrypted File: " + encrypted);
    System.out.println("Decrypted File: " + decrypted);
```

**Ejemplo 3 - Resultado:**

**Nota:** El resultado puede variar, ya que se utiliza "sal" al realizar el cifrado.

```shell
Encrypted File: rDRwr8JVSUd/j0KK08xHcqw0cK/CVUlHf49CitPMR3IMFCJ7iUMfGlz9NPoAzNZnmwFtK16sim8AqlaXMT2jHP
                uunS8BVomNlRaGQP0c+D4PLAhfaDsHwdlbmjR9KoCIxND2kxCUySdoYx5+CnDDwactDvLU9RjVu9FLf/vdhBuM
                L4kR5cAhp1xq9MG9HfCDIX9E0oxAM27MFrzUlokeztTSDZ21O5AOy9lhaz8yq0OWF/FQCNuQj49hdK2XuBpTrE
                y07zKRgx+I83XuO2h53g==
Decrypted File: Pepe Pecas pica papas con un pico, con un pico pica papas Pepe Pecas.Si Pepe Pecas pica 
                papas con un pico, ¿dónde está el pico con que Pepe Pecas pica papas?
```

**Ejemplo 4: Obtener los bytes**

Si lo prefieres, puedes obtener los bytes ejecutando el método ```getBytes()```.
Es de utilidad, si la intención es la de realizar algo personalizado con los datos.

```java
    // Cifra el texto "nacho" con la llave "secret"
    byte[] encrypted = new EncryptUtil("secret").encrypt("nacho").getBytes();
    
    // Desencripta el texto con la llave "secret"
    byte[] decrypted = new EncryptUtil("secret").decrypt(encrypted).getBytes();
    
    System.out.println("Encrypted Data: " + encrypted);
    System.out.println("Decrypted Data: " + decrypted);
```

**Ejemplo 4 - Resultado:**

**Nota:** El resultado puede variar, ya que se muestra la referencia en memoria de los datos.
```shell
    Encrypted Data: [B@28ffffe0
    Decrypted Data: [B@754b13a8
```


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
* [Ver código fuente](src/main/com/ignaciosandoval/utils/curp/builder/CURPBuilder.java).
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
* [Ver código fuente](src/main/com/ignaciosandoval/utils/curp/validador/CURPValidador.java).
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

## Contact Utils

Read in [English](README.md#contact-utils).

### Email Address

* Versión 1.0
* Utilería para validar, dar formato, enmascarar, verificar y extraer metadatos de una Dirección de Correo Electrónico.
* [Ver código fuente](src/main/com/ignaciosandoval/utils/contact/EmailAddress.java).
* [Ver javadoc](https://iasandoval.github.io/nacho-utils/com/ignaciosandoval/utils/contact/EmailAddress.html).


**Ejemplo**
```java
    ContactMethod email = new EmailAddress("NACHO@nacho.com");

    System.out.println(email);
```

**Resultado:**
```json lines
    EmailAddress: {
        value: "NACHO@nacho.com",
        formatted: "nacho@nacho.com",
        userName: "nacho",
        domain: "nacho.com",
        masked: "na***@nacho.com",
        verified: false,
        isEmpty: false,
        isValid: true,
        type: "EmailAddress"
    }
```

### Phone Number

* Versión 1.0
* Utilería para validar, dar formato, enmascarar, verificar y extraer metadatos de un Número de Teléfono.
* [Ver código fuente](src/main/com/ignaciosandoval/utils/contact/PhoneNumber.java).
* [Ver javadoc](https://iasandoval.github.io/nacho-utils/com/ignaciosandoval/utils/contact/PhoneNumber.html).


**Ejemplo**
```java
    ContactMethod phone = new PhoneNumber("AX(123) 456  78-90 ALKSDJ");

    System.out.println(phone);
```

**Resultado:**
```json lines
    PhoneNumber: {
        value: "1234567890",
        formatted: "(123) 456-7890",
        areaCode: "123",
        masked: "(***) ***-7890",
        verified: false,
        isEmpty: false,
        isValid: true,
        type: "PhoneNumber"
    }
```

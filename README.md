# Nacho Utils

Leer en [Español](README_ES.md).

## About

The idea of this repository, is to publish utilities (Java) 
that I have used in real projects, hopping will be "util" for you xD.

I will be updating this project from time to time.
 
# Utilities

1. [Date of Birth](#date-of-birth).
2. [Social Security Number](#social-security-number).
3. [Encrypt Util](#encrypt-util).
4. [CURP Utils](#curp-utils).

## Date Utils

Leer en [Español](README_ES.md#date-utils).
 
### Date of Birth

* Version 1.0
* This is the first utility committed to the repository.
* Its function is to extract "metadata" from the Date of Birth.
 
* [See Source Code](src/main/com/ignaciosandoval/utils/date/DateOfBirth.java).
* [See javadoc](https://iasandoval.github.io/nacho-utils/com/ignaciosandoval/utils/date/DateOfBirth.html).

 
 **Example:**
 ```java
    DateOfBirth dob = new DateOfBirth(1983, 12, 3);
    System.out.println(dob.toString());
 ``` 
 
 **Result:**
 ```
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

Leer en [Español](README_ES.md#ssn-utils).

### Social Security Number

* Version 1.0
* The goal is to validate and format a US Social Security number (SSN).
* The validate method is using the "Over-The-Top Validation" regular expression explained in the article
["Validating Social Security Numbers through Regular Expressions"](http://rion.io/2013/09/10/validating-social-security-numbers-through-regular-expressions-2/) by Rion Williams.

* [See Source Code](src/main/com/ignaciosandoval/utils/ssn/SocialSecurityNumber.java).
* [See javadoc](https://iasandoval.github.io/nacho-utils/com/ignaciosandoval/utils/ssn/SocialSecurityNumber.html).


**Example:**
```java
    SocialSecurityNumber ssn = new SocialSecurityNumber(52063811);
    System.out.println(ssn.toString());
```
**Result:**
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

Leer en [Español](README_ES.md#security-utils).

### Encrypt Util

* Version 2.0 **Updated as of 2021-05-05:**
* Class and implementation refactor to allow Text or Files encryption/decryption.
* Now uses Advanced Encryption Standard using the Galois/Counter Mode and No Padding (AES/GCM/NoPadding) transformation.

* [See Source Code](src/main/com/ignaciosandoval/utils/security/EncryptUtil.java).
* [See javadoc](https://iasandoval.github.io/nacho-utils/com/ignaciosandoval/utils/security/EncryptUtil.html).

**Example 1: Encrypt/Decrypt Text**
```java
    // Encrypt text "nacho" using key "secret". 
    String encrypted = new EncryptUtil("secret").encrypt("nacho").toString();
    // Decrypt encrypted text using key "secret"
    String decrypted = new EncryptUtil("secret").decrypt(encrypted).toString();

    System.out.println("Encrypted Text: " + encrypted);
    System.out.println("Decrypted Text: " + decrypted);
```

**Example 1 - Result:**

**Note:** The result may vary, as the method uses "salt" while performing the encryption.
```shell
    Encrypted Text: GDa5uPI/Sx6cH52xciJzqRg2ubjyP0senB+dsXIic6k9HVHPipG7GhzyuSS2sF5v0XOnOqY=
    Decrypted Text: nacho
```

**Example 2: Encrypt/Decrypt Files**

**Note:** You can pass the same file to cipher it, but this example is to show how 
a File is Encrypted and Decrypted by keeping the original file and by creating their corresponding encrypted 
and decrypted files.
```java
    // Keep original file
    File file = new File ("files/chicken.pdf");
    
    // Copy file to Encrypt, adding "_encrypted" to the file name.
    File fileEncrypted = new File ("files/chicken_encrypted.pdf");
    Files.copy(file.toPath(), fileEncrypted.toPath(), StandardCopyOption.REPLACE_EXISTING);
    
    // Encrypt and Write File. This will create file "files/chicken_encrypted.pdf". 
    new EncryptUtil("secret").encrypt(fileEncrypted).writeFile();

    // Copy File to Decrypt, adding "_decrypted" to the file name.
    File fileDecrypted = new File ("files/chicken_decrypted.pdf");
    Files.copy(fileEncrypted.toPath(), fileDecrypted.toPath(), StandardCopyOption.REPLACE_EXISTING);
    
    // Decrypt File and Write File. This will create file "files/chicken_decrypted.pdf".
    new EncryptUtil("secret").decrypt(fileDecrypted).writeFile();
```

**Example 2 - Result:**

* The code will generate 2 files, ```chicken_encrypted.pdf``` and ```chicken_decrypted.pdf```.

**Example 3: Encrypt/Decrypt File to Base64**

```java
    // Text file to encrypt
    File file = new File ("files/file.txt");
    
    // Encrypt file and return its Base64 representation
    String encrypted = new EncryptUtil("secret").encrypt(file).toString();
    
    // Decrypt Base64 File
    String decrypted = new EncryptUtil("secret").decrypt(encrypted).toString();
    
    System.out.println("Encrypted File: " + encrypted);
    System.out.println("Decrypted File: " + decrypted);
```

**Example 3 - Result:**

**Note:** The result may vary, as the method uses "salt" while performing the encryption.

```shell
Encrypted File: rDRwr8JVSUd/j0KK08xHcqw0cK/CVUlHf49CitPMR3IMFCJ7iUMfGlz9NPoAzNZnmwFtK16sim8AqlaXMT2jHP
                uunS8BVomNlRaGQP0c+D4PLAhfaDsHwdlbmjR9KoCIxND2kxCUySdoYx5+CnDDwactDvLU9RjVu9FLf/vdhBuM
                L4kR5cAhp1xq9MG9HfCDIX9E0oxAM27MFrzUlokeztTSDZ21O5AOy9lhaz8yq0OWF/FQCNuQj49hdK2XuBpTrE
                y07zKRgx+I83XuO2h53g==
Decrypted File: Pepe Pecas pica papas con un pico, con un pico pica papas Pepe Pecas.Si Pepe Pecas pica 
                papas con un pico, ¿dónde está el pico con que Pepe Pecas pica papas?
```

**Example 4: Get data bytes**

If you prefer, you can get the encrypted/decrypted data calling the method ```getBytes()```.
This is helpful, if the intention is to perform some custom treatment to the data.

```java
    // Encrypt text "nacho" using key "secret"
    byte[] encrypted = new EncryptUtil("secret").encrypt("nacho").getBytes();
    
    // Decrypt encrypted text using key "secret"
    byte[] decrypted = new EncryptUtil("secret").decrypt(encrypted).getBytes();
    
    System.out.println("Encrypted Data: " + encrypted);
    System.out.println("Decrypted Data: " + decrypted);
```

**Example 4 - Result:**

**Note:** The result may vary, as this is printing the memory reference of the data.
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

Leer en [Español](README_ES.md#contact-utils).

### Email Address

* Version 1.0
* Utility class to Validate, Format, Mask, Verify and extract metadata from an Email Address.
* [See Source Code](src/main/com/ignaciosandoval/utils/contact/EmailAddress.java).
* [See javadoc](https://iasandoval.github.io/nacho-utils/com/ignaciosandoval/utils/contact/EmailAddress.html).


**Example**
```java
    ContactMethod email = new EmailAddress("NACHO@nacho.com");

    System.out.println(email);
```

**Result:**
```json
    EmailAddress: {
        value: 'NACHO@nacho.com',
        formatted: 'nacho@nacho.com',
        userName: 'nacho',
        domain: 'nacho.com',
        masked: 'na***@nacho.com',
        verified: false,
        isEmpty: false,
        isValid: true,
        type: 'EmailAddress'
    }
```

### Phone Number

* Version 1.0
* Utility class to Validate, Format, Mask, Verify and extract metadata from a Phone Number.
* [See Source Code](src/main/com/ignaciosandoval/utils/contact/PhoneNumber.java).
* [See javadoc](https://iasandoval.github.io/nacho-utils/com/ignaciosandoval/utils/contact/PhoneNumber.html).


**Example**
```java
    ContactMethod phone = new PhoneNumber("AX(123) 456  78-90 ALKSDJ");

    System.out.println(phone);
```

**Result:**
```json
    PhoneNumber: {
        value: '1234567890',
        formatted: '(123) 456-7890',
        areaCode: '123',
        masked: '(***) ***-7890',
        verified: false,
        isEmpty: false,
        isValid: true,
        type: 'PhoneNumber'
    }
```

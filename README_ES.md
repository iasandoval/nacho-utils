# Nacho Utils

[English](README.md)

## Acerca de

La idea de este repositorio, es la de publicar utilerías (Java) 
que he aplicado en proyectos reales, esperando que les 
puedan ser de "utilidad" xD.

Iré actualizando este proyecto poco a poco en mi tiempo libre.

## Utilerías

### Date of Birth
Esta es la primera utilería agregada al repositorio. 
Su función es la extraer "meta datos" de la fecha de nacimiento.

* [Ver código fuente](src/main/mx/com/nacho/utils/DateOfBirth.java).
* [Ver javadoc](https://iasandoval.github.io/nacho-utils/mx/com/nacho/utils/DateOfBirth.html).

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

### Social Security number
Segunda utilería agregada al repositorio. 
Su función es la validar y dar formato al número de seguro social de los 
Estados Unidos ("Social Security Number") o SSN.

El método para validar el SSN utiliza la expresion regular "Over-The-Top Validation" publicada en el artículo
["Validating Social Security Numbers through Regular Expressions"](http://rion.io/2013/09/10/validating-social-security-numbers-through-regular-expressions-2/) de Rion Williams.

* [Ver código fuente](src/main/mx/com/nacho/utils/SocialSecurityNumber.java).
* [Ver javadoc](https://iasandoval.github.io/nacho-utils/mx/com/nacho/utils/SocialSecurityNumber.html).


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

### EncryptUtil (Versión 2.0)])
**Actualizado al 2021-05-05:**
* La Clase y la implementación has sido actualizada para permitir el Cifrado de texto y archivos.
* Ahora utiliza la transformación "Advanced Encryption Standard using the Galois/Counter Mode and No Padding" (AES/GCM/NoPadding).

* [Ver código fuente](src/main/mx/com/nacho/utils/EncryptUtil.java).
* [Ver javadoc](https://iasandoval.github.io/nacho-utils/mx/com/nacho/utils/EncryptUtil.html).

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
Encrypted File: rDRwr8JVSUd/j0KK08xHcqw0cK/CVUlHf49CitPMR3IMFCJ7iUMfGlz9NPoAzNZnmwFtK16sim8AqlaXMT2jHPuunS8B
                VomNlRaGQP0c+D4PLAhfaDsHwdlbmjR9KoCIxND2kxCUySdoYx5+CnDDwactDvLU9RjVu9FLf/vdhBuML4kR5cAhp1xq
                9MG9HfCDIX9E0oxAM27MFrzUlokeztTSDZ21O5AOy9lhaz8yq0OWF/FQCNuQj49hdK2XuBpTrEy07zKRgx+I83XuO2h5
                3g==
Decrypted File: Pepe Pecas pica papas con un pico, con un pico pica papas Pepe Pecas.Si Pepe Pecas pica 
                papas con un pico, ¿dónde está el pico con que Pepe Pecas pica papas?
```

**Ejemplo 4: Obtener los bytes**

Si lo prefieres, puedes obtener los bytes ejecutando el método ```getBytes()```.
Es de utilidad, si tú intención es la de realizar algo personalizado con los datos.

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
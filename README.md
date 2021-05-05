# Nacho Utils

[Español](README_ES.md)

## About

The idea of this repository, is to publish utilities (Java) 
that I have used in real projects, hopping will be "util" for you xD.

I will be updating this project from time to time.
 
## Utilities
 
### Date of Birth
This is the first utility committed to the repository.
Its function is to extract "meta data" from the Date of Birth.
 
* [See Source Code](src/main/mx/com/nacho/utils/DateOfBirth.java).
* [See javadoc](https://iasandoval.github.io/nacho-utils/mx/com/nacho/utils/DateOfBirth.html).

 
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
 
### Social Security number
This is the second utility committed to the repository. 
Its functions is to validate and format a US Social Security number (SSN).

The validate method is using the "Over-The-Top Validation" regular expression explained in the article
["Validating Social Security Numbers through Regular Expressions"](http://rion.io/2013/09/10/validating-social-security-numbers-through-regular-expressions-2/) by Rion Williams.

* [See Source Code](src/main/mx/com/nacho/utils/SocialSecurityNumber.java).
* [See javadoc](https://iasandoval.github.io/nacho-utils/mx/com/nacho/utils/SocialSecurityNumber.html).


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

### EncryptUtil (Version 2.0)
**Updated as of 2021-05-05:**
* Class and implementation refactor to allow Text or Files encryption/decryption.
* Now uses Advanced Encryption Standard using the Galois/Counter Mode and No Padding (AES/GCM/NoPadding) transformation.

* [See Source Code](src/main/mx/com/nacho/utils/EncryptUtil.java).
* [See javadoc](https://iasandoval.github.io/nacho-utils/mx/com/nacho/utils/EncryptUtil.html).

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
This is helpful, if your intention is to perform some custom treatment to the data.

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
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

### EncryptUtil

* [See Source Code](src/main/mx/com/nacho/utils/EncryptUtil.java).
* [See javadoc](https://iasandoval.github.io/nacho-utils/mx/com/nacho/utils/EncryptUtil.html).

**Example (Tradicional)**
```java
    EncryptUtil enc = new EncryptUtil();
    enc.setKey("secret");

    String encrypted = enc.encrypt("nacho");
    String decrypted = enc.decrypt(encrypted);

    System.out.println(encrypted);
    System.out.println(decrypted);
```

**Example (Chained methods)**
```java
    String encrypted = new EncryptUtil("nacho").withKey("secret").encrypt();
    String decrypted = new EncryptUtil(encrypted).withKey("secret").decrypt();

    System.out.println(encrypted);
    System.out.println(decrypted);
```

**Result:**

**Note:** The result may vary, as the method uses "salt" while performing the encrypt. 
```java
    +Cytw3P82pOHEShZhQsRy5q8UhH/XKsSYW4fkeYQMRVTbQOVd7gGzNnD1z+P1FiB
    nacho
```
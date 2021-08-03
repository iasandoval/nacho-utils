## SSN Utils

Read in [English](README.md#ssn-utils).

### Social Security number

* Versión 1.0
* Su función es la de validar y dar formato al número de seguro social de los Estados Unidos ("Social Security Number")
  o SSN.
* El método para validar el SSN utiliza la expresión regular "Over-The-Top Validation" publicada en el artículo
  ["Validating Social Security Numbers through Regular Expressions"](http://rion.io/2013/09/10/validating-social-security-numbers-through-regular-expressions-2/) de Rion Williams.

* [Ver código fuente](SocialSecurityNumber.java).
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
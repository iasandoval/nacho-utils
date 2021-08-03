## Date Utils

Read in [English](README.md#date-utils).

### Date of Birth

* Versión 1.0
* Esta es la primera utilería agregada al repositorio.
* El objetivo es extraer "meta datos" de la fecha de nacimiento.

* [Ver código fuente](DateOfBirth.java).
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
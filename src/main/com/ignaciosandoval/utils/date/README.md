## Date Utils

Leer en [Español](README_ES.md#date-utils).

### Date of Birth

* Version 1.0
* This is the first utility committed to the repository.
* Its function is to extract "metadata" from the Date of Birth.

* [See Source Code](DateOfBirth.java).
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
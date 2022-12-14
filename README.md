# SQL Coding Challenge

## Steps

1. Check installation prerequisites: JDK version >=17, Maven latest version, your preferred IDE/editor. To see that everything basic is working, execute `mvn verify`, it should show you a `SUCCESS` build.
2. Read carefully through "Requirements" and "Out-of-scope" to focus on the right things.
3. IMPORTANT: After finishing coding, please add your comments and remarks inside this `README.md` file, see end of file. It helps us to gain context on how you reflect on the coding task.
4. Send back the result as mentioned inside the coding challenge mail.

Coding guidance: Combine "clean, object-oriented, tested code" with "working software".


##  Requirements

* Read and parse data from [`purchases.csv`](/src/main/resources/purchases.csv) and [`users.csv`](/src/main/resources/users.csv) and initialize to [`Table.java`](/src/main/java/exercise/Table.java)
* [`Table.java`](/src/main/java/exercise/Table.java) should be a general implementation, not specific to the data. Column names are inside .csv files and parsing should be based on this source.
* Implement an ORDER BY DESC ([SQL reference](http://www.w3schools.com/sql/sql_orderby.asp))
  * Input: 
    * the name of the column to order the rows
  * Output: the ordered table
* Implement an INNER JOIN of two tables ([SQL reference](https://www.w3schools.com/sql/sql_join.asp))
  * Input:
    * the name of the column to use for the join from the right table
    * the name of the column to use for the join from the left table
  * Output: the joined table
* Structure and design [`Table.java`](/src/main/java/exercise/Table.java)
  * Think about an appropriated domain model. Hint: the domain should not be purchases and users.
* You are not restricted to implement everything in the existing file/folder structure. Feel free to add or alter files.
* Feel free to add Maven dependencies when you want to use external libraries. 

## Out-of-scope

* NO "real" persistence necessary (do not integrate MySQL, HSQLDB, h2database, or JPA). Use your own internal storage model (see also [`Table.java`](/src/main/java/exercise/Table.java)).
* NO need to implement any SQL parser or anything, Java code interface is sufficient.
* NO Javadoc necessary. Tests and self explaining code are sufficient.


## Your comments / remarks

What would you do if you had more time? Which design decisions and trade-offs did you take?

* Using external library should be better choice to avoid corner/edge cases otherwise implementing own CSV parser.
* In the current implementation which consists of for loop inside for loop, this could be avoided by using Map also, while reading data should be stored index based so that retrieval should be fast.
* Current implementation has order of O(n2) while merging data (inner join), it could be avoided by creating index based data.


Have fun!

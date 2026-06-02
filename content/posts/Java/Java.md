# VARIABLES
## VARIABLES PRIMITIVAS
 - `byte` 	Stores whole numbers from `-128 to 127`
 - `short` 	Stores whole numbers from `-32,768 to 32,767`
 - `int` 	Stores whole numbers from `-2,147,483,648 to 2,147,483,647`
 - `long` 	Stores whole numbers from `-9,223,372,036,854,775,808 to 9,223,372,036,854,775,807`
 - `float` 	Stores fractional numbers. Sufficient for storing 6 to 7 decimal digits
 - `double` 	Stores fractional numbers. Sufficient for storing 15 to 16 decimal digits
 - `boolean` 	Stores true or false values
 - `char` 	Stores a single character/letter or ASCII values

 ## STRING
  - `indexOf()`: method returns the `index` (the position) of the first occurrence of a specified text in a string (including whitespace).  
  - The `toUpperCase()` method converts a string to upper case letters.
  - The `toLowerCase()` method converts a string to lower case letters.
  - `charAt()` method to access a character at a specific position in a string: `txt.charAt(0)`
  - `trim()`: elimina los espacios del inicio y del fin de la cadena de texto.  

### CONCATENACION STRING Y NUMEROS
  ```java
  String x = "10";
  int y = 20;
  String z = x + y;  // z will be 1020 (a String)
  ```

# CASTING
  - ` int myInt = (int) myDouble; // Manual casting: double to int`
  ```java
  // Esto causaría un error de compilación:
  // float miNumero = 100.0; 

  // Esto es correcto, ya que el literal es un float:
  float miNumero = 100.0f; // Es practica comun identificar con una f al final el float  
  ````

# OPERATOR
 - `&&`  	Logical and 	Returns true if both statements are true 	`x < 5 &&  x < 10` 	
 - `||`  	Logical or 	Returns true if one of the statements is true 	`x < 5 || x < 4` 	
 - `!` 	Logical not 	Reverse the result, returns false if the result is true 	`!(x < 5 && x < 10)`

 - When dividing two integers in Java, the result will also be an integer. For example, `10 / 3` gives `3`. If you want a decimal result, use double values, like `10.0 / 3`.

# MATH
 - `Math.max(x,y)` method can be used to find the highest value of x and y.
 - `Math.min(x,y)` method can be used to find the lowest value of x and y.
 - `Math.sqrt(x)` method returns the square root of x.
 - `Math.abs(x)` method returns the absolute (positive) value of x.
 - `Math.pow(x, y)` method returns the value of x raised to the power of y.
 
 - `Math.round(x)` - rounds to the nearest integer
 - `Math.ceil(x)` - rounds up (returns the smallest integer greater than or equal to x)
 - `Math.floor(x)` - rounds down (returns the largest integer less than or equal to x)
  ```java
    Math.round(4.6);  // 5
    Math.round(4.5);  // 5
    Math.ceil(4.1);   // 5.0
    Math.floor(4.9);  // 4.0
  ```
 - `Math.random()` returns a random number between 0.0 (inclusive), and 1.0 (exclusive). The methods returns a double.  
  `int randomNum = (int)(Math.random() * 101);  // 0 to 100`: Para obtener un valor entre 0 y 100.   


# LAMBDA FUNTION
- En Java, el operador :: se refiere a una referencia de método, que es una forma simplificada 
  de escribir una expresión lambda para llamar a un método.  

# Key word 'this'  
-  La palabra clave 'this' se usa para hacer referencia al objeto actual en un método o constructor, y para resolver ambigüedades en el código.  

# GENERICS
- Although generics can be used in other ways, you’ll often use generics to
  write type-safe collections. In other words, code that makes the compiler
  stop you from putting a Dog into a list of Ducks.

ArrayList<Type>: ArrayList es la clase, Type es el tipo que permite almacenar la clase.

1. Creando instancias de generic clases.
  new ArrayList<Song>()
2. Declarando y asignando varibles de generic types.
  List<Song> songList = new ArrayList<Song>()
3. Declarando y llamando metodos que toman parametros de generic types.
  void foo(List<Song> list)
  x.foo(songList)

- Ejemplo en la documentacion de ArrayList:
  **public class ArrayList<E> extends AbstractList<E> implements List<E>**
  - Think of “E” as a stand-in for “the type of element you want this collection to
    hold and return.” (E is for Element.)

public <T extends Animal> void takeThing(ArrayList<T> list)
es diferente de:
public void takeThing(ArrayList<Animal> list)

El primero permite un parametro ArrayList de tipo Animal y sus subtype pero
el segundo solo permite un ArrayList de tipo Animal.

- public void takeAnimals(List<? extends Animal> animals){} 
  + "El compilador no permite añadir mas elementos a la lista pasada por parametros"
  + "El signo '?' permite pasar una lista de Animal y de las clases que hereden de Animal"

  **public void takeThing(ArrayList<Animal> list) { }** "Solo permite un ArrayList por parametro de tipo Animal, no de tipo Dog, Cat, etc"
  **public <T extends Animal> void takeAnimals1(List<T> list) { }** "La lista q pasa por parametro debe ser de tipo Animal y sus subtipos"
  **public <T extends Animal> List<T> takeAnimals(List<T> list) { }** "La lista q pasa por parametro y la lista que devuelve debe ser de tipo Animal y sus subtipos.
      Tiene que ser del mismo tipo (ej. Si es de Dog, la lista de parametro y la que devuelve tienen que ser de Dog)"
  **public List<? extends Animal> takeAnimals(List<? extends Animal> animals) { }** "La lista q pasa por parametro y la lista que devuelve debe ser de tipo Animal y sus subtipos.
      No tienen que coincidir los tipos (ej. Se puede pasar una lista de Dog y devolver una de elfante)"

- Los generics que se pasan por parametros con la estructura **metodo(Colecion<? extends Clase> animals)** o **metodo(Colecion<T extends Clase> animals)**, no 
  permiten agregar elementos a la coleccion. Se pueden modificar los elementos si la clase tiene implementado los metodos set.  

- Using the wildcard (“? extends”) is fine when you don’t care much about the generic type, you just
  want to allow all subtypes of some type.

- Using a type parameter (“T”) is more helpful when you want to do more with the type itself, for
  example in the method’s return.

# CONSTRUCTOR
  - Si no se crea un constructor, el compilador crea un constructor vacio. Al crear al menos un constructor ya el compilador no crea el constructor vacio.
    Always provide a no-arg constructor if you can, to make it easy for programmers to make a working object. Supply default values.  
  - Los constructores soportan sobrecarga de operadores(ej. constructor(), constructor(tipoDeDato param), constructor(tipoDeDato param, param1, etc)).  
  - No se pueden tener dos constructores con la misma cantidad de parametros y del mismo tipo (**ej. constructor(String a, int b) y constructor(String c, int m)**)
  - Las variables de los objetos se le asigna un valor por defecto si no son inicializadas. Para 0/0.0/false for primitives, and null for references.  

  ## Constructor en la herencia
  - Cuando se crea un objeto subtipo en una estructura de herencia, se ejecutan todos los constructores de los padres en la herencia.  
  - ***The superclass parts of an object have to be fully formed (completely built) before the subclass parts can be constructed***  
  - The call to super() must be the first statement in each constructor! 
  - Cuando un constructor de una clase padre lleva parametros la clase hija debe llamar al consturctor padre(super(parametros)) y pasarle los parametros. 
     ```java
      public abstract class Animal {
        private String name;
        private Integer weight;

        Animal(String name, Integer weight){
            this.name = name;
            this.weight = weight;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
      }

      public abstract class Canine extends Animal{

        private Integer dogTooth;
        Canine( String name, Integer weight, Integer dogTooth ) { // Se le pasan los parametros de la clase padre

            super(name, weight); // Se le pasan los parametros de la clase padre
            this.dogTooth = dogTooth;
        }

        public Integer getDogTooth() {
            return dogTooth;
        }

        public void setDogTooth(Integer dogTooth) {
            this.dogTooth = dogTooth;
        }
    }```
- Usa this() para llamar a un constructor dentro de otro constructor con sobrecarga de operadores en la misma clase. 
  + La llamada a this() solo puede ser usada en un constructor y debe ser la primera linea en el constructor.
  + Un constructor puede llamar a super() o this(), nunca a los dos.

- A local **variable lives** only within the method that declared the variable.  
- An **instance variable lives** as long as the object does. If the object is still alive, so are its instance variables.

# METODOS
- Referencia a método 

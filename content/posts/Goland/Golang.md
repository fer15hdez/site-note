# Golang 

## Comandos
 ` go list -f '{{.Target}}' `
  `go clean -modcache`: Limpia la cache de los modulos   


## Crear un modulo
`go mod init nombre_modulo`
- Un módulo es una colección de paquetes relacionados que se versionan juntos como una unidad lógica para gestionar dependencias.
- Esto crea un archivo "go.mod"  
- Para usar un modulo se importa desde el archivo donde se necesita
    ````go
    import (
	"fmt"
	"example.com/greetings"
    )
    ```
- Para especificar la direccion del modulo que se quiere utilizar cuando es local    
    `go mod edit -replace nombre_mod_actual=dir/nombre_mod_utilizar`  
    - Esto escribe en el archivo "go.mod"  
    `replace example.com/greetings => ../greetings`  

- Para sincronizar las dependencias de los modulos  
    `go mod tidy`

## Paquetes 
 - Un paquete es una colección de archivos .go dentro de un directorio   
 - Deben tener el mismo nombre en `package nombre_package`  

 ### Paquetes estandar de go
   #### strings
   - Manipula las cadenas de texto.  
   - `strings.TrimSpace`: Elimina los espacios en blanco al inicio y al final de la cadena.
   - `.HasPrefix(cadena, prefijo)`: Dice si la cadena empieza con el prefijo.  
   - `.TrimPrefix(cadena, prefix)`: Elimina el prefijo del inicio de la cadena (si existe). 
   - `.Fields(cadena)`: Divide la cadena en las palabras que estan separadas por espacio y devuelve un slice de string.  
   - `.ToLower(cadena)`: Convierte la cadena en minuscula.  
   ### bufio
   - Permite leer archivo por tramos, leer de la consola la entrada del usuario, 
   ### strconv
   - Util para convertir tipos string a int.  
   ### time
   - `time.Now()`: Se utiliza para obtener la hora y fecha actuales del sistema.   
   - `time.Since(t)`: Calcula la diferencia entre la hora actual y t.  
   - `t.Format("2006-01-02")`: "2006-01-02" representa el formato YYYY-MM-DD.  
   - `t.Format("03:04 PM")` : "03:04 PM" representa el formato hh:mm AM/PM.  
   - `t.Add(d)`, `t.Sub(t2)`, `t.Before(t2)`, `t.After(t2)`, `t.Equal(t2)`
   ```go
    // Crea un momento específico en la zona horaria UTC
    cumpleanos := time.Date(1990, time.May, 15, 0, 0, 0, 0, time.UTC)
   ```


## Crear workSpace
 `go work init ./hello ./greetings` -> Crear un archivo "go.work" donde se adiciona los valores:
   ```go
   go 1.24.4

    use (
        ./hello
        ./greetings
    )
   ``` 
   - Esto le dice a cualquier llamada que use ?¿?¿?¿?¿?


## Escribir en la consola
- Printf(): Muesta el texto formateado.  
    `fmt.Printf("i has value: %v and type: %T\n", i, i)`
- Println(): Muestra el texto e imprime un salto de linea.  
- Print(): Muestra el texto tal y como se escribe.       

## Logger
```go
    import (
	"log"
    )

    log.Fatalf("Error al obtener el directorio actual: %v", err)
```
## Variables
 `var i int` -> Declara una variable de tipo entero.  
 `var c, python, java bool` -> Declara varias variables del mismo tipo.  
 `k := 3` -> Forma corta de declaracion. Identifica el tipo del valor asignado.  

 - Sin inicializar
     - 0 para tipos numéricos,
     - false para tipo booleano, y
     - "" (la cadena vacía) para cadenas de texto.

### Tipos
 - bool, string, 
 - int(enteros especificos: int8,  int16,  int32,  int64),  
     uint uint8 uint16 uint32 uint64 uintptr // Son los positivos de los int  
     `byte` // alias para uint8  (2^8 - 1 = 256 - 1 = 255)  
     `rune` // alias para int32  ( (2^{32}-1) -> 0 a 4 294 967 295)
            // representa un punto de código Unicode
 - float32, float64
 - complex64 complex128         

 - Convertir int a String:
    `import "strconv"`
    `texto + strconv.Itoa(numero)`  
 - Convertir `int` a `float64`: `float64(numero_int)`  
 - Convertir `slice de byte` a `string`: `string(el_slice)`: Interpreta el valor del byte como valor ASCII y representa el char.  
 - Convertir `slice de string` a `string`: `strings.Join(el_slice, separador)`: ej. `strings.Join(el_slice, "-")`: `strings` es un paquete, se tiene que importar.  
 - Para convertir array en string se debe convertir a slice   
   ```go
   // 'a' es un slice. Toma del lugar 0 al 2
      a := nombre_array[0:2]       
      b := nombre_array[inicio_tramo:fin_tramo]       
      c := nombre_array[:]  // Convierte el array completo en un slice.      
   ```

### Constantes
 - Las constantes se declaran como variables, pero con la palabra clave `const`  
 - Las constantes no se pueden declarar usando la sintaxis `:=`  

## Control de Flujo

### IF y ELSE
   ```go
    // v tiene alcance hasta el else
    // el puede tener una expresion a evaluar antes del ";"
        if v := math.Pow(x, n); v < lim {
            return v
        } else {
            fmt.Printf("%g >= %g\n", v, lim)
        }
   ```
### For
   ```go
    // el range es para recorrer colecciones
    for indice, valor := range coleccion {
		fmt.Printf("Accion")
	}
    for i := range 5{ fmt.Printf(i) }
    for i := 0; i < 10; i++ {
		sum += i
	}
    // for continuo
    for ; sum < 1000; {
		sum += sum
	}
    // for como while
    for sum < 1000 {
		sum += sum
	}
    // for infinito
    for {
	}   

   ```    
   - La sentencia `break` se utiliza para terminar la ejecución del bucle más interno que la contiene.
   - La sentencia `continue` se utiliza para saltar el resto del código del cuerpo del bucle en la iteración actual y pasar inmediatamente a la siguiente iteración.

### Switch
   ```go
   // Se evalua de arriba hacia abajo, si da un verdadero termina
   // Puede sin la condicion inicial (os := runtime.GOOS; os) y seria true. Es util para sustituir una larga 
   // lista de if else
    switch os := runtime.GOOS; os {
        case "darwin":
            fmt.Println("OS X.")
        case "linux":
            fmt.Println("Linux.")
        case valor >= 5 && valor <= 10: // Se pueden hacer comparaciones en el mismo case
            fmt.Println("Mi texto.")
        case "Linux", "Mac OS", "Otro OS": // Si hay varios valores con la misma logica se pueden agrupar en el case
            fmt.Println("Unix.")    
        default:
            // freebsd, openbsd,
            // plan9, windows...
            fmt.Printf("%s.\n", os)
        }
   ```

## Punteros
- Un puntero contiene la dirección de memoria de un valor. 
  `var p int = 10` : p es una variable que tiene como valor en memoria 10.   
  `var p *int` : p es un puntero a un valor int.  
  `p = &i`: p es un puntero a i. (p tiene la dir de memoria al valor de i).  
  `*p = 21`: el * desreferencia a p y modifica el valor que esta en memoria.  
- El operador & (operador de dirección) solo se puede aplicar a una variable o a una expresión que devuelva un valor variable(ej. una funcion que devuelve un valor). No se puede aplicar al resultado de una llamada a función que no devuelve un valor (ej. llamar a una funcion que no devuelve nada. `&variable.funcion() - no devuelve nada`).
- En Go, la regla es que el operador de dirección (&) solo se puede aplicar a una expresión direccionable (`addressable expression`).  
- La expresión que sigue al operador & debe representar algo que tiene una ubicación de memoria estable y fija que el programa puede referenciar.  
- Solo puedes usar & para obtener la dirección de un elemento que ya existe y persiste en la memoria (una variable o una parte de una variable), nunca para el resultado temporal de una operación (ej. El resultado de una funcion).  

## Function
- Sintaxis
    ```go
    
    func FunctionName(param1 type, param2 type, param3 type) type {
        // code to be executed
        return output
    }
    ```
    ```go

    package main
    import ("fmt")

    // Se pueden devolver uno o mas de un valor(ej. result y text1)
    // Se puede 
    func myFunction(x int, y string) (result int, txt1 string) {
    result = x + x
    txt1 = y + " World!"
    return
    }
    ```
### Defer
   - Una instrucción defer, aplaza la ejecución de una función hasta el retorno de la funcion circundante (que la contiene).  
        La funcion defer se ejecuta cuando la funcion donde esta termina.  
   - Los argumentos de la llamada diferida se evalúan inmediatamente, pero la llamada a la función no se ejecuta hasta que la función que la rodea retorna.  
   - Si existen mas de una funcion defer estas se almacenan en una pila LIFO y esta se ejecuta cuando termina la funcion donde estan.  
### Funcion clausuras o closures
   - Una función también puede construir y devolver otra función.
    
    ```go
    package main

    import "fmt"

    // crearIncrementador es una función que retorna OTRA función.
    // La función retornada "recuerda" el valor de 'cantidad' (closure).
    func crearIncrementador(cantidad int) func(int) int {
        // Esta es la función anónima que será retornada
        return func(x int) int {
            return x + cantidad
        }
    }

    func main() {
        // Creamos un incrementador que suma 5
        incrementarPorCinco := crearIncrementador(5)

        // Creamos un incrementador que suma 10
        incrementarPorDiez := crearIncrementador(10)

        fmt.Println("5 + 3 =", incrementarPorCinco(3))   // Salida: 5 + 3 = 8
        fmt.Println("10 + 7 =", incrementarPorDiez(7)) // Salida: 10 + 7 = 17
    }      
    ```
### Funciones Variádicas
   - Aceptan un número variable de argumentos del mismo tipo.  
   - `Solo el Último Parámetro`: Solo el último parámetro de una función puede ser variádico.  
   - `Un Solo Tipo Variádico`: Una función solo puede tener un parámetro variádico.  
   - `Tipo Slice`: Dentro de la función, el parámetro variádico se maneja siempre como un slice.
   ```go
        func sumaTodos(numeros ...int) int {
        // El parámetro 'numeros' se comporta internamente como un slice de int ([]int).
        total := 0
        for _, num := range numeros {
            total += num
        }
        return total
    }   
   ```
   - Si se le pasa un slice hay que desempaquetarlo `sumaTodos(valores...)`. Si no se desempaqueta da error de tipos.  

## Collections

### Arrays
- Los arrays no se pueden redimensionar  
  `var a [10]int`: declara una variable a como un array de diez enteros.  
  `primos := [6]int{2, 3, 5, 7, 11, 13}`


### Slice
- Un slice se forma especificando dos índices, un límite inferior y superior, separados por dos puntos.  
- Un slice no almacena ningún dato, solo describe una sección de un array subyacente (array real (o físico)).  
  Es una especie de puntero.  
- Cambiar los elementos de un slice modifica los correspondientes elementos de su array subyacente.  
- Otros slices que comparten el mismo array subyacente verán esos cambios.  
- Un slice no se puede modificar mas alla de la capacidad del array subyacente (array real (o físico)).  
- El valor cero(valor por defecto de acuerdo al tipo de datos), cuando no se inicializa un slice, es `cero` para numeros, `0.0` float, `""` cadena vacia para string, `false` para bool, `nil` para punteros.  
- Un slice nulo tiene una longitud y capacidad de 0 y no tiene array subyacente.  
- Los slices pueden contener cualquier tipo, incluyendo otros slices.  
    `s := make([][]int, dy)`: un slice de slice de enteros.  

- Syntax  
  `slice_name := []datatype{values}`  
  `var myslice []int` Un slice vacio.  
  `myslice := []int{}` Un slice vacio.  
  `myslice := []int{1,2,3}`  

- Si se crea un slice vacio la forma de agregarle valor es con `append()`.  
- Capacidad y len()
  - len() function - returns the length (longitud) of the slice (the number of elements in the slice). Es el numero de elementos al que puedes acceder dentro del slice, si intentas a acceder a un index superior al `len()` va a dar fuera de rango.   
  - cap() function - La capacidad de un slice es el número de elementos en el array subyacente (array real (o físico)), contando desde el primer elemento en el slice. La capacidad te dice cuántos elementos puedes agregar al slice usando la función `append()` antes de que Go tenga que reasignar memoria.  

- Funcion Make()  
    - If the capacity parameter is not defined, it will be equal to length.  
      `slice_name := make([]type, length, capacity)` 

- Agregar elementos al slice  
    `s = append(nombre_slice_a_modificar, elem, elem2, ...)` 
    - Los elementos a agregar deben ser del mismo tipo que los contenidos por el slice.  

- Funcion Copy()
    - Sintaxis: `copy(destino []T, oringen []T) int`
    - Es la manera más eficiente y segura de mover datos de un slice a otro, ya que maneja internamente la lógica de la copia y evita errores comunes al trabajar con índices.
    - Diferencias entre una asignacion y copy(): 
       - La asignacion Copia la cabecera del slice. Ambas variables apuntan al mismo array subyacente. Mientras que el copy() No afecta el array subyacente de dst. Simplemente sobreescribe los valores dentro de él.

### Maps
- El valor cero de un map es nil. Un map nulo no tiene claves, ni se pueden agregar claves. Si le faltan las `{}` al final de la declaracion el valor que toma es nil. Las `{}` aunque no tenga valor dentro inicializa el map.       
- La funcion make retorna un map del tipo dado, inicilizado y listo para usar.  
- Sintaxis
    ```go
    var a = map[KeyType]ValueType{key1:value1, key2:value2,...}
    b := map[KeyType]ValueType{key1:value1, key2:value2,...}

    // Un map sin inicializar. No se le puede agregar valor.  
    var d map[KeyType]ValueType

    // Un map vacio. Se le puede agregar valor.
    var c = map[KeyType]ValueType{}

    // ej
    var a = map[string]string{"brand": "Ford", "model": "Mustang", "year": "1964"}
    b := map[string]int{"Oslo": 1, "Bergen": 2, "Trondheim": 3, "Stavanger": 4}

    // Con make() function
    var a = make(map[KeyType]ValueType)
    b := make(map[KeyType]ValueType)
    ```   
- Eliminar elementos de un map
  `delete(map_name, key)`      

- Iterar sobre el maps
```go
    func main() {
        a := map[string]int{"one": 1, "two": 2, "three": 3, "four": 4}

        for k, v := range a {
            fmt.Printf("%v : %v, ", k, v)
        }
    }
```

- Comprobar que una clave este presente
  `elem, ok = m[key]`: Si key está en m, entonces ok es true y `elem` toma el valor. Si no esta `elem` es cero y `ok` es false.  

### Struct
- Los campos de struct se pueden acceder a través de un puntero a un struct.  
    `p := &v`
	`p.X = valor`
    `fmt.Println(p.X)`

- Sintaxis
    ```go
    type struct_name struct {
        member1 datatype;
        member2 datatype;
        member3 datatype;
    }
    // ej
    type Persona struct{
        name string
        age int
    }

    // Acceso a un struct
    var person1 Persona // crea la struct
    // Asignara valores
    person1.name = "Maria"
    person1.age = 30

    // Acceder a los valores de los campos 
    fmt.Print(person1.name)
    ```      

## Método
- Un método es esencialmente una función, pero con una diferencia crucial: tiene un argumento receptor especial.  
- Un método es una funcion asociada a cualquier tipo definido por el usuario (estructuras, tipos básicos con alias, etc.).   
- Si el tipo receptor es un puntero (*TipoReceptor), el método puede modificar el valor original del receptor.   
- Solo puedes declarar un método con un receptor cuyo tipo este definido en el mismo paquete como el método (debe estar en el mismo archivo).  

- Sintaxys
```go
    func (nombre_receptor TipoReceptor) NombreMetodo(parametros) (retornos) {
        // Cuerpo del método
    }
```

## Interfaces
- Sintaxy
```go
    type NombreDeLaInterfaz interface {
        // Firma del método 1: nombre(parametros) (retornos)
        Metodo1(int) string
        // Firma del método 2:
        Metodo2() error
        // ... más firmas de métodos
    }
```
```go
    type Abser interface {
       Abs() float64
    }
    type MyFloat float64
    type Vertex struct {
        X, Y float64
    }

    func (v *Vertex) Abs() float64 { // Receptor por PUNTERO
        return valor
    }

    func (v Vertex) Metodo_Valor() float64 { // Receptor por VALOR
        return valor
    }

    func (f MyFloat) Abs() float64 {
        return valor
    }

    // Cuando defines un método con un receptor por puntero (func (v *Vertex) Abs() float64), 
    // ese método solo puede ser llamado directamente en un puntero a Vertex.

    var a Abser
    f := MyFloat(10)
    v := Vertex{1, 2}
    a = f // Se usa por valor
    a = v // Da error, porque trata de asignar a la interfaz Abser (que es "a") Vertex que implementa la interfaz por puntero.   
```
- Un tipo implementa una interfaz implementando sus métodos. No hay una declaración explícita de intenciones, ni la palabra clave "implements".  
   - Solo se imprementa los mentodos de la interfaz, no existe una palabra clave para identificarlo, con eso es suficiente para implementar la interfaz.  
- Para usar el polimorfismo si se declara de tipo puntero la implementacion de una interfaz `(func (v *Vertex) Abs() float64)` 
  esta no se puede usar en un TYPE declarado de tipo valor.   
  (Las interfaces cuando se implementan usando un receptor por VALOR se pueden usar usando TYPE por VALOR y por PUNTERO. Si la interface se implementa por PUNTERO solo se puede usar por el TYPE por puntero)(Esto suscede cuando tratamos de usar el polimorfismo usando la implementacion de una interfaz).   
- Si al menos UNO DE LOS METODOS de un struct usa un receptor por puntero, para que ese struct implemente la interfaz, siempre debe pasar un puntero de ese struct. Es decir, cuando se vaya a pasar ese `struct` como parametro de un metodo donde se pase la `interfaz` (como parametro) que implementa se debe pasar un puntero del `struct`. 
  ```go
    func main(){
        var c Coche
        // Al struct Coche implementar un metodo con un receptor como puntero es necesario pasar un 
        // puntero de Coche a la funcion "moverObjeto()"
        moverObjeto(&c)
    }
    type Movible interface {
        marchaAlFrente(m uint)
        marchaAtras(m int)
        velocidadActual() int
    }
    
    type Coche struct {
        marcha      int
        speedActual int
    }
    // Implementacion de receptor con puntero
    func (c *Coche) marchaAlFrente(m uint) {
        // Codigo
    }
    // Implementacion de receptor por valor
    func (c Coche) marchaAtras(m int){
        // Codigo
    }
    // Implementacion de receptor por valor
    func (c Coche) velocidadActual() int{
        // Codigo
    }

    // Interfaz pasada por parametro
    func moverObjeto(m Movible){}
  ```    

### Valores de interfaz
- Una variable de interfaz en Go se representa internamente como una dupla (par de valores).  
  ```go
        var i interface{} // Declaramos una variable de interfaz vacía

	// En este punto, 'i' es un valor de interfaz nulo.
	// Su tipo concreto es <nil> y su valor concreto es <nil>.
	fmt.Printf("Valor de i: %v, Tipo de i: %T\n", i, i) // Salida: Valor de i: <nil>, Tipo de i: <nil>

    a := i 
    // "a" es de tipo "i" (la interfaz) y valor <nil>  

  ```

### Valores de interfaz nulo
```go
    package main

    import "fmt"

    // Definimos una interfaz simple
    type Saludar interface {
        Hola() string
    }

    // Definimos un tipo que implementa la interfaz
    type Persona struct {
        Nombre string
    }

    func (p Persona) Hola() string {
        return "Hola, mi nombre es " + p.Nombre
    }

    func main() {
        var s Saludar // Declaramos una variable de interfaz de tipo 'Saludar'

        // En este punto, 's' es nula (tipo=<nil>, valor=<nil>)
        fmt.Printf("Valor de s: %v, Tipo de s: %T\n", s, s) // Salida: Valor de s: <nil>, Tipo de s: <nil>

        if s == nil {
            fmt.Println("La interfaz 's' es nula. No tiene tipo ni valor.")
        }

        // ¡PELIGRO! Intentamos llamar a un método en una interfaz nula.
        // Esto causará un panic en tiempo de ejecución.
        fmt.Println(s.Hola()) // Esto va a fallar: panic: runtime error: invalid memory address or nil pointer dereference
    }
```

### La interfaz vacía
- El tipo de interfaz que específica cero métodos es conocida como una _interfaz_vacia: `interface{}`
- Un tipo implementa una interfaz si cumple con todos los métodos de esa interfaz. Si una interfaz no requiere ningún método, entonces todos los tipos (números enteros, cadenas de texto, structs, slices, mapas, etc.) automáticamente cumplen ese "contrato" vacío.  
- Debido a que puede contener cualquier tipo de valor, interface{} es la forma de Go de manejar valores de tipo desconocido o arbitrario. Es el equivalente más cercano al concepto de "Object" en Java o "Any" en TypeScript/Python, aunque con la seguridad de tipos de Go.  

### Aserción de Tipo (type assertion) - (Es como un casting)
  - Si a una variable de tipo interfaz se le asigna el valor de un objeto que implementa esa interfaz, desde esa varible se llaman a los metodos implementados por el objeto concreto que tiene la interfaz.  
  - Desde la variable de tipo interfaz no se pueden llamar a los metodos que implementa el objeto concreto que no estan en la interfaz.  
  - Para llamar a los metodos que estan en implementacion concreta y no estan en la interfaz es necesario hacer una `Aserción de Tipo (type assertion)`.  
    ```go
    func main() {
        // f es Figura, pero internamente contiene un Rectangulo
        var f Figura = Rectangulo{Ancho: 10, Altura: 5}

        // Aserción de Tipo (Casting): Intentamos extraer el Rectangulo
        // rectConcreto es el valor, ok es un booleano que indica si la aserción fue exitosa
        // i.(T): 
          // i es la variable de tipo interfaz.
          // T es el tipo concreto (struct, int, string, etc.) al que esperas que se convierta el valor contenido en i.
          // Los paréntesis () contienen el tipo de destino.
          // t, ok := i.(T)
        rectConcreto, ok := f.(Rectangulo) 

        if ok {
            // Ahora que sabemos que es un Rectangulo, podemos acceder a sus campos
            fmt.Printf("El Ancho del struct es: %.1f\n", rectConcreto.Ancho) 
        } else {
            fmt.Println("La variable f no contenía un tipo Rectangulo.")
        }
    }
    ```
  ####  Type Switch (La Alternativa más Elegante)
  ```go
    func ProcesarFormaConSwitch(f Forma) {
        // La declaración en el tipo switch tiene la misma sintaxis que el tipo aserción i.(T), pero el tipo específico T es remplazado por la palabra clave type.
        switch valor := f.(type) {
        case Cuadrado:
            // Aquí 'valor' ES un Cuadrado, y puedes acceder a sus campos
            fmt.Printf("Es un Cuadrado con Lado: %.2f\n", valor.Lado)
        case Circulo:
            // Aquí 'valor' ES un Círculo
            fmt.Printf("Es un Círculo con Radio: %.2f\n", valor.Radio)
        case nil:
            // El valor de la interfaz es nil
            fmt.Println("La interfaz está vacía (nil)")
        default:
            // Cualquier otro tipo que implemente la interfaz
            fmt.Printf("Es un tipo desconocido: %T\n", valor)
        }
    }
  ```

## GoRoutine
 - Las goroutine son funciones que se ejecutan de forma independiente y concurrente

## Canales
 - Es cómo las goroutines se comunican entre sí de forma segura.
 - WaitGroups se usan cuando solo necesitas esperar a que un grupo de goroutines termine. 
    Es un contador que garantiza que el programa principal no se cierre prematuramente.
- Cuando creas un canal con make(chan int), este es un canal bidireccional por defecto, lo que significa que puedes enviar y recibir valores de él. 
    + Es en la firma (argumentos) de la función donde le dices a Go si debe ser de solo escritura (chan<-) o de solo lectura (<-chan).
- Canal de solo lectura: `<- chan int`. 
    + Se define en el parametro de la funcion(ej. `func calculateSum(numbers []int, result <- chan int, wg *sync.WaitGroup) {}`)  
- Canal de solo escritura: `chan <- int`  
    + Se define en el parametro de la funcion(ej. `func calculateSum(numbers []int, result chan<- int, wg *sync.WaitGroup) {}`)
- Si desde un goroutine (funcion) se envian varios datos por un canal se debe esperar en el otro goroutine con el que se comunica (ej. main()) todos los datos que se enviaron.     



## Trabajo con archivos
```go

    import(
        "os"
        "path/filepath"
    )

    // Path actual
    os.Getwd()

    // Listar archivos y dir de un path
    // Retorna un slice de fs.DirEntry y un error.
    // Cuando se recorre el slice se puede preguntar si es un dir o file(entry.IsDir())
    os.ReadDir(dirname string)

    // Obtener el ultimo elemento de la ruta 
    // Si termina el 
    filepath.Base()
    
    // Obtener la Extensión de un archivo
    // En archivos ".bashrc" devuelve una cadena vacia
    // En archivos "readme" (sin una extencion con punto) devuelve una cadena vacia
    // "archivo.tar.gz" devuelve ".gz"
    filepath.Ext(path string)
```

- Crear un directorio: `os.Mkdir("nombre_dir", 0755)`
- Crear directorios anidados: `os.MkdirAll("dir1/dir2/dir3", 0755)`
- Eliminar directorio (vacío): `os.Remove("nombre_dir")`
- Eliminar directorio (y su contenido recursivamente): `os.RemoveAll("nombre_dir")`

```go 
    // informacion de un archivo
    fileInfo, err := os.Stat("mi_archivo.txt") // Asume que 'mi_archivo.txt' existe

    fmt.Println("Nombre del archivo:", fileInfo.Name())
	fmt.Println("Tamaño (bytes):", fileInfo.Size())
	fmt.Println("Es directorio:", fileInfo.IsDir())
	fmt.Println("Permisos:", fileInfo.Mode())
	fmt.Println("Última modificación:", fileInfo.ModTime())
```
- Eliminar Archivos: `os.Remove`
- Renombrar y Mover Archivos: `err = os.Rename("viejo_nombre.txt", "nuevo_nombre.txt")`
- Mover: `err = os.Rename("nuevo_nombre.txt", "temp_dir/archivo_movido.txt")`. El dir temp_dir debe existir.  
- Unir path: `filepath.Join(path, entry.Name())`.  
- Leer de un Archivo
    - Leer un archivo completo: `content, err := os.ReadFile("datos.txt")`
    - Leer por bloques: 
        ```go
            buffer := make([]byte, 10) // Buffer para leer 10 bytes a la vez
            for {
                n, err := file.Read(buffer) // Lee hasta 10 bytes en el buffer
                if err != nil {
                    if err.Error() == "EOF" { // EOF (End Of File) significa que llegamos al final
                        break
                    }
                    fmt.Println("Error al leer:", err)
                    return
                }
                fmt.Printf("Leídos %d bytes: %s\n", n, string(buffer[:n])) // buffer[:n] para imprimir solo los bytes leídos
            }
        ```
- Escribir en un Archivo: 
    ```go
        file, err := os.Create("datos.txt")
        // Escribir un string
	    bytesWritten, err := file.WriteString("¡Hola, Go!\n")
        // Escribir bytes
        data := []byte("Más datos en bytes.\n")
        bytesWritten, err = file.Write(data)

        // Escribe al final del archivo
        file, err := os.OpenFile("dataJson.json", os.O_APPEND|os.O_WRONLY|os.O_CREATE, 0644)
        file.Write(toWrite)
	    file.WriteString("\nMas string")

    ```     
- Crear un Archivo: 
    ```go
        // os.Create crea un archivo o lo trunca si ya existe.
        // Retorna un puntero a os.File y un error.
        file, err := os.Create("mi_archivo.txt")
    ```       
### Trabajo con archivos JSON
   ```go
      import (
        "encoding/json"
        "fmt"
        "os"
        "time"
        )  
      func main(){
        //----- Escribir en un archivo json -----   
        // Se crea la estructura que se va a escribir en el json.        
        actualChange := ExchangeData{
            BaseCoin:    "USD",
            Date:        time.Now().Format("2006-06-02"),
            Conversions: coins,
	    } 
        // MarshalIndent convierte la data a json con las opciones de indent. Devuelve un slice de byte.
        jsonData, err := json.MarshalIndent(actualChange, "", " ")
        if err != nil {
            fmt.Printf("Error convirtiendo a json: %s", err)
            return err
        }

        // Se crea el archivo. os.WriteFile devuelve un dato de tipo error.   
        errWriteFile := os.WriteFile(nameFile, jsonData, 0644)
        if errWriteFile != nil {
            return errWriteFile
        }       
        
      } 
      // Se debe crear una estructura que identifique los campos que se van a escribir en el json.
      type CoinChange struct {
            Coin string  `json:"moneda"` // Debe tener esta estructura que define como van a quedar los campos
            Rate float64 `json:"rate"`   // El campo 'Coin' va a tener como nombre en el json 'moneda'
        } 
      //----- Leer de un archivo JSON -------
      func readJsonCoin(nameFile string) (CoinChange, error) {
            // El tipo de datos donde se almacena el contenido json del archivo.  
            // Los campos deben coincidir con los que estan en el json sino no se asignan a la estructura.  
            var data CoinChange  
            file, err := os.ReadFile(nameFile)
            if err != nil {
                return data, err
            }

            // Unmarshal lee del file los datos y los asigna al struct data. Pasarle un puntero es ideal para que modifique al original.  
            err = json.Unmarshal(file, &data)

            fmt.Printf("El struct dentro del readJsonCoin: \n%+v\n", data)

            return data, err
        }
   ```
## Trabajo en consola (CLI)
```go
    import (
	"bufio"
	"fmt"
	"os"
	"strconv" // Para convertir strings a otros tipos
	"strings" // Para limpiar espacios y saltos de línea
)

    // Crear un nuevo lector para la entrada estándar (teclado)
	reader := bufio.NewReader(os.Stdin)

    nombre, err := reader.ReadString('\n') // Leer hasta el salto de línea

    // Eliminar el salto de línea al final del string, que ReadString incluye
	nombre = strings.TrimSpace(nombre) // TrimSpace elimina espacios y saltos de línea (incluyendo \r\n de Windows)

```

### Formatear cadenas en Consola

  -  %v: Imprime el valor de la variable en el formato predeterminado.
  -  %T: Imprime el tipo de la variable. Es muy útil para la depuración.
  -  %s: Se usa específicamente para cadenas (string).
  -  %d: Se usa para números enteros (int).
  -  %f: Se usa para números flotantes (float).
  -  %b: Muestra el binario de un decimal.
  -  %+v: Imprime los valores incluyendo los nombres de los campos de la struct.  
  -  %#v: Es muy útil para la depuración, porque muestra la representación del (struct, variable, etc) exactamente como si lo hubieras escrito en tu código.
  - %.2f: Esto le dice a Go que muestre un número flotante con exactamente dos decimales. La nomenclatura es: un . (punto) seguido de un número, que se coloca entre el % y el verbo de formato.

  - Ancho de campo: Un número después del % le dice a Go cuántos caracteres debe usar para el valor. Por ejemplo, `%5d` reservará 5 espacios para un número entero.
  - Alineación: Un signo - antes del ancho alinea el texto a la izquierda. Sin el -, el texto se alinea a la derecha.

## Errores
 - Verificación de errores: Usar el patrón `if err != nil`.  
 
 ```go
    func openFile(path string) error { // devolver el error para el use la funcion tenga como manejarlo
        archivo, err := os.Open(path) //capturar el error

        if err != nil { // patron para manejar el error
            log.Printf("\nError al abrir archivo en funcion openFile: %v", err.Error()) // Escribir el error especificando funcion y el error real
        } else {
            fmt.Printf("Archivo: %v", archivo)
        }

        return err // Retornar el error
  }
 ```
 ### Errores personalizados
  - Un error personalizado en Go es simplemente una `struct` que implementa la `interfaz error`, la cual solo requiere un método llamado    `Error().`
    ```go
        type ErrorDeArchivo struct {
                    NombreArchivo string
                    Err           error
        }

        func (e *ErrorDeArchivo) Error() string {
            return fmt.Sprintf("error al procesar el archivo %s: %v", e.NombreArchivo, e.Err)
        }
    ```

 ## Test
  - El archivo donde se implementa el test debe terminar en `_test.go`.  
  - Los archivos deben pertenecer a al mismo paquete.  
  - El comando `go test` ejecuta todos los archivos terminados en `_test.go`
  ```go
    // La estructura del nombre es TestNombreFuncion con el parametro (t *testing.T)
     func TestNombreFuncion(t *testing.T) {}
  ```

## Random
  - Existen funciones para generar random en float, int.  
    ```go
        import (
        "fmt"
        "math/rand"
        "time"
    )

    func main() {
        // go superior 1.20
        // Generar una semilla basada en el tiempo (para la nueva fuente)
        fuente := rand.NewSource(time.Now().UnixNano())
        
        // Crear un nuevo generador con esa fuente
        generador := rand.New(fuente)

        // Usa el nuevo generador para todas las llamadas
        numero := generador.Intn(100)
        fmt.Printf("Número generado con fuente nueva: %d\n", numero)
    }
    ```
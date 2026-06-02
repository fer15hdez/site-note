# Cadenas - String 
---

## Funciones de Texto (Strings) en Python

Las cadenas de texto (strings) son secuencias inmutables de caracteres. Python ofrece una gran variedad de métodos y funciones para manipularlas. Aquí algunas de las más utilizadas:

### Métodos Comunes de Cadenas

* **`str.strip()`**: Elimina los espacios en blanco (espacios, tabulaciones, saltos de línea) del inicio y del final de la cadena.
    * `"  Hola Mundo  ".strip()` resulta en `"Hola Mundo"`

* **`str.split()`**: Divide la cadena en una lista de subcadenas usando un delimitador (por defecto, cualquier espacio en blanco).
    * `"uno dos tres".split()` resulta en `['uno', 'dos', 'tres']`
    * `"a,b,c".split(',')` resulta en `['a', 'b', 'c']`

* **`str.splitlines()`**: Divide la cadena en una lista de líneas, eliminando los caracteres de salto de línea.
    * `"Línea 1\nLínea 2".splitlines()` resulta en `['Línea 1', 'Línea 2']`

* **`str.join(iterable)`**: Concatena los elementos de un iterable (como una lista de cadenas) usando la cadena original como separador.
    * `", ".join(['manzana', 'pera', 'uva'])` resulta en `"manzana, pera, uva"`

* **`str.replace(old, new)`**: Devuelve una copia de la cadena con todas las ocurrencias de una subcadena reemplazadas por otra.
    * `"Hola Mundo".replace("Mundo", "Python")` resulta en `"Hola Python"`

* **`str.lower()` / `str.upper()`**: Devuelve una copia de la cadena convertida a minúsculas o mayúsculas.
    * `"Python".lower()` resulta en `"python"`

* **`str.startswith(prefix)` / `str.endswith(suffix)`**: Verifica si la cadena comienza o termina con un prefijo o sufijo dado, respectivamente. Devuelven `True` o `False`.
    * `"archivo.txt".endswith(".txt")` resulta en `True`
    
* **`patron in cadena`**: El operador `in` verifica si un `patron` (subcadena) existe dentro de una `cadena` principal. Devuelve `True` o `False`. Es sensible a mayúsculas y minúsculas.
    * `"azul" in "El cielo es azul"` resulta en `True`
    * `"verde" in "El cielo es azul"` resulta en `False`

---

## Colecciones en Python

Las colecciones son tipos de datos que permiten almacenar múltiples valores en una sola variable. Cada tipo de colección tiene características únicas en cuanto a cómo almacena, ordena y permite acceder a sus elementos.

### 1. Listas (`list`)

* **Ordenadas**: Mantienen el orden de inserción de los elementos.
* **Cambiables (mutables)**: Puedes añadir, eliminar o modificar elementos después de su creación.
* **Permiten duplicados**: Pueden contener el mismo valor varias veces.
* **Heterogéneas**: Pueden contener elementos de diferentes tipos de datos.

    ```python
    mi_lista = [1, "Hola", 3.14, True]
    mi_lista.append(4) # Añadir elemento
    mi_lista[0] = 0    # Modificar elemento
    ```

### 2. Tuplas (`tuple`)

* **Ordenadas**: Mantienen el orden de inserción.
* **Inmutables**: No se pueden cambiar después de su creación. Una vez definida, su contenido no puede ser modificado.
* **Permiten duplicados**.
* **Heterogéneas**.

    ```python
    mi_tupla = (10, "Adiós", False)
    # mi_tupla[0] = 5  # Esto generaría un error (TypeError)
    ```

### 3. Conjuntos (`set`)

* **No ordenados**: Los elementos no tienen un orden definido; el orden puede variar.
* **Cambiables (mutables)**: Puedes añadir o eliminar elementos.
* **No permiten duplicados**: Cada elemento es único. Si intentas añadir un duplicado, simplemente lo ignorará.
* **Heterogéneos**.

    ```python
    mi_conjunto = {1, 2, 3, 2, 4} # Los duplicados se eliminan automáticamente
    print(mi_conjunto) # Podría imprimir {1, 2, 3, 4} o {1, 4, 2, 3} etc.
    mi_conjunto.add(5) # Añadir elemento
    ```

### 4. Diccionarios (`dict`)

* **Ordenados** (a partir de Python 3.7; en versiones anteriores no lo eran).
* **Cambiables (mutables)**: Puedes añadir, eliminar o modificar pares clave-valor.
* **Claves únicas**: Cada clave debe ser única (los valores pueden ser duplicados). Las claves deben ser inmutables (cadenas, números, tuplas).
* Almacenan datos en pares **clave-valor**.

    ```python
    mi_diccionario = {"nombre": "Ana", "edad": 25, "ciudad": "Montevideo"}
    print(mi_diccionario["nombre"]) # Acceder por clave: "Ana"
    mi_diccionario["edad"] = 26     # Modificar valor
    mi_diccionario["país"] = "Uruguay" # Añadir nuevo par
    ```

---

## La función `len()`

La función incorporada **`len()`** es una de las más versátiles y fundamentales en Python. Su propósito es devolver el **número de elementos** de un objeto. Funciona con todas las colecciones y con las cadenas de texto.

### ¿Cómo funciona `len()`?

* **Para Cadenas de Texto (`str`)**: Devuelve el número de caracteres en la cadena.
    ```python
    cadena = "Python"
    print(len(cadena)) # Salida: 6
    ```
* **Para Listas (`list`)**: Devuelve el número de elementos en la lista.
    ```python
    numeros = [10, 20, 30, 40]
    print(len(numeros)) # Salida: 4
    ```
* **Para Tuplas (`tuple`)**: Devuelve el número de elementos en la tupla.
    ```python
    coordenadas = (34.5, -56.7)
    print(len(coordenadas)) # Salida: 2
    ```
* **Para Conjuntos (`set`)**: Devuelve el número de elementos únicos en el conjunto.
    ```python
    colores = {"rojo", "verde", "azul"}
    print(len(colores)) # Salida: 3
    ```
* **Para Diccionarios (`dict`)**: Devuelve el número de pares clave-valor (es decir, el número de claves).
    ```python
    config = {"user": "admin", "pass": "123", "db": "main"}
    print(len(config)) # Salida: 3
    ```

### Consideraciones

* `len()` es una función de tiempo constante para la mayoría de los tipos de colecciones incorporados de Python (lo que significa que es muy rápida, independientemente del tamaño de la colección).
* No puedes usar `len()` en tipos de datos individuales como números enteros (`int`), flotantes (`float`) o booleanos (`bool`), ya que no son colecciones.

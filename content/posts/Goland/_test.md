# TEST

## La Regla de Oro
 - Un test es código que ejecuta otro código y afirma (asegura) que el resultado es correcto.

## Terminología Clave
 - __Aserción (Assertion/Affirmation__): El punto central del test. Es una llamada a una función (ej. assert.Equal(esperado, actual)) que verifica una condición. Si la condición es falsa, el test falla.

 - __Fixture__: Es el entorno o conjunto de datos preconfigurados que se necesitan para ejecutar un test (ej. un usuario, una conexión a base de datos, un archivo temporal).

 - __Cobertura de Código (Code Coverage)__: Una métrica que mide la cantidad de código fuente que ha sido ejecutada por los tests. Una cobertura alta no garantiza un software libre de errores, pero indica que gran parte del código ha sido ejercitado.

## Tipos de Tests (Jerarquía Piramidal)

  ### Tests Unitarios (Unit Tests) 🧪 (Los más usados)
  - __Propósito__: Probar las unidades de código más pequeñas y aisladas (funciones, métodos, structs) de forma individual.
  - __Objetivo__: Verificar la lógica interna y asegurar que el componente funciona correctamente de forma aislada.
  - __Características__: Son los más rápidos de ejecutar y deben tener la mayor cobertura.
  - __Aislamiento__: Deben estar completamente aislados de dependencias externas (bases de datos, APIs externas, sistemas de archivos). Esto se logra usando Mocks o Stubs.

  ### Tests de Integración (Integration Tests) 🔗
   - __Propósito__: Probar la interacción entre dos o más unidades o componentes (ej. un servicio y una base de datos, o dos microservicios).
   - __Objetivo__: Verificar que las partes del sistema funcionan correctamente cuando se unen.
   - __Características__: Son más lentos que los unitarios porque involucran recursos reales.
  
  ## Tests de Extremo a Extremo (End-to-End o E2E) 🛣️ (Los menos usados)   


## Testing Específico en Go (Go Testing)
  - El archivo donde se implementa el test debe terminar en `_test.go`.  
  - El archivo de test puede estar en el mismo paquete que el código que prueba (ej. package main) o en un paquete separado (ej. package main_test), lo cual es útil para probar solo la interfaz pública.  
  - `go test` ejecuta todos los archivos terminados en `_test.go`
  - `go test ./...`: Ejecuta los tests de forma recursiva en todos los subdirectorios.
  - `go test -v`: Muestra una salida verbosa, incluyendo los tests que pasaron.
  - `go test -run NombreDeTest:` Ejecuta solo los tests cuyo nombre coincide con el patrón especificado (útil para ejecutar un subconjunto de tests).
  - `go test -cover`: Muestra el porcentaje de cobertura de código.



  ```go
    // La estructura del nombre es TestNombreFuncion con el parametro (t *testing.T)
     func TestNombreFuncion(t *testing.T) {}
  ```  
   ### Funciones
   - `t.Fail()`: Indica que el test ha fallado, pero continúa la ejecución del test.	No detiene la función.
   - `t.Errorf(...)`: Combina t.Logf() y t.Fail(). Registra el error y el test falla.	No detiene la función.
   - `t.Fatal(...)`: Registra el error e inmediatamente detiene la ejecución del test.	Detiene la función (similar a panic).
   - `t.Run(...)`: Permite agrupar subtests dentro de un test principal (útil para pruebas basadas en tablas).	Estructura la salida y permite ejecutar tests específicos.

## Tests Basados en Tablas (Table Driven Tests)
  - Esta es la técnica más usada y recomendada en Go. Consiste en definir un slice de structs (la "tabla") donde cada elemento representa un caso de prueba.

  ```go
    func TestSuma(t *testing.T) {
        // La tabla de datos
    tests := []struct {
        name   string
        a      int
        b      int
        esperado int
    }{
        {"Positivos", 1, 2, 3},
        {"Negativos", -1, -2, -3},
        {"Cero", 0, 5, 5},
    }

    for _, tt := range tests {
        t.Run(tt.name, func(t *testing.T) { // tt.name se usa para el t.Run
            resultado := Suma(tt.a, tt.b) // se ejecuta la funcion suma que es la se esta probando.
            if resultado != tt.esperado {
                t.Errorf("Suma(%d, %d) dio %d, se esperaba %d", tt.a, tt.b, resultado, tt.esperado)
            }
        })
    }
  }
  ```
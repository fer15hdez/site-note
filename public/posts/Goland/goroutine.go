package main

import (
	"fmt"
	"sync" // Este es el paquete que necesitamos importar para usar WaitGroup
)

func saludar(ch chan bool) {

	fmt.Println("¡Hola desde la goroutine!")
	// nombre_chanel <- valor
	ch <- true
}

func saludarWaitGroup(wg *sync.WaitGroup) {
	// Importante usar el defer para que termine la ejecucion de la funcion aunque de error
	defer wg.Done() // 3. El contador disminuye en 1 cuando la goroutine termina
	fmt.Println("¡Hola desde la goroutine!")
}

func main() {
	// se crea un chanel de tipo bool. Puede ser de cualquier tipo
	// pero en este caso se usa bool para indicar que la goroutine ha terminado
	channel := make(chan bool)

	// Se llama a la goroutine con el termino "go nombre_función(canal)".
	// Esto permite que la función se ejecute en paralelo con el resto del programa.
	// se usa la función go para ejecutar la función saludar en una goroutine
	// Esto permite que el programa principal no se bloquee y pueda continuar ejecutándose
	// mientras la goroutine se ejecuta en paralelo.
	go saludar(channel)

	// Esto es necesario para que el programa principal no termine antes de que la goroutine haya terminado su ejecución.
	// Se espera a que la goroutine envíe un valor al canal.
	// Mientras no llegue la señal del goroutine la funcion no sigue ejecutandose
	<-channel
	// tambien se puede usar el valor enviado por el chanel
	// nom_var := <-channel

	// Asi se le asigna un valor al canal
	// channel := <- valor

	fmt.Println("El programa principal terminó.")

	// -------------- Ahora usando WaitGroup --------------
	var wg sync.WaitGroup // 1. Crea la variable WaitGroup
	wg.Add(1)             // 2. Agrega 1 al contador de tareas

	go saludarWaitGroup(&wg) // 3. Lanza la goroutine y le pasa la referencia al WaitGroup

	wg.Wait() // 4. Espera a que el contador llegue a 0

	fmt.Println("El programa principal terminó.")
}


// --------------------------------
// Pool de worker (Patron)

import (
    "fmt"
    "sync"
    "time" // Para simular un trabajo real y ver el paralelismo
)

func worker(id int, jobs <-chan int, wg *sync.WaitGroup) {
    // defer se asegura que wg.Done() se llame al terminar la goroutine
    defer wg.Done()

    // Este bucle se ejecutará hasta que el canal de trabajos se cierre.
	// Mientras no haya un close() en el main() el bucle se ejecutara hasta el infinito.
	// El for toma el primer valor del job, no se accede mediante indice.
    for job := range jobs {
        fmt.Printf("Trabajador %d comenzó a procesar el trabajo %d\n", id, job)
        time.Sleep(time.Millisecond * 500) // Simula un trabajo que toma tiempo
        fmt.Printf("Trabajador %d terminó el trabajo %d\n", id, job)
    }
}

func main() {
    // 1. Crea el canal para los trabajos
    jobs := make(chan int, 100)

    // 2. Crea el WaitGroup
    var wg sync.WaitGroup

    // 3. Lanza 3 trabajadores que estarán esperando trabajos del canal
    for i := 1; i <= 3; i++ {
        wg.Add(1)
        go worker(i, jobs, &wg)
    }

    // 4. Envía 10 trabajos al canal de trabajos
	// Cuando se agregan valores a un canal con buffer funciona como una lista para agregar
    for i := 1; i <= 10; i++ {
        jobs <- i // Se le asigna el valor al canal
    }
    
    // 5. Cierra el canal. Esto le dice a los trabajadores que ya no hay más trabajos.
    close(jobs)

    // 6. Espera a que todos los trabajadores hayan terminado sus tareas
    wg.Wait()

    fmt.Println("\nTodas las tareas han sido procesadas. Fin del programa.")
}
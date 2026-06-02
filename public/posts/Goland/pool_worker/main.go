package main

import (
	"fmt"
	"os"
	"sync"
	// Para simular un trabajo real y ver el paralelismo
	// "poolWorker/main/velocidad"
)

func worker(id int, jobs <-chan int, result chan<- int, wg *sync.WaitGroup) {
	defer wg.Done()

	for v := range jobs {
		fmt.Printf("\nValor chanel: %v ... con worder: %v", v, id)
		// time.Sleep(500)
		tmp := pow(v)
		result <- tmp
		fmt.Printf("\nValor para devolver %v ---- con worker: %v", tmp, id)
	}

}

func pow(number int) int {
	// time.Sleep(500)
	return number * number
}

func main() {
	var wg sync.WaitGroup
	chanel := make(chan int, 10)
	result := make(chan int, 10)
	numberList := []int{}

	for v := range 10 {
		numberList = append(numberList, v)
	}

	// urlPicture := []string{
	// 	"www.google.com",
	// 	"www1.google.com",
	// 	"www2.google.com",
	// 	"www3.google.com",
	// 	"www4.google.com",
	// }

	for id := range 3 {
		wg.Add(1)
		go worker(id, chanel, result, &wg)
	}

	for _, num := range numberList {
		chanel <- num
	}
	close(chanel)
	wg.Wait()

	for range 10 {
		devolveCanel := <-result
		fmt.Printf("\nValor devuelto por el canal: %v", devolveCanel)
	}

	close(result)

	wg.Wait()
	fmt.Printf("\nFin del programa de concurrencia")
	//-------------------
	carro := Coche{
		marcha:      0,
		speedActual: 0,
	}
	carro.marchaAlFrente(2)
	moverObjeto(&carro)

	// ERROR-------------------
	archivo, err := openFile("texto")

	if err != nil {
		fmt.Printf("Error: %v", err)
	}

	fmt.Printf("archivo: %v", archivo)

	nombre := "Juan"
	edad := 30
	miniCoche := Coche{}
	fmt.Printf("\nMini Coche %#v\n", &miniCoche)
	fmt.Printf("Hola, mi nombre es %s y tengo %d años. Tipo nombre %T, Tipo edad %T\n", nombre, edad, nombre, edad)

	fmt.Printf(" |%6v|  |%10s|\n", "Marcha", "Velocidad")
	fmt.Printf(" |%6d|  |%10d|\n", 1, -10)
	fmt.Printf(" |%6d|  |%10d|\n", 2, 20)

	precio := 12.34567
	fmt.Printf("El precio es: %8.2f", precio)

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

func (c *Coche) marchaAlFrente(m uint) {
	c.marcha = int(m)
	c.speedActual = 10 //GetVelocidadFactor(int(m))

	fmt.Printf("\nMarcha adelante. Marcha: %v, Velocidad %v", c.marcha, c.speedActual)
}

func (c *Coche) marchaAtras(m int) {
	c.marcha = m
	if c.marcha == -1 {
		c.speedActual = 10
		fmt.Println("En reversa")
	} else {
		c.speedActual = 0
	}
}

func (c *Coche) velocidadActual() int {
	return c.speedActual
}

func moverObjeto(m Movible) {
	m.marchaAlFrente(2)
}

// Error
func openFile(path string) (*os.File, error) {
	var errorDeArchivo ErrorDeArchivo
	archivo, err := os.Open(path)

	if err != nil {
		errorDeArchivo.NombreArchivo = path
		errorDeArchivo.Err = err
		return nil, &errorDeArchivo
		// log.Printf("\nError al abrir archivo en funcion openFile: %v", errorDeArchivo)
	}

	return archivo, nil
}

type ErrorDeArchivo struct {
	NombreArchivo string
	Err           error
}

func (e *ErrorDeArchivo) Error() string {
	return fmt.Sprintf("error al procesar el archivo %s: %v", e.NombreArchivo, e.Err)
}

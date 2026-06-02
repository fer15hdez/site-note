// Búsqueda en Arrays con sus métodos

//****** indexOf ******/ 
// El método indexOf te permite encontrar la posición de un elemento dentro de un Array. 
// Si el elemento no existe, entonces retorna -1.
const emojis = ['✨', '🥑', '😍']
const posicionCorazon = emojis.indexOf('😍')
console.log(posicionCorazon) // -> 2

// --------------------------------------
//****** includes ******/ 
// El método includes determina si un Array incluye un determinado elemento, 
// devolviendo true o false según corresponda.
const emojis1 = ['✨', '🥑', '😍']
const tieneCorazon = emojis1.includes('😍')
console.log(tieneCorazon) // -> true
// El método .includes() también funciona con las cadenas de texto. 
// Puedes utilizarlo para buscar una subcadena dentro de una 
// cadena de texto: 'Hola mundo'.includes('Hola') // -> true

// --------------------------------------
//****** some ******/ 
// El método some te permite verificar si al menos uno de los elementos de un Array cumple 
// con una condición.
// Se le pasa una función como argumento. Esta función recibe como argumento cada uno de 
// los elementos del Array y debe retornar un valor booleano.
const emojis2 = ['✨', '🥑', '😍']
const tieneCorazon2 = emojis2.some(emoji => emoji === '😍')
console.log(tieneCorazon) // -> true

// --------------------------------------
//****** every ******/ 
// El método every te permite verificar si todos los elementos de un Array cumplen con una 
// condición.
// Se le pasas una función como argumento. Esta función recibe como argumento el elemento del 
// array que está iterando en ese momento y debe retornar un valor booleano para saber si el 
// elemento cumple con la condición.
// ¿Todos los números son pares?
const numbers = [2, 4, 7, 10, 12]
const todosSonPares = numbers.every(number => number % 2 === 0)
console.log(todosSonPares) // -> false

// --------------------------------------
//****** find ******/ 
// - El método find te permite encontrar el primer elemento que cumple con una condición.
// - Devuelve el elemento en sí

const numbers1 = [13, 27, 44, -10, 81]
// encuentra el primer número negativo
const firstNegativeNumber = numbers1.find(number => number < 0)
console.log(firstNegativeNumber) // -> -10

// - Si no encuentra ningún elemento que cumpla con la condición, el método find retorna undefined.
// - Igual que some y every, el método find deja de iterar sobre el Array en cuanto encuentra 
// un elemento que cumple con la condición.

// --------------------------------------
//****** findIndex ******/ 
// - Devuelve el índice del primer elemento que cumple con la condición
const numbers2 = [13, 27, 44, -10, 81]
// encuentra el índice del primer número negativo
const firstNegativeNumberIndex = numbers2.findIndex(number => number < 0)
console.log(firstNegativeNumberIndex) // -> 3
// ahora puedes usar el índice para acceder al elemento
console.log(numbers[firstNegativeNumberIndex]) // -> -10

// ******** Transformación de Arrays en JavaScript ********
//****** filter ******/
// filter: Un nuevo Array con los elementos que cumplan una condición (funcion)
const numbers3 = [1, 2, 3, 4, 5, 6, 7]
const evenNumbers = numbers3.filter(function (number) {
  return number % 2 === 0
})
console.log(evenNumbers) // [2, 4, 6]

// --------------------------------------
//****** map ******/
// map: Un nuevo Array con los elementos transformados
// El método map crea un nuevo array de la misma longitud que el original, pero con los 
// elementos transformados por una función que le pasamos como parámetro. 
const numbers4 = [1, 2, 3]
const doubleNumbers = numbers4.map((number) => {
  return number * 2
})
console.log(doubleNumbers) // [2, 4, 6]

// --------------------------------------
// ****** map + filter: Un nuevo Array con los elementos transformados y filtrados ******
const numbers5 = [1, 2, 3, 4, 5, 6, 7]
const numsGreaterThanFive = numbers5
  .map(number => number * 2) // [2, 4, 6, 8, 10, 12, 14]
  .filter(number => number > 5) // [6, 8, 10, 12, 14]
console.log(numsGreaterThanFive) // [6, 8, 10, 12, 14]

// --------------------------------------
//****** reduce: Un único valor a partir de un array //******
// - Este método te permite crear un único valor a partir de un Array.
// - Recibe dos parámetros: una función que se ejecutará por cada elemento y un valor inicial, 
// opcional, que será donde podremos acumular los valores.

// El acumulador: el valor que se va a ir acumulando en cada iteración
// El elemento actual: el elemento del Array que estamos iterando en ese momento.
// El índice: la posición del elemento actual en el Array.
const numbers6 = [1, 2, 3]
const sum = numbers6.reduce((accumulator, currentNumber) => {
  return accumulator + currentNumber
}, 0) // <- el 0 es el valor inicial
console.log(sum) // 6

// --------------------------------------
const numbers7 = [1, 2, 3, 4, 5, 6, 7]

const doubleEvenNumbers = numbers7.reduce((accumulator, currentNumber) => {
  const isEven = currentNumber % 2 === 0
  const doubled = currentNumber * 2
  const isGreaterThanFive = doubled > 5

  // si es par y mayor que 5, lo añadimos al array
  if (isEven && isGreaterThanFive) {
    // para ello devolvemos un nuevo array con el valor actual
    return accumulator.concat(doubled)
  } else {
    // si no, devolvemos lo que ya teníamos
    return accumulator
  }
}, []) // <- el array vacío es el valor inicial

console.log(doubleEvenNumbers) // [8, 12]

// --------------------------------------
// ****** SORT: Ordenamiento personalizado con sort() ******

// - Un valor negativo si el primer argumento debe aparecer antes que el segundo.
// - Un valor positivo si el segundo argumento debe aparecer antes que el primero.
// - Cero si ambos argumentos son iguales.
let numeros = [5, 10, 2, 25, 7]

numeros.sort(function(a, b) {
  return a - b
})

console.log(numeros) // [2, 5, 7, 10, 25]

//*** ARRAW FUNTION ***/

// La sintaxis básica de una función flecha 
//Las funciones flecha son siempre funciones anónimas y function expressions. 
//Esto significa que no tienen nombre y que se asignan a una variable.
const miFuncionFlecha = () => {
    // código a ejecutar
  }

  //En lugar de la palabra clave function, utilizamos una flecha => para definir la función. 
  //También podemos omitir los paréntesis alrededor de los parámetros si solo tenemos uno
  const saludar = nombre => {
    console.log("Hola " + nombre)
  }

//   RETURN IMPLICITO
//   Cuando una función flecha tiene una sola expresión, podemos omitir las llaves {} y la 
//   palabra clave return para hacerla aún más corta.
// Función flecha con return implícito
const sumarFlecha = (a, b) => a + b

// function expression
const sum = function (a, b) {
    return a + b
  }
//   Cuando una función no tiene nombre se le llama función anónima.
// A las funcones expression no se le aplica el HOISTING.
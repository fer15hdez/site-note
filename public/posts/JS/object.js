
// Declaración y asignación de objetos
const persona = {
  name: 'Dani',
  age: 30,
  isWorking: true
}

//  - Las propiedades y métodos de un objeto pueden ser de cualquier tipo de JavaScript, 
//   incluso otros objetos o arrays.
const persona1 = {
  name: 'Dani',
  age: 30,
  isWorking: true,
  family: ['Miguel', 'Maria'], // array
  address: { // otro objeto
    street: 'Calle de la piruleta',
    number: 13,
    city: 'Barcelona'
  }
}

//   METODOS
const persona2 = {
  name: 'Dani',
  age: 30,
  isWorking: true,
  family: ['Miguel', 'Maria'],
  address: {
    street: 'Calle de la piruleta',
    number: 13,
    city: 'Barcelona'
  },
  walk: function () { // <- método
    console.log('Estoy caminando')
  }
}

//   Acceder a propiedades y métodos de un objeto
const persona3 = { name: 'Dani' }
console.log(persona3.name) // Dani
// - Puedes usar variables para acceder a las propiedades y métodos de un objeto. 
// Para ello, tienes que usar la notificación por corchetes [].
const persona4 = { name: 'Dani' }
let property = 'name'

console.log(persona4[property]) // -> Dani

// - Para acceder a las propiedades de un objeto anidado, podemos usar la notación de corchetes o 
// la notación de punto:
const spiderman2 = {
  name: 'Spidey',
  universe: 42,
  powers: ['web', 'invisibility', 'spider-sense'],
  partner: {
    name: 'Mary Jane',
    universe: 42,
    powers: ['red hair', 'blue eyes']
  }
}

console.log(spiderman2.partner.name) // 'Mary Jane'
console.log(spiderman2['partner']['name']) // 'Mary Jane'

// Acceder a un metodo
const persona5 = {
  name: 'Dani',
  walk: function () {
    console.log('Estoy caminando')
  }
}
persona5.walk() // -> Estoy caminando

let method = 'walk'
persona5[method]() // -> Estoy caminando

//   Eliminar propiedades de un objeto
const persona6 = { name: 'Dani', age: 18 }
delete persona6.age
console.log(persona6) // -> { name: 'Dani' }


//   ----------------------------------------------------------

// Iterando con for...in
// - La estructura de control for...in nos permite crear un bucle que itera sobre todas las 
// propiedades enumerables de un objeto, en un orden arbitrario. 
const spiderman = {
  name: 'Spidey',
  universe: 42,
  powers: ['web', 'invisibility', 'spider-sense']
}

for (const property in spiderman) {
  console.log(`${property}: ${spiderman[property]}`);
}

// -> name: Spidey
// -> universe: 42
// -> powers: web,invisibility,spider-sense

//   ----------------------------------------------------------
// Transformar un objeto en un array

// Iterar con Object.keys()
// El método Object.keys() retorna un array con las propiedades enumerables de un objeto.
const spiderman1 = {
  name: 'Spidey',
  universe: 42,
  powers: ['web', 'invisibility', 'spider-sense']
}

const properties = Object.keys(spiderman1)

console.log(properties.length) // 3

properties.forEach(property => {
  console.log(property)
})

// -> name
// -> universe
// -> powers


//   ---------
// Iterando con Object.values()

// El método Object.values() retorna un array con los valores correspondientes a las 
// propiedades enumerables de un objeto.
const values = Object.values(spiderman)

values.forEach(value => {
  console.log(value)
})

// -> Spidey
// -> 42
// -> [ 'web', 'invisibility', 'spider-sense' ]

//   ---------
// Iterando con Object.entries()
// El método Object.entries() retorna un array de arrays, donde cada subarray es 
// un par [propiedad, valor] correspondiente a las propiedades enumerables de un objeto.
const entries = Object.entries(spiderman)

entries.forEach(entry => {
  console.log(entry)
})

// -> [ 'name', 'Spidey' ]
// -> [ 'universe', 42 ]
// -> [ 'powers', [ 'web', 'invisibility', 'spider-sense' ] ]

//Another example
const entries1 = Object.entries(spiderman)

entries1.forEach(entry => {
  const property = entry[0]
  const value = entry[1]

  console.log(`${property}: ${value}`)
})

// ----------------------------------------------------------------------------------

// El operador in para comprobar si una propiedad existe
// Este operador comprueba si una propiedad existe en un objeto y devuelve true o false
const gamesystem = {
  name: 'PS5',
  price: 550,
  specs: {
    cpu: 'AMD Ryzen Zen 2',
    gpu: 'AMD Radeon RDNA 2',
  }
}
console.log('name' in gamesystem) // -> true
console.log('specifications' in gamesystem) // -> false

if ('specifications' in gamesystem) {
  console.log(gamesystem.specifications.ram)
} else {
  console.log('No hay especificaciones')
}

// ----------------
// El operador de Encadenamiento Opcional, ?.
// - El Operador de Encadenamiento Opcional ?. permite leer el valor de una propiedad 
// ubicada profundamente dentro de una cadena de objetos conectados, sin tener que validar 
// expresamente que cada referencia en la cadena es válida.
console.log(gamesystem.specifications?.cpu)
// -> undefined
console.log(gamesystem.specs?.cpu)
// -> AMD Ryzen Zen 2
console.log(gamesystem[specs]?.[cpu])
// -> AMD Ryzen Zen 2

// ----------------------------------------------------------------------------------
// Object references and copying (cloning)
let user1 = { name: "John" };
let admin = user1; // copy the reference
console.log(admin.name) // John

// **** Comparison by reference *****
//  Two objects are equal only if they are the same object, reference the same object. 

// *** Cloning and merging ***
// The first argument dest is a target object.
// Further arguments is a list of source objects.
Object.assign(dest, ...sources)
let user = { name: "John" };

let permissions1 = { canView: true };
let permissions2 = { canEdit: true };

// copies all properties from permissions1 and permissions2 into user
Object.assign(user, permissions1, permissions2);

// now user = { name: "John", canView: true, canEdit: true }
alert(user.name); // John
alert(user.canView); // true
alert(user.canEdit); // true
//If the copied property name already exists, it gets overwritten

// *** Nested cloning ***
// *** structuredClone *** 
// Function properties aren’t supported.
let user2 = {
  name: "John",
  sizes: {
    height: 182,
    width: 50
  }
};

let clone = structuredClone(user2);

alert(user2.sizes === clone.sizes); // false, different objects

// user and clone are totally unrelated now
user2.sizes.width = 60;    // change a property from one place
alert(clone.sizes.width); // 50, not related

// ----------------------------------------------------------------------------------
// *** Constructor function ***
// For convention the first letter is uppercase but it works the same if used in lowercase.
function User(name) {
  this.name = name;
  this.isAdmin = false;
}
let user = new User("Jack");
alert(user.name); // Jack
alert(user.isAdmin); // false

// Methods in constructor
function User(name) {
  this.name = name;

  this.sayHi = function () {
    alert("My name is: " + this.name);
  };
}

let john = new User("John");

john.sayHi(); // My name is: John

/*
john = {
   name: "John",
   sayHi: function() { ... }
}
*/

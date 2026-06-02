### Control Structure
## for ... of ...
let frutas = ['🍎', '🍌', '🍓']

for (let fruta of frutas) {
  console.log(fruta) // imprime el elemento en la posición i
}
## array.forEach()
Uno de esos métodos es array.forEach(), que nos permite ejecutar una function para cada uno de los elementos del array. Esta función recibe como parámetros el elemento que se está iterando en ese momento, el índice del elemento y el propio array.Uno de esos métodos es array.forEach(), que nos permite ejecutar una function para cada uno de los elementos del array. Esta función recibe como parámetros el elemento que se está iterando en ese momento, el índice del elemento y el propio array.

let frutas = ['🍎', '🍌', '🍓']

frutas.forEach(function (fruta, index, originalArray) {
  console.log(fruta) // imprime el elemento en la posición i
})

### ------------------------------------------------------------------------------------------------
### Numeric Conversion – Occurs in math operations.
Value 	Becomes…
undefined ->	NaN
null ->	0
true / false ->	1 / 0
string ->	The string is read “as is”, whitespaces (includes spaces, tabs \t, newlines \n etc.) from both sides are ignored. An empty string becomes 0. An error gives NaN. 

***Example***
alert( Number("   123   ") ); // 123
alert( Number("123z") );      // NaN (error reading a number at "z")
alert( Number(true) );        // 1
alert( Number(false) );       // 0

### Boolean Conversion – Occurs in logical operations.
Value 	Becomes…
0, null, undefined, NaN, "" ->	false
any other value ->	true

### Coercing Primitive Values.  
  -  **anything → boolean:** If it’s null, undefined, 0 (or -0), '’ (empty string), or false, the result of the coercion is false. If it’s ANYTHING else, it’s true
 -   **number → string:** the string representation of that number (which might be the scientific notation format… seeNumber.toString() if you need to know when it uses that).
 -   **string → number:** if the string represents a valid number (including formats like scientific notation, hexadecimals, octals, etc), it’s converted to that number. And if it’s an empty string, it’s 0. Otherwise it’s just NaN.
 -  **boolean → number:** true becomes 1, false becomes 0
 -  **null or undefined → number:** 0 and NaN respectively
 -  **null or undefined → string:** 'null' and 'undefined' respectively
 -  **Objects “Coerce” to Primitives** → It's convert to primitive type from 'valueOf function'. If that doesn’t work, it tries to get a primitive value from toString function.  
 -  **array** → toString returns a string that joins the string representations of its values with a comma. So[1, {}, 3].toString() will return '1,[object Object],3'.  
    + for empty arrays, or arrays with a single null value, it will return an empty string.  

1. - A string that doesn’t represent a number is NaN.  
2. - There’s only 5 things that coerce to false, everything else just becomes true.  
3. - Null, undefined, and numbers are all spelled exactly like the strings that you’d expect.  
4. - undefined is Not-A-Number.  

1. **Comparison between a boolean and a non-boolean value:**
The boolean value true is considered greater than any non-boolean value (except NaN and undefined).
The boolean value false is considered less than any non-boolean value (except NaN and undefined).
2. **Comparison between a number and a string:**
If the string can be parsed as a valid number, it will be converted to a number and compared numerically.
If the string cannot be parsed as a valid number, it will be converted to NaN, and any comparison involving NaN will yield false except for NaN itself.
3. **Comparison between objects and primitive values:**
When an object is compared to a primitive value, the object is converted to a primitive value using the valueOf() and toString() methods in a specific order:
The valueOf() method is called first, and if it returns a primitive value, that value is used for comparison.
If the valueOf() method does not return a primitive value, the toString() method is called, and if it returns a primitive value, that value is used for comparison.
If both valueOf() and toString() do not return a primitive value, a TypeError is thrown.
**String Comparison:** When comparing strings, JavaScript compares them character by character based on their Unicode values. The comparison is done by comparing the Unicode value of each character from left to right.  



### Operator
The exponentiation operator a ** b raises 'a' to the power of 'b'.
alert( 2 ** 2 ); // 2² = 4

***unary + ***
- The plus operator + applied to a single value, doesn’t do anything to numbers. But if the operand is not a number, the unary plus converts it into a number.
- It actually does the same thing as Number(...), but is shorter.
// Converts non-numbers
alert( +true ); // 1
alert( +"" );   // 0

### Root of a number
alert( 4 ** (1/2) ); // 2 (power of 1/2 is the same as a square root)
alert( 8 ** (1/3) ); // 2 (power of 1/3 is the same as a cubic root)

### String concatenation with binary +
let s = "my" + "string";
alert(s); // mystring
***If any of the operands is a string, then the other one is converted to a string too.***
alert( '1' + 2 ); // "12"
alert( 2 + '1' ); // "21"
alert(2 + 2 + '1' ); // "41" and not "221"
alert('1' + 2 + 2); // "122" and not "14"

***The binary + is the only operator that supports strings in such a way. Other arithmetic operators work only with numbers and always convert their operands to numbers.***

Here’s the demo for subtraction and division:
alert( 6 - '2' ); // 4, converts '2' to a number
alert( '6' / '2' ); // 3, converts both operands to numbers

### Increase counter
***This operator do same increase over the var but the diference is the result ***
let counter = 0;
alert( ++counter ); // 1
alert( counter ); // 1

let counter = 0;
alert( counter++ ); // 0
alert( counter ); // 1

### ------------------------------------------------------------------------------------------------
### Nullish coalescing operator '??'
The result of a ?? b is:

    if 'a' is defined, then 'a',
    if 'a' isn’t null/defined, then 'b'.
### The optional chaining ?. syntax has three forms:  

    obj?.prop – returns obj.prop if obj exists, otherwise undefined.  
    obj?.[prop] – returns obj[prop] if obj exists, otherwise undefined.  
    obj.method?.() – calls obj.method() if obj.method exists, otherwise returns undefined.  

As we can see, all of them are straightforward and simple to use. The ?. checks the left part for null/undefined and allows the evaluation to proceed if it’s not so.  

A chain of ?. allows to safely access nested properties.  

### ------------------------------------------------------------------------------------------------
### Data type
A primitive
    Is a value of a **primitive** type.
    There are **7 primitive types**: string, number, bigint, boolean, symbol, null and undefined.

An object
    Is capable of storing multiple values as properties.
    Can be created with {}, for instance: {name: "John", age: 30}. There are other kinds of objects in JavaScript: functions, for example, are objects.

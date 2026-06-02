# Install and CRUD (Create, Read, Update, Delete)

## Install
https://www.mongodb.com/docs/manual/tutorial/install-mongodb-on-ubuntu/  

## DRUD Version mongoDB 7  
https://www.mongodb.com/docs/manual/crud/  

### Insert
***db.collection.insertOne() ***  
*Example*  
``` javascript 
try {
   db.collection.insertOne( 
    { item: "card", 
    qty: 15 } );
} catch (e) {
   print (e);
};
```

#### Insertar un documento con un documento embebido: -- detalles
``` javascript 
db.productos.insertOne({
  "nombre": "Smartphone Samsung Galaxy S23",
  "precio": 999.00,
  "categoria": "Electrónica",
  "stock": 10,
  "detalles": {
    "pantalla": "6.1 pulgadas AMOLED",
    "procesador": "Snapdragon 8 Gen 2",
    "memoriaRAM": "8GB"
  }
})
```

#### Insertar un documento con un array de valores: -- temas
``` javascript 
db.productos.insertOne({
  "nombre": "Curso de Desarrollo Web",
  "precio": 49.99,
  "categoria": "Educación",
  "temas": ["HTML", "CSS", "JavaScript", "Node.js"]
})
```

#### db.collection.insertMany()
*Example*  
``` javascript 
try {
   db.products.insertMany( [
      { item: "card", qty: 15 },
      { item: "envelope", qty: 20 },
      { item: "stamps" , qty: 30 }
   ] );
} catch (e) {
   print (e);
}
```

### Read
#### db.collection.find()
`db.collection.find( <query>, <projection>, <options> ) `
- Projection: El segundo argumento opcional del método find(). Es un documento JSON que especifica qué campos incluir o excluir de los documentos devueltos.   
  Returns documents in the bios collection where _id equals 5:   
    ``db.bios.find( { _id: 5 } )``

### Operadores de Comparación
  `$eq`: "igual a",  
  `$ne`: "no igual a",   
  `$gt`: "mayor que",   
  `$gte`: "mayor o igual que",   
  `$lt`: "menor que",    
  `$lte`: "menor o igual que",   
  `$in`: "coincide con cualquiera de los valores especificados en un array",   
  `$nin`: "no coincide con ninguno de los valores especificados en un array"  

### Operadores Lógicos     
   `$and`: (y),   
   `$or`: (o),   
   `$not`: (no),   
   `$nor`: (ni)  

### Operadores de Elemento  
`$exists`: (verifica si un campo existe),   
`$type`: (verifica el tipo de un campo).  

### Operadores de Evaluación
`$regex`: (permite búsquedas basadas en expresiones regulares),   
`$where`: (permite usar expresiones JavaScript para la consulta).  

### Operadores de Array  
`$all`: (coincide con documentos que contienen todos los elementos especificados en un array),   
`$elemMatch`: (coincide con documentos que contienen un elemento dentro de un array que coincide con todos los criterios especificados),   
`$size`: (coincide con documentos cuyo array tiene un tamaño específico).    

*Ejemplos*  
`db.productos.find({ "categoria": "Electrónica", "stock": { $gt: 10 } })`   
`db.productos.find({ $or: [ { "precio": { $lt: 30 } }, { "categoria": "Libros" } ] })`   
`db.productos.find({}, { "nombre": 1, "precio": 1, "_id": 0 })`: Utilizar proyecciones para incluir solo ciertos campos (nombre y precio)  

`db.productos.find({ "nombre": { $regex: "portátil", $options: "i" } })`: todos los productos cuyo nombre contenga la palabra "portátil" (sin importar si está en mayúsculas o minúsculas: $options: "i").    

`db.productos.find({ "nombre": { $regex: "^M" } })`: todos los productos cuyo nombre comience con la letra "M".  
*Esto devolverá todos los productos cuya categoría sea "Electrónica" o "Libros"*    
`db.productos.find({ "categoria": { $in: ["Electrónica", "Libros"] } })`: Utilizar operadores de array ($in para buscar productos en ciertas categorías)   

`db.collection.find().sort({ <campo1>: <orden>, <campo2>: <orden>, ... })`: Ordenar resultados.  `1`: ascedente, `-1`: descendente.  
`db.productos.find().sort({ precio: -1 }).limit(10)`: Limitar resultados en 10.  

``` javascript 
db.productos.insertMany([
  { "nombre": "Producto A", "etiquetas": ["rojo", "grande", "brillante"] },
  { "nombre": "Producto B", "etiquetas": ["azul", "pequeño"] },
  { "nombre": "Producto C", "etiquetas": ["grande", "brillante"] }
]);
db.productos.find({ "etiquetas": { $all: ["grande", "brillante"] } })
```
- Esto devolverá los documentos "Producto A" y "Producto C", ya que ambos tienen las etiquetas "grande" y "brillante".

#### Contar documentos
`db.miColeccion.countDocuments({})` : contar todos los documentos en una colección
`db.miColeccion.countDocuments({ estado: "activo" })` : contar documentos que cumplen con un criterio específico


#### db.collection.findOne()
- Devuelve un único documento que coincide con los criterios de búsqueda especificados.  



#### db.collection.aggregate()
 *Se usa para el framework de agregación, que permite procesar y transformar documentos a través de una serie de etapas (como `$lookup, $match, $project, $group,` etc.).*  
 - Permite realizar transformaciones complejas, cálculos y análisis en colecciones de documentos.  
 - Te permite remodelar, filtrar, agrupar, unir y analizar datos de maneras que serían difíciles o imposibles con las operaciones de consulta básicas.  
 - La agregación en MongoDB se basa en el concepto de una "pipeline" (tubería o cadena de etapas). Imagina que tus documentos fluyen a través de una serie de etapas, y en cada etapa, se realiza una operación específica sobre los documentos. La salida de una etapa se convierte en la entrada de la siguiente.  
 
 *Sintaxis básica*  
  ```javascript
      db.collection.aggregate([
          { <etapa1> },
          { <etapa2> },
          { <etapa3> },
          // ... más etapas
        ]);
  ```
*Etapas comunes*  
- `$match` (Filtrar documentos)  
- `$project` (Remodelar documentos)  
- `$group` (Agrupar y agregar datos):  Agrupa los documentos de entrada por una expresión de identificador especificada y aplica expresiones acumuladoras a cada grupo.  
  Es similar a GROUP BY en SQL.  
  + Operadores acumuladores comunes: $sum, $avg, $min, $max, $count, $push, $addToSet.  
    ``` javascript
        // Contar el número de usuarios por ciudad
      {
        $group: {
          _id: "$city", // Agrupar por el campo 'city'
          totalUsuarios: { $sum: 1 }, // Contar cada documento en el grupo
          edadPromedio: { $avg: "$age" } // Calcular el promedio de 'age'
        }
      }
    ```
- $sort (Ordenar documentos): Reordena los documentos de entrada.  
  `{ $sort: { edad: 1, nombre: -1 } } // Ordenar por edad ascendente (1), luego por nombre descendente(-1) `   
- $limit (Limitar el número de documentos): Pasa solo los primeros n documentos a la siguiente etapa.  
  `{ $limit: 10 } // Pasar solo los primeros 10 documentos`  
- $skip (Omitir documentos): Omite los primeros n documentos y pasa el resto.  
  `{ $skip: 20 } // Omitir los primeros 20 documentos`
- $unwind (Desestructurar arrays): Desestructura un campo de array de los documentos de entrada. Por cada elemento del array, $unwind genera un nuevo documento de salida,  
  con el elemento del array reemplazando el array.   
- $lookup (Unir colecciones): Realiza una operación de "left outer join" a otra colección en la misma base de datos.  
    ``` javascript
    {
      $lookup: {
        from: "orders",        // Colección a la que se une
        localField: "userId",  // Campo en la colección actual
        foreignField: "_id",   // Campo en la colección 'orders'
        as: "userOrders"       // Nombre del nuevo campo que contendrá los documentos unidos
      }
    }
    ```
- $addFields (Agregar nuevos campos): Agrega nuevos campos a los documentos de entrada y mantiene los campos existentes. Similar a $project, pero está diseñado para añadir sin eliminar otros campos por defecto. Calcular nuevos valores basados en campos existentes, enriquecer documentos.   
    ```JavaScript
    { $addFields: 
      { totalVentas:  {
      $multiply: ["$cantidad", "$precio"] 
        }
      }
    } 
    ```

- $unset (Eliminar campos - MongoDB 4.2+)  
`{ $unset: ["password", "tempField"] }`


### SIMULACION DE JOIN

``` javascript 
// La colleccion "contents" tiene un campo "staff" de tipo array donde almacena los id de la colleccion "users".  
 db["contents"].aggregate([
  {
    $match: { // Se aplica filtro dentro de la colecion "contents"  
      _id: ObjectId('67d98e24750cb7002820d2fd') // Trae solo a la coleccion con el id especificado.  
    }
  },
  { // El $lookup simula el JOIN  
    $lookup: {
      from: "users", //nombre_de_la_coleccion_foranea
      localField: "staff", // campo_en_la_coleccion_de_entrada
      foreignField: "_id", // campo_en_la_coleccion_foranea
      as: "staffDetails" // nombre_del_campo_de_salida_en_array
    }
  },
  {
    $project: {
      // Remodela cada documento en el flujo, incluyendo, excluyendo o renombrando campos. Puedes agregar nuevos campos calculados o reestructurar campos existentes.  
      // La opcion $project permite la opcion de mostrar (con 1) u ocultar (con 0) campos. Solo se puede usar una de las dos opciones.

      // Aquí puedes incluir los campos que quieres conservar del documento original (si los hubiera, como _id)
      // y usar expresiones para transformar otros campos.
      
      // Incluye _id del documento original si lo necesitas en alguna etapa posterior.
      // Si no lo necesitas en absoluto, puedes omitirlo o establecerlo a 0 aquí.
      // _id: "$_id", // Opcional, si quieres conservar el _id original para futuras operaciones.

      // Trabajando sobre el campo que contiene el array de documentos foraneos.  
      // Transforma staffDetails usando $map
      staffDetails: {
        $map: {
          input: "$staffDetails",
          as: "staffMember",
          in: {
            // Define la forma del nuevo objeto para cada elemento del array.  
            // Define los campos que quieres incluir de cada staffMember
            firstName: "$$staffMember.name",
            lastName: "$$staffMember.lastName",  
            email: "$$staffMember.email"
            // Si quieres excluir _id del usuario, simplemente no lo incluyas aquí.
          }
        }
      },
      // Incluye subject y number si los necesitas en la siguiente etapa para excluirlos después.
      // Si ya sabes que no los quieres, puedes omitirlos aquí.
      subject: 1, // Inclúyelo para poder excluirlo en la siguiente etapa
      number: 1   // Inclúyelo para poder excluirlo en la siguiente etapa
    }
  },
  {
    $project: {
      // Siguiente etapa $project:
      // Excluye los campos finales que no deseas en el resultado final.
      _id: 0,
      subject: 0,
      number: 0
      // staffDetails se mantiene porque no lo excluimos aquí y fue creado en la etapa anterior.
    }
  }
]);

```

****

### Update
db.collection.updateOne(filter, update, options)   
**Syntax**
``` javascript
db.collection.updateOne(
   <filter>,
   <update>,
   {
     upsert: <boolean>,
     writeConcern: <document>,
     collation: <document>,
     arrayFilters: [ <filterdocument1>, ... ],
     hint:  <document|string>        // Available starting in MongoDB 4.2.1
   }
)
```  

**Example**  
``` javascript
db.students.updateOne(
   { _id: 1 },
   { $set: { key: "value", } },
)
``` 

`db.collection.updateMany()`  
**ReplaceOne() replaces the first matching document in the collection that matches the filter, using the replacement document.**
``` javascript 
db.collection.replaceOne()    
db.collection.replaceOne(
   <filter>,
   <replacement>,
   {
      upsert: <boolean>,
      writeConcern: <document>,
      collation: <document>,
      hint: <document|string>                   // Available starting in 4.2.1
   }
)
```

### Delete

**Sintaxis**
***Deletes the first document that matches the filter. Use a field that is part of a unique index such as _id for precise deletions.***
``` javascript 
db.collection.deleteOne(
    <filter>,
    {
      writeConcern: <document>,
      collation: <document>,
      hint: <document|string>
    }
)
```

**Example**
``` javascript 
try {
   db.orders.deleteOne( { "_id" : ObjectId("563237a41a4d68582c2509da") } );
} catch (e) {
   print(e);
}
db.collection.deleteMany()
db.collection.deleteMany(
   <filter>,
   {
      writeConcern: <document>,
      collation: <document>
   }
)
```

**Example** 
**The following operation deletes all documents where client : "Crude Traders Inc.":** 
``` javascript 
try {  
   db.orders.deleteMany( { "client" : "Crude Traders Inc." } );  
} catch (e) {  
   print (e);  
}   
```
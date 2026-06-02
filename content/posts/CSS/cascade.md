# Cascade  
## Specificity
La especificidad es el modo que tiene el navegador de decidir qué regla se aplica si diversas reglas tienen selectores diferentes pero podrían aplicarse a un mismo elemento.

1. Un selector de elemento es menos específico (selecciona todos los elementos de aquel tipo que aparecen en la página) por lo que presenta una puntuación más baja en especificidad.
1. Un selector de clase es más específico (selecciona solo los elementos de una página que tienen un valor de atributo class dado), y por tanto recibe una puntuación mayor.
<pre><code>
    .main-heading { 
        color: red; 
    }
            
    h1 { 
        color: blue; 
    }
 
</code></pre>
```
 <h1 class="main-heading">This is my heading.</h1> 
```
La clase es la que prevalece sobre el selector h1.

### The order of importance, from least important, to most important is as follows:

  1.  ***normal***  rule type, such as font-size, background or color
  1.  ***animation*** rule type
  1.  ***!important*** rule type (following the same order as origin)
  1.  ***transition*** rule type

 ## Comprender la cascada
 Hay que considerar tres factores, que se enumeran a continuación en orden de importancia creciente. Los posteriores invalidan los anteriores:

1.   Orden en el código
1.   Especificidad
1.   Importancia

Un elemento que hay que tener en cuenta es que aunque pensamos en términos de selectores y reglas que se aplican a lo que estos seleccionan, **no es toda la regla lo que se sobrescribe**, sino solo las propiedades que entran en conflicto.

Este comportamiento ayuda a evitar repeticiones en el CSS. Una práctica común es definir estilos genéricos para los elementos básicos y luego, crear clases para los elementos que son diferentes.

La **cantidad de especificidad de un selector** se mide usando cuatro valores diferentes (o componentes), que pueden describirse como **millares, centenas, decenas y unidades** (cuatro dígitos individuales dispuestos en cuatro columnas):

   1. **Millares:** Se suma un punto en esta columna si la declaración está en un atributo de style o, como suelen denominarse, estilos en línea. Tales declaraciones no tienen selectores, por lo que su especificidad siempre es 1000.
   1. **Centenas:** Se suma un punto en esta columna por cada selector con ID particular que esté contenido en el selector general.
   1. **Decenas:** Se suma un punto en esta columna por cada selector de clase, de atributo o pseudoclase que estén contenidos en el selector general.
1. **Unidades:** Se suma un punto en esta columna por cada selector o pseudoelemento que esté contenido en el selector general.

**Nota**: El selector universal (*), los operadores de combinación (+, >, ~, ' ') y la pseudo-clase de negación (:not) no tienen ningún efecto sobre la especificidad.

## Propiedad !important

<code>!important.</code> Se utiliza para convertir una propiedad y un valor particular en el elemento más específico, de modo que se invalidan las reglas normales de la cascada.

**Nota:** La única manera de anular la declaración !important sería incluir otra declaración !important en una declaración con la misma especificidad que aparezca más adelante en el orden del código fuente, o con una especificidad superior.

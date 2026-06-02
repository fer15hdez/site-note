// La clase Canine es la clase abstracta.
// Hereda de Animal.
// De las clases abstractas no se puede crear instancias ni objetos.
// El principal objetivo es que sean heredables.
// Las clases abstractas pueden tener metodos abstractos y no abstractos.

// You cannot make a new instance of an abstract type but you CAN make an array object declared to HOLD that type.
abstract public class Canine extends Animal
{
    public void roam() { }

    // - Los metodos abstractos deben ser sobreescritos(overridden). La primera clase concreta 
    // que herede de la clase abstracta (Canine) debe implementar el metodo.
    // En el caso que Canine implemente un metdo que es abstracto en Animal la clase que herede 
    // de Canine no tiene que implementar el metodo, pero si en Canine no se implementa el metodo 
    // abstracto de Animal la primera clase concreta que herede de Canine debe implementar el metodo abstracto de Animal.
    // - Sobreescribir el metodo debe tener el mismo nombre, la misma cantidad de argumentos, devolver un tipo compatible
    // con el metodo abstracto.
    // - En la sintaxis el metodo no tiene {} y termina en ; , es decir no tiene cuerpo.
    // - Si se declara un metodo abstracto la clase debe ser abstracta tambien. 
    public abstract void eat();

    // 
}
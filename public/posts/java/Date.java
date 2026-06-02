import java.time.*; // Este paquete contiene LocalDate, LocalTime y LocalDateTime.
import java.time.format.*;  // Este paquete contiene DateTimeFormatter.

public class Date {
    
    public static void main(String[] args) {
        LocalTime ahora = LocalTime.now();
        System.out.println(ahora);
    }
}

// Las clases LocalDate, LocalTime y LocalDateTime no mantienen relación alguna de herencia. 
// Esto quiere decir que no puedes hacer uso del polimorfismo. Por ello, una referencia de tipo LocalDateTime no puede
// apuntar a un objeto de tipo LocalDate
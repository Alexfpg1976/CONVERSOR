import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

// ALMACENA EL HISTORIAL DE CONVERSIONES, OBJETO DE CONVERSIONES,
// Y LEE LA ENTRADA DEL USUARIO.
public class SUBMENU {
    private final List<CONVERSOR> historial;
    private final CONSULTA consulta;
    private final Scanner entrada;

    // CONSTRUCTOR INICIAL DE VARIABLES
    public SUBMENU(List<CONVERSOR> historial, CONSULTA consulta, Scanner entrada) {
        this.historial = historial;
        this.consulta = consulta;
        this.entrada = entrada;
    }

    // METODO PRINCIPAL QUE INICIA EL SUBMENU
    public void mostrarSubMenu() {
        boolean volverMenu = false;
        // BUCLE QUE MANTIENE EL SUBMENU HASTA QUE EL USUARIO DECIDE VOLVER
        while (!volverMenu) {
            MOSTRAROP();
            try {
                // LEE LA OPCION SELECCIONADA POR EL USUARIO
                int opcion = entrada.nextInt();
                switch (opcion) {
                    case 1 -> MANEJARCONVERSION("USD", "ARS");
                    case 2 -> MANEJARCONVERSION("ARS", "USD");
                    case 3 -> MANEJARCONVERSION("USD", "BRL");
                    case 4 -> MANEJARCONVERSION("BRL", "USD");
                    case 5 -> MANEJARCONVERSION("USD", "COP");
                    case 6 -> MANEJARCONVERSION("COP", "USD");
                    case 7 -> volverMenu = true; // Regresar al menú principal
                    default -> System.out.println("OPCION NO VALIDA!\n");
                }
            } catch (InputMismatchException e) {
                // MANEJO DE ERRORES SI LA ENTRADA NO ES UN NUMERO
                System.out.println("ERROR: ENTRADA NO VALIDA. INGRESA UN NUMERO.");
                entrada.next(); // LIMPIA EL BUFFER
            }
        }
    }

    //METODO QUE MUESTRA LAS OPCIONES DE CONVERSION
    public static void MOSTRAROP() {
        System.out.println("""
                ******************* SUBMENU *********************
                
                1 - DOLAR - A - PESO ARGENTINO
                2 - PESO ARGENTINO - A - DOLAR
                3 - DOLAR - A - REAL BRASILEÑO
                4 - REAL BRASILEÑO - A - DOLAR
                5 - DOLAR - A - PESO COLOMBIANO
                6 - PESO COLOMBIANO - A - DOLAR
                7 - VOLVER AL MENU PRINCIPAL
                
                ELIJA UNA OPCION VALIDA
                
                *************************************************""");
    }
    // METODO PARA MANEJAR LA CONVERSION DE MONEDAS
    private void MANEJARCONVERSION(String moneda1, String moneda2) {
        double valor = 0;
        boolean valorValido = false;

        //BUCLE QUE ASEGURA QUE EL VALOR INGRESADO SEA VALIDO
        while (!valorValido) {
            try {
                System.out.print("\nINGRESA EL VALOR QUE DESEAS CONVERTIR: ");
                valor = entrada.nextDouble();
                valorValido = true; // VALOR PARA SALIR DEL BUCLE
            } catch (InputMismatchException e) {
                System.out.println("ERROR: DEBES INGRESAR NUMEROS NO LETRAS. INTENTA NUEVAMENTE.");
                entrada.next(); // LIMPIAR BUFFER
            }
        }

        // METODO QUE IMPRIME Y GUARDA LA CONVERSION
        System.out.println();
        IMPRIMIRCONVERSORYGUARDAR(historial, consulta, moneda1, moneda2, valor);
    }

    //METODO QUE IMPRIME Y GUARDA LA CONVERSION
    public void IMPRIMIRCONVERSORYGUARDAR(List<CONVERSOR> HISTORIAL, CONSULTA cambiar_,
                                          String moneda1, String moneda2, double valor) {
        MONEDA moneda = cambiar_.cambiarMoneda(moneda1, moneda2, valor);
        System.out.println(moneda.cambioDeMoneda(valor) + "\n");
        CONVERSOR historial = new CONVERSOR(moneda, LocalDateTime.now(), valor);
        HISTORIAL.add(historial);
    }
}

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PRINCIPAL {
    public static void main(String[] args) {
        List<CONVERSOR> historialSS = new ArrayList<>();
        CONSULTA consultar_ = new CONSULTA();
        Scanner entrada = new Scanner(System.in);

        System.out.println("******************************************************");
        System.out.println("******** BIENVENIDO/A AL CONVERSOR DE MONEDAS ********");

        boolean bandera = false;

        while (!bandera) {
            mostrarOpciones();

            try {
                bandera = convertirMoneda(entrada, historialSS, consultar_);
            } catch (InputMismatchException e) {
                System.out.println("NO PUEDES INGRESAR LETRAS, SOLO NÚMEROS!!!!\n");
                entrada.next(); // Limpiar el buffer
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage() + "\n");
            }
        }
    }

    public static void mostrarOpciones() {
        System.out.println("""
                1 - DOLAR - A - PESO ARGENTINO
                2 - PESO ARGENTINO - A - DOLAR
                3 - DOLAR - A - REAL BRASILEÑO
                4 - REAL BRASILEÑO - A - DOLAR
                5 - DOLAR - A - PESO COLOMBIANO
                6 - PESO COLOMBIANO - A - DOLAR
                7 - VISUALIZAR HISTORIAL 
                8 - SALIR
                
                ELIJA UNA OPCION VALIDA 
                *************************************************""");
    }

    public static void imprimirConversorYGuardar(List<CONVERSOR> historialSS, CONSULTA cambiar_, String moneda1, String moneda2, double valor) {
        MONEDA moneda = cambiar_.cambiarMoneda(moneda1, moneda2, valor);
        System.out.println(moneda.cambioDeMoneda(valor) + "\n");
        CONVERSOR historial = new CONVERSOR(moneda, LocalDateTime.now(), valor);
        historialSS.add(historial);
    }

    public static void imprimirHistorial(List<CONVERSOR> historialSS) {
        System.out.println("\n**************************************************** HISTORIAL *******************************************************");
        historialSS.forEach(System.out::print);
        System.out.println("************************************************************************************************************************\n");
    }

    public static boolean convertirMoneda(Scanner entrada, List<CONVERSOR> historialSS, CONSULTA cambiar_) {
        boolean bandera = false;
        int opcion;

        try {
            opcion = entrada.nextInt();
            switch (opcion) {
                case 1 -> manejarConversion(entrada, historialSS, cambiar_, "USD", "ARS");
                case 2 -> manejarConversion(entrada, historialSS, cambiar_, "ARS", "USD");
                case 3 -> manejarConversion(entrada, historialSS, cambiar_, "USD", "BRL");
                case 4 -> manejarConversion(entrada, historialSS, cambiar_, "BRL", "USD");
                case 5 -> manejarConversion(entrada, historialSS, cambiar_, "USD", "COP");
                case 6 -> manejarConversion(entrada, historialSS, cambiar_, "COP", "USD");
                case 7 -> imprimirHistorial(historialSS);
                case 8 -> bandera = true;
                default -> System.out.println("OPCION NO VALIDA!\n");
            }
        } catch (InputMismatchException e) {
            System.out.println("ERROR: Entrada no válida.");
            entrada.next(); // Limpiar el buffer
        }

        return bandera;
    }

    private static void manejarConversion(Scanner entrada, List<CONVERSOR> historialSS, CONSULTA cambiar_, String moneda1, String moneda2) {
        System.out.print("\nINGRESA EL VALOR QUE DESEAS CONVERTIR: ");
        double valor = entrada.nextDouble();
        System.out.println();
        imprimirConversorYGuardar(historialSS, cambiar_, moneda1, moneda2, valor);
    }
}
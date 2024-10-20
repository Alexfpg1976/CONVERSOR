import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PRINCIPAL {
    public static void main(String[] args) {

        // ARRAY HISTORIAL GUARDA LAS LISTAS DE LAS CONVERSIONES REALIZADAS
        List<CONVERSOR> HISTORIAL = new ArrayList<>();
        CONSULTA consultar_ = new CONSULTA();
        Scanner entrada = new Scanner(System.in);

        // ENCABEZADO MENU
        System.out.println("******************************************************");
        System.out.println("******** BIENVENIDO/A AL CONVERSOR DE MONEDAS ********");

        boolean MENU = false;

        // BUCLE QUE MANTIENE EL PROGRAMA ACTIVO HASTA QUE EL USUARIO ELIGE SALIR
        while (!MENU) {
            MOSTRAROP();

            // LLAMA AL METODO QUE GESTIONA LA CONVERSION Y EL HISTORIAL
            try {
                MENU = CONVERTIRMONEDAS(entrada, HISTORIAL, consultar_);
            } catch (InputMismatchException e) {
                System.out.println("NO PUEDES INGRESAR LETRAS, SOLO NÚMEROS!!!!\n");
                entrada.next(); // Limpiar el buffer
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage() + "\n");
            }
        }
    }

    // MENU QUE MUESTRA LAS OPCIONES DE USUSARIO
    public static void MOSTRAROP() {
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

    // ESTE SEGMENTO REALIZA UNA CONVERSION DE LA MONEDA, MUESTRA EL RESULTADO AL USUARIO
    // Y GUARDA DICHA CONVERSION EN EL HISTORIAL PARA FUTURAS CONSULTAS.
    public static void IMPRIMIRCONVERSORYGUARDAR(List<CONVERSOR> HISTORIAL, CONSULTA cambiar_,
                                                 String moneda1, String moneda2, double valor) {
        MONEDA moneda = cambiar_.cambiarMoneda(moneda1, moneda2, valor);
        System.out.println(moneda.cambioDeMoneda(valor) + "\n");
        CONVERSOR historial = new CONVERSOR(moneda, LocalDateTime.now(), valor);
        HISTORIAL.add(historial);
    }


    // ESTE SEGMENTO IMPRIME TODO EL HISTORIAL DE CONVERSIONES REALIZADAS HASTA EL MOMENTO,
    public static void IMPRIMIRHISTORIAL(List<CONVERSOR> HISTORIAL) {
        System.out.println("\n**************************************************** HISTORIAL *******************************************************");
        HISTORIAL.forEach(System.out::print);
        System.out.println("************************************************************************************************************************\n");
    }

    //ESTE SEGMENTO GESTIONA LAS  OPCINES DEL MENU DEL CONVERSOR DE MONEDAS, LLAMANDO A FUNCIONES
    // ESPECIFICAS PARA REALIZAR CONVERSIONES.
    public static boolean CONVERTIRMONEDAS(Scanner entrada, List<CONVERSOR> HISTORIAL, CONSULTA cambiar_) {
        boolean MENU = false;
        int opcion;

        try {
            opcion = entrada.nextInt();
            switch (opcion) {
                case 1 -> MANEJARCONVERSION(entrada, HISTORIAL, cambiar_, "USD", "ARS");
                case 2 -> MANEJARCONVERSION(entrada, HISTORIAL, cambiar_, "ARS", "USD");
                case 3 -> MANEJARCONVERSION(entrada, HISTORIAL, cambiar_, "USD", "BRL");
                case 4 -> MANEJARCONVERSION(entrada, HISTORIAL, cambiar_, "BRL", "USD");
                case 5 -> MANEJARCONVERSION(entrada, HISTORIAL, cambiar_, "USD", "COP");
                case 6 -> MANEJARCONVERSION(entrada, HISTORIAL, cambiar_, "COP", "USD");
                case 7 -> IMPRIMIRHISTORIAL(HISTORIAL);
                case 8 -> MENU = true;
                default -> System.out.println("OPCION NO VALIDA!\n");
            }
        } catch (InputMismatchException e) {
            System.out.println("ERROR: Entrada no válida.");
            entrada.next(); // Limpiar el buffer
        }

        return MENU;
    }

    // ESTE SEGMENTO GESTIONA LA CONVERSION DE UNA MONEDA A OTRA
    private static void MANEJARCONVERSION(Scanner entrada, List<CONVERSOR> HISTORIAL,
                                          CONSULTA cambiar_, String moneda1, String moneda2) {
        double valor = 0;
        boolean valorValido = false;

        // BUCLE QUE PIDE AL USUARIO QUE INGRESE UN VALOR HASTA QUE LO HAGA CORRECTAMENTE
        while (!valorValido) {
            try {
                System.out.print("\nINGRESA EL VALOR QUE DESEAS CONVERTIR: ");
                valor = entrada.nextDouble();
                valorValido = true; // VALOR VALIDO, SALIR DEL BUCLE
            } catch (InputMismatchException e) {
                System.out.println("ERROR: DEBES INGRESAR NUMEROS NO PUEDES INGRESAR LETRAS. " +
                        "Intenta nuevamente.");
                entrada.next(); // LIMPIAR BUFFER
            }
        }

        System.out.println();
        IMPRIMIRCONVERSORYGUARDAR(HISTORIAL, cambiar_, moneda1, moneda2, valor);
    }
}
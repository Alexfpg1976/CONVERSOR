import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
//INICIA LA LISTA PARA GUARDAR HISTORIAL DE CONVERSIONES, LEER LA ENTRADA DEL USUARIO
//Y LA CREACION DEL SUBMENU
public class PRINCIPAL {
    public static void main(String[] args) {
        List<CONVERSOR> historial = new ArrayList<>();
        CONSULTA consulta = new CONSULTA();
        Scanner entrada = new Scanner(System.in);
        SUBMENU submenu = new SUBMENU(historial, consulta, entrada);

        boolean salir = false;

        //BUCLE QUE MUESTRA EL MENU PRINCIPAL HASTA QUE EL USUARIO DECIDA SALIR
        while (!salir) {
            mostrarMenuPrincipal(entrada);
            try {
                // LEE OPCION SELECCIONADA POR EL USUARIO EN EL MENU PRINCIPAL
                int opcion = entrada.nextInt();
                switch (opcion) {
                    // MUESTRA EL SUBMENU DE CONVERSIONES
                    case 1:
                        submenu.mostrarSubMenu();
                        break;
                    // IMPRIME EL HISTORIAL DE CONVERSIONAES REALIZADAS
                    case 2:
                        IMPRIMIRHISTORIAL(historial);
                        break;
                    // FINALIZA LA APLICACION
                    case 3:
                        salir = true;
                        System.out.println("¡GRACIAS POR USAR EL CONVERSOR DE MONEDAS!");
                        break;
                    default:
                        // MANEJA OPCIONES NO VALIDAS
                        System.out.println("OPCION NO VALIDA!\n");
                }
            } catch (InputMismatchException e) {
                // MANEJO DE ERRORES SI LA ENTRADA NO ES UN NUMERO
                System.out.println("ERROR: ENTRADA NO VALIDA. INGRESA UN NUMERO.");
                entrada.next(); // LIMPIAR BUFFFER
            }
        }
    }

    // METODO PARA MOSTAR EL MENU PRINCIPAL
    private static void mostrarMenuPrincipal(Scanner entrada) {
        System.out.println("""
                ************** MENU PRINCIPAL **************
                
                1 - CONVERSION DE MONEDAS
                2 - VISUALIZAR HISTORIAL
                3 - SALIR
                
                ELIJA UNA OPCION VALIDA
                
                ***********************************************""");
    }

    // METODO PARA IMPRIMIR EL HISTORIAL DE CONVERSIONES
    public static void IMPRIMIRHISTORIAL(List<CONVERSOR> HISTORIAL) {
        System.out.println("\n**************************************************** HISTORIAL *******************************************************");
        HISTORIAL.forEach(System.out::print);
        System.out.println("************************************************************************************************************************\n");
    }
}


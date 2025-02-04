

package ar.charlycimino.controlgastos;

import java.util.Scanner;

public class ControlGastos {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        
               // Pedir la categoría del gasto
        System.out.println("Ingrese la categoría del gasto (Ejemplo: Alimentación, Transporte, Ocio):");
        String categoria = scanner.nextLine();

        // Pedir el valor del gasto y validar la entrada
        double valor = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                System.out.println("Ingrese el valor del gasto:");
                valor = Double.parseDouble(scanner.nextLine());
                if (valor <= 0) {
                    System.out.println("El valor debe ser mayor a 0. Intente nuevamente.");
                } else {
                    entradaValida = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un número válido.");
            }
        }

        // Confirmación del gasto agregado
        System.out.println("Gasto agregado: Categoría - " + categoria + ", Valor - " + valor);
    }
}

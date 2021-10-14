
package com.mycompany.programaprolo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Aplicacion {

    public static void main(String[] args) throws IOException {
        while(true){
            int opcion = 0;
            System.out.println("----------------------");
            System.out.println("1. Expresióñ Aritmetica");
            System.out.println("2. Expresión logica");
                System.out.println("3. Split");
            System.out.println("4. Salir");
            
            BufferedReader leer = new BufferedReader(
                          new InputStreamReader(System.in));
            
            try{
                opcion = Integer.parseInt(leer.readLine());
            }catch(NumberFormatException e){
                System.out.println("---------------------");
                System.out.println("Opción no disponible");
                continue;
            }
            
            switch(opcion){
                case 1:
                    System.out.println("---------------------");
                    System.out.println("Ingresa Expresión: ");
                    String infija = leer.readLine();
                    new Aritmetica(infija);
                    break;
                case 2:
                    System.out.println("---------------------");
                    new Logica();
                    break;
                case 3:
                    System.out.println("---------------------");
                    System.out.println("Ingresa Expresión: ");
                    String exp = leer.readLine();
                    new Expresion(exp);
                    break;
                case 4:
                    System.out.println("---------------------");
                    System.out.println("BYE");
                    System.exit(0);
                default:
                    System.out.println("---------------------");
                    System.out.println(opcion);
                    System.out.println("Opción o disponible");
            }
        }
    }
    
}

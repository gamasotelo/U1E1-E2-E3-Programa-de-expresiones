
package com.mycompany.programaprolo;

import java.util.ArrayList;

public class Expresion {
    String cadena;
    ArrayList numeros = new ArrayList();
    ArrayList cadenas = new ArrayList();
    ArrayList caracteres = new ArrayList();
    
    Expresion(String cadena){
        this.cadena = cadena;
        String[] cadenaSeparada = dividirCadena(cadena);
        determinarTipo(cadenaSeparada);
        imprimirResultados();
    }
    
    private String[] dividirCadena(String expresion){
        return expresion.split(" ");
    }
    
    private void determinarTipo(String[] lista){
        for (String dato : lista) {
            
            if(esNumero(dato)){
                numeros.add(dato);
            }else if(esCadena(dato)){
                cadenas.add(dato);
            }else{
                caracteres.add(dato);
            }
        }
    }
    
    private void imprimirResultados(){
        int i = 0;
        System.out.println("");
        System.out.print("[");
        for (Object numero : numeros) {
            if(i==0){
                System.out.print(numero);
                i++;
            }else{
                System.out.print(", " + numero);
            } 
        }
        System.out.println("]");
        System.out.print("[");
        i=0;
        for(Object cadena : cadenas) {
            if(i==0){
                System.out.print(cadena);
                i++;
            }else{
                System.out.print(", " + cadena);
            }
        }
        System.out.println("]");
        System.out.print("[");
        i=0;
        for (Object caracter : caracteres) {
            if(i==0){
                System.out.print(caracter);
                i++;
            }else{
                System.out.print(", "+caracter);
            }
        }
        System.out.println("]");
        
        System.out.println("Números = " + numeros.size() );
        System.out.println("Cadenas = " + cadenas.size() );
        System.out.println("Caracteres = " + caracteres.size() );
    }
    
    private boolean esNumero(String dato){
        try{
            Double.parseDouble(dato);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    private boolean esCadena(String dato){
        return (dato.length() > 1) ? true : false;
    }
}

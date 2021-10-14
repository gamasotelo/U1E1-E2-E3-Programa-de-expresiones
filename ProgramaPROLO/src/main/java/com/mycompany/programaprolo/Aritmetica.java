
package com.mycompany.programaprolo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Aritmetica {
    
    private ArrayList<String> expresionPosfija = new ArrayList();
    private Stack <String>pilaOperadores = new Stack();
    private Stack <String>pilaEvaluacion = new Stack();
    private HashMap<String, Integer> precedencia = new HashMap();
    
    Aritmetica(String expresion){
        definirPrecedencia();
        convertirPosfijo(expresion);
        evaluarExpresion();
    }
    
    
   
    private void convertirPosfijo(String infijo){
        String[] expresionDividida = dividirExpresion(infijo);
        
        for (String caracter : expresionDividida) {
            evaluarCondicion(caracter);
        }       
        if(!pilaOperadores.isEmpty()){     
            vaciarPila();
        }
        
        
        String posfijo = expresionPosfija.toString();
        System.out.println("Expresión en posfijo: " + posfijo);
    }
    
    private String[] dividirExpresion(String cadena){
       String[] result = cadena.split("(?<=[-+*/()^])|(?=[-+*/()^])");
       return result;
    }
    
    private void evaluarCondicion(String caracter){
        if(esNumero(caracter)){ //**Si es un operando
            
            expresionPosfija.add(caracter);
            
        }else{                  //**Si es un operador
            if(pilaOperadores.isEmpty()){//>>Si la pila esta vacia
                pilaOperadores.push(caracter);
            }else{                      //>>Si la pila tiene algo
                if(caracter.equals("(")){//si abre parentesis
                    pilaOperadores.push(caracter);
                }else if(caracter.equals(")")){// si cierra parentesis
                    while(true){
                        String sacado = String.valueOf(pilaOperadores.pop());
                        if(!sacado.equals("(")){
                            expresionPosfija.add(sacado);
                        }else{
                            break;
                        }                     
                    }
                }else{
                    int precedenciaCaracter = precedencia.get(caracter);
                    int precedenciaPila = precedencia.get(pilaOperadores.peek());

                    if(precedenciaCaracter > precedenciaPila){//caracter mayor que la pila
                        pilaOperadores.push(caracter);
                    }else if(precedenciaCaracter == precedenciaPila){//Misma precedencia
                        String cima_pila = String.valueOf(pilaOperadores.pop());
                        expresionPosfija.add(cima_pila);
                        pilaOperadores.push(caracter);
                    }else if(precedenciaCaracter < precedenciaPila){//pila mauor que el caracter
                        while(!pilaOperadores.isEmpty() || !(precedenciaCaracter < precedenciaPila)){
                            String sacado = String.valueOf(pilaOperadores.pop());
                            expresionPosfija.add(sacado);
                            if(pilaOperadores.isEmpty()){
                                break;
                            }
                            precedenciaPila = precedencia.get(pilaOperadores.peek());
                        }
                        pilaOperadores.push(caracter);
                    }
                }
            }
        } 
    }
    
    private void definirPrecedencia(){
        precedencia.put("^", 3);
        precedencia.put("*", 2);
        precedencia.put("/", 2);
        precedencia.put("+", 1);
        precedencia.put("-", 1);
        precedencia.put("(", 0);
    }
    
    private void vaciarPila(){
        while(!pilaOperadores.isEmpty()){
                String sacado = String.valueOf(pilaOperadores.pop());
                if(!sacado.equals("(")){
                    expresionPosfija.add(sacado);
                }
            }
    }
    
    private boolean esNumero(String dato){
        try{
            Double.parseDouble(dato);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    
    private void evaluarExpresion(){
        
        for (String elemento : expresionPosfija) {
            if(esNumero(elemento)){
                pilaEvaluacion.push(elemento);
            }else{
                float numDer = Float.parseFloat(pilaEvaluacion.pop());
                float numIzq = Float.parseFloat(pilaEvaluacion.pop());
                float resultado = realizarOperacion(numIzq, elemento, numDer);
                pilaEvaluacion.push(String.valueOf(resultado));
            }
        }     
        System.out.println("El resultado es: " + pilaEvaluacion.pop());
    }
    
    private float realizarOperacion(float numIzq, String operador, float numDer){
        switch(operador){
            case "-":
                return numIzq - numDer;
            case "+":
                return numIzq + numDer;
            case "/":
                return numIzq / numDer;
            case "*":
                return numIzq * numDer;
            case "^":
                double base =(numIzq);
                double potencia = (numDer);
                return (float)(Math.pow(base, potencia));
            default:
                return 0 + 0;
        }
    }
}

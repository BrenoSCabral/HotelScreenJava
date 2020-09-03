/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telacadastrohotel;

import java.util.HashMap;

/**
 * Classe repsonsável por guardar as informações de par Hóspede-Quarto
 * @author breno
 */
public class Registrador {
    /* 
        Essa classe foi criada para correlacionar Hospedes com quartos
        Eu escolhi o HashMap para esse funcao pois creio ser natural digitar o nome do hospede e obter
        seu quarto facilmente.
    */
    
    private HashMap<String, Integer> registrosSimples;
    private HashMap<Hospede, Quarto> registrosCompleto;
    
    public Registrador(){
        this.registrosSimples = new HashMap();
        this.registrosCompleto = new HashMap();       
    }
    
    public void addRegistro(Hospede h, Quarto q){
        registrosCompleto.put(h,q);
        this.registrosSimples.put(h.getNome(), q.getNumero());
    }
    
    public String registroToS(String h){
        try{
            return "O Hóspede " + h + " está no quarto " + registrosSimples.get(h).toString();
        } catch (NullPointerException e) {
            return "Hóspede não encontrado.";
        }
    }
}
    

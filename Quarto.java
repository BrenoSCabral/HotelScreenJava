/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telacadastrohotel;

/**
 * A classe que é responsável pelo quarto
 * @author breno
 */
public class Quarto {
    private int numero;
    private Hospede hospede;   

    /**
     * 
     * @param numero
     * @param hospede 
     */
    public Quarto(int numero, Hospede hospede) {
        this.numero = numero;
        this.hospede = hospede;
    }

    public int getNumero() {
        return numero;
    }

    public Hospede getHospede() {
        return hospede;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }
    
}

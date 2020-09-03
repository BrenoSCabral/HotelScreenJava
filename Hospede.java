/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telacadastrohotel;

/**
 * A classe responsável por definir o hóspede
 * @author breno
 */
public class Hospede {
    private long CPF;
    private long RG;
    private String nome;
    private int idade;
    private String endereço;

    /**
     * 
     * @param CPF
     * @param RG
     * @param nome
     * @param idade
     * @param endereço 
     */
    Hospede(long CPF, long RG, String nome, int idade, String endereço) {
        this.CPF = CPF;
        this.RG = RG;
        this.nome = nome;
        this.idade = idade;
        this.endereço = endereço;
    }
    public void setCPF(long CPF) {
        this.CPF = CPF;
    }

    public void setRG(long RG) {
        this.RG = RG;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setEndereço(String endereço) {
        this.endereço = endereço;
    }

    public long getCPF() {
        return CPF;
    }

    public long getRG() {
        return RG;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getEndereço() {
        return endereço;
    }
    
}

package com.gabkt.gdc.model;

import java.io.Serializable;
import java.util.Objects;

public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    private String diaInstala;
    private String diaVenci;
    private String telefone1;
    private String telefone2;
    private String email;
    private String nome;
    private String dataNasc;
    private String mae;
    private String cpf;
    private String rg;
    private String complemento;

    public Cliente(){

    }

    public Cliente(String diaInstala, String diaVenci, String telefone1, String telefone2, String email, String nome, String dataNasc, String mae, String cpf, String rg, String complemento) {
        this.diaInstala = diaInstala;
        this.diaVenci = diaVenci;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
        this.email = email;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.mae = mae;
        this.cpf = cpf;
        this.rg = rg;
        this.complemento = complemento;
    }

    public String getDiaInstala() {
        return diaInstala;
    }

    public void setDiaInstala(String diaInstala) {
        this.diaInstala = diaInstala;
    }

    public String getDiaVenci() {
        return diaVenci;
    }

    public void setDiaVenci(String diaVenci) {
        this.diaVenci = diaVenci;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getMae() {
        return mae;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "diaInstala='" + diaInstala + '\'' +
                ", diaVenci='" + diaVenci + '\'' +
                ", telefone1='" + telefone1 + '\'' +
                ", telefone2='" + telefone2 + '\'' +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", dataNasc='" + dataNasc + '\'' +
                ", mae='" + mae + '\'' +
                ", cpf='" + cpf + '\'' +
                ", rg='" + rg + '\'' +
                ", complemento='" + complemento + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(cpf, cliente.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}

package com.entidades;

import java.io.Serializable;

public abstract class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codigo;
    private String nome;
    private String marca;
    private double preco;

    public Produto(String codigo, String nome, String marca, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.marca = marca;
        this.preco = preco;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Produto [codigo=" + codigo + ", nome=" + nome + ", marca=" + marca + ", preco=" + preco + "]";
    }
}
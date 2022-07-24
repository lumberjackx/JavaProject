package com.iefp;

public class Cliente
{
    private int nrCliente;
    private boolean estado;
    private String nome;
    private int telefone;
    private double crediMax;

    public void setNrCliente(int nrCliente)
    {
        this.nrCliente = nrCliente;
    }

    public int getNrCliente()
    {
        return this.nrCliente;
    }

    public void setEstado(boolean estado)
    {
        this.estado = estado;
    }

    public boolean getEstado()
    {
        return this.estado;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getNome()
    {
        return this.nome;
    }

    public void setTelefone(int telefone)
    {
        this.telefone = telefone;
    }

    public int getTelefone()
    {
        return this.telefone;
    }

    public void setCrediMax(double crediMax)
    {
        this.crediMax = crediMax;
    }

    public double getCrediMax()
    {
        return this.crediMax;
    }
}

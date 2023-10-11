package org.acme;

public class ItemPedido {
    private double precoUnitario;
    private int quantidade;

    public ItemPedido(double precoUnitario, int quantidade) {
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
    }

    public double getSubtotal() {
        return precoUnitario * quantidade;
    }
}

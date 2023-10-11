package org.acme;

import java.util.List;

public class Pedido {
    private List<ItemPedido> itens;
    private DescontoService descontoService;

    public Pedido(List<ItemPedido> itens, DescontoService descontoService) {
        this.itens = itens;
        this.descontoService = descontoService;
    }

    public double calcularValorTotal() {
        double valorTotal = 0.0;
        for (ItemPedido item : itens) {
            valorTotal += item.getSubtotal();
        }

        double desconto = descontoService.calcularDesconto(valorTotal);
        valorTotal = valorTotal - desconto;

        // Verificar se o valor total é negativo e retornar 0.0 se for
        if (valorTotal < 0.0) {
            valorTotal = 0.0;
        }

        return valorTotal;

    }

    public double calcularValorTotalComExcecao() {
        double valorTotal = 0.0;
        for (ItemPedido item : itens) {
            valorTotal += item.getSubtotal();
        }

        double desconto = descontoService.calcularDesconto(valorTotal);
        valorTotal = valorTotal - desconto;

        if (valorTotal < 0.0) {
            throw new IllegalArgumentException("O valor total após aplicar o desconto não pode ser negativo.");
        }

        return valorTotal;
    }
}

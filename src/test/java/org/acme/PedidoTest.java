package org.acme;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PedidoTest {
    
    @Mock
    DescontoService descontoService;

    @Mock
    DescontoService descontoService1;
    @Mock
    DescontoService descontoService2;

    @InjectMocks
    Pedido pedido;

    @Test
    public void discountTest() {
        when(descontoService.calcularDesconto(10.0)).thenReturn(1.0);

        ItemPedido item = new ItemPedido(10.0, 1);
        List<ItemPedido> itens = new ArrayList<>();
        itens.add(item);

        pedido = new Pedido(itens, descontoService);

        assertEquals(9.0, pedido.calcularValorTotal());
    }

    @Test
    public void discountAsZeroTest() {
        when(descontoService.calcularDesconto(10.0)).thenReturn(0.0);

        ItemPedido item = new ItemPedido(10.0, 1);

        List<ItemPedido> itens = new ArrayList<>();

        itens.add(item);

        Pedido pedido = new Pedido(itens, descontoService);

        assertEquals(10.0, pedido.calcularValorTotal());
    }

    @Test
    public void discountIsBiggerThanValueTest() {
        when(descontoService.calcularDesconto(100.0)).thenReturn(200.0);

        ItemPedido item = new ItemPedido(100.0, 1);
        List<ItemPedido> itens = new ArrayList<>();
        itens.add(item);

        pedido = new Pedido(itens, descontoService);

        assertEquals(0.0, pedido.calcularValorTotal());
    }

    @Test
    public void discountIsBiggerThanValueTestWithExeption() {
        when(descontoService.calcularDesconto(100.0)).thenReturn(200.0);

        ItemPedido item = new ItemPedido(100.0, 1);
        List<ItemPedido> itens = new ArrayList<>();
        itens.add(item);

        pedido = new Pedido(itens, descontoService);

        assertThrows(IllegalArgumentException.class, pedido::calcularValorTotalComExcecao);
    }

    @Test
    public void testCalcularValorTotalComListaVazia() {
        
        DescontoService descontoService = valorTotal -> 10.0;

        Pedido pedido = new Pedido(Collections.emptyList(), descontoService);

        assertEquals(0.0, pedido.calcularValorTotal());
    }
}

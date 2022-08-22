package com.moises.foodapp.domain.listener;

import com.moises.foodapp.domain.event.PedidoConfirmadoEvent;
import com.moises.foodapp.domain.model.Pedido;
import com.moises.foodapp.domain.service.EnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class NotificacaoClientePedidoConfirmadoListener {

    @Autowired
    private EnvioEmailService envioEmail;

    @TransactionalEventListener
    public void aoConfirmarPedido(PedidoConfirmadoEvent event) {

        Pedido pedido = event.getPedido();

        var mensagem = EnvioEmailService.Mensagem.builder()
                .assunto(pedido.getRestaurante().getNome() + " - Pedido confirmado")
                .corpo("pedido-confirmado.html")
                .variavel("pedido", pedido)
                .destinatario(pedido.getCliente().getEmail())
                .build();

        envioEmail.enviar(mensagem);

    }

}

package com.ldsk.server.controller;

import java.util.ArrayList;

import com.ldsk.server.model.ClienteVendedor;
import com.ldsk.server.protocol.Mensagem;
import com.ldsk.server.protocol.StatusMensagem;

public class ControllerLeilao {
	OperateService service = new OperateService();
	
	public ControllerLeilao() {
	}
	
	public Mensagem operate(String operacao, Mensagem m) {
		Mensagem reply = null;
		
		try {
			switch(operacao) {
			case "VENDA":
				reply = new Mensagem("VENDA_REPLY");
					
				String nomeVendedor = (String) m.getParam("nomeVendedor");
				String descricaoArtigo = (String) m.getParam("descricaoArtigo");
				float valorInicial = (float) m.getParam("valorInicial");
					
				String responseVenda = service.iniciaLeilao(nomeVendedor, descricaoArtigo, valorInicial);
					
				reply.setParam("response", responseVenda);
				reply.setStatusMensagem(StatusMensagem.OK);
					
				return reply;
				
			
			case "ENCERRAR":
				reply = new Mensagem("ENCERRAR_REPLY");
					
				int idVendedor = (int) m.getParam("idVendedor");
				int artigoId = (int) m.getParam("artigoId");
					
				String responseEncerrar = service.encerraLeilao(idVendedor, artigoId);
					
				reply.setParam("response", responseEncerrar);
				reply.setStatusMensagem(StatusMensagem.OK);
					
				return reply;
			
			case "LISTAR":
				
				reply = new Mensagem("LISTA_REPLY");
					
				ArrayList<ClienteVendedor> responseListar = service.listarClientesVendedores();
				reply.setParam("response", responseListar);
				reply.setStatusMensagem(StatusMensagem.OK);
				return reply;
			}
		} catch (Exception e) {
			reply = new Mensagem("SERVER_REPLY");
			reply.setStatusMensagem(StatusMensagem.PARAMERROR);
		}
		
		return null;
	}
	
}

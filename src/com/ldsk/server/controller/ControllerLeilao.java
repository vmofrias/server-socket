package com.ldsk.server.controller;

import java.util.ArrayList;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ldsk.server.model.Artigo;
import com.ldsk.server.model.ClienteVendedor;
import com.ldsk.server.protocol.Mensagem;
import com.ldsk.server.protocol.StatusMensagem;
import com.ldsk.server.service.OperateService;

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
					
					JsonParser jsonParser = new JsonParser();
					JsonObject jsonObject = (JsonObject) jsonParser.parse(responseVenda);
					
					reply.setParam("response", jsonObject);
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
				
					
				case "LANCAR":
					reply = new Mensagem("LANCAR_REPLY");
					
					int artigoId_ = (int) m.getParam("artigoId");
					float valorLance = (float) m.getParam("valorLance");
					String emailContato = (String) m.getParam("emailContato");
						
					String responseLancar = service.executaLance(artigoId_, valorLance, emailContato);
						
					reply.setParam("response", responseLancar);
					reply.setStatusMensagem(StatusMensagem.OK);
						
					return reply;
					
				case "LISTAR_ARTIGOS":
					
					reply = new Mensagem("LISTAR_ARTIGOS_REPLY");
						
					ArrayList<Artigo> responseListarArtigos = service.listarArtigosAbertos();
					reply.setParam("response", responseListarArtigos);
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

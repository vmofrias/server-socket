package com.ldsk.server.service;

import java.util.ArrayList;

import com.ldsk.server.dao.ArtigoDAO;
import com.ldsk.server.dao.ClienteCompradorDAO;
import com.ldsk.server.dao.ClienteVendedorDAO;
import com.ldsk.server.dao.LanceDAO;
import com.ldsk.server.model.Artigo;
import com.ldsk.server.model.ClienteComprador;
import com.ldsk.server.model.ClienteVendedor;
import com.ldsk.server.model.Lance;
import com.ldsk.server.model.util.StatusArtigo;

public class OperateService {
	ArtigoDAO listaArtigo = new ArtigoDAO();
	ClienteVendedorDAO listaVendedor = new ClienteVendedorDAO();
	ClienteCompradorDAO listaComprador = new ClienteCompradorDAO();
	LanceDAO listaLances = new LanceDAO();
	
	public String iniciaLeilao(String nomeVendedor, String descricaoArtigo, float valorInicial) {
		ClienteVendedor cliente = new ClienteVendedor();
		cliente.setNome(nomeVendedor);
		Artigo artigo = new Artigo(descricaoArtigo, valorInicial, cliente.getId());
		cliente.setArtigo(artigo);
		
		listaArtigo.adicionarArtigo(artigo);
		listaVendedor.adicionarVendedor(cliente);
		
		//ArrayList<ClienteVendedor> response = listaVendedor.getListaVendedor();
		String response = "Vendedor de ID " + cliente.getId() +
				" " + cliente.getNome() + " - iniciou um leilao com sucesso.";
		
		return response;
	}
	
	public String encerraLeilao(int idVendedor, int artigoId) {
		for (Artigo artigo : listaArtigo.getListaArtigos()) {
			if((artigo.getClienteVendedorForeignKey() == idVendedor) && (artigo.getId() == artigoId)) {
				artigo.setStatusArtigo(StatusArtigo.ENCERRADO);
				Lance vencedor = listaLances.maiorValor(artigoId);
				
				String response =  "Leilao do artigo id " + artigo.getId() + " encerrado!\n" +
				"O vencedor do leilao foi :\n\n" + vencedor;
				
				System.out.println("I AM HERE NHHAHAHA");
				
				return response;
			}
		}
		
		return "Não houveram lances para o mesmo.";
	}
	
	public String executaLance(int artigoId, float valorLance, String emailContato) {
		if(listaArtigo.verificaStatusPorId(artigoId) == StatusArtigo.ABERTO) {
			ClienteComprador cliente = new ClienteComprador(emailContato);
			listaComprador.adicionarComprador(cliente);
			
			Lance lance = new Lance(artigoId, cliente.getEmail(), valorLance,
					cliente.getId());
			
			listaLances.adicionarLance(lance);
			
			Lance maiorValor = listaLances.maiorValor(artigoId);
			
			if(lance.getClienteCompradorForeignKey() == maiorValor.getClienteCompradorForeignKey()) {
				listaArtigo.getListaArtigos().get(artigoId).setValorFinal(valorLance);
				
				return "\nLance recebido com sucesso! \nO seu lance eh o maior ate o momento - \n" +
						"Valor do lance: " + lance.getValor();
			}else {
				return "\nLance recebido com sucesso! \nO seu lance nao eh o maior ate o momento";
			}
		}else {
			return "\nLeilao para esse artigo encerrado!";
		}
	}
	
	public ArrayList<Artigo> listarArtigosAbertos(){
		ArrayList<Artigo> response = listaArtigo.getListaArtigos();
		
		return response;
	}
	
	
	public ArrayList<ClienteVendedor> listarClientesVendedores() {
		ArrayList<ClienteVendedor> response = listaVendedor.getListaVendedor();
		
		return response;
	}
}

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
		
		String response = "Vendedor de ID " + cliente.getId() +
				" " + cliente.getNome() + " - iniciou um leilao com sucesso.";
		
		return response;
	}
	
	public String encerraLeilao(int idVendedor, int artigoId) {
		for (Artigo artigo : listaArtigo.getListaArtigos()) {
			if((artigo.getClienteVendedorForeignKey() == idVendedor) && (artigo.getId() == artigoId)) {
				artigo.setStatusArtigo(StatusArtigo.ENCERRADO);
				String response;
				
				if(listaLances.isEmptyByArtigoId(artigoId)) {
					
					response =  "Leilao do artigo id " + artigo.getId() + " encerrado!\n" +
							"Leilao encerrado sem nenhum lance.";
				}else {
					Lance vencedor = listaLances.maiorValor(artigoId);
					
					response =  "Leilao do artigo id " + artigo.getId() + " encerrado!\n" +
					"O vencedor do leilao foi :\n\n" + vencedor;
				}
				
				return response;
			}
		}
		
		return "Artigo nao equivalente ao vendedor responsavel inserido.";
	}
	
	public String executaLance(int artigoId, float valorLance, String emailContato) {
		String response;
		
		if(listaArtigo.verificaStatusPorId(artigoId) == StatusArtigo.ABERTO) {
			if(listaArtigo.verificaValor(artigoId, valorLance)) {
				ClienteComprador cliente = new ClienteComprador(emailContato);
				listaComprador.adicionarComprador(cliente);
				
				Lance lance = new Lance(artigoId, cliente.getEmail(), valorLance,
						cliente.getId());
				
				listaLances.adicionarLance(lance);
				
				Lance maiorValor = listaLances.maiorValor(artigoId);
				
				if(lance.getClienteCompradorForeignKey() == maiorValor.getClienteCompradorForeignKey()) {
					listaArtigo.atualizaValorFinalPorId(artigoId, valorLance);
					
					response = "\nLance recebido com sucesso! \nO seu lance eh o maior ate o momento - \n" +
							"Valor do lance: " + lance.getValor();
					
					return response;
				}else {				
					response = "\nLance recebido com sucesso! \nO seu lance nao eh o maior ate o momento";
					return response;
				}
			}else {
				return "\nValor do lance menor que o valor inicial do artigo!";
			}
		}else{
			response = "\nLeilao para esse artigo encerrado!";
			
			return response;
		}			
	}
	
	public ArrayList<Artigo> listarArtigosAbertos(){
		ArrayList<Artigo> response = new ArrayList<Artigo>();
		
		for (Artigo artigo : listaArtigo.getListaArtigos()) {
			if(artigo.getStatusArtigo() == StatusArtigo.ABERTO) {
				response.add(artigo);
			}
		}
		
		return response;
	}
	
	
	public ArrayList<ClienteVendedor> listarClientesVendedores() {
		ArrayList<ClienteVendedor> response = listaVendedor.getListaVendedor();
		
		return response;
	}
}

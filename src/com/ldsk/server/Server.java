package com.ldsk.server;

import java.io.IOException;
import java.net.Socket;

import com.ldsk.server.service.SocketService;

public class Server {
	
	public static void main(String[] args) {
		SocketService socketService = new SocketService();
		int port = 12345;		

		try {
			System.out.println("Aguardando conexao...");
			socketService.criarServerSocket(port);
			System.out.println("Server rodando na porta: " + port);
			
			
			while(true) {
				Socket socket = socketService.esperaConexao();
				System.out.println("Cliente conectado.");
				socketService.trataConexao(socket);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			System.out.println("Erro no cast: " + e.getMessage());
		}
	}
}

package com.ldsk.server;

import java.io.IOException;
import java.net.Socket;

import com.ldsk.server.service.SocketService;

public class Server {
	
	public static void main(String[] args) {
		SocketService socketService = new SocketService();
		
		try {
			System.out.println("Aguardando conexao...");
			socketService.criarServerSocket(5555);
			System.out.println("Cliente conectado.");
			
			while(true) {
				Socket socket = socketService.esperaConexao();
				socketService.trataConexao(socket);
				System.out.println("Cliente finalizado.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			System.out.println("Erro no cast: " + e.getMessage());
		}
	}
}

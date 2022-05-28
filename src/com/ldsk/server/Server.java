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

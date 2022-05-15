package com.ldsk.server.protocol;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Mensagem implements Serializable {
	private static final long serialVersionUID = 1L;

	private String operacao;
	private StatusMensagem statusMensagem;

	Map<String, Object> params;

	public Mensagem() {

	}

	public Mensagem(String operacao) {
		this.operacao = operacao;
		params = new HashMap<>();
	}

	public String getOperacao() {
		return operacao;
	}

	public StatusMensagem getStatusMensagem() {
		return statusMensagem;
	}

	public void setStatusMensagem(StatusMensagem s) {
		this.statusMensagem = s;
	}

	public void setParam(String chave, Object valor) {
		params.put(chave, valor);
	}

	public Object getParam(String chave) {
		return params.get(chave);
	}

	@Override
	public String toString() {
		String m = "\nOperacao: " + operacao;
		m += "\nStatus: " + statusMensagem + "\n";

		m += "\nParametros:\n ";
		for (String p : params.keySet()) {
			m += "\n" + p + ": " + params.get(p) + "\n\n";
		}

		return m;
	}
}

package banco_superior.Persistencia;

import banco_superior.excecao.ClienteJaCadastradoException;
import banco_superior.excecao.ClienteNaoEncontradoException;
import banco_superior.modelo.ICliente;

public interface IPersistenciaCliente {
	
	public void cadastrarCliente(ICliente obj) throws ClienteJaCadastradoException;
	
	public ICliente localizarClientePorCPF(String CPF) throws ClienteNaoEncontradoException;
	
	public ICliente localizarClientePorCNPJ(String CNPJ)throws ClienteNaoEncontradoException ;
	
	public void removerCliente(ICliente obj);

	public void atualizarCliente(ICliente cliente)throws ClienteNaoEncontradoException;
}

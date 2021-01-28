package banco_superior.modelo;

import banco_superior.excecao.ContaJaCadastradaException;
import banco_superior.excecao.ErroInesperadoException;
import banco_superior.excecao.NumeroJaCadastradoException;

public interface ICliente {
	
	public void adicionarConta(IConta conta) throws ContaJaCadastradaException;
	
	public void adicionarTelefone(String Telefone) throws NumeroJaCadastradoException;
	
	public void removerTelefone(String Telefone) throws ErroInesperadoException;
	
	public void setNome(String novoNome);
	
	public void setEmail(String email);

}

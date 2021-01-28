package banco_superior.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import banco_superior.excecao.ContaJaCadastradaException;
import banco_superior.excecao.ErroInesperadoException;
import banco_superior.excecao.NumeroJaCadastradoException;

public class ClientePessoaFisica implements ICliente, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String cpf;
	String nome;
	String dataNascimento;
	String email;
	
	HashSet<String> telefones = new HashSet<String>();
	
	private List<IConta> contas = new ArrayList<IConta>();
	
	public ClientePessoaFisica(String nome, String cpf, String dataNascimento, String email) {
		
		this.cpf = cpf;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.email = email;
		
	}
	
	public ClientePessoaFisica(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "ClientePessoaFisica [cpf=" + cpf + ", nome=" + nome + ", dataNascimento=" + dataNascimento + ", email=" + email
				+ ", telefones=" + telefones + ", contas=" + contas + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientePessoaFisica other = (ClientePessoaFisica) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}

	public void adicionarConta(IConta contaCliente)throws ContaJaCadastradaException 
	{
		if (!contas.contains(contaCliente)) 
		{
			this.contas.add(contaCliente);
		}
		else
			throw new ContaJaCadastradaException("Conta Já se encontra Cadastrada");
			
	}
	
	public boolean contemConta(IConta contaCliente) {
		return this.contas.contains(contaCliente);
	}
	
	public void removerConta(IConta conta) {
		if(contas.contains(conta)) {
			contas.remove(conta);
		}

	}
	
	public void adicionarTelefone(String telefone) throws NumeroJaCadastradoException{
		if(!telefones.contains(telefone))
		{
			this.telefones.add(telefone);
		}
		else
			throw new NumeroJaCadastradoException("Número já cadastrado");
	}
	
	public void removerTelefone(String telefone) throws ErroInesperadoException
	{
		if (telefones.contains(telefone)) 
		{
			telefones.remove(telefone);
		}
		else
			throw new ErroInesperadoException("Telefone não encontrado");
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}


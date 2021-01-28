package banco_superior.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import banco_superior.excecao.ContaJaCadastradaException;
import banco_superior.excecao.ErroInesperadoException;
import banco_superior.excecao.NumeroJaCadastradoException;

public class ClientePessoaJuridica implements ICliente, Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String cnpj;
	String razaoSocial;
	String nomeFantasia;
	String email;
	
	HashSet<String> telefones = new HashSet<String>();
	
	private List<IConta> contas = new ArrayList<IConta>();
	
	public ClientePessoaJuridica(String razaoSocial, String cnpj, String nomeFantasia, String email) {
		
		this.cnpj = cnpj;
		this.razaoSocial = razaoSocial;
		this.nomeFantasia = nomeFantasia;
		this.email = email;
		
	}
	
	public ClientePessoaJuridica(String cnpj) 
	{
		this.cnpj = cnpj;
	}


	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNome() {
		return nomeFantasia;
	}

	public void setNome(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public HashSet<String> getTelefones() {
		return telefones;
	}


	@Override
	public String toString() {
		return "ClientePessoaJuridica [cnpj=" + cnpj + ", razaoSocial=" + razaoSocial + ", nomeFantasia=" + nomeFantasia
				+ ", email=" + email + ", telefones=" + telefones + ", contas=" + contas + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
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
		ClientePessoaJuridica other = (ClientePessoaJuridica) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
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
	
	public boolean contemConta(IConta contaCliente) 
	{
		return this.contas.contains(contaCliente);
	}
	
	public void removerConta(IConta conta) 
	{
		if(contas.contains(conta)) 
		{
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
	
	public void removerTelefone(String telefone) throws ErroInesperadoException{
		if (telefones.contains(telefone)) 
		{
			telefones.remove(telefone);
		}
		else
			throw new ErroInesperadoException("Telefone não encontrado");
	}

}

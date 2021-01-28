package banco_superior.Persistencia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import banco_superior.excecao.ClienteJaCadastradoException;
import banco_superior.excecao.ClienteNaoEncontradoException;
import banco_superior.modelo.ClientePessoaFisica;
import banco_superior.modelo.ClientePessoaJuridica;
import banco_superior.modelo.ICliente;

public class PersistenciaEmArquivoCliente implements IPersistenciaCliente {
	
	private List<ICliente> clientesCadastrados = new ArrayList<ICliente>();
	
	public PersistenciaEmArquivoCliente() 
	{
		lerConteudoArquivo();
	}
	

	@Override
	public void cadastrarCliente(ICliente obj) throws ClienteJaCadastradoException{
		if(!clientesCadastrados.contains(obj))
		{
			clientesCadastrados.add(obj);
			salvarEmArquivo();
		}
		else
			throw new ClienteJaCadastradoException("Cliente já cadastrado no banco superior");
	}

	@Override
	public ICliente localizarClientePorCPF(String CPF) throws ClienteNaoEncontradoException {
		// TODO Auto-generated method stub
		ICliente cliente = new ClientePessoaFisica(CPF);
		
		if(clientesCadastrados.contains(cliente))
		{
			int index = clientesCadastrados.indexOf(cliente);
			cliente = clientesCadastrados.get(index);
			return cliente;
		}
		else
			throw new ClienteNaoEncontradoException("Cliente não encontrado no sistema Banco superior");
		
	}

	@Override
	public ICliente localizarClientePorCNPJ(String CNPJ) throws ClienteNaoEncontradoException{
		// TODO Auto-generated method stub
		ICliente cliente = new ClientePessoaJuridica(CNPJ);
		
		if(clientesCadastrados.contains(cliente))
		{
			int index = clientesCadastrados.indexOf(cliente);
			cliente = clientesCadastrados.get(index);
			return cliente;
		}
		else
			 throw new ClienteNaoEncontradoException("Cliente não encontrado no sistema Banco superior");
	}

	@Override
	public void removerCliente(ICliente obj) {
		// TODO Auto-generated method stub
		if(clientesCadastrados.contains(obj))
		{
			clientesCadastrados.remove(obj);
			salvarEmArquivo();
		}

	}
	
	private void lerConteudoArquivo() {
		try {
			FileInputStream fis = new FileInputStream("clientes_cadastrados.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object obj = ois.readObject();
			clientesCadastrados = (ArrayList<ICliente>)obj;
			ois.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void salvarEmArquivo() {
		try {
			FileOutputStream fos = new FileOutputStream("clientes_cadastrados.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(clientesCadastrados);
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void atualizarCliente(ICliente cliente)throws ClienteNaoEncontradoException{
		if(clientesCadastrados.contains(cliente)) {
			int index = clientesCadastrados.indexOf(cliente);
			clientesCadastrados.add(index, cliente);
			salvarEmArquivo();
		}
		else
			throw new ClienteNaoEncontradoException("Cliente não encontrado no sistema");
	}
		

}

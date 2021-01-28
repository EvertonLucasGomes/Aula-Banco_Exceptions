package banco_superior.modelo;

import banco_superior.Persistencia.IPersistenciaCliente;
import banco_superior.Persistencia.PersistenciaEmArquivoCliente;
import banco_superior.excecao.ClienteJaCadastradoException;
import banco_superior.excecao.ClienteNaoEncontradoException;
import banco_superior.excecao.ContaDesativadaException;
import banco_superior.excecao.ContaJaCadastradaException;
import banco_superior.excecao.ErroInesperadoException;
import banco_superior.excecao.NumeroJaCadastradoException;
import banco_superior.excecao.SaldoInsuficienteException;
import banco_superior.excecao.ValorInvalidoException;

public class Programa {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		
		IConta c1 = new ContaSalario("111", "222", "15/06/2020");

		IConta c2 = new ContaInvestimento("122", "223", "15/08/2020");
		
		IConta c3 = new ContaInvestimento("565", "895", "25/01/2021");
		
		IConta c4 = new ContaSalario("445", "995", "25/12/2020");
		
		ICliente Model = new ClientePessoaFisica("--", "--", "--", "---");
		
		ClientePessoaFisica cliente1 = new ClientePessoaFisica("Everton", "54451451", "hjea", "222222");
		ClientePessoaJuridica cliente2 = new ClientePessoaJuridica("Evertn", "54459451", "hjeas", "222822");
		
		try {
			cliente1.adicionarTelefone("99999-9888");
		} catch (NumeroJaCadastradoException e4) {
			// TODO Auto-generated catch block
			System.out.println(e4);
		}
		try {
			cliente2.adicionarTelefone("98888-8888");
		} catch (NumeroJaCadastradoException e4) {
			// TODO Auto-generated catch block
			System.out.println(e4);
		}
		
		try {
			cliente1.adicionarConta(c1);
		} catch (ContaJaCadastradaException e3) {
			// TODO Auto-generated catch block
			System.out.println(e3);
		}
		try {
			cliente1.adicionarConta(c3);
		} catch (ContaJaCadastradaException e3) {
			// TODO Auto-generated catch block
			System.out.println(e3);
		}
		try {
			cliente2.adicionarConta(c2);
		} catch (ContaJaCadastradaException e3) {
			// TODO Auto-generated catch block
			System.out.println(e3);
		}
		try {
			cliente2.adicionarConta(c4);
		} catch (ContaJaCadastradaException e3) {
			// TODO Auto-generated catch block
			System.out.println(e3);
		}

		System.out.println("CONTAS CRIADAS SEM SALDO: ");
		System.out.println(cliente1);
		System.out.println(cliente2);

		try {
			c1.depositar(1000f);
		} catch (ValorInvalidoException | ContaDesativadaException | ErroInesperadoException e2) {
			// TODO Auto-generated catch block
			System.out.println(e2);
		}
		try {
			c2.depositar(100f);
		} catch (ValorInvalidoException | ContaDesativadaException | ErroInesperadoException e2) {
			// TODO Auto-generated catch block
			System.out.println(e2);
		}
		
		System.out.println("CONTAS COM SALDO: ");
		System.out.println(cliente1);
		System.out.println(cliente2);
				
	
		try {
			c1.transferencia(c2, 10000f);
		} catch (SaldoInsuficienteException | ValorInvalidoException | ContaDesativadaException
				| ErroInesperadoException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1);
		}

		
		System.out.println("CONTAS APÓS TRANSFERÊNCIA: ");
		System.out.println(cliente1);
		System.out.println(cliente2);
		
		//iniciando a persistencia
		
		IPersistenciaCliente pac = new PersistenciaEmArquivoCliente();
		
			
		// persistindo dados de pessoa fisica
		try {
			pac.cadastrarCliente(cliente1);
		} catch (ClienteJaCadastradoException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());;
		}
				

		
		ICliente obj = Model;
		try {
			obj = pac.localizarClientePorCPF("54451451");
						
		} catch (ClienteNaoEncontradoException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	
		System.out.println(cliente1);
		
		obj.setNome("everton Lucas daklsndfask");
		try {
			pac.atualizarCliente(obj);
		} catch (ClienteNaoEncontradoException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		System.out.println(".........................................................................................................................");

		System.out.println(obj);
		
		System.out.println(cliente2);
		
		System.out.println(cliente1);
		
		obj.setNome("everton daklsndfask");
		try {
			pac.atualizarCliente(obj);
		} catch (ClienteNaoEncontradoException e)
		{
			// TODO Auto-generated catch block
			System.out.println(e);
		}
			
			System.out.println(cliente1);
		
	}
}

package banco_superior.modelo;

import java.io.Serializable;

import banco_superior.excecao.ContaDesativadaException;
import banco_superior.excecao.ErroInesperadoException;
import banco_superior.excecao.SaldoInsuficienteException;
import banco_superior.excecao.ValorInvalidoException;

public class ContaPoupanca implements IConta, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String numeroConta;
	String agencia;
	private float saldo;
	boolean status;
	String dataAbertura;
	
	public ContaPoupanca(String numeroConta, String agencia, String dataAbertura) {
		
		this.numeroConta = numeroConta;
		this.dataAbertura = dataAbertura;
		this.saldo = 0f;
		this.status = true;
		this.agencia = agencia;
		
	}

	@Override
	public void depositar(float valorDepositado) throws ValorInvalidoException, ContaDesativadaException, ErroInesperadoException {
		if (valorDepositado>0 && this.status)
		{
			this.saldo += valorDepositado;
		}
		else if(valorDepositado <= 0)
		{
			throw new ValorInvalidoException("Operação inválida, valor insuficiente para deposito");
		}
		else if(this.status == false)
		{
			throw new ContaDesativadaException("Operação inválida, a conta está desativada");
		}
		else 
		{
			throw new ErroInesperadoException("Não foi possível concluira a operação");
		}
	}


	@Override
	public void desativarConta() {
		this.status = false;
		
	}

	@Override
	public void ativarConta() {
		this.status = true;
		
	}

	@Override
	public void sacar(float valorSacado) throws SaldoInsuficienteException {
		if(valorSacado > 0 && this.status && 
				this.saldo >= (valorSacado+(valorSacado*TAXA_SACAR_CONTA_POUPANCA))) 
		{
			this.saldo -= (valorSacado+(valorSacado*TAXA_SACAR_CONTA_POUPANCA));
		}
		else if(this.saldo < (valorSacado+(valorSacado*TAXA_SACAR_CONTA_POUPANCA))) 
		{
			throw new SaldoInsuficienteException("Saldo insuficiente para saque");
		}
	}

	@Override
	public void transferencia(IConta contaDestino, float valorTransferido) throws SaldoInsuficienteException, ValorInvalidoException, ContaDesativadaException, ErroInesperadoException{
		if(contaDestino instanceof ContaInvestimento && (valorTransferido > 0 && (valorTransferido+(valorTransferido*TAXA_ADM_CONTA_INVESTIMENTO)<= this.saldo)))
		{
			this.saldo -= (valorTransferido+(valorTransferido*TAXA_ADM_CONTA_INVESTIMENTO));
			contaDestino.depositar(valorTransferido);
			
		}
		else if (contaDestino instanceof ContaCorrente && (valorTransferido > 0 && (valorTransferido+(valorTransferido*TAXA_ADM_CONTA_CORRENTE)<= this.saldo))) 
		{
			this.saldo -= (valorTransferido+(valorTransferido*TAXA_SACAR_CONTA_CORRENTE));
			contaDestino.depositar(valorTransferido);
		}
		else if(contaDestino instanceof ContaSalario && (valorTransferido > 0 && (valorTransferido+(valorTransferido*TAXA_ADM_CONTA_SALARIO)<= this.saldo))) 
		{
			this.saldo -= (valorTransferido+(valorTransferido*TAXA_ADM_CONTA_SALARIO));
			contaDestino.depositar(valorTransferido);
		}
		else if (contaDestino instanceof ContaPoupanca && (valorTransferido > 0 && (valorTransferido < this.saldo)))
		{
			this.saldo -= (valorTransferido+(valorTransferido*TAXA_SACAR_CONTA_POUPANCA));
			contaDestino.depositar(valorTransferido);
		}
		else
			throw new SaldoInsuficienteException("Saldo insuficiente para transferência");
	}


	@Override
	public String toString() 
	{
		return "ContaPoupanca [numeroConta=" + numeroConta + ", agencia=" + agencia + ", saldo=" + saldo + ", status="
				+ status + ", dataAbertura=" + dataAbertura + "]";
	}
	
}
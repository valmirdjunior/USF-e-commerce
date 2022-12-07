package br.com.dpaulla.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public @Data class OrderData implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//DADOS TRANSACAO INFORMAÇÕES GERAIS
	private int id;
	private long userId;
	private String orderStatus; 	//A = AGUARDANDO PAGAMENTO, PA = PAGO
	private String orderId;
	private String orderShippingPrice;
	private String orderShippingType; 	//1 - Encomenda normal (PAC), 2 - SEDEX, 3 - Tipo de frete
	private String orderSubtotalPrice;
	private String orderTotalPrice;
	private String orderPaymentType; 	//1 = BOLETO, 2 = CARTÃO DE CRÉDITO VISA, 3 = CARTÃO DE CRÉDITO MASTERCARD.
	private String orderPaymentAmounts;
	private String orderPaymentPrinceAmount;
	private String hashCode;
	private String orderDate;
	private String orderPaymantDate;
	
	//DADOS DE PAGAMENTO PELO BOLETO
	
	private String transacaoBoletoLink;
	private String transacaoBoletoDataGeracao;
	private String transacaoBoletoCPF;
	private String transacaoBoletoTelefone;
	
	private String transacaoBoletoTelefoneDDD;

	//DADOS DE PAGAMENTO PELO CARTÃO
	private String transacaoCartaoNumero;	
	private String transacaoCartaoNome;
	private String transacaoCartaoCodigoSeguranca;
	private String transacaoCartaoVencimento;
	private String transacaoCartaoCPF;
	private String transacaoCartaoDatadeNascimento;
	private String transacaoCartaoTelefone;
	private String transacaoCartaoToken;
	
	private String transacaoCartaoTelefoneDDD;
	private String transacaoDadosPortadorIguaisComprador;
	
	//DADOS DO COMPRADOR
	private String transacaoCompradorNome;	
	private String transacaoCompradorCPF;
	private String transacaoCompradorDataNascimento;	
	private String transacaoCompradorTelefone;
	private String transacaoCompradorEmail;
	
	private String transacaoCompradorTelefoneDDD;
	private String transacaoDadosCobrancaIguaisEntrega;
	
	//ENDEREÇO COBRANÇA
	private String transacaoCobrancaCep;
	private String transacaoCobrancaEndereco;
	private String transacaoCobrancaNumero;
	private String transacaoCobrancaEnderecoComplemento;
	private String transacaoCobrancaBairro;
	private String transacaoCobrancaCidade;
	private String transacaoCobrancaEstado;	

	//DADOS ENDEREÇO DE ENTREGA
	private String transacaoEntregaCep;
	private String transacaoEntregaEndereco;
	private String transacaoEntregaEnderecoComplemento;
	private String transacaoEntregaNumero;
	private String transacaoEntregaBairro;
	private String transacaoEntregaCidade;
	private String transacaoEntregaEstado;		

}

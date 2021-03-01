package ecommerce;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ecommerce {

	public static Scanner leia = new Scanner(System.in);
	public static Cliente pessoa = new Cliente();
	public static Pedido pedido = new Pedido();
	public static List<Produto> produtos = new ArrayList<>();
	public static List<Carrinho> itensCarrinho = new ArrayList<>();
	public static DecimalFormat df = new DecimalFormat("0.00");

	public static void main(String[] args) {

		produtos.add(new Produto(1, "NMD_R1", 10, 250));
		produtos.add(new Produto(2, "RACER ADAPA", 10, 150));
		produtos.add(new Produto(3, "FALCON 2.0", 10, 600));
		produtos.add(new Produto(4, "TOP 300", 10, 460));
		produtos.add(new Produto(5, "ADVANTAGE", 10, 800));
		produtos.add(new Produto(6, "ADIDAS ENERGY", 10, 130));
		produtos.add(new Produto(7, "DURAMO SL", 10, 450));
		produtos.add(new Produto(8, "ZX 2K BOOST", 10, 150));
		produtos.add(new Produto(9, "ADIDAS YEEZY", 10, 600));
		produtos.add(new Produto(10, "ZX 1K BOOST", 10, 75));

		System.out.println("\t\t\t\t\t    LOJA SPORT\n");
		System.out.println("\t\t\t————— O aconchego e qualidade que seus pés merecem! —————\n");

		menu();

	}

	public static void renovarEstoque() {
		for (Produto item : produtos) {
			if (item.getEstoque() == 0) {
				item.setEstoque(10);
				System.out.println(
						"===================================================================================================\n");
				System.out.println("\t\t\t\t  ——— AVISO! ———");
				System.out.println("Produto " + item.getDescricao() + " estoque renovado!");
			}
		}
	}

	public static void menu() {

		System.out.println(
				"===================================================================================================\n");
		System.out.print("\n\t\t\t\t     ——— PÁGINA INICIAL ———\n\n");
		System.out.println("1 => Cadastro");
		System.out.println("2 => Comprar");
		System.out.println("3 => Ver produtos");
		System.out.println("4 => Ver carrinho");
		System.out.println("5 => Sair");
		System.out.print("\nPor favor, selecione uma das opções acima: ");
		int opcao = checkInt();

		switch (opcao) {
		case 1:
			cadastro();
			break;

		case 2:
			comprar();
			break;

		case 3:
			exibirProdutos();
			break;

		case 4:
			exibirCarrinho();
			break;
		case 5:
			System.out.println("\n\t\t\t\t  ——— PROGRAMA FINALIZADO ———");
			System.exit(0);

		default:
			System.out.println("Opção inválida");
			menu();
		}

	}

	private static void exibirCarrinho() {
		if (itensCarrinho.isEmpty()) {
			System.out.println("Carrinho vazio!");
			menu();
		} else {
			System.out.printf(
					"\n\n\t\t\t\t Bem-vinde à Loja Sport, %s %s!\n\t\t  Aqui você encontrará uma váriedade de tênis esperando por você!\n",
					pessoa.tratamento(pessoa.getGenero()), (pessoa.getNome() == null) ? "Visitante" : pessoa.getNome());
			System.out.println(
					"\n——————————————————————————————————————————————————————————————————————————————————————————————————— ");
			System.out.print("\n\t\t\t\t\t  ——— CARRINHO ———\n\n");
			System.out.println("\nCÓDIGO \t\t PRODUTO \t\t QUANTIDADE \t\t PREÇO \t\t SUBTOTAL\n");
			for (Carrinho itemCarrinho : itensCarrinho) {
				System.out.println(itemCarrinho.getCodigo() + " \t\t " + itemCarrinho.getDescricao() + " \t\t "
						+ itemCarrinho.getQuantidade() + " \t\t\t " + itemCarrinho.getPreco() + "\t\t "
						+ itemCarrinho.getSubtotal());
				System.out.println(
						"\n——————————————————————————————————————————————————————————————————————————————————————————————————— ");
			}
			System.out.print("\n\t\t\t\t\t  ——— OPÇÕES ———\n\n");
            System.out.println("1 => Alterar item\n" + "2 => Remover item\n" + "3 => Voltar para o menu\n" + "4 => Finalizar compra");
            System.out.print("\n> Selecione uma opção: ");
			int opcao = checkInt();

			
			while (opcao < 1 || opcao > 4) {
				System.out.print("Selecione uma opção válida: ");
				opcao = checkInt();
			}

			switch (opcao) {
			case 1:
				alterarItem();
				break;

			case 2:
				removerItem();
				break;

			case 3:
				menu();
				break;

			case 4:
				finalizarCompra();
				break;

			default:
				exibirCarrinho();
			}
		}
	}

	private static void removerItem() {
		System.out.print("\n> Digite o código do produto: ");
		int codigo = checkInt();
		
		int indice = 11;
		boolean codigoValido = false;
		
		try {
			for (Carrinho itemCarrinho : itensCarrinho) {
				if (itemCarrinho.getCodigo() == codigo) {
					indice = itensCarrinho.indexOf(itemCarrinho);
					for(Produto item : produtos) {
						if(itemCarrinho.getCodigo() == item.getCodigo()) {
							item.setEstoque(item.getEstoque() + itemCarrinho.getQuantidade());
							codigoValido = true;
						}
					}
				}
			}

			if(codigoValido) {
				itensCarrinho.remove(indice);
				exibirCarrinho();
			} else {
				System.out.println("Código inválido");
				exibirCarrinho();
			}
			
		} catch (Exception e) {
			System.out.println("Digite o código informado inválido");
			removerItem();
		}
	}

	private static void alterarItem() {
		System.out.print("\n> Digite o código do produto: ");
		int codigo = checkInt();
		boolean codigoValido = false;
		try {
			for (Carrinho itemCarrinho : itensCarrinho) {
				if (itemCarrinho.getCodigo() == codigo) {
					for(Produto item : produtos) {
						if(itemCarrinho.getCodigo() == item.getCodigo()) {
							item.setEstoque(item.getEstoque() + itemCarrinho.getQuantidade());
							System.out.print("Digite a quantidade nova: ");
							int quantidade = checkInt();
							while(quantidade < 1 || quantidade > item.getEstoque()) {
								System.out.print("Digite a quantidade nova: ");
								quantidade = checkInt();
							}
							itemCarrinho.setQuantidade(quantidade);
							item.setEstoque(item.getEstoque() - itemCarrinho.getQuantidade());
							codigoValido = true;
						}
					}
				}
			}
			if(codigoValido) {
				System.out.println("Produto atualizado com sucesso!");
				exibirCarrinho();
			} else {
				System.out.println("Código inválido");
				exibirCarrinho();
			}
			
		} catch (Exception e) {
			System.out.println(" o código informado não está no carrirnho");
			removerItem();
		}
	}

	private static void finalizarCompra() {

		for (Carrinho itemCarrinho : itensCarrinho) {
			pedido.setTotalGeral(itemCarrinho.getSubtotal());
		}
		System.out.print("\n\t\t\t\t  ——— OPÇÕES DE PAGAMENTO ———");
		System.out.print("\n\n1 => À VISTA -> 10% DESCONTO \r\n" + "2 => À VISTA - CRÉDITO -> SEM DESCONTO\r\n"
				+ "3 => 2X -> ACRÉSCIMO DE 10%  \r\n" + "4 => 3X -> ACRÉSCIMO DE 15% \r\n");
		System.out.print("\nSelecione uma opção: ");
		int opcao = checkInt();

		if (!pedido.opcaoPagamento(opcao)) {
			System.out.println("Opção inválida, digite outra opção!");
			finalizarCompra();
		}

		pedido.setValorImposto(9);

		System.out.println(
				"\n——————————————————————————————————————————————————————————————————————————————————————————————————— ");
		System.out.println("\t\t\t\t  ——— EXIBINDO NOTA FISCAL ———");
		System.out.println("\n> LOJA SPORT - CNPJ 49.033.004/0018-03");
		System.out.println("\nIMPOSTO COBRADO 9%: R$ " + df.format(pedido.getValorImposto()));
		System.out.printf("CLIENTE: %s %s\n", pessoa.tratamento(pessoa.getGenero()),
				(pessoa.getNome() == null) ? "Visitante" : pessoa.getNome());
		System.out.println("\nCÓDIGO \t\t PRODUTO \t\t QTDE. \t\t PREÇO \t\t SUBTOTAL");
		for (Carrinho itemCarrinho : itensCarrinho) {
			System.out.println(itensCarrinho.indexOf(itemCarrinho) + " \t\t " + itemCarrinho.getDescricao() + " \t\t "
					+ itemCarrinho.getQuantidade() + " \t\t " + itemCarrinho.getPreco() + " \t\t "
					+ itemCarrinho.getSubtotal());
		}

		System.out.println("\nValor total da nota: R$" + pedido.getTotalGeral());
		if (opcao == 1) {
			System.out.println(pedido.getFormaPagamento() + "  " + df.format(pedido.getTotalComDesconto())
					+ "\nEconomia de: R$" + (pedido.getTotalGeral() - pedido.getTotalComDesconto()));
		} else {
			System.out.println(pedido.getFormaPagamento() + "\nAcréscimo de: R$ "
					+ (pedido.getTotalComDesconto() - pedido.getTotalGeral()) + "\nTotal com acréscimo:  "
					+ pedido.getTotalComDesconto());
			System.out.println(
					"\nEm: " + pedido.getQuantidadeParcelas() + "X de " + df.format(pedido.getValorParcelas()));
		}

		renovarEstoque();
		itensCarrinho.clear();
		menu();
	}

	private static void comprar() {
		System.out.printf(
				"\n\n\t\t\t\t Bem-vinde à Loja Sport, %s %s!\n\t\t  Aqui você encontrará uma váriedade de tênis esperando por você!\n",
				pessoa.tratamento(pessoa.getGenero()), (pessoa.getNome() == null) ? "Visitante" : pessoa.getNome());
		
		mostrarEstoque();
		
		System.out.print("\n> Digite o código do produto: ");
		int codigo = checkInt();

		if (codigo < 1 || codigo > 10) {
			System.out.println("Código inválido!");
			comprar();
		}

		for (Carrinho itemCarrinho : itensCarrinho) {
			if (itemCarrinho.getCodigo() == codigo) {
				System.out.println("Esse item já está no carrinho!");
				System.out.println("> Deseja alterar o quantidade? (S/N): ");
				char resposta = leia.next().toUpperCase().charAt(0);
				if (resposta == 'S') {
					System.out.println("> Digite a nova quantidade quantidade: ");
					int quantidade = checkInt();
					for (Produto item : produtos) {
						if (codigo == item.getCodigo()) {
							item.setEstoque(item.getEstoque() + itemCarrinho.getQuantidade());
							while (quantidade > item.getEstoque()) {
								System.out.println("Quantidade em estoque: " + item.getEstoque());
								System.out.println("Digite um quantidade válida: ");
								quantidade = checkInt();
							}
							item.atualizarEstoque(quantidade);
						}
					}
					itemCarrinho.setQuantidade(quantidade);
					System.out.println("Item do carrinho atualizado com sucesso!");
					menu();
				} else {
					menu();
				}
			}
		}

		int quantidade = 0;
		for (Produto item : produtos) {
			if (codigo == item.getCodigo()) {

				if (item.getEstoque() == 0) {
					System.out.println("Produto sem estoque, escolha outro produto!");
					comprar();
				}

				System.out.println("> Digite a quantidade: ");
				quantidade = checkInt();

				if (quantidade < 1) {
					System.out.println("Quantidade inválida!");
					comprar();
				}

				if (quantidade > item.getEstoque()) {
					System.out.println("Quantidade acima do estoque!");
					comprar();
				}

				itensCarrinho.add(new Carrinho(item.getCodigo(), item.getDescricao(), quantidade, item.getPreco()));
				item.atualizarEstoque(quantidade);
				System.out.println("Produto adicionado ao carrinho!");
				menu();
			}
		}
	}

	private static void exibirProdutos() {
		System.out.printf(
				"\n\n\t\t\t\t Bem-vinde à Loja Sport, %s %s!\n\t\t  Aqui você encontrará uma váriedade de tênis esperando por você!\n",
				pessoa.tratamento(pessoa.getGenero()), (pessoa.getNome() == null) ? "Visitante" : pessoa.getNome());
		System.out.println(
				"\n———————————————————————————————————————————————————————————————————————————————————————————————————");
		System.out.println("\n\t\t\t\t\t——— NOSSOS PRODUTOS ———\n");
		System.out.println("\n\tCÓDIGO \t\t PRODUTO \t\t  ESTOQUE \t\t PREÇO(R$)\n");

		for (Produto item : produtos) {
			System.out.println("\t  " + item.getCodigo() + " \t\t " + item.getDescricao() + " \t\t    "
					+ item.getEstoque() + " \t\t\t  " + item.getPreco() + "\n");
		}
		menu();
	}
	
	private static void mostrarEstoque() {
		System.out.println(
				"\n———————————————————————————————————————————————————————————————————————————————————————————————————");
		System.out.println("\n\t\t\t\t\t——— NOSSOS PRODUTOS ———\n");
		System.out.println("\n\tCÓDIGO \t\t PRODUTO \t\t  ESTOQUE \t\t PREÇO(R$)\n");

		for (Produto item : produtos) {
			System.out.println("\t  " + item.getCodigo() + " \t\t " + item.getDescricao() + " \t\t    "
					+ item.getEstoque() + " \t\t\t  " + item.getPreco() + "\n");
		}
	}

	private static void cadastro() {

		System.out.println(
				"\n——————————————————————————————————————————————————————————————————————————————————————————————————— ");
		System.out.print("\n\t\t\t\t  ——— CADASTRO DO USUÁRIO ———");
		System.out.print("\n\n> Digite seu nome: ");
		String nome = leia.next();
		System.out.print("> Digite seu sexo (M - Masculino /F - Feminino /X - outros): ");
		char sexo = leia.next().toUpperCase().charAt(0);

		pessoa.setNome(nome);
		pessoa.setGenero(sexo);
		
		System.out.print("\n\n> Cadastro efetuado com sucesso! \n\n");
		menu();
	}

	public static int checkInt() {
		while (!leia.hasNextInt()) {
			System.out.println("> Digite um valor válido: ");
			leia.next();
		}
		int checked = leia.nextInt();
		return checked;
	}

}

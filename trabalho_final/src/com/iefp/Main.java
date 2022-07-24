package com.iefp;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        char opcao;

        Clientes listaClientes = new Clientes();        //Adiciona uma nova instancia listaClientes a classe clientes

        listaClientes.lerFicheiroClientes();            //Le o ficheiro clientes.csv e popula a instancia listaClientes

        do                                              //menu
        {
            System.out.println("1 - Listar clientes ativos");
            System.out.println("2 - Adicionar clientes");
            System.out.println("3 - Eliminar um cliente");
            System.out.println("4 - Modificar dados de um cliente");
            System.out.println("5 - Consultar movimentos de um cliente e verificar credito disponivel");
            System.out.println("6 - Adicionar compra a um cliente");
            System.out.println("7 - Adicionar pagamento a um cliente");
            System.out.println("8 - Sair do programa");

            System.out.print("\nQual a opcao: ");
            opcao = input.next().charAt(0);
            input.nextLine();

            switch(opcao)
            {
                case '1' :
                    funcao_opcao1(input, listaClientes);
                    break;

                case '2' :
                    funcao_opcao2(input, listaClientes);
                    break;

                case '3' :
                    funcao_opcao3(input, listaClientes);
                    break;

                case '4' :
                    funcao_opcao4(input, listaClientes);
                    break;

                case '5' :
                    funcao_opcao5(input, listaClientes);
                    break;

                case '6' :
                    funcao_opcao6(input, listaClientes);
                    break;

                case '7' :
                    funcao_opcao7(input, listaClientes);
                    break;
            }

        }while(opcao != '8');
    }

    private static void funcao_opcao1(Scanner input, Clientes listaClientes)    //funcao que entra de outro menu para escolher
{                                                                               //visualizar um ou todos os clientes ativos
        char opcao;

        do
        {
            System.out.println("\n1 - Listar um cliente");
            System.out.println("2 - Listar todos os clientes");
            System.out.println("3 - Voltar ao menu principal");

            System.out.print("\nQual a opcao: ");
            opcao = input.next().charAt(0);
            input.nextLine();

            switch(opcao)
            {
                case '1' :
                    try
                    {
                        System.out.print("Qual o numero de cliente: ");
                        int nrCliente = input.nextInt();
                        listaClientes.mostraCliente(nrCliente);
                    }
                    catch(InputMismatchException e)
                    {
                        System.out.println("\nErro: Entrada incorreta");
                        input.nextLine();
                    }
                    break;

                case '2' :
                    listaClientes.mostraClientes();
                    break;

                case '3' :
                    System.out.println();
                    break;

                default :
                    System.out.println("\nOpcao invalida");

            }
       }while(opcao != '3');
    }

    private static void funcao_opcao2(Scanner input, Clientes listaClientes)        //funcao que me adiciona um cliente novo a lista
    {
        try
        {
            System.out.print("\nQual o nome do novo cliente: ");
            String nome = input.nextLine();
            System.out.print("Qual o numero de telefone do novo cliente: ");
            int telefone = input.nextInt();
            System.out.print("Qual o credito maximo do novo cliente: ");
            double crediMax = input.nextDouble();
            boolean estado = true;

            listaClientes.adicionaCliente(listaClientes.getNumeroMax() + 1, estado, nome, telefone, crediMax);
            listaClientes.escreverFicheiroClientes();
            System.out.println("\nCliente adicionado com sucesso\n");
        }
        catch(InputMismatchException e)
        {
            System.out.println("\nErro: Entrada Incorreta\n");
            input.nextLine();
        }
    }

    private static void funcao_opcao3(Scanner input, Clientes listaClientes)        //funcao que remove/deixa inativo um cliente
    {
        try
        {
            System.out.print("\nQual o numero de cliente que quer remover: ");
            int nrCliente = input.nextInt();
            Cliente cliente = listaClientes.dameClienteByNr(nrCliente);
            if(cliente != null)
            {
                if(cliente.getEstado() == true)
                {
                    listaClientes.removerDaLista(nrCliente);
                    listaClientes.escreverFicheiroClientes();
                }
                else
                {
                    System.out.println("\nEsse numero de cliente ja foi removido\n");
                }
            }
            else
            {
                System.out.println("\nEsse numero de cliente nao existe\n");
            }
        }
        catch(InputMismatchException d)
        {
            System.out.println("\nErro: Entrada incorreta\n");
            input.nextLine();
        }
    }

    private static void funcao_opcao4(Scanner input, Clientes listaClientes)        //funcao que entra dentro de outro menu
    {                                                                               //e me altera os campos/propiedades de um cliente
        try
        {
            System.out.print("Qual o numero de cliente que quer modificar: ");
            int nrCliente = input.nextInt();

            if(listaClientes.dameClienteByNr(nrCliente) != null)
            {
                System.out.println("\n1 - Alterar o nome");
                System.out.println("2 - Alterar o estado");
                System.out.println("3 - Alterar o numero de telefone");
                System.out.println("4 - Alterar o credito maximo");
                System.out.println("5 - Voltar ao menu principal");

                System.out.print("\nQual a opcao: ");
                char opcao = input.next().charAt(0);
                input.nextLine();

                switch(opcao)
                {
                    case '1' :
                        System.out.print("Qual o novo nome: ");
                        String nome = input.nextLine();
                        listaClientes.alteraNomeCliente(nrCliente, nome);
                        break;

                    case '2' :
                        listaClientes.alteraEstadoCliente(nrCliente);
                        break;

                    case '3' :
                        System.out.print("Qual o novo numero de telefone: ");
                        int telefone = input.nextInt();
                        listaClientes.alteraTelefoneCliente(nrCliente, telefone);
                        break;

                    case '4' :
                        System.out.print("Qual o novo credito maximo:");
                        double crediMax = input.nextDouble();
                        listaClientes.alteraCrediMaxCliente(nrCliente, crediMax);
                        break;

                    case '5' :
                        System.out.println();
                        break;

                    default :
                        System.out.println("\nOpcao Invalida\n");
                }
                listaClientes.escreverFicheiroClientes();                   //Escreve no ficheiro as alterações feitas
            }
            else
            {
                System.out.println("\nEsse numero de cliente nao existe\n");
            }
        }
        catch(InputMismatchException e)
        {
            System.out.println("\nErro: Entrada incorreta\n");
            input.nextLine();
        }
    }

    private static void funcao_opcao5(Scanner input, Clientes listaClientes)        //Funcao que consulta os movimentos de um cliente
    {                                                                               //e mostra o credimax e o credito disponivel de um cliente
        try
        {
            System.out.print("Qual o numero de cliente que quer ver: ");
            int nrCliente = input.nextInt();
            Cliente cliente = listaClientes.dameClienteByNr(nrCliente);

            if(cliente != null)
            {
                System.out.println("\nCredito Maximo: " + cliente.getCrediMax());
                double creditoDisponivel = listaClientes.getSaldo(nrCliente)+cliente.getCrediMax();
                System.out.println("Credito Disponivel: " + creditoDisponivel);
                ArrayList <String> movimentos = listaClientes.lerFicheiroCliente(nrCliente);
                System.out.println("Movimentos: ");
                for(int i=0; i<movimentos.size(); i++)
                {
                    System.out.println(movimentos.get(i));
                }
                System.out.println();
            }
            else
            {
                System.out.println("\nEsse numero de cliente nao existe\n");
            }
        }
        catch(InputMismatchException e)
        {
            System.out.println("\nErro: Entrada incorreta\n");
            input.nextLine();
        }
        catch(NumberFormatException e)
        {
            System.out.println("\nErro de leitura\n");
            input.nextLine();
        }
    }

    private static void funcao_opcao6(Scanner input, Clientes listaClientes)        //Funcao que vai adicionar uma compra a um cliente
    {
        try
        {
            System.out.print("Qual o numero de cliente que quer fazer uma compra: ");
            int nrCliente = input.nextInt();
            Cliente cliente = listaClientes.dameClienteByNr(nrCliente);

            if(cliente != null)
            {
                if(cliente.getEstado() == true)
                {
                    System.out.print("De que valor e a compra: ");
                    double compra = input.nextDouble();

                    listaClientes.fazerCompra(nrCliente, compra);
                }
                else
                {
                    System.out.println("\nImpossivel realizar a operacao, esse numero de cliente esta inativo\n");
                }
            }
            else
            {
                System.out.println("\nEsse numero de cliente nao existe\n");
            }
        }
        catch(InputMismatchException e)
        {
            System.out.println("\nErro: Entrada incorreta\n");
            input.nextLine();
        }
        catch(NumberFormatException e)
        {
            System.out.println("\nErro de leitura\n");
            input.nextLine();
        }
    }

    private static void funcao_opcao7(Scanner input, Clientes listaClientes)        //Funcao que faz depositos na conta de um cliente
    {
        try
        {
            System.out.print("Qual o numero de cliente que quer fazer um pagamento: ");
            int nrCliente = input.nextInt();
            Cliente cliente = listaClientes.dameClienteByNr(nrCliente);

            if(cliente != null)
            {
                if(cliente.getEstado() == true)
                {
                    System.out.print("De que valor e o pagamento: ");
                    double pagamento = input.nextDouble();

                    listaClientes.fazerPagamento(nrCliente, pagamento);
                }
                else
                {
                    System.out.println("\nImpossivel realizar a operacao, esse numero de cliente esta inativo\n");
                }
            }
            else
            {
                System.out.println("\nEsse numero de cliente nao existe\n");
            }
        }
        catch(InputMismatchException e)
        {
            System.out.println("\nErro: Entrada incorreta\n");
            input.nextLine();
        }
        catch(NumberFormatException e)
        {
            System.out.println("\nErro de leitura\n");
            input.nextLine();
        }
    }

}


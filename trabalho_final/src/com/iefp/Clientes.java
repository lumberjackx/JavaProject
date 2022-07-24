package com.iefp;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Clientes
{
    private ArrayList<Cliente> listaClientes = new ArrayList<>();       //Array list do tipo cliente usada para guardar a lista de clientes

    public void fazerPagamento(int nrCliente, double valor)             //Funcao que me permite fazer depositos na conta de um cliente
    {
        String filename = nrCliente + ".csv";
        ArrayList<String> movimentosCliente = lerFicheiroCliente(nrCliente);

        movimentosCliente.add(String.valueOf(valor));           //adiciona a lista movimentosCLiente o valor do deposito

        try
        {
            File file = new File(filename);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            for(int i=0; i < movimentosCliente.size(); i++)
            {
                printWriter.println(movimentosCliente.get(i));      //Aqui vai escrever no ficheiro a minha lista movimentosCliente
            }                                                       //ja atualizada
            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();

            System.out.println("\nPagamento feito com sucesso\n");
        }
        catch(IOException e)
        {
            System.out.println("\nErro ao escrever no ficheiro\n");
        }


    }

    public void fazerCompra(int nrCliente, double valor)                //Funcao que adiciona uma compra ao cliente
    {
        String filename = nrCliente + ".csv";
        ArrayList<String> movimentosCliente = lerFicheiroCliente(nrCliente);
        double saldo = getSaldo(nrCliente);
        Cliente cliente = dameClienteByNr(nrCliente);

        if(saldo - valor >= -cliente.getCrediMax())                 //So deixa fazer a compra se o saldo >= -crediMax
        {
            movimentosCliente.add(String.valueOf(-valor));          //Adiciona a lista movimentosCliente a compra = -valor

            try
            {
                File file = new File(filename);
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                PrintWriter printWriter = new PrintWriter(bufferedWriter);

                for(int i=0; i < movimentosCliente.size(); i++)
                {
                    printWriter.println(movimentosCliente.get(i));      //Aqui escrevo no ficheiro nrCliente.csv a lista movimentosCLiente
            }                                                           //ja atualizada
                printWriter.close();
                bufferedWriter.close();
                fileWriter.close();

                System.out.println("\nCompra feita com sucesso\n");
            }
            catch(IOException e)
            {
                System.out.println("\nErro ao escrever no ficheiro\n");
            }
        }
        else
        {
            System.out.println("\nNao tem saldo suficiente para fazer essa compra\n");
        }
    }

    public double getSaldo(int nrCliente)               //Funcao que me le o ficheiro nrCLiente.csv e me devolve o saldo desse cliente
    {
        String filename = nrCliente + ".csv";
        String linha;

        Cliente cliente = dameClienteByNr(nrCliente);

        double saldo = cliente.getCrediMax();       //Inicializo o saldo ao crediMax

        try
        {
            File file = new File(filename);

            if(file.exists() && file.canRead())
            {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                while ((linha = bufferedReader.readLine()) != null)
                {
                    saldo = saldo + Double.valueOf(linha);          //Aqui o meu saldo vai sendo atualizado conforme os movimentos
                }                                                   //presentes no ficheiro nrcliente.csv
                bufferedReader.close();
                fileReader.close();
            }
            else
            {
                System.out.println("\nImpossivel ler ficheiro\n");
            }
        }
        catch(IOException e)
        {
            System.out.println("\nOcorreu um erro ao abrir ficheiro\n");
        }
       return saldo;
    }

    public ArrayList<String> lerFicheiroCliente(int nrCliente)              //Funcao que me le o ficheiro nrCliente.csv e me
    {                                                                       //devolve uma lista com os movimentos de um cliente
        String filename = nrCliente + ".csv";
        String linha;
        ArrayList<String> movimentosCliente = new ArrayList<>();

        try
        {
            File file = new File(filename);

            if(file.exists() && file.canRead())
            {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                while ((linha = bufferedReader.readLine()) != null)
                {
                    movimentosCliente.add(linha);                               //Adiciona linha a linha cada movimento á lista
                }                                                               //movimentosCliente
                bufferedReader.close();
                fileReader.close();

                return movimentosCliente;
            }
            else
            {
                System.out.println("\nImpossivel ler ficheiro\n");
            }
        }
        catch(IOException e)
        {
            System.out.println("\nOcorreu um erro ao abrir ficheiro\n");
        }
        return null;
    }

    public void alteraCrediMaxCliente(int nrCliente, double crediMax)       //Funcao que me altera/faz set da propiedade crediMax
    {
        Cliente cliente = dameClienteByNr(nrCliente);

        cliente.setCrediMax(crediMax);

        System.out.println("\nCredito maximo de cliente alterado com sucesso\n");
    }

    public void alteraTelefoneCliente(int nrCliente, int novoNumero)        //Funcao que me altera/faz set da propriedade telefone
    {                                                                       //de um cliente
        Cliente cliente = dameClienteByNr(nrCliente);

        cliente.setTelefone(novoNumero);

        System.out.println("\nNumero de telefone alterado com sucesso\n");
    }

    public void alteraEstadoCliente(int nrCliente)              //Funcao que me altera o estado de um cliente de false para true
    {                                                           //porque se quisermos o contrario, e na terceira opcao do menu
        Cliente cliente = dameClienteByNr(nrCliente);           //no remove cliente ele passa de true para false

        if(cliente.getEstado()== false)
        {
            cliente.setEstado(true);
            System.out.println("\nEstado de cliente alterado para true\n");
        }
        else
        {
            System.out.println("\nEstado de cliente ja e true\n");
        }
    }

    public void alteraNomeCliente(int nrCliente, String novoNome)           //Funcao que me altera/faz set da propriedade nome
    {                                                                       //de um cliente
        Cliente cliente = dameClienteByNr(nrCliente);

        cliente.setNome(novoNome);

        System.out.println("\nNome de cliente alterado com sucesso\n");
    }

    public void removerDaLista(int nrCliente)               //Funcao que me deixa inativo um cliente ou seja passa o estado de
    {                                                       //true para false
        Cliente cliente = dameClienteByNr(nrCliente);

        cliente.setEstado(false);
        System.out.println("\nCliente tornado inativo com sucesso\n");

    }

    public int getNumeroMax()                              //Funcao que me vai buscar o nrCliente do fim da listaClientes que e
    {                                                      //suposto ser o nr maximo
        Cliente cliente = listaClientes.get(listaClientes.size()-1);

        return cliente.getNrCliente();
    }

    public Cliente dameClienteByNr(int nrCliente)           //Funcao que me retorna null se nao encontrar na listaCLiente um
    {                                                       //cliente com o nrCliente passado por parametro ou entao da-me o
        for(int i=0; i<listaClientes.size();i++)            //cliente encontrado
        {
            if(listaClientes.get(i).getNrCliente() == nrCliente)
            {
                return listaClientes.get(i);
            }
        }
        return null;
    }

    public void mostraCliente(int nrCliente)                //Esta funcao e chamada do menu 1 e mostra os dados de um cliente
    {                                                       //ativo em especifico
        Cliente cliente = dameClienteByNr(nrCliente);

        if(cliente != null)
        {
            if(cliente.getEstado() == true)
            {
                System.out.println("\nNumero de cliente: " + cliente.getNrCliente());
                System.out.println("Nome: " + cliente.getNome());
                System.out.println("Telefone: " + cliente.getTelefone());
                System.out.println("Credito Maximo: " + cliente.getCrediMax());
            }
            else
            {
                System.out.println("\nEsse numero de cliente esta inativo");
            }
        }
        else
        {
            System.out.println("\nEsse numero de cliente nao existe");
        }
    }

    public void mostraClientes()            //Esta funcao e chamada do menu 1 e mostra-me a listaClientes so com o nrCliente e o nome
    {
        System.out.println();

        for(int i=0; i < listaClientes.size(); i++)
        {
            if(listaClientes.get(i).getEstado() == true)
            {
                System.out.println("Numero cliente: " + listaClientes.get(i).getNrCliente() + " | Nome: " + listaClientes.get(i).getNome());
            }
        }
    }

    public void adicionaCliente(int nrCliente, boolean estado, String nome, int telefone, double crediMax)  //Esta Funcao e chamada
{                                                                             //do menu2 e adiciona um cliente a nossa listaCLientes
        Cliente c = new Cliente();                                            //e cria um ficheiro de movimentos relativos a esse
                                                                                //cliente
        c.setNrCliente(nrCliente);
        c.setEstado(estado);
        c.setNome(nome);
        c.setTelefone(telefone);
        c.setCrediMax(crediMax);

        this.listaClientes.add(c);

        criaFicheiroMovimentos(nrCliente);
    }

    private void criaFicheiroMovimentos(int nrCliente)          //Funcao que me cria um ficheiro para gravar os movimentos de cada
{                                                               //cliente
        String filename = nrCliente + ".csv";

        try
        {
            File file =new File(filename);
            file.createNewFile();
        }
        catch(IOException e)
        {
            System.out.println("\nImpossivel criar ficheiro\n");
        }
    }

    public void lerFicheiroClientes()               //Funcao que le o ficheiro clientes.csv e popula a minha listaClientes
    {
        String filename = "clientes.csv";
        String linha;

        try
        {
            File file = new File(filename);

            if(file.exists() && file.canRead())
            {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                while ((linha = bufferedReader.readLine()) != null)
                {
                    StringTokenizer st = new StringTokenizer(linha, ";");

                    int nrCliente = Integer.valueOf(st.nextToken());

                    boolean estado = Boolean.parseBoolean(st.nextToken());

                    String nome = st.nextToken();

                    int telefone = Integer.valueOf(st.nextToken());

                    double crediMax = Double.valueOf(st.nextToken());

                    adicionaCliente(nrCliente, estado, nome, telefone, crediMax);
                }

                bufferedReader.close();
                fileReader.close();
            }
            else
            {
                System.out.println("Impossivel ler ficheiro");
            }
        }
        catch(IOException e)
        {
            System.out.println("Ocorreu um erro");
        }
    }

    public void escreverFicheiroClientes()              //Funcao que me escreve a minha listaCLientes no ficheiro clientes.csv
    {
        String filename = "clientes.csv";

        try
        {
            File file = new File(filename);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

           for(int i=0; i < listaClientes.size(); i++)
           {
               printWriter.println(listaClientes.get(i).getNrCliente() + ";" + listaClientes.get(i).getEstado() + ";" + listaClientes.get(i).getNome() + ";" + listaClientes.get(i).getTelefone() + ";" + listaClientes.get(i).getCrediMax());
           }

            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();

        }
        catch(IOException ex)
        {
            System.out.println("\nOcorreu um erro ao escrever no ficheiro\n");
        }
    }

}

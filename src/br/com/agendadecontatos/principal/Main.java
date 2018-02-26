package br.com.agendadecontatos.principal;

import br.com.agendadecontatos.classes.Contatos;
import br.com.agendadecontatos.persistencia.ContatosDao;
import java.io.RandomAccessFile;
import java.util.List;
import javax.swing.JOptionPane;
        
public class Main {

    public static void main(String[] args) throws Exception{
        
        RandomAccessFile arq = new RandomAccessFile ("Saida.txt", "rw");
        Main objMain = new Main();
        
        int menuPrincial;
        menuPrincial = Integer.parseInt(JOptionPane.showInputDialog("Bem Vindo!\n\nO que voce deseja fazer?\n1 - Inserir novo contato.\n2 - Exibir todos os contatos.\n3 - Pesquisar por nome.\n4 - Pesquisar por id.\n5 - Editar um contato.\n6 - Apagar um contato.\n0 - Sair do programa."));
        for(int i = 0; menuPrincial != 0; i++){
            if(menuPrincial == 1){
                objMain.salvar();
            }
            if(menuPrincial == 2){
                objMain.exibirTodos();
            }
            if(menuPrincial == 3){
                objMain.buscaPorNome();
            }
            if(menuPrincial == 4){
                objMain.exibirPorId();
            }
            if(menuPrincial == 5){
                objMain.executarUpdate();
            }
            if(menuPrincial == 6){
                objMain.executarDelete();
            }
            
            menuPrincial = Integer.parseInt(JOptionPane.showInputDialog("Bem Vindo!\n\nO que voce deseja fazer?\n1 - Inserir novo contato.\n2 - Exibir todos os contatos.\n3 - Pesquisar por nome.\n4 - Pesquisar por id.\n5 - Editar um contato.\n6 - Apagar um contato.\n0 - Para sair do programa."));
        }
        
    }
    
    //metodo que salva um novo contato
    public void salvar(){
        
        Contatos objContatos = new Contatos();
            
        objContatos.setNome(JOptionPane.showInputDialog("Entre com o nome: "));
        objContatos.setTelefone(JOptionPane.showInputDialog("Entre com  o telefone: "));
        objContatos.setCelular(JOptionPane.showInputDialog("Entre com o celular: "));
        objContatos.setEmail(JOptionPane.showInputDialog("Entre com o email: "));
        objContatos.setEndereco(JOptionPane.showInputDialog("Entre com o endereco: "));
        
        try {
            ContatosDao objContatosDao = new ContatosDao();
            objContatosDao.salvar(objContatos);
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
        }
    }
    
    //metodo que exibe todos os contatos
    public void exibirTodos(){
        
        ContatosDao objContatosDao = new ContatosDao();
        try {
            objContatosDao.exibirTodos();
        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
        }
    }
    
    //metodo para buscar por nome atraves de uma lista
    public void buscaPorNome(){
        ContatosDao objContatosDao = new ContatosDao();
        
        try {
            
            String nome = JOptionPane.showInputDialog("Digite o nome a pesquisar: ");
            List <Contatos> listaNomes= objContatosDao.consultaLista(nome);
            
            for(Contatos objContatos: listaNomes){
                
                JOptionPane.showMessageDialog(null, "ID: " +objContatos.getId()+ "\nNome: " +objContatos.getNome()+ "\nTelefone: " +objContatos.getCelular()+ "\nCelular: " +objContatos.getCelular()+ "\nEmail: " +objContatos.getEmail()+ "\nEndereco: " +objContatos.getEndereco());
            }
            
        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
        }
        
    }
    
    //Metodo que exibe um unico contato
    public void exibirPorId(){
        
        ContatosDao objContatosDao = new ContatosDao();
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Entre com o id que deseja buscar: "));
            Contatos objContatos = objContatosDao.exibirPorId(id);
            
            JOptionPane.showMessageDialog(null, "ID: " +objContatos.getId()+ "\nNome: " +objContatos.getNome()+ "\nTelefone: " +objContatos.getCelular()+ "\nCelular: " +objContatos.getCelular()+ "\nEmail: " +objContatos.getEmail()+ "\nEndereco: " +objContatos.getEndereco());
            
        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
        }
    }
    
    //metodo para fazer um update
    public void executarUpdate(){
        
        Contatos objContatos = new Contatos();
        ContatosDao objContatosDao = new ContatosDao();
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Entre com o id que deseja alterar: "));
            objContatos.setId(id);
            String nome = JOptionPane.showInputDialog("Entre com o nome a ser alterado; ");
            objContatos.setNome(nome);
            String telefone = JOptionPane.showInputDialog("Entre com o telefone a ser alterado: ");
            objContatos.setTelefone(telefone);
            String celular = JOptionPane.showInputDialog("Entre com o celular a ser alterado: ");
            objContatos.setCelular(celular);
            String email = JOptionPane.showInputDialog("Entre com o email a ser alterado: ");
            objContatos.setEmail(email);
            String endereco = JOptionPane.showInputDialog("Entre com o endereco a ser alterado: ");
            objContatos.setEndereco(endereco);
            
            objContatosDao.alterar(objContatos);
        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
        }
    }
    
    
    //metodo para fazer um delete
    public void executarDelete(){
        ContatosDao objContatosDao = new ContatosDao();
        
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Entre com o ID que deseja deletar: "));
            objContatosDao.deletar(id);
            
        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
        }
    }
}


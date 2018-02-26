package br.com.agendadecontatos.persistencia;

import br.com.agendadecontatos.classes.Contatos;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ContatosDao extends Dao {

    public void salvar(Contatos objContatos) throws Exception {

        abrirDB();

        String query = "INSERT into contatos values(null, ?,?,?,?,?)";
        pstmt = conn.prepareStatement(query);

        pstmt.setString(1, objContatos.getNome());
        pstmt.setString(2, objContatos.getTelefone());
        pstmt.setString(3, objContatos.getCelular());
        pstmt.setString(4, objContatos.getEmail());
        pstmt.setString(5, objContatos.getEndereco());
        pstmt.execute();

        fecharDB();
    }

    public Contatos exibirTodos() throws Exception {
        abrirDB();

        String query = "SELECT * from contatos";
        pstmt = conn.prepareStatement(query);
        rs = pstmt.executeQuery();
        String impressao = "";
        RandomAccessFile arq = new RandomAccessFile ("Saida.txt", "rw");

        if (rs.first()) {
            do {
                System.out.println("Id: " + rs.getInt("id") + " Nome: " + rs.getString("nome") + " Telefone: " + rs.getString("telefone") + " Celular: " + rs.getString("celular") + " Endereco: " + rs.getString("endereco") + " Email: " + rs.getString("email")+ "\n");
                impressao = impressao + ("\nId: " + rs.getInt("id") + " - Nome: " + rs.getString("nome") + " - Telefone: " + rs.getString("telefone") + " - Celular: " + rs.getString("celular") + " - Endereco: " + rs.getString("endereco") + " - Email: " + rs.getString("email"));
            } while (rs.next());
            {
                JOptionPane.showMessageDialog(null, impressao);
                arq.writeBytes (impressao +"\n");
            }
        }

        fecharDB();
        return null;
    }

    public Contatos exibirPorId(int id) throws Exception {
        abrirDB();

        String query = "SELECT * from contatos WHERE id = ?";
        pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, id);
        rs = pstmt.executeQuery();

        Contatos objContatos = new Contatos();

        if (rs.next()) {

            objContatos.setId(rs.getInt("id"));
            objContatos.setNome(rs.getString("nome"));
            objContatos.setTelefone(rs.getString("telefone"));
            objContatos.setCelular(rs.getString("celular"));
            objContatos.setEmail(rs.getString("email"));
            objContatos.setEndereco(rs.getString("endereco"));

        }
        fecharDB();
        return objContatos;
    }

    public void alterar(Contatos objContatos) throws Exception {
        abrirDB();
        String query = "UPDATE contatos set nome = ?, telefone = ?, celular = ?, email = ?, endereco = ? WHERE id =?";
        pstmt = conn.prepareStatement(query);

        pstmt.setString(1, objContatos.getNome());
        pstmt.setString(2, objContatos.getTelefone());
        pstmt.setString(3, objContatos.getCelular());
        pstmt.setString(4, objContatos.getEmail());
        pstmt.setString(5, objContatos.getEndereco());
        pstmt.setInt(6, objContatos.getId());
        pstmt.executeUpdate();
        fecharDB();
    }

    public void deletar(int id) throws Exception {
        abrirDB();

        String query = "DELETE from contatos WHERE id = ?";
        pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, id);
        pstmt.execute();

        fecharDB();
    }
    
    public List<Contatos> consultaLista(String nome)throws Exception{
        abrirDB();
        
        String query = "SELECT * from contatos WHERE nome like ?";
        pstmt = conn.prepareStatement(query);
        pstmt.setString(1, nome + "%");
        rs = pstmt.executeQuery();
        
        Contatos objContatos;
        
        List <Contatos> listaNomes = new ArrayList<Contatos>();
        while(rs.next()){
            objContatos = new Contatos();
            
            objContatos.setId(rs.getInt("id"));
            objContatos.setNome(rs.getString("nome"));
            objContatos.setTelefone(rs.getString("telefone"));
            objContatos.setCelular(rs.getString("celular"));
            objContatos.setEmail(rs.getString("email"));
            objContatos.setEndereco(rs.getString("endereco"));
            
            listaNomes.add(objContatos);
        }
        fecharDB();
        return listaNomes;
    }
}

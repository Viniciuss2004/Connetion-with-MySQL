import java.sql.*;

public class ConnectionMysql {

    // Método para inserir um nome no banco de dados
    public void insertName(String nome) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Estabelece a conexão com o banco de dados
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/teste", "root", "Vini240804");

            // SQL para inserção de dados
            String sql = "INSERT INTO nome (nome) VALUES (?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nome);

            // Executa a inserção
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Nome inserido com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechamento dos recursos
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

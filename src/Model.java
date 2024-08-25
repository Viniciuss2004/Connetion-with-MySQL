import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Model {

    // Método para inserir um nome no banco de dados
    public void insertName(String nome) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/teste", "root", "Vini240804");
            String sql = "INSERT INTO nome (nome) VALUES (?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nome);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Nome inserido com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para recuperar todos os nomes do banco de dados com seus IDs
    public List<String> getNamesWithIds() {
        List<String> names = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/teste", "root", "Vini240804");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT id, nome FROM nome");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("nome");
                names.add(id + " - " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return names;
    }

    // Método para deletar um nome pelo ID e reorganizar os IDs
    public void deleteNameById(int id) {
        Connection connection = null;
        PreparedStatement deleteStatement = null;
        Statement updateStatement = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/teste", "root", "Vini240804");

            // Deletar o registro
            String deleteSql = "DELETE FROM nome WHERE id = ?";
            deleteStatement = connection.prepareStatement(deleteSql);
            deleteStatement.setInt(1, id);
            int rowsAffected = deleteStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Nome deletado com sucesso!");

                // Reorganizar os IDs
                updateStatement = connection.createStatement();
                updateStatement.executeUpdate("SET @count = 0");
                updateStatement.executeUpdate("UPDATE nome SET id = (@count := @count + 1)");
                updateStatement.executeUpdate("ALTER TABLE nome AUTO_INCREMENT = 1");
            } else {
                System.out.println("Nenhum nome encontrado com o ID especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (deleteStatement != null) deleteStatement.close();
                if (updateStatement != null) updateStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


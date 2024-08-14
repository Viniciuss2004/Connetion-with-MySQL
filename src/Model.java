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

    // Método para recuperar todos os nomes do banco de dados
    public List<String> getNames() {
        List<String> names = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/teste", "root", "Vini240804");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM nome");

            while (resultSet.next()) {
                names.add(resultSet.getString("nome"));
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
}

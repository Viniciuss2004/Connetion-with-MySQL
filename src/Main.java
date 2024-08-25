import javax.swing.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = JOptionPane.showInputDialog("Digite o número de acordo com a necessidade:\n\n1- Inserir nome\n2- Listar nomes\n3- Deletar nome pelo ID\n4- Sair do programa\n");

            // Verifica se a entrada não é nula ou vazia
            if (input != null && !input.trim().isEmpty()) {
                int num;
                try {
                    num = Integer.parseInt(input.trim()); // Converte a entrada para um número inteiro
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, digite um número.");
                    continue;
                }

                if (num == 1) {
                    String nome = JOptionPane.showInputDialog("Digite o nome para inserir:");
                    if (nome != null && !nome.trim().isEmpty()) {
                        model.insertName(nome);
                    } else {
                        JOptionPane.showMessageDialog(null, "Nome inválido. Tente novamente.");
                    }

                } else if (num == 2) {
                    List<String> names = model.getNamesWithIds();
                    StringBuilder nomesConcatenados = new StringBuilder("Nomes salvos no banco de dados:\n\n");
                    for (String name : names) {
                        nomesConcatenados.append(name).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, nomesConcatenados.toString());

                } else if (num == 3) {
                    String idInput = JOptionPane.showInputDialog("Digite o ID do nome a ser deletado:");
                    if (idInput != null && !idInput.trim().isEmpty()) {
                        try {
                            int id = Integer.parseInt(idInput.trim());
                            model.deleteNameById(id);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "ID inválido. Por favor, digite um número.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "ID inválido. Tente novamente.");
                    }

                } else if (num == 4) {
                    JOptionPane pane = new JOptionPane("Saindo...", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
                    JDialog dialog = pane.createDialog("Mensagem");

                    // Timer para fechar o diálogo após 3 segundos
                    Timer timer = new Timer(1000, e -> dialog.dispose());
                    timer.setRepeats(false);
                    timer.start();

                    dialog.setVisible(true);
                    break;

                } else {
                    JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, digite um número.");
            }
        }

        scanner.close();
    }
}

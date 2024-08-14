import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Digite 1 para inserir um nome ou 2 para listar os nomes. Digite 0 para sair:");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            if (choice == 1) {
                System.out.println("Digite o nome para inserir:");
                String nome = scanner.nextLine();
                model.insertName(nome);
            } else if (choice == 2) {
                List<String> names = model.getNames();
                System.out.println("Nomes salvos no banco de dados:");
                for (String name : names) {
                    System.out.println(name);
                }
            } else if (choice == 0) {
                System.out.println("Saindo...");
                break;
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
        
        scanner.close();
    }
}

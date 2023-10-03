package biblioteca;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        SQLiteConnector.getConnection(); // Inicialize a conexão com o banco de dados
        DatabaseSetup.createTables(); // Crie as tabelas se elas não existirem

        System.out.println("Bem-vindo à BibliotecaJava!");

        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Adicionar Livro");
            System.out.println("2. Remover Livro");
            System.out.println("3. Buscar Livros por Título");
            System.out.println("4. Empréstimo de Livro");
            System.out.println("5. Devolução de Livro");
            System.out.println("6. Adicionar Usuário");
            System.out.println("7. Listar Livros Disponíveis");
            System.out.println("8. Listar Livros Emprestados");
            System.out.println("9. Sair");

            int escolha = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do teclado

            switch (escolha) {
                case 1:
                    System.out.println("Digite o título do livro:");
                    String tituloLivro = scanner.nextLine();
                    System.out.println("Digite o autor do livro:");
                    String autorLivro = scanner.nextLine();
                    System.out.println("Digite o ISBN do livro:");
                    String isbnLivro = scanner.nextLine();
                    Livro novoLivro = new Livro(tituloLivro, autorLivro, isbnLivro);
                    biblioteca.adicionarLivro(novoLivro);
                    System.out.println("Livro adicionado com sucesso!");
                    break;
                case 2:
                    System.out.println("Digite o título do livro que deseja remover:");
                    String tituloRemover = scanner.nextLine();
                    List<Livro> livrosEncontrados = biblioteca.buscarLivrosPorTitulo(tituloRemover);
                    if (!livrosEncontrados.isEmpty()) {
                        System.out.println("Livros encontrados com esse título:");
                        for (int i = 0; i < livrosEncontrados.size(); i++) {
                            System.out.println((i + 1) + ". " + livrosEncontrados.get(i).getTitulo());
                        }
                        System.out.println("Digite o número do livro que deseja remover:");
                        int numeroLivro = scanner.nextInt();
                        if (numeroLivro >= 1 && numeroLivro <= livrosEncontrados.size()) {
                            Livro livroRemover = livrosEncontrados.get(numeroLivro - 1);
                            biblioteca.removerLivro(livroRemover);
                            System.out.println("Livro removido com sucesso!");
                        } else {
                            System.out.println("Opção inválida.");
                        }
                    } else {
                        System.out.println("Nenhum livro com esse título encontrado.");
                    }
                    break;
                case 3:
                    System.out.println("Digite o título do livro que deseja buscar:");
                    String tituloBuscar = scanner.nextLine();
                    List<Livro> livrosEncontradosBusca = biblioteca.buscarLivrosPorTitulo(tituloBuscar);
                    if (!livrosEncontradosBusca.isEmpty()) {
                        System.out.println("Livros encontrados com esse título:");
                        for (Livro livro : livrosEncontradosBusca) {
                            System.out.println(livro);
                        }
                    } else {
                        System.out.println("Nenhum livro com esse título encontrado.");
                    }
                    break;
                case 4:
                    System.out.println("Digite o título do livro que deseja emprestar:");
                    String tituloEmprestar = scanner.nextLine();
                    List<Livro> livrosEncontradosEmprestimo = biblioteca.buscarLivrosPorTitulo(tituloEmprestar);
                    if (!livrosEncontradosEmprestimo.isEmpty()) {
                        System.out.println("Livros encontrados com esse título:");
                        for (int i = 0; i < livrosEncontradosEmprestimo.size(); i++) {
                            System.out.println((i + 1) + ". " + livrosEncontradosEmprestimo.get(i).getTitulo());
                        }
                        System.out.println("Digite o número do livro que deseja emprestar:");
                        int numeroEmprestimo = scanner.nextInt();
                        if (numeroEmprestimo >= 1 && numeroEmprestimo <= livrosEncontradosEmprestimo.size()) {
                            Livro livroEmprestar = livrosEncontradosEmprestimo.get(numeroEmprestimo - 1);
                            System.out.println("Digite o nome do usuário que deseja emprestar o livro:");
                            String nomeUsuarioEmprestimo = scanner.next();
                            Usuario usuarioEmprestimo = new Usuario(nomeUsuarioEmprestimo, 1); // ID do usuário pode ser gerado automaticamente
                            biblioteca.emprestarLivro(livroEmprestar, usuarioEmprestimo);
                        } else {
                            System.out.println("Opção inválida.");
                        }
                    } else {
                        System.out.println("Nenhum livro com esse título encontrado.");
                    }
                    break;
                case 5:
                    System.out.println("Digite o título do livro que deseja devolver:");
                    String tituloDevolucao = scanner.nextLine();
                    List<Livro> livrosEncontradosDevolucao = biblioteca.buscarLivrosPorTitulo(tituloDevolucao);
                    if (!livrosEncontradosDevolucao.isEmpty()) {
                        System.out.println("Livros encontrados com esse título:");
                        for (int i = 0; i < livrosEncontradosDevolucao.size(); i++) {
                            System.out.println((i + 1) + ". " + livrosEncontradosDevolucao.get(i).getTitulo());
                        }
                        System.out.println("Digite o número do livro que deseja devolver:");
                        int numeroDevolucao = scanner.nextInt();
                        if (numeroDevolucao >= 1 && numeroDevolucao <= livrosEncontradosDevolucao.size()) {
                            Livro livroDevolucao = livrosEncontradosDevolucao.get(numeroDevolucao - 1);
                            biblioteca.retornarLivro(livroDevolucao);
                        } else {
                            System.out.println("Opção inválida.");
                        }
                    } else {
                        System.out.println("Nenhum livro com esse título encontrado.");
                    }
                    break;
                case 6:
                    System.out.println("Digite o nome do novo usuário:");
                    String nomeNovoUsuario = scanner.nextLine();
                    Usuario novoUsuario = new Usuario(nomeNovoUsuario, 1); // ID do usuário pode ser gerado automaticamente
                    biblioteca.adicionarUsuario(novoUsuario);
                    System.out.println("Usuário adicionado com sucesso!");
                    break;
                case 7:
                    biblioteca.listarLivrosDisponiveis();
                    break;
                case 8:
                    biblioteca.listarLivrosEmprestados();
                    break;
                case 9:
                    System.out.println("Obrigado por usar a BibliotecaJava!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        }
    }
}

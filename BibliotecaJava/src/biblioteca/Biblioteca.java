package biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livro> livrosDisponiveis;
    private List<Livro> livrosEmprestados;
    private List<Usuario> usuarios;

    public Biblioteca() {
        livrosDisponiveis = new ArrayList<>();
        livrosEmprestados = new ArrayList<>();
        usuarios = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        livrosDisponiveis.add(livro);
    }

    public void removerLivro(Livro livro) {
        livrosDisponiveis.remove(livro);
        livrosEmprestados.remove(livro);
    }

    public List<Livro> buscarLivrosPorTitulo(String titulo) {
        List<Livro> resultado = new ArrayList<>();
        for (Livro livro : livrosDisponiveis) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                resultado.add(livro);
            }
        }
        return resultado;
    }

    public void emprestarLivro(Livro livro, Usuario usuario) {
        if (livrosDisponiveis.contains(livro)) {
            livrosDisponiveis.remove(livro);
            livrosEmprestados.add(livro);
            System.out.println("O livro '" + livro.getTitulo() + "' foi emprestado para " + usuario.getNome());
        } else {
            System.out.println("O livro '" + livro.getTitulo() + "' não está disponível para empréstimo.");
        }
    }

    public void retornarLivro(Livro livro) {
        if (livrosEmprestados.contains(livro)) {
            livrosEmprestados.remove(livro);
            livrosDisponiveis.add(livro);
            System.out.println("O livro '" + livro.getTitulo() + "' foi retornado à biblioteca.");
        } else {
            System.out.println("Este livro não foi emprestado da biblioteca.");
        }
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void listarLivrosDisponiveis() {
        System.out.println("Livros Disponíveis na Biblioteca:");
        for (Livro livro : livrosDisponiveis) {
            System.out.println(livro);
        }
    }

    public void listarLivrosEmprestados() {
        System.out.println("Livros Emprestados:");
        for (Livro livro : livrosEmprestados) {
            System.out.println(livro);
        }
    }
}

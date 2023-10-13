package ui;

import java.util.Scanner;
import java.util.*;
import java.io.*;
import academico.Aluno;
import academico.Disciplina;



public class Sistema {
	private List<Aluno> alunos;
    private Scanner scanner;

    public Sistema() {
        this.alunos = new ArrayList<>();
        this.scanner = new Scanner(System.in).useLocale(Locale.US);
    }

    public void executar() {
    	System.out.println("Bem vindo ao sistema de notas!");
    	System.out.println();

        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();

            try {
                switch (opcao) {
                    case 1:
                        inserirAluno();
                        break;
                    case 2:
                        inserirDisciplina();
                        break;
                    case 3:
                        adicionarNota();
                        break;
                    case 4:
                        calcularMedia();
                        break;
                    case 5:
                        listarAlunos();
                        break;
                    case 6:
                        sair();
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

            System.out.println();
        } while (opcao != 6);
    }

    private void exibirMenu() {
        System.out.println("==== Menu ====\n"
        				+"1. Inserir aluno\n"
        				+"2. Inserir disciplina\n"
        				+"3. Adicionar nota na avaliação\n"
        				+"4. Calcular média\n"
        				+"5. Listar todos os alunos com as suas disciplinas e médias\n"
       					+"6. Sair");
    }

    private int lerOpcao() {
        System.out.print("Digite a opção desejada: ");
        return scanner.nextInt();
    }

    private void inserirAluno() {
        System.out.print("Digite o numero da Matricula do aluno: ");
        int numMatricula = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite o nome do aluno: ");
        String nome = scanner.nextLine();

        Aluno aluno = new Aluno(nome, numMatricula );
        alunos.add(aluno);
        System.out.println("Aluno inserido com sucesso.");
    }

    private void inserirDisciplina() {
        System.out.print("Digite o código da disciplina: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Digite o nome da disciplina: ");
        String nome = scanner.nextLine();

        Disciplina disciplina = new Disciplina(codigo, nome);

        System.out.print("Digite o Numero da matricula do aluno: ");
        int numMatricula = scanner.nextInt();
        scanner.nextLine(); 

        Aluno aluno = encontrarAlunoPorCodigo(numMatricula);
        if (aluno != null) {
            aluno.adicionarDisciplina(disciplina);
            System.out.println("Disciplina inserida com sucesso para o aluno " + aluno.getNome() + ".");
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    private void adicionarNota() {
        System.out.print("Digite o código da disciplina: ");
        int codigoDisciplina = scanner.nextInt();

        Disciplina disciplina = encontrarDisciplinaPorCodigo(codigoDisciplina);
        if (disciplina != null) {
            System.out.print("Digite a nota: ");
            double nota = scanner.nextDouble();
            validarNota(nota);
            disciplina.adicionarNota(nota);

            System.out.println("Nota adicionada com sucesso para a disciplina " + disciplina.getNome() + ".");
        } else {
            System.out.println("Disciplina não encontrada.");
        }
    }

    private void calcularMedia() {
        System.out.print("Digite o código do aluno: ");
        int codigoAluno = scanner.nextInt();

        Aluno aluno = encontrarAlunoPorCodigo(codigoAluno);
        if (aluno != null) {
            List<Disciplina> disciplinas = aluno.getDisciplinas();
            for (Disciplina disciplina : disciplinas) {
                try {
                    double mediaAritmetica = disciplina.calcularMediaAritmetica();
                    double mediaPonderada = disciplina.calcularMediaPonderada();
                    String resultadoFinal = mediaAritmetica >= 7 ? "Aprovado" : "Reprovado";

                    System.out.println("Disciplina: " + disciplina.getNome());
                    System.out.println("Média Aritmética: " + mediaAritmetica);
                    System.out.println("Média Ponderada: " + mediaPonderada);
                    System.out.println("Resultado Final: " + resultadoFinal);
                    System.out.println();
                } catch (IllegalStateException e) {
                    System.out.println("Erro ao calcular média da disciplina " + disciplina.getNome() + ": " + e.getMessage());
                }
            }
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    private void listarAlunos() {
        for (Aluno aluno : alunos) {
            System.out.println("Aluno: " + aluno.getNome());
            List<Disciplina> disciplinas = aluno.getDisciplinas();
            for (Disciplina disciplina : disciplinas) {
                System.out.println("Disciplina: " + disciplina.getNome());
                List<Double> notas = disciplina.getNotas();
                System.out.print("Notas: ");
                for (double nota : notas) {
                    System.out.print(nota + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    private void sair() {
        salvarDados();
        System.out.println("Sistema encerrado.");
    }

     public Aluno encontrarAlunoPorCodigo(int numMatricula) {
        for (Aluno aluno : alunos) {
            if (aluno.getNumMatricula() == numMatricula) {
                return aluno;
            }
        }
        return null;
    }

    public Disciplina encontrarDisciplinaPorCodigo(int codigo) {
        for (Aluno aluno : alunos) {
            List<Disciplina> disciplinas = aluno.getDisciplinas();
            for (Disciplina disciplina : disciplinas) {
                if (disciplina.getCodigo() == codigo) {
                    return disciplina;
                }
            }
        }
        return null;
    }

    private void validarNota(double nota) {
        if (nota < 0 || nota > 10) {
            throw new IllegalArgumentException("A nota deve estar entre 0 e 10.");
        }
    }

    private void salvarDados() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("dados.txt"))) {
            for (Aluno aluno : alunos) {
                writer.println("Aluno: " + aluno.getNome());
                List<Disciplina> disciplinas = aluno.getDisciplinas();
                for (Disciplina disciplina : disciplinas) {
                    try {
                        double mediaAritmetica = disciplina.calcularMediaAritmetica();
                        double mediaPonderada = disciplina.calcularMediaPonderada();
                        String resultadoFinal = mediaAritmetica >= 7 ? "Aprovado" : "Reprovado";

                        writer.println("Disciplina: " + disciplina.getNome());
                        writer.println("Média Aritmética: " + mediaAritmetica);
                        writer.println("Média Ponderada: " + mediaPonderada);
                        writer.println("Resultado Final: " + resultadoFinal);
                    } catch (IllegalStateException e) {
                        writer.println("Erro ao calcular média da disciplina " + disciplina.getNome() + ": " + e.getMessage());
                    }
                }
                writer.println();
            }
            System.out.println("Dados salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }
}
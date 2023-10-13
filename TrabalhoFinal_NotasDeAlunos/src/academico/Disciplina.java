package academico;

import java.util.*;

public class Disciplina implements Medias {
    private int codigo;
    private String nome;
    private List<Double> notas;

    public Disciplina(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
        this.notas = new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public List<Double> getNotas() {
        return notas;
    }

    public void adicionarNota(double nota) {
        notas.add(nota);
    }

    @Override
    public double calcularMediaAritmetica() {
        if (notas.isEmpty()) {
            throw new IllegalStateException("Não há notas cadastradas para a disciplina.");
        }

        double soma = 0.0;
        for (double nota : notas) {
            soma += nota;
        }
        return soma / notas.size();
    }

    @Override
    public double calcularMediaPonderada() {
        if (notas.size() != 2) {
            throw new IllegalStateException("É necessário ter exatamente 2 notas para calcular a média ponderada.");
        }

        double mediaPonderada = (notas.get(0) + (2 * notas.get(1))) / 3;
        return mediaPonderada;
    }
}

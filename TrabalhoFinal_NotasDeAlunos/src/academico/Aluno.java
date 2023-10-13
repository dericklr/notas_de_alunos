package academico;

import java.util.*;

public class Aluno {
	private String nome;
	private int numMatricula;
	private List<Disciplina> disciplinas ;
	
	public Aluno( String nome, int numMatricula) {
        this.numMatricula = numMatricula;
        this.nome = nome;
        this.disciplinas = new ArrayList<>();
    }

    public int getNumMatricula() {
        return numMatricula;
    }

    public String getNome() {
        return nome;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        disciplinas.add(disciplina);
    }
}

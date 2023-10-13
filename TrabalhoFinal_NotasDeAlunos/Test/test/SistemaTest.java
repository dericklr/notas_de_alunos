package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import academico.Aluno;
import academico.Disciplina;
import ui.Sistema;

public class SistemaTest {

    private Sistema sistema;

    @BeforeEach
    public void setUp() {
        sistema = new Sistema();
    }

    @Test
    public void testInserirAluno() {
     
        Aluno aluno1 = sistema.encontrarAlunoPorCodigo(1);
        Assertions.assertNotNull(aluno1);
        Assertions.assertEquals(1, aluno1.getNumMatricula());
        Assertions.assertEquals("João", aluno1.getNome());

        Aluno aluno2 = sistema.encontrarAlunoPorCodigo(2);
        Assertions.assertNotNull(aluno2);
        Assertions.assertEquals(2, aluno2.getNumMatricula());
        Assertions.assertEquals("Maria", aluno2.getNome());
    }

    @Test
    public void testInserirDisciplina() {

        Disciplina disciplina = sistema.encontrarDisciplinaPorCodigo(1);
        Assertions.assertNotNull(disciplina);
        Assertions.assertEquals(1, disciplina.getCodigo());
        Assertions.assertEquals("Matemática", disciplina.getNome());
    }

    @Test
    public void testAdicionarNota() {

        Disciplina disciplina = sistema.encontrarDisciplinaPorCodigo(1);
        Assertions.assertNotNull(disciplina);
        Assertions.assertEquals(1, disciplina.getCodigo());
        Assertions.assertEquals(1, disciplina.getNotas().size());
        Assertions.assertEquals(8.5, disciplina.getNotas().get(0));
    }

    
}
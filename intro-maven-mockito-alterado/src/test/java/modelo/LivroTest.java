package modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;

public class LivroTest {

    private Livro livro;

    @BeforeEach
    public void setUp() {
        livro = new Livro(1, "Teste", 100, 50);
    }

    @Test
    public void testReservarLivroDisponivel() {
        livro.reservar("test@example.com");
        assertEquals(Livro.EstadoLivro.RESERVADO, livro.getEstado());
        assertEquals("test@example.com", livro.getEmailReserva());
    }

    @Test
    public void testReservarLivroJaReservado() {
        livro.reservar("test1@example.com");
        livro.reservar("test2@example.com");
        assertEquals(Livro.EstadoLivro.RESERVADO, livro.getEstado());
        assertEquals("test1@example.com", livro.getEmailReserva());
    }

    @Test
    public void testLocarLivroDisponivel() {
        livro.locar("test@example.com");
        assertEquals(Livro.EstadoLivro.LOCADO, livro.getEstado());
        assertEquals("test@example.com", livro.getEmailReserva());
    }

    @Test
    public void testLocarLivroJaLocado() {
        livro.locar("test1@example.com");
        livro.locar("test2@example.com");
        assertEquals(Livro.EstadoLivro.LOCADO, livro.getEstado());
        assertEquals("test1@example.com", livro.getEmailReserva());
    }

    @Test
    public void testDevolverLivro() {
        livro.devolver();
        assertEquals(Livro.EstadoLivro.DISPONIVEL, livro.getEstado());
        assertNull(livro.getEmailReserva());
    }
}

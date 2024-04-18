package modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GerenciadorLivroTest {

    private GerenciadorLivro gerenciadorLivro;
    private LivroDao livroDaoMock;
    private EmailSender emailSenderMock;

    @BeforeEach
    public void setUp() {
        livroDaoMock = mock(LivroDao.class);
        emailSenderMock = mock(EmailSender.class);
        gerenciadorLivro = new GerenciadorLivro(livroDaoMock, emailSenderMock);
    }

    @Test
    public void testAvisarReservaNoFinal() {
        // Mock de livros reservados
        Livro livro1 = new Livro();
        livro1.setDataReserva(LocalDate.now().minusDays(6));
        livro1.setEmailReserva("user1@example.com");
        Livro livro2 = new Livro();
        livro2.setDataReserva(LocalDate.now().minusDays(8));
        livro2.setEmailReserva("user2@example.com");

        List<Livro> reservados = new ArrayList<>();
        reservados.add(livro1);
        reservados.add(livro2);

        // Mock do comportamento do LivroDao
        when(livroDaoMock.listarReservados()).thenReturn(reservados);

        // Chama o método a ser testado
        gerenciadorLivro.avisarReservaNoFinal();

        // Verifica se o método de envio de email foi chamado corretamente
        verify(emailSenderMock).enviaEmail(eq("Seu livro está reservado e será liberado em breve."), eq("Reserva de Livro"), eq("user1@example.com"));
        verify(emailSenderMock, never()).enviaEmail(eq("Seu livro está reservado e será liberado em breve."), eq("Reserva de Livro"), eq("user2@example.com"));
    }

    @Test
    public void testAvisarLocacaoFinal() {
        // Mock de livros locados
        Livro livro1 = new Livro();
        livro1.setDataReserva(LocalDate.now().minusDays(13));
        livro1.setEmailReserva("user1@example.com");
        Livro livro2 = new Livro();
        livro2.setDataReserva(LocalDate.now().minusDays(10));
        livro2.setEmailReserva("user2@example.com");

        List<Livro> locados = new ArrayList<>();
        locados.add(livro1);
        locados.add(livro2);

        // Mock do comportamento do LivroDao
        when(livroDaoMock.listarLocados()).thenReturn(locados);

        // Chama o método a ser testado
        gerenciadorLivro.avisarLocacaoFinal();

        // Verifica se o método de envio de email foi chamado corretamente
        verify(emailSenderMock).enviaEmail(eq("O prazo de locação do seu livro está expirado."), eq("Locação de Livro"), eq("user1@example.com"));
        verify(emailSenderMock).enviaEmail(eq("Seu livro está próximo do prazo de devolução."), eq("Locação de Livro"), eq("user2@example.com"));
    }
}

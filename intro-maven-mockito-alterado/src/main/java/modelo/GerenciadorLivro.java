package modelo;

import java.time.LocalDate;
import java.util.List;

public class GerenciadorLivro {
    private LivroDao lDao;
    private EmailSender emailSender;

    public GerenciadorLivro(LivroDao lDao, EmailSender emailSender) {
        this.lDao = lDao;
        this.emailSender = emailSender;
    }

    public void reservar(int idLivro, String email) {
        Livro livro = lDao.get(idLivro);
        if (livro != null) {
            livro.reservar(email);
            lDao.alterar(livro);
        }
    }

    public void locar(int idLivro, String email) {
        Livro livro = lDao.get(idLivro);
        if (livro != null) {
            livro.locar(email);
            lDao.alterar(livro);
        }
    }

    public void devolver(int idLivro) {
        Livro livro = lDao.get(idLivro);
        if (livro != null) {
            livro.devolver();
            lDao.alterar(livro);
        }
    }

    public void avisarReservaNoFinal() {
        List<Livro> reservados = lDao.listarReservados();
        for (Livro livro : reservados) {
            // Verifica se a reserva foi feita entre 5 e 7 dias atrás
            if (livro.getDataReserva().isAfter(LocalDate.now().minusDays(7)) &&
                livro.getDataReserva().isBefore(LocalDate.now().minusDays(5))) {
                emailSender.enviaEmail("Seu livro está reservado e será liberado em breve.", "Reserva de Livro", livro.getEmailReserva());
            }
        }
    }

    public void avisarLocacaoFinal() {
        List<Livro> locados = lDao.listarLocados();
        for (Livro livro : locados) {
            // Verifica se a locação foi feita há mais de 12 dias
            if (livro.getDataReserva().isBefore(LocalDate.now().minusDays(14))) {
                emailSender.enviaEmail("O prazo de locação do seu livro está expirado.", "Locação de Livro", livro.getEmailReserva());
            } else if (livro.getDataReserva().isBefore(LocalDate.now().minusDays(12))) {
                emailSender.enviaEmail("Seu livro está próximo do prazo de devolução.", "Locação de Livro", livro.getEmailReserva());
            }
        }
    }
}

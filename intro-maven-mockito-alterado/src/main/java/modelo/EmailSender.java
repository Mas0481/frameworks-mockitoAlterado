package modelo;

public interface EmailSender {
    void enviaEmail(String corpo, String titulo, String email);
}

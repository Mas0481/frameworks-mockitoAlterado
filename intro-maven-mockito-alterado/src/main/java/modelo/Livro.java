package modelo;

import java.time.LocalDate;

public class Livro {
    private int id;
    private String titulo;
    private int paginasLidas;
    private int paginas;
    private EstadoLivro estado;
    private String emailReserva;
    private LocalDate dataReserva;

    public Livro(int id, String titulo, int paginas, int pagsLidas) {
        this.id = id;
        this.paginas = paginas;
        this.titulo = titulo;
        this.estado = EstadoLivro.DISPONIVEL;
        this.emailReserva = null;
        this.paginasLidas = pagsLidas;
    }

    public Livro() {
		
	}

	public void reservar(String email) {
        if (this.estado == EstadoLivro.DISPONIVEL) {
            this.estado = EstadoLivro.RESERVADO;
            this.emailReserva = email;
        }
    }

    public void locar(String email) {
        if (this.estado == EstadoLivro.DISPONIVEL || (this.estado == EstadoLivro.RESERVADO && this.emailReserva.equals(email))) {
            this.estado = EstadoLivro.LOCADO;
            this.emailReserva = email;
        }
    }

    public void devolver() {
        this.estado = EstadoLivro.DISPONIVEL;
        this.emailReserva = null;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getPaginasLidas() {
        return paginasLidas;
    }


    public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public EstadoLivro getEstado() {
		return estado;
	}

	public void setEstado(EstadoLivro estado) {
		this.estado = estado;
	}

	public String getEmailReserva() {
		return emailReserva;
	}

	public void setEmailReserva(String emailReserva) {
		this.emailReserva = emailReserva;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setPaginasLidas(int paginasLidas) {
		this.paginasLidas = paginasLidas;
	}

	public LocalDate getDataReserva() {
		return dataReserva;
	}

	public void setDataReserva(LocalDate dataReserva) {
		this.dataReserva = dataReserva;
	}

	// Enumeração para representar o estado do livro
    public enum EstadoLivro {
        DISPONIVEL,
        RESERVADO,
        LOCADO
    }
}

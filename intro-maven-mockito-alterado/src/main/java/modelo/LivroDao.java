package modelo;

import java.util.ArrayList;
import java.util.List;

public class LivroDao {
	public void inserir(Livro l) {
	}
	
	public void deletar(int id) {
	}
	
	public void alterar(Livro l) {
	}
	
	public Livro get(int id) {
		return new Livro();
	}
	
	
	public List<Livro> listar(int limit, int offset){
		return new ArrayList<Livro>();
	}
	
	public List<Livro> listarReservados(){
		return new ArrayList<Livro>();
	}
	
	public List<Livro> listarLocados() {
		return new ArrayList<Livro>();
	}
}
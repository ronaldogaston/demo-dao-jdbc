package application;

import java.util.Date;

import model.dao.FabricaDao;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class Program {

	public static void main(String[] args) {
		
		Departamento obj = new Departamento(1, "Livros");
		
		Vendedor vendedor = new Vendedor(21, "Jéssica Closs", "jehcloss@gmail.com", new Date(), 2100.0, obj);
		
		VendedorDao vendedorDao = FabricaDao.cadastroVendedorDao();
		
		System.out.println(vendedor);

	}

}

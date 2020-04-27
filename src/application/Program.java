package application;

import java.util.List;

import model.dao.FabricaDao;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class Program {

	public static void main(String[] args) {
		
		VendedorDao vendedorDao = FabricaDao.cadastroVendedorDao();
		
		System.out.println("=== TESTE 1: Vendedor FindById (Buscando vendedor por ID) ===");
		Vendedor vendedor = vendedorDao.findById(3);
		System.out.println(vendedor);

		System.out.println("\n === TESTE 2: Vendedor FindByDepartamento (Buscando departamento por ID) ===");
		Departamento departamento = new Departamento(2, null);
		List<Vendedor> list = vendedorDao.findByDepartamento(departamento);
		
		for (Vendedor x : list) {
			System.out.println(x);
		}
		
		System.out.println("\n === TESTE 3: Vendedor FindAll (Buscando todos os vendedores) ===");
		list = vendedorDao.findAll();
	
		for (Vendedor x : list) {
			System.out.println(x);
		}
	}

}

package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.FabricaDao;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		VendedorDao vendedorDao = FabricaDao.cadastroVendedorDao();
		
		System.out.println("=== TESTE 1: Vendedor FindById (Buscando vendedor por ID) ===");
		Vendedor vendedor = vendedorDao.findById(3);
		System.out.println(vendedor);

		System.out.println("\n === TESTE 2: Vendedor FindByDepartamento (Buscando vendedor pela id do departamento) ===");
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
		
		System.out.println("\n === TESTE 4: Vendedor Insert (Adicionando novo vendedor) ===");
		Vendedor newVendedor = new Vendedor(null, "Daniel", "danielgoncalves@gmail.com", new Date(), 2100.0, departamento);
		vendedorDao.insert(newVendedor);
		System.out.println("Adicionado novo ID = " + newVendedor.getId());
		
		System.out.println("\n === TESTE 5: Vendedor Update (Atualizando um cadastro de vendedor) ===");
		vendedor = vendedorDao.findById(1);
		vendedor.setNome("Ronaldo G Gaston");
		vendedorDao.update(vendedor);
		System.out.println("Atualização completa!");
		
		System.out.println("\n === TESTE 6: Vendedor Delete (Deleta um cadastro de vendedor) ===");
		System.out.println("Informe um ID para ser deletado: ");
		int id = sc.nextInt();
		vendedorDao.deleteById(id);
		
		sc.close();
	}

}

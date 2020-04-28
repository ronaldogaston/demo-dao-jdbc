package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DepartamentoDao;
import model.dao.FabricaDao;
import model.entities.Departamento;

public class Program2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		DepartamentoDao departamentoDao = FabricaDao.cadastroDepartamentoDao();
		
		System.out.println("=== TESTE 1: Departamento FindById (Buscando departamento por ID) ===");
		Departamento dep = departamentoDao.findById(3);
		System.out.println(dep);
		
		System.out.println("\n === TESTE 2: Departamento FindAll (Buscando todos os departamentos em ordem alfabética) ===");
		List<Departamento> list = departamentoDao.findAll();
		
		for (Departamento x : list) {
			System.out.println(x);
		}
		
		System.out.println("=== TESTE 3: Departamento Insert (Adicionando novo departamento) ===");
		Departamento newDepartamento = new Departamento(null, "Compras");
		departamentoDao.insert(newDepartamento);
		System.out.println("Adicionado novo ID = " + newDepartamento.getId());
		
		System.out.println("\n === TESTE 4: Departamento Update (Atualizando um cadastro de departamento) ===");
		dep = departamentoDao.findById(6);
		dep.setNome("Gastronomia");
		departamentoDao.update(dep);
		System.out.println("Atualização completa!");
		
		System.out.println("\n === TESTE 5: Departamento Delete (Deleta um cadastro do departamento) ===");
		System.out.print("Informe um ID para ser deletado: ");
		int id = sc.nextInt();
		departamentoDao.deleteById(id);
		
		sc.close();
	}

}

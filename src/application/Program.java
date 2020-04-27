package application;

import model.dao.FabricaDao;
import model.dao.VendedorDao;
import model.entities.Vendedor;

public class Program {

	public static void main(String[] args) {
		
		VendedorDao vendedorDao = FabricaDao.cadastroVendedorDao();
		
		Vendedor vendedor = vendedorDao.findById(3);
		
		System.out.println(vendedor);

	}

}

package model.dao;

import model.dao.impl.VendedorDaoJDBC;

public class FabricaDao {
	
	/* MACETE:
	 * A classe vai expor um método que
	 * retorna o tipo da interface
	 * mais internamente vai estânciar uma implementação.
	 * Para não precisar expor a implentação
	 * Deixa somente a interface.
	 */
	
	public static VendedorDao cadastroVendedorDao() { 
		return new VendedorDaoJDBC();
	}

}

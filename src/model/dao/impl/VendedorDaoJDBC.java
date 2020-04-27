package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class VendedorDaoJDBC implements VendedorDao{

	private Connection conn;
	
	public VendedorDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Vendedor obj) {
		
	}

	@Override
	public void update(Vendedor obj) {
		
	}

	@Override
	public void deleteById(Integer id) {
		
	}

	@Override
	public Vendedor findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT vendedor.*, departamento.Nome as DepNome "
					+ "FROM vendedor INNER JOIN departamento "
					+ "ON vendedor.IdDepartamento "
					+ "WHERE vendedor.Id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				Departamento dep = instanciaDepartamento(rs);
				Vendedor vendedor = instanciaVendedor(rs, dep);
				return vendedor;
			}
			else {
				return null;
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Vendedor instanciaVendedor(ResultSet rs, Departamento dep) throws SQLException {
		Vendedor vendedor = new Vendedor();
		vendedor.setId(rs.getInt("Id"));
		vendedor.setNome(rs.getString("Nome"));
		vendedor.setEmail(rs.getString("Email"));
		vendedor.setDataNascimento(rs.getDate("DataNascimento"));
		vendedor.setSalarioBase(rs.getDouble("SalarioBase"));
		vendedor.setDepartamento(dep);
		return vendedor;
	}

	private Departamento instanciaDepartamento(ResultSet rs) throws SQLException {
		Departamento dep = new Departamento();
		dep.setId(rs.getInt("IdDepartamento"));
		dep.setNome(rs.getString("DepNome"));
		return dep;
	}

	@Override
	public List<Vendedor> findAll() {

		return null;
	}

	@Override
	public List<Vendedor> findByDepartamento(Departamento departamento) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT vendedor.*, departamento.Nome as DepNome "
					+ "FROM vendedor INNER JOIN departamento "
					+ "ON vendedor.IdDepartamento = departamento.Id "
					+ "WHERE Departamento.Id = ? "
					+ "ORDER BY Nome");
			
			st.setInt(1, departamento.getId());
			rs = st.executeQuery();
			
			List<Vendedor> list = new ArrayList<>();
			Map<Integer, Departamento> map = new HashMap<>(); // Será guardado qualquer departamento instaciado
						
			while (rs.next()) {
				
				Departamento dep = map.get(rs.getInt("IdDepartamento")); // Busca o ID do Departamento do Banco
				
				if (dep == null) { // Realiza a instanciação caso o 'dep' tenha vindo como nulo acima
					dep = instanciaDepartamento(rs);
					map.put(rs.getInt("IdDepartamento"), dep);
				}
				Vendedor vendedor = instanciaVendedor(rs, dep);
				list.add(vendedor);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}

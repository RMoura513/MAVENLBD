package persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import model.Cliente;

public class ClienteDao implements ICrud<Cliente>, IClienteDao {
	
	private GenericDao gDao;
	
	public ClienteDao(GenericDao gDao) {
		this.gDao = gDao;
		
	}
	
	@Override
	public void inserir(Cliente cl) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "INSERT INTO cliente VALUES (?, ?, ?, ?, ?)";
		
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.US);
	    String string_data = cl.getDt_nascimento().format(dtf);
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, cl.getCpf());
		ps.setString(2, cl.getNome());
		ps.setString(3, cl.getEmail());
		ps.setFloat(4, cl.getLimite_de_credito());
		ps.setString(5, string_data);
		ps.execute();
		ps.close();
		c.close();
	}

	@Override
	public void atualizar(Cliente cl) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "UPDATE cliente SET nome = ?, email = ?, limite_de_credito = ?, dt_nascimento = ? WHERE CPF = ?";
		
	      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.US);
	      String string_data = cl.getDt_nascimento().format(dtf);
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(5, cl.getCpf());
		ps.setString(1, cl.getNome());
		ps.setString(2, cl.getEmail());
		ps.setFloat(3, cl.getLimite_de_credito());
		ps.setString(4, string_data);
		ps.execute();
		ps.close();
		c.close();
		
	}

	@Override
	public void excluir(Cliente cl) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "DELETE cliente WHERE CPF = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, cl.getCpf());
		ps.execute();
		ps.close();
		c.close();
		
	}

	@Override
	public Cliente buscar(Cliente cl) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "SELECT CPF, nome, email, limite_de_credito, dt_nascimento FROM cliente WHERE CPF = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, cl.getCpf());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			cl.setCpf(rs.getString("CPF"));
			cl.setNome(rs.getString("nome"));
			cl.setEmail(rs.getString("email"));
			cl.setLimite_de_credito(rs.getFloat("limite_de_credito"));
			cl.setDt_nascimento(LocalDate.parse(rs.getString("dt_nascimento")));
		}
		rs.close();
		ps.close();
		c.close();
		return cl;
		
		
	}

	@Override
	public List<Cliente> listar() throws SQLException, ClassNotFoundException {
		List<Cliente> clientes = new ArrayList<Cliente>();
		Connection c = gDao.getConnection();
		String sql = "SELECT CPF, nome, email, limite_de_credito, dt_nascimento FROM cliente";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Cliente cl = new Cliente();
			cl.setCpf(rs.getString("CPF"));
			cl.setNome(rs.getString("nome"));
			cl.setEmail(rs.getString("email"));
			cl.setLimite_de_credito(rs.getFloat("limite_de_credito"));
			cl.setDt_nascimento(LocalDate.parse(rs.getString("dt_nascimento")));
			
			clientes.add(cl);
		}
		rs.close();
		ps.close();
		c.close();
		return clientes;
		
	}

	@Override
	public String iudCliente(String acao, Cliente cl) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_crud_cliente (?, ?, ?, ?, ?, ?, ? ) }";
		
	      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.US);
	      String string_data = cl.getDt_nascimento().format(dtf);
		
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, acao);
		cs.setString(2, cl.getCpf());
		cs.setString(3, cl.getNome());
		cs.setString(4, cl.getEmail());
		cs.setFloat(5, cl.getLimite_de_credito());
		cs.setString(6, string_data);
		cs.registerOutParameter(7,  Types.VARCHAR);
		
		cs.execute();
		String saida = cs.getString(7);
		cs.close();
		c.close();
		
		return saida;
		
		
		
		
	}
	

}

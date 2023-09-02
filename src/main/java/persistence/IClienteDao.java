package persistence;

import java.sql.SQLException;

import model.Cliente;

public interface IClienteDao {

	public String iudCliente(String acao, Cliente cl) throws SQLException, ClassNotFoundException;
	
}

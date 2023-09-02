package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cliente;
import persistence.GenericDao;
import persistence.ClienteDao;


@WebServlet("/cliente")
public class ClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ClienteController() {
		super();
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("cliente.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//entrada
		String cmd = request.getParameter("botao");
		String cpf = request.getParameter("CPF");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String limite_de_credito = request.getParameter("limite_de_credito");
		String dt_nascimento = request.getParameter("dt_nascimento");
		
		//retorno
		String saida = "";
		String erro = "";
		Cliente cl = new Cliente();
		List<Cliente> clientes = new ArrayList<>();
		
		if (!cmd.contains("Listar")){
			cl.setCpf(cpf);
		}
		if (cmd.contains("Cadastrar") || cmd.contains("Alterar")) {
			
			LocalDate data = (LocalDate.parse(dt_nascimento));
			
			cl.setNome(nome);
			cl.setEmail(email);
			cl.setLimite_de_credito(Float.parseFloat(limite_de_credito));
			cl.setDt_nascimento(data);
		}
		try {
			if (cmd.contains("Cadastrar")) {
				saida = cadastrarCliente(cl);
				cl = null;
			}
			if (cmd.contains("Alterar")) {
				saida = alterarCliente(cl);
				cl = null;
			}
			if (cmd.contains("Excluir")) {
				excluirCliente(cl);
				cl = null;
			}
			if (cmd.contains("Buscar")) {
				cl = buscarCliente(cl);
			}
			if (cmd.contains("Listar")) {
				clientes = listarClientes();
			}
			
		}catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		}finally {
			request.setAttribute("saida", saida);
			request.setAttribute("erro", erro);
			request.setAttribute("cliente", cl);
			request.setAttribute("clientes", clientes);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("cliente.jsp");
			rd.forward(request, response);
			
		}
		
	}

	private String cadastrarCliente(Cliente cl) throws SQLException, ClassNotFoundException {
		GenericDao gDao = new GenericDao();
		ClienteDao cDao = new ClienteDao(gDao);
		String saida = cDao.iudCliente("I", cl);
		
		return saida;
	}

	private String alterarCliente(Cliente cl) throws SQLException, ClassNotFoundException {
		GenericDao gDao = new GenericDao();
		ClienteDao cDao = new ClienteDao(gDao);
		String saida = cDao.iudCliente("U", cl);
		
		return saida;
		}

	private String excluirCliente(Cliente cl) throws SQLException, ClassNotFoundException {
		GenericDao gDao = new GenericDao();
		ClienteDao cDao = new ClienteDao(gDao);
		String saida = cDao.iudCliente("D", cl);
		
		return saida;
		}

	private Cliente buscarCliente(Cliente cl) throws SQLException, ClassNotFoundException {
		GenericDao gDao = new GenericDao();
		ClienteDao cDao = new ClienteDao(gDao);
		cDao.buscar(cl);
		return cl;
	}

	private List<Cliente> listarClientes() throws SQLException, ClassNotFoundException {
		GenericDao gDao = new GenericDao();
		ClienteDao cDao = new ClienteDao(gDao);
		List<Cliente> clientes = cDao.listar();
		return clientes;	
	}
}

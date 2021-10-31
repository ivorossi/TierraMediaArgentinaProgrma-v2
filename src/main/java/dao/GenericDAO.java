package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import jdbc.ProvedorDeConeccion;

public abstract class GenericDAO<T> {

	protected PreparedStatement consulta(String sql) throws SQLException {

		Connection conn = ProvedorDeConeccion.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		return statement;
	}

	public int contarTodos() {
		int total = 0;
		try {
			ResultSet resultados = this.consulta(this.consultaDeContarTodos()).executeQuery();
			resultados.next();
			total = resultados.getInt("total");
		} catch (Exception e) {
			System.out.println(e);
		}
		return total;
	}

	public LinkedList<T> dameTodos() {

		LinkedList<T> listaDeObjetos = new LinkedList<T>();

		try {
			ResultSet resultados = consulta(consultaDeDameTodos()).executeQuery();
			while (resultados.next()) {
				listaDeObjetos.add((T) convertirT(resultados));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return listaDeObjetos;
	}

	public T buscar(String key) {
		T objeto = null;
		try {
			PreparedStatement statement = this.consulta(consultaDeBuscar());
			statement.setString(1, key);
			ResultSet resultados = statement.executeQuery();

			if (resultados.next())
				objeto = (T) this.convertirT(resultados);

		} catch (Exception e) {
			System.out.println(e);
		}
		return objeto;
	}

	protected abstract int agregar(T t);

	protected abstract int modificar(T t);

	protected abstract int borrar(T t);

	protected abstract String consultaDeBuscar();

	protected abstract String consultaDeDameTodos();

	protected abstract String consultaDeContarTodos();

	protected abstract T convertirT(ResultSet resultados) throws SQLException;
}

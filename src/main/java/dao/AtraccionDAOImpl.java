package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import excepcion.ExcepcionDeProducto;
import productos.Atraccion;

public class AtraccionDAOImpl extends GenericDAO<Atraccion> {

	@Override
	public int agregar(Atraccion atraccion) {
		int cambios = 0;
		try {
			PreparedStatement statement = super.consulta("INSERT INTO atraccion" + 
		"(  nombre, tipo, costo, cupo, duracion, id_atraccion) VALUES (?, ?, ?, ?, ?, ?)");
			statement.setString(1, atraccion.getNombreDeProducto());
			statement.setString(2, atraccion.getTipoDeProducto());
			statement.setInt(3, atraccion.getCostoTotal());
			statement.setInt(4, atraccion.getCupo());
			statement.setDouble(5, atraccion.getTimepoDeProducto());
			statement.setInt(6, atraccion.getIdProducto());

			cambios += statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return cambios;
	}

	@Override
	public int modificar(Atraccion atraccion) {
		int cambios = 0;
		try {
			PreparedStatement statement = super.consulta(
					"UPDATE atraccion SET nombre =?, tipo =?, costo =?," + "cupo =?, duracion =? WHERE id_atraccion =?");
			statement.setString(1, atraccion.getNombreDeProducto());
			statement.setString(2, atraccion.getTipoDeProducto());
			statement.setInt(3, atraccion.getCostoTotal());
			statement.setInt(4, atraccion.getCupo());
			statement.setDouble(5, atraccion.getTimepoDeProducto());
			statement.setInt(6, atraccion.getIdProducto());

			cambios = statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return cambios;
	}

	@Override
	public int borrar(Atraccion atraccion) {
		int cambios = 0;
		try {
			PreparedStatement statement = super.consulta("DELETE FROM atraccion WHERE id_atraccion =?");
			statement.setInt(1, atraccion.getIdProducto());
			cambios = statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return cambios;
	}

	@Override
	public String consultaDeContarTodos() {
		return "SELECT count(1) AS total FROM atraccion";
	}

	@Override
	protected String consultaDeDameTodos() {
		return "SELECT * FROM atraccion";
	}

	@Override
	protected String consultaDeBuscar() {
		return "SELECT * FROM atraccion WHERE nombre =?";
	}

	@Override
	protected Atraccion convertirT(ResultSet resultados) throws SQLException {
		try {
			return new Atraccion(resultados.getInt(1), resultados.getString(2), resultados.getString(3),
					resultados.getInt(4), resultados.getDouble(6), resultados.getInt(5));
		} catch (ExcepcionDeProducto e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}
}

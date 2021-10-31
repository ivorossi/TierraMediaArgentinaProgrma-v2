package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import excepcion.ExcepcionDelUsuario;
import usuario.Usuario;

public class UsuarioDAOImpl extends GenericDAO<Usuario> {

	@Override
	public int agregar(Usuario visitante) {
		int cambios = 0;
		try {
			PreparedStatement statement = super.consulta("INSERT INTO visitante"
					+ "(usuario, contrasenia, nombre, gusto, presupuesto, tiempo_disponible) VALUES (?, ?, ?, ?, ?, ?)");
			statement.setString(1, visitante.getUsuario());
			statement.setString(2, visitante.getContrasenia());
			statement.setString(3, visitante.getNombre());
			statement.setString(4, visitante.getGusto());
			statement.setInt(5, visitante.getPresupuesto());
			statement.setDouble(6, visitante.getTiempoDisponible());
			cambios += statement.executeUpdate();
			ItinerarioDAOImpl itinerario =new ItinerarioDAOImpl(); 
			cambios += itinerario.agregar(visitante);
		} catch (Exception e) {
			System.out.println(e);
		}
		return cambios;
	}

	@Override
	public int modificar(Usuario visitante) {
		int cambios = 0;
		try {
			PreparedStatement statement = super.consulta("UPDATE visitante SET contrasenia =?, nombre =?, gusto =?,"
					+ "presupuesto =?, tiempo_disponible =? WHERE usuario =?");
			statement.setString(1, visitante.getContrasenia());
			statement.setString(2, visitante.getNombre());
			statement.setString(3, visitante.getGusto());
			statement.setInt(4, visitante.getPresupuesto());
			statement.setDouble(5, visitante.getTiempoDisponible());
			statement.setString(6, visitante.getUsuario());
			cambios = statement.executeUpdate();
			ItinerarioDAOImpl itinerario =new ItinerarioDAOImpl(); 
			cambios += itinerario.modificar(visitante);
		} catch (Exception e) {
			System.out.println(e);
		}
		return cambios;
	}

	@Override
	public int borrar(Usuario visitante) {
		int cambios = 0;
		try {
			PreparedStatement statement = super.consulta("DELETE FROM visitante WHERE usuario =?");
			statement.setString(1, visitante.getUsuario());
			cambios = statement.executeUpdate();
			ItinerarioDAOImpl itinerario =new ItinerarioDAOImpl(); 
			cambios += itinerario.borrar(visitante);
		} catch (Exception e) {
			System.out.println(e);

		}
		return cambios;
	}

	@Override
	public String consultaDeContarTodos() {
		return "SELECT count(1) AS total FROM visitante";
	}

	@Override
	protected String consultaDeDameTodos() {
		return "SELECT * FROM visitante";
	}

	@Override
	protected String consultaDeBuscar() {
		return "SELECT * FROM visitante WHERE usuario =?";
	}

	@Override
	protected Usuario convertirT(ResultSet resultados) throws SQLException {
		try {
			return new Usuario(resultados.getString(1), resultados.getString(2), resultados.getString(3),
					resultados.getString(4), resultados.getInt(5), resultados.getDouble(6));
		} catch (ExcepcionDelUsuario e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}

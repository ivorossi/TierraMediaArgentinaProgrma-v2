package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jdbc.ProvedorDeConeccion;
import productos.Producto;
import usuario.Usuario;

public class ItinerarioDAOImpl {

	private PreparedStatement consulta(String sql) throws SQLException {

		Connection conn = ProvedorDeConeccion.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		return statement;
	}

	public int agregar(Usuario visitante) {
		int cambios = 0;
		try {
			for (Producto elemento : visitante.getItinerario()) {

				PreparedStatement statement = this.consulta(
						"INSERT INTO itinerario" + "(  usuario, id_atraccion, id_promocion) VALUES ( ?, ?, ?)");
				statement.setString(1, visitante.getUsuario());
				if (elemento.esPromo())
					statement.setInt(3, elemento.getIdProducto());
				if (!elemento.esPromo())
					statement.setInt(2, elemento.getIdProducto());
				cambios += statement.executeUpdate();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return cambios;

	}

	public int modificar(Usuario visitante) {
		int cambios = 0;
		cambios += this.borrar(visitante);
		cambios += this.agregar(visitante);
		return cambios;
	}

	protected int borrar(Usuario visitante) {
		int cambios = 0;
		try {
			PreparedStatement statement = this.consulta("DELETE FROM itinerario WHERE usuario =?");
			statement.setString(1, visitante.getUsuario());
			cambios = statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return cambios;
	}

	public ArrayList<Producto> buscar(String usuario) {

		PromocionDAOImpl itinerarioPromo = new PromocionDAOImpl();
		AtraccionDAOImpl itinerarioAtraccion = new AtraccionDAOImpl();
		ArrayList<Producto> atracciones = new ArrayList<Producto>();

		try {
			PreparedStatement statement = this.consulta("SELECT promocion.nombre" + " FROM itinerario"
					+ " JOIN promocion ON promocion.id_promocion = itinerario.id_promocion"
					+ " WHERE itinerario.usuario = ?");
			statement.setString(1, usuario);
			ResultSet resultados = statement.executeQuery();

			while (resultados.next()) {
				atracciones.add(itinerarioPromo.buscar(resultados.getString(1)));
			}
			PreparedStatement statement2 = this.consulta("SELECT atraccion.nombre" + " FROM itinerario"
					+ " JOIN atraccion ON atraccion.id_atraccion = itinerario.id_atraccion"
					+ " WHERE itinerario.usuario = ?");
			statement2.setString(1, usuario);
			ResultSet resultados2 = statement2.executeQuery();

			while (resultados2.next()) {
				atracciones.add(itinerarioAtraccion.buscar(resultados2.getString(1)));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return atracciones;
	}

}

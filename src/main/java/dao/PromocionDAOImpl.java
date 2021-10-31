package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import excepcion.ExcepcionDeProducto;
import productos.Promo;
import productos.PromoAbsoluta;
import productos.PromoAxB;
import productos.PromoPorcentual;

public class PromocionDAOImpl extends GenericDAO<Promo> {

	@Override
	public int agregar(Promo promocion) {
		int cambios = 0;
		try {
			PreparedStatement statement = super.consulta("INSERT INTO promocion"
					+ "(id_promocion,  nombre, tipo_promocion, descuento, tipo) VALUES ( ?, ?, ?, ?, ?)");
			statement.setInt(1, promocion.getIdProducto());
			statement.setString(2, promocion.getNombreDeProducto());
			statement.setString(3, promocion.getTipoDePromo());
			statement.setString(4, promocion.getDescuento());
			statement.setString(5, promocion.getTipoDeProducto());
			cambios += statement.executeUpdate();
			AtraccionesDePromoDAOImpl atraccionDePromo = new AtraccionesDePromoDAOImpl();
			cambios = atraccionDePromo.agregar(promocion);
		} catch (Exception e) {
			System.out.println(e);
		}
		return cambios;
	}

	@Override
	public int modificar(Promo promocion) {
		int cambios = 0;
		try {
			PreparedStatement statement = super.consulta(
					"UPDATE promocion SET nombre =?, tipo_promocion =?, descuento =?,"
							+ "tipo =? WHERE id_promocion =?");
			statement.setString(1, promocion.getNombreDeProducto());
			statement.setString(2, promocion.getTipoDePromo());
			statement.setString(3, promocion.getDescuento());
			statement.setString(4, promocion.getTipoDeProducto());
			statement.setInt(5, promocion.getIdProducto());
			cambios += statement.executeUpdate();
			AtraccionesDePromoDAOImpl atraccionDePromo = new AtraccionesDePromoDAOImpl();
			cambios += atraccionDePromo.modificar(promocion);
		} catch (Exception e) {
			System.out.println(e);
		}
		return cambios;
	}

	@Override
	public int borrar(Promo promocion) {
		int cambios = 0;
		try {
			PreparedStatement statement = super.consulta("DELETE FROM promocion WHERE id_promocion =?");
			statement.setInt(1, promocion.getIdProducto());
			cambios = statement.executeUpdate();
			AtraccionesDePromoDAOImpl atraccionDePromo = new AtraccionesDePromoDAOImpl();
			cambios += atraccionDePromo.borrar(promocion);
		} catch (Exception e) {
			System.out.println(e);
		}
		return cambios;
	}

	@Override
	protected String consultaDeBuscar() {
		return "SELECT * FROM promocion WHERE nombre =?";
	}

	@Override
	protected String consultaDeDameTodos() {
		return "SELECT * FROM promocion";
	}

	@Override
	protected String consultaDeContarTodos() {
		return "SELECT * FROM promocion WHERE nombre =?";
	}

	@Override
	public Promo convertirT(ResultSet resultados) throws SQLException {
		AtraccionesDePromoDAOImpl atraccionDePromo = new AtraccionesDePromoDAOImpl();

		try {
			if (resultados.getString(3).equalsIgnoreCase("absoluta")) {
				return new PromoAbsoluta(resultados.getInt(1), resultados.getString(2), resultados.getString(5),
						Integer.parseInt(resultados.getString(4)), atraccionDePromo.buscar(resultados.getInt(1)));
			}
			if (resultados.getString(3).equalsIgnoreCase("AxB")) {
				return new PromoAxB(resultados.getInt(1), resultados.getString(2), resultados.getString(5),
						resultados.getString(4), atraccionDePromo.buscar(resultados.getInt(1)));
			}
			if (resultados.getString(3).equalsIgnoreCase("absoluta")) {
				return new PromoPorcentual(resultados.getInt(1), resultados.getString(2), resultados.getString(5),
						Double.parseDouble(resultados.getString(4)), atraccionDePromo.buscar(resultados.getInt(1)));
			}
		} catch (ExcepcionDeProducto e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}

}

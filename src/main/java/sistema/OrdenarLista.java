package sistema;

import java.util.Comparator;

import productos.Producto;
import usuario.Usuario;

public class OrdenarLista implements Comparator<Producto> {
	private String gustoDelUsuario;

	public OrdenarLista(Usuario visitante) {
		this.gustoDelUsuario = visitante.getGusto();
	}

	private int compararGusto(Producto o1, Producto o2) {
		if (this.gustoDelUsuario.equalsIgnoreCase(o1.getTipoDeProducto())
				&& (!this.gustoDelUsuario.equalsIgnoreCase(o2.getTipoDeProducto())))
			return -1;
		if (!this.gustoDelUsuario.equalsIgnoreCase(o1.getTipoDeProducto())
				&& (this.gustoDelUsuario.equalsIgnoreCase(o2.getTipoDeProducto())))
			return 1;

		return 0;
	}

	public int compare(Producto o1, Producto o2) {
		int resultado;
		resultado = this.compararGusto(o1, o2);
		if (resultado == 0) {
			resultado = -Boolean.compare(o1.esPromo(), o2.esPromo());
			if (resultado == 0) {
				resultado = -Double.compare(o1.getCostoTotal(), o2.getCostoTotal());
				if (resultado == 0) {
					resultado = -Double.compare(o1.getTimepoDeProducto(), o2.getTimepoDeProducto());
				}
			}
		}
		return resultado;
	}
}

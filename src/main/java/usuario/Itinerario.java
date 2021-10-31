package usuario;

import java.util.ArrayList;

import dao.ItinerarioDAOImpl;
import productos.Atraccion;
import productos.Producto;
import productos.Promo;

public class Itinerario {
	private ArrayList<Producto> productosDelusuario = new ArrayList<Producto>();

	public Itinerario(String usuario) {
		
		ItinerarioDAOImpl itinerario = new ItinerarioDAOImpl();
		this.productosDelusuario = itinerario.buscar(usuario);
	}

	public ArrayList<Producto> getProductosDelUsuario() {
		return this.productosDelusuario;
	}

	public void setProducto(Producto productoDelusuario) {
		this.productosDelusuario.add(productoDelusuario);
	}

	public boolean noEstaComprado(Producto haComprar) {
		
		for (Producto yaComprados : productosDelusuario) {
			
			if (yaComprados.esPromo()) {
				Promo comprado = (Promo) yaComprados;
				for (Atraccion elemento : comprado.getAtracciones()) {
					if(haComprar.verificaCompra(elemento))
						return true;
				}
			}else{
				 if(haComprar.verificaCompra(yaComprados));
					return true;
			}
		}
		return false;
	}

	private String toStrigDeOroductos() {
		String listadoDeProductos = "";
		for (Producto elemento : productosDelusuario) {
			listadoDeProductos += elemento.toString() + "\n\n";
		}
		return listadoDeProductos;
	}

	@Override
	public String toString() {
		return " tu itinerario: \n\n" + toStrigDeOroductos();
	}

}

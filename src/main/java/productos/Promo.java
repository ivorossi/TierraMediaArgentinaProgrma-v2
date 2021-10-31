package productos;

import java.util.ArrayList;

public abstract class Promo extends Producto {

	protected ArrayList<Atraccion> atracciones;

	public Promo(int id_producto, String nombreDeProducto, String tipoDeProducto, ArrayList<Atraccion> atracciones) {

		super(id_producto, nombreDeProducto, tipoDeProducto);
		this.atracciones = atracciones;
		this.costoDraucionDePromo();
	}
	private void costoDraucionDePromo() {
		double tiempo = 0;
		int costo = 0;
		for (Atraccion elemento : atracciones) {
			tiempo += elemento.getTimepoDeProducto();
			costo += elemento.getCostoTotal();
		}
		super.timepoDeProducto = tiempo;
		super.costoTotal = costo;
	}

	public boolean hayCupo() {
		for (Atraccion elemento : atracciones) {
			if (!elemento.hayCupo())
				return false;
		}
		return true;
	}

	@Override
	public boolean esPromo() {
		return true;
	}

	@Override
	public void restarCupo() {
		for (Atraccion elemento : atracciones)
			elemento.restarCupo();
	}

	public ArrayList<Atraccion> getAtracciones() {
		return this.atracciones;
	}

	private String toStrigDeAtracciones() {
		String listadoDeAtracciones = "";
		int i = 0;
		for (Atraccion elemento : atracciones) {
			listadoDeAtracciones += elemento.getNombreDeProducto();
			i++;
			if (i < atracciones.size()) {
				listadoDeAtracciones += ", ";
			} else {
				listadoDeAtracciones += ";";
			}
		}
		return listadoDeAtracciones;
	}

	public boolean verificaCompra(Producto haComprar) {
		for (Atraccion elemento : atracciones) {
			if (elemento.verificaCompra(haComprar))
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return super.toString() + "]" + "\natracciones inculidas:\n" + this.toStrigDeAtracciones();
	}

	public abstract String getTipoDePromo();

	public abstract String getDescuento();
}

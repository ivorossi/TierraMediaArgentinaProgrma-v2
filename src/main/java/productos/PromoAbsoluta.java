package productos;

import java.util.ArrayList;

import excepcion.ExcepcionDeProducto;

public class PromoAbsoluta extends Promo {
	
	private int costoDePromo;

	public PromoAbsoluta(int idProducto, String nombreDeProducto, String tipoDeProducto, 
			int costoDePromo, ArrayList<Atraccion> atracciones) throws ExcepcionDeProducto {

		super(idProducto,nombreDeProducto, tipoDeProducto, atracciones);
		if (costoDePromo <= 0)
			throw new ExcepcionDeProducto(costoDePromo + ": se esperan un costo de promo mayor a 0");

		this.costoDePromo = costoDePromo;
		this.aplicarDescuento();
	}

	private void aplicarDescuento() {
		super.costoTotal = this.costoDePromo;
	}

	@Override
	public String toString() {
		return "Promo de tipo absoluta, el pack te queda en : " + costoDePromo + " monedas.\n" + super.toString();
	}

	@Override
	public String getTipoDePromo() {
		return "absoluta";
	}

	@Override
	public String getDescuento() {
		return Integer.toString( this.costoDePromo);
	}

}

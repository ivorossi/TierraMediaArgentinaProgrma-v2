package usuario;

import java.util.ArrayList;

import excepcion.ExcepcionDelUsuario;
import productos.Producto;

public class Usuario {
	private String usuario, contrasenia;
	private String nombreCompleto;
	private String gusto;
	private int presupuesto;
	private double tiempoDisponible;
	private Itinerario itinerarioDelUsuario;

	public Usuario(String usuario, String contrasenia, String nombreCompleto, String gusto, 
					int presupuesto, double tiempoDisponible) throws ExcepcionDelUsuario {
		if (tiempoDisponible <= 0)
			throw new ExcepcionDelUsuario(tiempoDisponible + ": se esperan valores positivos de tiempo disponible ");
		if (presupuesto <= 0)
			throw new ExcepcionDelUsuario(presupuesto + ": se esperan valores positivos de presupuesto");
		this.nombreCompleto = nombreCompleto;
		this.gusto = gusto;
		this.tiempoDisponible = tiempoDisponible;
		this.presupuesto = presupuesto;
		this.usuario = usuario;
		this.contrasenia = contrasenia; 
		this.itinerarioDelUsuario = new Itinerario(usuario);
	}
	
	public void Comprar(Producto compra) {
		this.presupuesto -= compra.getCostoTotal();
		this.tiempoDisponible -= compra.getTimepoDeProducto();
		compra.restarCupo();
		itinerarioDelUsuario.setProducto(compra);
	}

	public String getNombre() {
		return nombreCompleto;
	}

	public String getGusto() {
		return gusto;
	}

	public int getPresupuesto() {
		return presupuesto;
	}

	public double getTiempoDisponible() {
		return tiempoDisponible;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}
	@Override
	public String toString() {
		return nombreCompleto + itinerarioDelUsuario + "\nGracias Por tu compra!!\n" + "tu vuelto es: " + presupuesto
				+ " monedas y tu tiempo libre es de: " + tiempoDisponible + "hs;";
	}

	public  ArrayList<Producto >getItinerario() {
		return this.itinerarioDelUsuario.getProductosDelUsuario();
	}
	
	public boolean loPuedoComprar(Producto haComprar) {
		
		return (this.getTiempoDisponible() >= haComprar.getTimepoDeProducto()
				&& this.getPresupuesto() >= haComprar.getCostoTotal() && haComprar.hayCupo()
				&& !this.itinerarioDelUsuario.estaComprado(haComprar));
	}
	




}

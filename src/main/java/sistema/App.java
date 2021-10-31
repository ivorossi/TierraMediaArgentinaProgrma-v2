package sistema;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import dao.AtraccionDAOImpl;
import dao.ItinerarioDAOImpl;
import dao.PromocionDAOImpl;
import dao.UsuarioDAOImpl;
import excepcion.ExcepcionDeProducto;
import excepcion.ExcepcionDelUsuario;
import productos.Atraccion;
import productos.Producto;
import productos.Promo;
import productos.PromoAxB;
import usuario.Usuario;

public class App {

	public static void main(String[] arg) throws ExcepcionDeProducto, ExcepcionDelUsuario {
		
		TierraMedia sad = new TierraMedia();
		sad.sistema();
		
		
		/*
		PromocionDAOImpl las = new PromocionDAOImpl();
		UsuarioDAOImpl usuario = new UsuarioDAOImpl();
		AtraccionDAOImpl atraccion = new AtraccionDAOImpl();
		Atraccion kalimdor = new Atraccion(7, "kalimdorsa","paisaje",20,53,62);
		Atraccion kalimdora = new Atraccion(5 ,"fe","paisaje",20,53,62);
		ArrayList<Atraccion> asd = new ArrayList<Atraccion>();
		
		asd.add(kalimdora);
		asd.add(kalimdor);

		Usuario roberto = new Usuario("elkakasds","2222","3333", "paisaje",4446,5566);
		
		Promo pascuas = new PromoAxB(14,"par5555que","aventura","f5555e",asd);

		
		roberto.Comprar(pascuas);
		roberto.Comprar(kalimdor);
		roberto.Comprar(kalimdora);
		System.out.println(usuario.modificar(roberto));
		*/
	}
}

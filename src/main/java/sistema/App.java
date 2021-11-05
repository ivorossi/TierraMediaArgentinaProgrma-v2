package sistema;
import excepcion.ExcepcionDeProducto;
import excepcion.ExcepcionDelUsuario;
public class App {

	public static void main(String[] arg) throws ExcepcionDeProducto, ExcepcionDelUsuario {
		
		TierraMedia sad = new TierraMedia();
		sad.sistema();

	}
}

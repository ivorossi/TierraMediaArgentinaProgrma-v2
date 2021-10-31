package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProvedorDeConeccion {

		private static String ruta = "jdbc:sqlite:tierraMedia.db";
		private static Connection connection;
		
		public static Connection getConnection() throws SQLException {
			if (connection == null) 
				connection = DriverManager.getConnection(ruta);			
			
			return connection;
		}



}

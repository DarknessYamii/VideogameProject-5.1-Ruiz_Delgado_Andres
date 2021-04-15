package videojuego;

import java.util.Scanner;

/**
 * Metodo que imprime el menu del gestor
 * 
 * @author andre
 *
 */
public class VideogameMenu {
	Videogame vg = new Videogame();
	Videogame_Methods vgm = new Videogame_Methods();

	// Menu Gestion
	public void gameMenu() {
		System.out.println("========================================\r\n"
				+ "======== Gestión de Videojuegos ========\r\n" + "========================================\r\n"
				+ "1.- Añadir un videojuego.\r\n" + "2.- Listar videojuegos.\r\n" + "3.- Borrar un videojuego.\r\n"
				+ "4.- Guardar datos en fichero.\r\n" + "5.- Recuperar datos desde fichero.\r\n"
				+ "0.- Salir de la aplicación.\r\n" + "========================================\n");
		Scanner scanner = new Scanner(System.in);
		String opcion;
		do {
			System.out.println("Introduzca la opcion elegida: ");
			opcion = scanner.nextLine();

			switch (opcion) {
			case "1":
				vgm.addGame();
				break;
			case "2":
				vgm.listGames();
				break;
			case "3":
				vgm.deleteGame();
				break;
			case "4":
				vgm.saveDataWrite();
				break;
			case "5":
				vgm.readData();
				break;
			case "0":
				System.out.println("Cerrando aplicacion");
				scanner.close();
				System.exit(0);
				break;
			default:
				System.out.println("Introduzca una opcion");
				break;
			}

		} while (!opcion.equals("0"));
	}
}

package videojuego;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Metodos para la clase videojuegos
 * 
 * @author andre
 *
 */
public class Videogame_Methods {
	Scanner sc = new Scanner(System.in);
	List<Videogame> vgList = new ArrayList<>();
//	Boolean para saber si ha guardado anteriormente
	boolean needSave;

	// Funcion para añadir un videojuego a la lista
	public void addGame() {
		try {
			// Comprobacion para ver si existen mas de 10 juegos
			if (vgList.size() >= 10) {
				System.out.println("No puedes añadir mas de 10 juegos");
				return;
			}
			Videogame vg = new Videogame();

			System.out.println("Introduzca los datos del Videojuego:\nNombre del videojuego: ");
			vg.setGame_name(sc.nextLine());

			System.out.println("Plataforma principal: (1.DS, 2.WII, 3.XBOX, 4.PS2, 5.PS3, 6.PS4, 7.PC)");
			String platform = sc.nextLine().toUpperCase();
			vg.setPlatform(Platform.valueOf(platform));

			System.out.println("Fecha de Lanzamiento: ");
			int d;
			int m;
			int yr;
			System.out.println("");
			System.out.println("Ingrese el dia");
			d = Integer.parseInt(sc.nextLine());
			while (d < 1 || d > 31) {// evita que el usuario ingrese una fecha no valida
				System.out.println("Por favor ingrese una fecha valida");
				d = Integer.parseInt(sc.nextLine());
				System.out.println("");
			}

			System.out.println("Ingrese el mes");
			m = Integer.parseInt(sc.nextLine());
			while (m < 1 || m > 12) {// evita que el usuario ingrese una fecha no valida
				System.out.println("Por favor ingrese una fecha valida");
				m = Integer.parseInt(sc.nextLine());
			}

			System.out.println("Ingrese el año");
			yr = Integer.parseInt(sc.nextLine());

			LocalDate releaseDate = LocalDate.of(yr, m, d);
			// Fecha valida
			if (dateValidation(releaseDate)) {
				vg.setReleaseDate(releaseDate);
			} else {
				System.err.println("Fecha invalida");
				System.exit(0);
			}
			System.out.println(vg.getGame_name() + "" + vg.getPlatform() + "" + vg.getReleaseDate());
			needSave = true;
			vgList.add(vg);

			System.out.println("Se ha creado el nuevo videojuego");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	// Comprueba si la fecha introducida es valida
	public boolean dateValidation(LocalDate localdate) {
		if (localdate.isAfter(LocalDate.now())) {
			return false;
		} else {
			return true;
		}
	}

	// Lista todos los videojuegos de la lista
	public void listGames() {
		if (vgList.isEmpty()) {
			System.out.println("No hay videojuegos");
			return;
		}
		for (Videogame vg : vgList) {
			System.out.println(vg.toString());
		}

		System.out.println("Total de videojuegos: " + vgList.size());
	}

	// Borra un juego de la lista
	public void deleteGame() {

		System.out.println("Introduzca el codigo del videojuego");
		Integer codigo = sc.nextInt();
		// Comprueba si esta en la lista
		if (vgList.stream().anyMatch(l -> l.getCod_game().equals(codigo))) {

			System.out.println("¿Seguro? (S/N)?");
			sc.nextLine();
			String validation = sc.nextLine().toUpperCase();
			if (validation.equals("S")) {
				// Recoge el juego con esa id y lo elimina
				vgList.remove(vgList.stream().filter(l -> l.getCod_game().equals(codigo)).findAny().get());
				System.err.println("Videojuego aniquilado");
				needSave = true;
			} else if (validation.equals("N")) {
				System.out.println("Eliminación cancelada");

			}
		} else {
			System.out.println("El juego no existe");
		}
	}

	// Guarda los datos del mapa en un .dat
	public void saveData(List<Videogame> list) {
		needSave = false;
		Path path = FileSystems.getDefault().getPath("./videojue.dat");
		try {
			Files.delete(path);
		} catch (NoSuchFileException x) {
			System.err.format("%s: no such" + " file or directory%n", path);
		} catch (IOException x) {
			System.err.println(x);
		}
		try {
			FileOutputStream fos = new FileOutputStream("./videojue.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			for (Videogame s : list) {
				oos.writeObject(s);
			}
			if (oos != null) {
				oos.close();
				fos.close();
			}

			System.out.println("Información guardada con éxito en el fichero videojuego.dat");
		} catch (IOException e) {
			System.err.println("[ERROR]: Ups ha ocurrido un error.");
			e.printStackTrace();
		}
	}

	// Ejecuta el metodo saveData
	public void saveDataWrite() {
		saveData(vgList);
	}

	// Lee el archivo videojue.dat y recupera su informacion
	public void dataComprobation() {
		try

		{
			File f = null;
			FileInputStream fe = null;
			ObjectInputStream ois = null;
			try {
				f = new File("videojue.dat");
				if (f.exists()) {
					fe = new FileInputStream(f);
					ois = new ObjectInputStream(fe);
					while (true) {
						Videogame vg = null;
						vg = (Videogame) ois.readObject();
						vg.toString();
						System.out.println("");
						vgList.add(vg);
					}

				}
			} catch (EOFException eof) {
				System.out.println(" --------------------------");
			} catch (FileNotFoundException fnf) {
				System.err.println("Fichero no encontrado " + fnf);
			} catch (IOException e) {
				System.err.println("Se ha producido una IOException");
				e.printStackTrace();
			} catch (Throwable e) {
				System.err.println("Error de programa: " + e);
				e.printStackTrace();
			} finally {
				if (ois != null) {
					ois.close();
					fe.close();
				}
			}
			Videogame.setCont(vgList.get(vgList.size() - 1).getCod_game() + 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Ejecuta dataComprobation analizando si se ha guardado anteriormente
	public void readData() {
		if (needSave == false) {
			dataComprobation();
		} else {
			System.out.println(
					"No has guardado los cambios, perderas todos los cambios aplicados anteriormente, desea continuar? S/N");
			String validation = sc.nextLine().toUpperCase();
			if (validation.equals("S")) {
				dataComprobation();
			} else {
				System.out.println("No se realizo la recuperacion");
			}

		}
	}

}

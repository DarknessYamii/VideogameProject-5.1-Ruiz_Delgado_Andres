package videojuego;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase Videogame el cual recopila y guarda la informacion de los videojuegos
 * 
 * @author andre
 *
 */
public class Videogame implements Serializable {

	private static int cont = 0;
	private Integer cod_game;
	private String game_name;
	private Platform platform;
	private LocalDate releaseDate;

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Videogame() {
		this.cod_game = cont++;
	}

	public static int getCont() {
		return cont;
	}

	public static void setCont(int cont) {
		Videogame.cont = cont;
	}

	public String getGame_name() {
		return game_name;
	}

	public void setGame_name(String game_name) {
		this.game_name = game_name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cod_game == null) ? 0 : cod_game.hashCode());
		result = prime * result + ((game_name == null) ? 0 : game_name.hashCode());
		result = prime * result + ((platform == null) ? 0 : platform.hashCode());
		result = prime * result + ((releaseDate == null) ? 0 : releaseDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Videogame other = (Videogame) obj;
		if (cod_game == null) {
			if (other.cod_game != null)
				return false;
		} else if (!cod_game.equals(other.cod_game))
			return false;
		if (game_name == null) {
			if (other.game_name != null)
				return false;
		} else if (!game_name.equals(other.game_name))
			return false;
		if (platform != other.platform)
			return false;
		if (releaseDate == null) {
			if (other.releaseDate != null)
				return false;
		} else if (!releaseDate.equals(other.releaseDate))
			return false;
		return true;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		try {
			this.platform = platform;
		} catch (IllegalArgumentException e) {

		}

	}

	@Override
	public String toString() {
		return "Codigo del videojuego: " + this.getCod_game() + "\nNombre del videojuego: " + this.getGame_name()
				+ "\nPlataforma: " + this.getPlatform() + "\nFecha de Lanzamiento: " + this.getReleaseDate();
	}

	public Integer getCod_game() {
		return cod_game;
	}

	public void setCod_game(Integer cod_game) {
		this.cod_game = cod_game;
	}

}

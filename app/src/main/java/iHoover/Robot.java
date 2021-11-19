package iHoover;

/**
 *
 * @author jaouhar
 * 
 */
public class Robot {

	static enum Orientation {
		N, W, E, S
	};

	/**
	 *
	 * a Vector is defined by coordinates x , y and an orientation.
	 */
	static class Vecteur {
		int x;
		int y;
		Orientation orientation;

		public Vecteur(int x, int y, Orientation orientation) {
			super();
			this.x = x;
			this.y = y;
			this.orientation = orientation;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Vecteur other = (Vecteur) obj;
			if (orientation != other.orientation)
				return false;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "x= " + x + ", y= " + y + ", orientation= " + orientation;
		}

	}

	public Robot() {

	}

	/**
	 * @param xdim x dimension
	 * @param ydim y dimension
	 * @param v0   current vector
	 * @return returns new vector || if we are in the edge it returns the same old
	 *         position
	 */
	private static Vecteur afterMovePosition(int xdim, int ydim, Vecteur v0) {
		Vecteur currentPos = new Vecteur(v0.x, v0.y, v0.orientation);
		switch (v0.orientation) {
		case N:
			if (++v0.y < ydim) {
				currentPos.y = v0.y;
				currentPos.x = v0.x;
			}
			break;
		case S:
			if (--v0.y >= 0) {
				currentPos.y = v0.y;
				currentPos.x = v0.x;
			}
			break;
		case W:
			if (--v0.x >= 0) {
				currentPos.y = v0.y;
				currentPos.x = v0.x;
			}
			break;
		case E:
			if (++v0.x < xdim) {
				currentPos.y = v0.y;
				currentPos.x = v0.x;
			}
		}
		return currentPos;
	}

	/**
	 * @param current orientation
	 * @param current command
	 * @return new orientation based on the command
	 */
	private static Orientation orientationCalculator(Orientation orientation, char command) {
		switch (orientation) {
		case N:
			return command == 'G' ? Orientation.W : Orientation.E;
		case S:
			return command == 'D' ? Orientation.W : Orientation.E;
		case W:
			return command == 'G' ? Orientation.S : Orientation.N;
		case E:
			return command == 'D' ? Orientation.S : Orientation.N;
		default:
			return null;
		}

	}

	/**
	 * @param xdim      x dimension
	 * @param ydim      y dimension
	 * @param v0        initial vector / position
	 * @param commandes
	 * @return the final vector / position
	 * @throws Exception if commands invalid
	 */
	public Vecteur automatiqueAspirateur(int xdim, int ydim, Vecteur v0, String commandes) throws Exception {

		Vecteur currentPos = v0;
		for (char c : commandes.toCharArray()) {
			switch (c) {
			case 'A':
				currentPos = afterMovePosition(xdim, ydim, currentPos);
				break;
			case 'G':
				currentPos.orientation = orientationCalculator(currentPos.orientation, 'G');
				break;
			case 'D':
				currentPos.orientation = orientationCalculator(currentPos.orientation, 'D');
				break;
			default:
				throw new Exception(
						"Invalid Input direction possible command : A -> forward , D -> turn right , G -> turn left");
			}
		}

		return currentPos;
	}
}

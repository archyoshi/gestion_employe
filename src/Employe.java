import java.io.Serializable;


public class Employe extends Personne implements Serializable{
	private int salaire;

	public Employe(){
		super();
		salaire=0;
	}

	public Employe(String nom, String prenom, int year, int month, int day, double taille, char sex,int salaire){
		super(nom, prenom, year, month, day, taille, sex);
		this.salaire=salaire;
	}

	public int getSalaire(){
		return salaire;}

	public void setSalaire(int salaire){
		this.salaire=salaire;
	}

	public String toString(){
		String msg = super.toString();
		msg += " reçoit un salaire de "+this.salaire;
		return msg;
	}
}

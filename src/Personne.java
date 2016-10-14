import java.io.Serializable;
import java.util.Calendar;

public class Personne implements Serializable{

		private int code;
		private String nom;
		private String prenom;
//		private Date dateNaissance; // on la remplace par year/month/day
		private int yearBirth;
		private int monthBirth;
		private int dayBirth;
		private double taille;
		private char sex;
		private static int nbp=0; // nbp = nombre personnes
								// nbp  ous servira � attribuer un code
								// incr�ment� de 1 � chaque cr�ation de "personne"

		public Personne(){
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, 1990);
			cal.set(Calendar.MONTH,0); // car les mois commencent de 0
			cal.set(Calendar.DAY_OF_MONTH, 1);
			yearBirth=cal.get(Calendar.YEAR);
			monthBirth=cal.get(Calendar.MONTH); // car les mois commencent de 0
			dayBirth=cal.get(Calendar.DAY_OF_MONTH);
			code = nbp;
			nom = "Sans nom";
			prenom = "Sans prenom";

			taille = 0;
			sex = 'm';
			nbp++;
		}

		public Personne(String nom, String prenom, int year,int month,int day, double taille, char sex){
			code = nbp;
			this.nom = nom;
			this.prenom = prenom;
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH,month);
			cal.set(Calendar.DAY_OF_MONTH, day);
			yearBirth=cal.get(Calendar.YEAR);
			monthBirth=cal.get(Calendar.MONTH);
			dayBirth=cal.get(Calendar.DAY_OF_MONTH);

			this.taille = taille;
			this.sex = sex;
			nbp++;
		}

		public String getNom() {
			return nom;
		}

		public String getPrenom() {
			return prenom;
		}

		public double getTaille() {
			return taille;
		}

		public char getSex() {
			return sex;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}

		public void setTaille(double taille) {
			this.taille = taille;
		}

		public void setSex(char sex) {
			this.sex = sex;
		}

		public int getYearBirth() {
			return yearBirth;
		}

		public int getMonthBirth() {
			return monthBirth;
		}

		public int getDayBirth() {
			return dayBirth;
		}

		public static int getNbp() {
			return nbp;
		}

		public void setYearBirth(int yearBirth) {
			this.yearBirth = yearBirth;
		}

		public void setMonthBirth(int monthBirth) {
			this.monthBirth = monthBirth;
		}

		public void setDayBirth(int dayBirth) {
			this.dayBirth = dayBirth;
		}

		public int getAge(){
			int age,md,dd; //md = month difference , dd = day difference
			Calendar rightNow = Calendar.getInstance();
			age = rightNow.get(Calendar.YEAR) - yearBirth;
			md = rightNow.get(Calendar.MONTH) - monthBirth;

			if (md<0)
				age--;
			else if(md == 0){
				dd = rightNow.get(Calendar.DAY_OF_MONTH)-dayBirth;
				if (dd < 0)
					age--;
			}

			return age;
		}

		public String toString(){
			String msg;
			int monthBirthBis = monthBirth+1; //car les mois commencent de 0 dans calendar !
			msg = "Personne : " + code +" "+ nom +" "+ prenom + " de taille " + taille;
			if (this.sex=='m')
				msg+= " n� le ";
			else
				msg+= " n�e le ";
			msg+= (dayBirth < 10) ? "0"+dayBirth : dayBirth;
			msg+="/";
			msg+= (monthBirthBis < 10) ? "0"+ monthBirthBis : monthBirthBis;
			msg+="/"+yearBirth;
			if (this.sex=='m')
				msg+= " et ag� de ";
			else
				msg+= " et ag�e de ";
			msg+=getAge()+ " ans.";

			return(msg);
		}

		public void afficherPersonne(){
			System.out.println(this.toString());
		}

		public int getCode() {
			return code;
		}

}

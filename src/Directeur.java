import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import javax.swing.text.html.HTMLDocument.Iterator;

public class Directeur extends Employe implements Serializable {
	private List<Employe> liste_employe; //on pourrait mettre LinkedListe

	public Directeur(){ // est ce qu on peut l enlever ??
		super();
		this.liste_employe = new  LinkedList<Employe>();
	}

	public Directeur(String nom, String prenom, int year, int month, int day, double taille, char sex,int salaire, List<Employe> liste_employe){
		super(nom, prenom, year, month, day, taille, sex, salaire);
		this.liste_employe=liste_employe;
	}

	public void ajouter_employe(Employe employe){
		if(!this.liste_employe.contains(employe))
			this.liste_employe.add(employe);
	}

	public void supprimer_employe(int code){
		for (Employe emp : this.liste_employe) {
			if (code == emp.getCode()) {
				this.liste_employe.remove(emp);
			}
		}
	}

/*
	public void supprimer_employe_bis(int code){
		Employe E;
		ListIterator<Employe> it=liste_employe.listIterator();

	}*/
//		this.liste_employe.remove(employe);

	public String toString(){
		String msg = super.toString();
		msg+="\nGere les employes suivants :\n";
		for (Employe emp : this.liste_employe)
				msg+=emp.toString()+"\n";
		return msg;
	}

	/*
	public void export(){
		FileOutputStream f;
		try {
			f = new  FileOutputStream("test.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(Employe E : liste_employe)
			try {
				oos.writeObject(f);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		try {
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			f.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/

	public void export(){
		File file = new File("fichier1.txt");
		FileWriter fw = null;

		try {
			fw = new FileWriter(file);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		for(Employe e : liste_employe){
			try {
				fw.write(e.toString());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		try {
			fw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void exportBis(){
		FileOutputStream file = null;
		try {
			file = new FileOutputStream("fichier2.txt");
		} catch (FileNotFoundException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
//		FileWriter fw = null;

		ObjectOutputStream fw =null;

		try {
			fw = new ObjectOutputStream(file);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		for(Employe e : liste_employe){
			try {
				fw.writeObject(e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		try {
			fw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/*
	public void importBis(){
		FileInputStream
		File file = new File("testFileWriter.txt");
		FileReader fr;
		fr = new  FileReader(file);
		str = "";
		int i = 0;
		while((i = fr.read()) != -1)
			str += (char)i;
		System.out.println(str);
	}*/

	public void importBis(){
		FileInputStream file = null;
		try {
			file = new FileInputStream("fichier2.txt");
		} catch (FileNotFoundException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
//		FileWriter fw = null;

		ObjectInputStream fw =null;

		try {
			fw = new ObjectInputStream(file);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		Employe e = null;
		int i=0;
		while(i != -1) {
			try {
				e=(Employe)fw.readObject();
				this.liste_employe.add(e);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				i=fw.read();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		try {
			fw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}

package hikers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.TreeMap;

public class hikingIO {
	//methods to serialize singleton
	public static TreeMap<String, User> users;
	public HashMap<String, Trails> trails;
	
	public static void saveData(singleton instance) throws IOException {
        FileOutputStream fos=new FileOutputStream("data.dat");
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeObject(instance);
        oos.close();
	}

	public static singleton retrieveData() throws IOException, ClassNotFoundException {
        File file=new File("data.dat");
        singleton data=null;
        if(file.exists()) {
        	FileInputStream fis=new FileInputStream("data.dat");
        	ObjectInputStream ois=new ObjectInputStream(fis);
        	data=(singleton) (ois.readObject());
        	ois.close();
                      	}
        	return data;
	}
	////methods to save individual data
	public static void saveUserData(TreeMap<String,User> user) throws IOException {
		users=new TreeMap<String,User>();
		users.putAll(user);
        FileOutputStream fos=new FileOutputStream("users.dat");
        ObjectOutputStream oos=new ObjectOutputStream(fos);

        oos.writeObject(user);
        oos.close();
	}

	public static TreeMap<String,User> getUserData() throws IOException, ClassNotFoundException {
        //TreeMap<String, User> users=
        File file=new File("users.dat");
        TreeMap<String, User> data = null;
        if(file.exists()) {
        	FileInputStream fis=new FileInputStream("users.dat");
        	ObjectInputStream ois=new ObjectInputStream(fis);
        	data=(TreeMap<String,User>) (ois.readObject());
        	ois.close();
                      }
        return data;
	}

	//save trails
	public static void saveTrailData(HashMap<String,Trails> trail) throws IOException {
        //TreeMap<String, User> users=
        FileOutputStream fos=new FileOutputStream("trails.dat");
        ObjectOutputStream oos=new ObjectOutputStream(fos);

        oos.writeObject(trail);
        oos.close();
		}

	public static HashMap<String,Trails> getTrailData() throws IOException, ClassNotFoundException {
        //TreeMap<String, User> users=
        File file=new File("trails.dat");
        HashMap<String, Trails> data = null;
        if(file.exists()) {
        	FileInputStream fis=new FileInputStream("trails.dat");
        	ObjectInputStream ois=new ObjectInputStream(fis);
        	data=(HashMap<String,Trails>) (ois.readObject());
        	ois.close();
                      	}
        	return data;
	}

	

}

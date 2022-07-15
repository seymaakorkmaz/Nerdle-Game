package nyp_8;

import java.io.*;

public class FileOperations {
    private Statistics s = null;
    private HalfGame h = null;
    
    public void init(String fileName) { 
    	//Oyunun basinda istatistik dosyasý var mý diye kontrol edilir.
    	//Eger dosya olusturulmamýssa olusturur ve icine tum degerleri 0 olan Statistics objesi atar.
        File file = new File(fileName);
        if(!file.exists()) {
            s = new Statistics(0, 0, 0, 0, 0);
            write(fileName,s);
        }
    }

    public Statistics read(String fileName) {
    	//istatistik dosyasýndan Statistics objesi alýnýr.
        try {
            FileInputStream dosya = new FileInputStream(fileName);
            ObjectInputStream oku = new ObjectInputStream(dosya);
            s = (Statistics) oku.readObject();
            oku.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public void write(String fileName,Statistics s) {
    	//istatistik dosyasýna Statistics objesi kaydedilir.
        try {
            FileOutputStream dosya = new FileOutputStream(fileName);
            ObjectOutputStream yaz = new ObjectOutputStream(dosya);
            yaz.writeObject(s);
            yaz.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public HalfGame readEquation(String fileName) {
    	//equation dosyasýndan  HalfGame objesi alýnýr.
        try {
            FileInputStream dosya = new FileInputStream(fileName);
            ObjectInputStream oku = new ObjectInputStream(dosya);
            h = (HalfGame) oku.readObject();
            oku.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return h;
    }

    public void writeEquation(String fileName,HalfGame h) {
    	//equation dosyasýna  HalfGame objesi kaydedilir.
        try {
            FileOutputStream dosya = new FileOutputStream(fileName);
            ObjectOutputStream yaz = new ObjectOutputStream(dosya);
            yaz.writeObject(h);
            yaz.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public void deleteFile(String fileName) {
    	//dosya silme
        File dosya = new File(fileName);
        dosya.delete();
        
    }
}












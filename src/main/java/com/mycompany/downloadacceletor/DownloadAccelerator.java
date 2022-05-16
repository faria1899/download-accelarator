/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.downloadacceletor;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.*;




class Home implements Runnable{
    @Override
    public void run()
    {
        for(int i = 0 ; i<=100;i++)
        {
            System.out.println("print document2" +i+" printB");
        }
        
    }
    
}

class Home1 implements Runnable
{
    @Override
    public void run()
    {
        for(int i = 0 ; i<=100;i++)
        {
            System.out.println("print document1" +i+" printA ");
        }
    }
}

class Download implements Runnable
{
    String link;
    File out;
    public Download (String link, File out)
    {
        this.link = link;
        this.out = out;
    }
    @Override
    public void run()
    {
        try{
            URL url = new URL(this.link);
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            double filesize = (double)http.getContentLengthLong();
            BufferedInputStream fin = new  BufferedInputStream(http.getInputStream());
            FileOutputStream  fall = new  FileOutputStream(this.out);
            BufferedOutputStream fout = new BufferedOutputStream(fall,1024);
            byte[] buffer = new byte[1024];
            double filedownloDED = 0.00;
            int read = 0;
            double percentDownloaded = 0.00;
            while((read = fin.read(buffer,0,1024))>=0 )
            {
                fout.write(buffer,0,read);
                filedownloDED += read;
                percentDownloaded =(filedownloDED*100)/filesize;
                String percent = String.format("%.4f",percentDownloaded);
                System.out.println("Download "+percent+"% of a file");
            }
            fout.close();
            fin.close();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
}

/**
*
* @author ACER
*/
public class DownloadAccelerator {
    
    /**
    * @param args the command line arguments
    */
    public static void main(String[] args) {
        System.out.println("test");
        
        /*
        Home ox1 = new Home();
        Thread t1 = new Thread(ox1);
        t1.start();
        Thread t2 = new Thread(ox1);
        t2.start();
        
        Home1 oxa = new Home1();
        Thread t3 = new Thread(oxa);
        t3.start();
        Thread t4 = new Thread(oxa);
        t4.start();
        String link = "https://www.facebook.com";
        File out = new File ("facebook.html");
        new Thread (new Download(link, out)).start();
        */
        
        RandomAcessfiled ret = new RandomAcessfiled();
        ret.randomACCEssFileRead();
        
        // TODO code application logic here
        
    }
}
class RandomAcessfiled
{
    public void randomACCEssFileRead()
    {
        System.out.println("inside random access");
        try {

            RandomAccessFile raf = new   RandomAccessFile ("C:\\Users\\HP\\Documents\\test.txt","rw");
            long fileLength = raf.length();
            long halfOfTheFile = Math.round((double)fileLength/2.0);

//raf.writeUTF =("read file");
            raf.seek(0);
            byte[] buffer = new byte[(int)halfOfTheFile];
            raf.read(buffer,0,buffer.length);
            FileOutputStream fout = new FileOutputStream("C:\\Users\\HP\\Documents\\test1.txt");
            fout.write(buffer,0,buffer.length);
            fout.flush();
            fout.close();
            
            
            raf.seek(halfOfTheFile+1);
            byte[] buffer2 = new byte[(int)(fileLength-halfOfTheFile)];
            raf.read(buffer2,0,buffer2.length);
            FileOutputStream fout2 = new FileOutputStream("C:\\Users\\HP\\Documents\\test2.txt");
            fout2.write(buffer2,0,buffer2.length);
            fout2.flush();
            fout2.close();


        } catch (IOException ex)
        {
            ex.printStackTrace(); 
        }
    
    }
}

package com.vashli.fileexplorerapp.Model;

import android.net.Uri;
import android.webkit.MimeTypeMap;
import com.vashli.fileexplorerapp.R;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class FileModel {
    public final static  String DIR = "DIRECTORY";
    public final static  String FILE_TXT = "txt";
    public final static  String FILE_DOC = "doc";
    public final static  String FILE_XLS = "xls";
    public final static  String FILE_ZIP = "zip";
    public final static  String FILE_PNG = "png";
    public final static  String FILE_MP3 = "mp3";
    public final static  String FILE_PDF = "pdf";

    private final static int K = 1024;

    private File file;
    private String type;
    private int icon;
    private long size;
    private String sizeDim;
    private Date createDate;

    public  FileModel(String path){
        this.file = new File(path);
        setType();
        setIcon();
        setSizeAndDim();
        setDate();

    }

    private void setDate() {
        this.createDate = new Date(file.lastModified());
    }

    private void setType(){
        if(file.isDirectory()) {
            this.type = DIR;
        }else{
            Uri file = Uri.fromFile(this.file);
            this.type = MimeTypeMap.getFileExtensionFromUrl(file.toString());
        }
    }

    private void setIcon(){
        switch(this.type){
            case DIR:
                this.icon = R.drawable.folder;
                break;
            case FILE_DOC:
                this.icon = R.drawable.doc;
                break;
            case FILE_MP3:
                this.icon = R.drawable.mp3;
                break;
            case FILE_PNG:
                this.icon = R.drawable.png;
                break;
            case FILE_TXT:
                this.icon = R.drawable.txt;
                break;
            case FILE_XLS:
                this.icon = R.drawable.xls;
                break;
            case FILE_ZIP:
                this.icon = R.drawable.zip;
                break;
            case FILE_PDF:
                this.icon = R.drawable.pdf;
                break;
            default:
                this.icon = R.drawable.zip;
                break;
        }
    }

    private void setSizeAndDim(){
        switch (this.type){
            case DIR:
                this.size = file.listFiles().length;
                this.sizeDim = "Items";
                break;
            default:
                this.size = file.length();

                int times = 0;
                while( this.size >= K){
                    this.size /= K;
                    times ++;

                }
                if(times == 0){
                    this.sizeDim = "B";
                }else if(times == 1){
                    this.sizeDim = "KB";
                }else if(times == 2){
                    this.sizeDim = "MB";
                }else {
                    this.sizeDim = "GB";
                }


        }
    }


    public int getIcon(){
        return this.icon;
    }

    public long getSize(){
        return this.size;
    }

    public String getSizeDim(){
        return this.sizeDim;
    }

    public Date getCreateDate(){
        return this.createDate;
    }

    public String getName(){
        return file.getName();
    }

    public String getType(){
        return this.type;
    }
    public String getPath(){
        return file.getPath();
    }

    public void deleteFile(){
        if(!file.canWrite()) return;
        if (this.type.equals(DIR)){
            File directory = new File(getPath());
            File[] files = directory.listFiles();
            for (File file:
                 files) {
                if(!file.canWrite()) continue;
                FileModel myFile = new FileModel(file.getPath());
                myFile.deleteFile();
            }
        }
        this.file.delete();
    }


    public String readText(){
        if(!this.type.equals(FILE_TXT)) return "";
        String result = "mari";
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            Scanner s = new Scanner(fileInputStream).useDelimiter("\\A");
            result = s.hasNext() ? s.next() : "";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }


    public void writeText(String text){
        if(!this.type.equals(FILE_TXT)) return;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(text);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStreamWriter.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<FileModel> getFiles() {
        if (!this.type.equals(DIR)) return null;
        File[] files = this.file.listFiles();
        ArrayList<FileModel> fileModels = new ArrayList<>();
        for (File file : files) {
            fileModels.add(new FileModel(file.getPath()));
        }
        return  fileModels;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package file;

import java.io.File;

/**
 *
 * @author César
 */
public class Archivo {
    private File archivo;
    private long size;

    public Archivo(File archivo) {
        this.archivo = archivo;
        this.size=0;
    }

    public Archivo(File archivo, long size) {
        this.archivo = archivo;
        this.size = size;
    }

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
    
    

    
    
    
    
    
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAS;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import file.Archivo;
import java.io.File;
import java.util.Iterator;

import file.Archivo;
import java.io.File;
import java.util.Iterator;

/**
 *
 * @author Tago
 */
public class Tree<E> {

    private TreeNode<E> raiz;
    private static boolean restringido;
    private static String extensionRestringida;

    public static boolean isRestringido() {
        return restringido;
    }

    public static void setRestringido(boolean restringido) {
        Tree.restringido = restringido;
    }

    public static String getExtensionRestringida() {
        return extensionRestringida;
    }

    public static void setExtensionRestringida(String extensionRestringida) {
        Tree.extensionRestringida = extensionRestringida;
    }
    public Tree() {
        raiz = new TreeNode();
    }

    public Tree(E content) {
        raiz = new TreeNode(content);
    }

    public void addNode(E content) {
        Tree<E> arbol = new Tree();
        TreeNode<E> nodo = new TreeNode(content);
        arbol.setRaiz(nodo);
        this.raiz.getHijos().addLast(arbol);
    }

    /**
     * este metodo considera como nodos carpetas y archivos de cualquier formato
     * @param file 
     */
    public void addFile(File file) {
        Tree<E> arbol = new Tree();
        TreeNode<E> nodo = new TreeNode(file);

        if (!file.isDirectory()) {
            nodo.setPeso(file.length());
        }
        arbol.setRaiz(nodo);
        if (file.isDirectory()) {
            this.raiz.getHijos().addLast(arbol);
            File[] lista = file.listFiles();
            //NUEVOOOOO CAMBIO CONFIRMAR ESTA VALIDACION
            if (lista != null) {
                for (File archivo : lista) {

                    arbol.addFile(archivo);
                }
            }
            this.raiz.setPeso(this.raiz.getPeso() + arbol.raiz.getPeso());
        } else {

            this.raiz.getHijos().addLast(arbol);
            this.raiz.setPeso(this.raiz.getPeso() + file.length());
        }
    }
    /**
     * Este metodo considera como unicos nodos las carpetas
     * @param file 
     */ 
    public void addDirectory(File file) {
        if (!file.isDirectory()) {
            this.raiz.setPeso(this.raiz.getPeso() + file.length());
        } else {
            Tree<E> arbol = new Tree();
            TreeNode<E> nodo = new TreeNode(file);
            arbol.setRaiz(nodo);
            this.raiz.getHijos().addLast(arbol);
            File[] lista = file.listFiles();
            if(lista!=null){
            for (File archivo : lista) {

                arbol.addDirectory(archivo);

            }}
            this.raiz.setPeso(this.raiz.getPeso() + arbol.raiz.getPeso());
        }

    }
    
    /**
     * Esto solo se encarga de imprimir los datos de cada uno de los nodos
     */
    public void ImprimirArbol() {

        LinkedList<Tree<E>> hijos = this.raiz.getHijos();
        Iterator<Tree<E>> iterator = hijos.iterator();
        if (this.isLeaf()) {
            System.out.println("Hijo:" + this.getRaiz().getContent() + "peso: " + this.raiz.getPeso());
        } else {
            System.out.println("Padre: " + this.getRaiz().getContent() + "peso: " + this.raiz.getPeso());
        }
        while (iterator.hasNext()) {
            Tree<E> hijo = iterator.next();

            hijo.ImprimirArbol();

        }
    }

    public boolean isLeaf() {
        return this.raiz.getHijos().isEmpty();
    }

    public TreeNode<E> getRaiz() {
        return raiz;
    }

    public void setRaiz(TreeNode<E> raiz) {
        this.raiz = raiz;
    }
     public static void build(Tree<Archivo> tree){
        if(tree.getRaiz().getContent().getArchivo().isDirectory()){
            for (File file:tree.getRaiz().getContent().getArchivo().listFiles()) {
                Tree<Archivo> t2=null;
                if(file.isDirectory()){
                    Archivo ar=new Archivo(file);
                    t2=new Tree(ar);
                    build(t2);
                }
                else{
                    
                    Archivo ar=new Archivo(file,file.length());
                    
                    t2=new Tree(ar);
                }
                
                if(restringido){
                    if(file.getName().endsWith(extensionRestringida) || file.isDirectory()){
                tree.getRaiz().getContent().setSize(tree.getRaiz().getContent().getSize()+t2.getRaiz().getContent().getSize());
                tree.getRaiz().getHijos().addLast(t2);
                    }
                }else{
                    tree.getRaiz().getContent().setSize(tree.getRaiz().getContent().getSize()+t2.getRaiz().getContent().getSize());
                tree.getRaiz().getHijos().addLast(t2);
                }
            }
        }
        else{
              if(restringido){
                    if(tree.getRaiz().getContent().getArchivo().getName().endsWith(extensionRestringida)){
           tree.getRaiz().getContent().setSize(tree.getRaiz().getContent().getArchivo().length());
                    }
              }else{
                         tree.getRaiz().getContent().setSize(tree.getRaiz().getContent().getArchivo().length());
                    }
           
        }
    }

}

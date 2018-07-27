package matriz;
import java.util.Scanner;
/*
 * @author Matulo
 */
public class Matriz {
    public static Scanner cin = new Scanner(System.in);
    //Funcion Matriz elevada a 0(identidad)
    public static int[][] cero(int matriz)
    {
        int [][]grafo0 = new int [matriz][matriz];
        for(int i=0;i<grafo0.length;i++)
        {
            for(int j=0;j<grafo0.length;j++)
            {
                if(i==j)
                {
                    grafo0[i][j]=1;
                }
                else
                {
                    grafo0[i][j]=0;
                }
            }
        }
        return grafo0;
     }
    //Funcion elevar
    public static int[][] elevar(int[][] grafo2,int[][] grafo1) 
     {
	int fila1= grafo1.length, columna1 = grafo1[0].length, columna2 = grafo2[0].length;
	int[][] elevar= new int[fila1][columna2];
	for(int x=0;x<elevar.length;x++) 
        {
                for(int y=0;y<elevar[x].length;y++) 
                {
                    for (int z=0;z<columna1;z++) 
                    {
            		elevar[x][y]+= grafo1[x][z]*grafo2[z][y];
                    }
                }
	}
	return elevar;
    }
    //Funcion sumar Matriz
    public static int[][] sumar_grafo(int[][] grafo1,int[][] grafo2)
    {
        int[][] grafo_conexo = new int[grafo1.length][grafo2[0].length];
	for(int i=0;i<grafo_conexo.length;i++) 
        {
            for(int j=0;j<grafo_conexo[i].length;j++) 
            {
                grafo_conexo[i][j]= grafo1[i][j]+grafo2[i][j];
            }
        }
	return grafo_conexo;
    }
    //Funcion mostrar si es conexo o no
    public static void conexo(int[][] grafo) 
    {
        int contador= 0;
        for(int[] grafo1 : grafo) 
        {
            for(int j=0;j<grafo.length;j++)
            {
                if(grafo1[j]==0)
                {
                    contador++;
                }
            }
        }
        if(contador==0)
        {
            System.out.println("Es Conexo");
        }
        else
        {
            System.out.println("No es Conexo");
        }
    }
    //Funcion mostrar Matrices
    public static void mostrar(int[][] grafo) 
    {
        for(int[] grafo1 : grafo) 
        {
            for (int y=0;y<grafo1.length;y++) 
            {
                System.out.print(grafo1[y]);
            }
            System.out.print("\n");
        }
    }
 
    public static void main(String[] args) {
        //Ingreso tamaño de la matriz
        int matriz, opcion;
        do
        {
            System.out.println("1) LLenar Matriz");
            System.out.println("2) Salir");
            opcion= cin.nextInt();
            switch(opcion)
            {
                case 1:
                    System.out.println("Ingrese tamaño de la matriz:");
                    matriz = cin.nextInt();
                    int [][] grafo = new int[matriz][matriz];
                    //Ingreso de valores a la matriz
                    for(int i= 0;i<grafo.length;i++)
                    {
                        for(int j=0;j<grafo.length;j++)
                        {
                            System.out.print("Ingrese valor: [" + i + "][" + j + "]: ");
                            grafo[i][j] = cin.nextInt();
                        }            
                    }
                    //Mostrar la matriz Generada
                    System.out.println("\n Matriz Generada(Adyacencia):");
                    mostrar(grafo);
                    //Matriz elevada
                    int [][] grafo2=grafo, grafo_conexo=grafo, cero=cero(matriz);
                    int coeficiente,contador=0;
                    System.out.println("Ingrese numero a elevar: ");
                    coeficiente= cin.nextInt();
                    if(coeficiente==0)//Matriz elevada a 0(identidad)
                    {
                        grafo2=cero(matriz);
                    }
                    else
                    {
                        while(contador<coeficiente)
                        {
                            if(contador==0)
                            {
                                grafo2=grafo;
                                grafo_conexo=sumar_grafo(grafo2,cero);
                            }
                            else if(contador==1)
                            {   
                                grafo2= elevar(grafo,grafo);
                                grafo_conexo=sumar_grafo(grafo2,grafo_conexo);
                            }
                            else if(contador>=2)
                            {
                                grafo2= elevar(grafo2,grafo);
                                grafo_conexo=sumar_grafo(grafo2,grafo_conexo);
                            }
                            contador++;
                        }
                    }
                    //Mostrar Matriz Elevada
                    System.out.println("Matriz Elevada:");
                    mostrar(grafo2);
                    //Ver si es conexo o no
                    System.out.println("###################################");
                    mostrar(grafo_conexo);
                    conexo(grafo_conexo);
                    break;
                default:
                    System.out.println("Elige otra opcion");
                    break;
            }
        }
        while(opcion!=2);
    }     
}

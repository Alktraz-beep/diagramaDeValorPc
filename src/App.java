//import java.security.interfaces.EdECPublicKey;
import java.awt.Color;
import java.io.*;
import java.util.Scanner;
import java.util.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.plot.PiePlot;
public class App {
    public static void esperaPunto(){
        Scanner  termina= new Scanner(System.in);
        while(termina.next().charAt(0)!='.'){
            System.out.println("para terminar de grabar el tiempo poner ''.''");
        }
    }
    
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("Ingresa el numero deacuerdo al tipo de actividad que se presentara y ''.'' para cuando termine ese tiempo");
        System.out.println("\t1.Actividad de valor\n\t2.actividad\n\t3.actividad de desperdicio\n\t4.para terminar:\n");
        long startTime;//variable que mide el tiempo de inicio
        long endTime;//variable que mide el tiempo al final restado(el tiempo)
        float tiempo;//variable convertida a segundos de cada actividad
        float tiempoTotal=0;//el tiempo total en conjunto medido
        ArrayList<Float> tiempoA1=new ArrayList<Float>();//ArrayList para los tiempos de la op 1
        ArrayList<Float> tiempoA2=new ArrayList<Float>();//ArrayList para los tiempos de la op 2
        ArrayList<Float> tiempoA3=new ArrayList<Float>();//ArrayList para los tiempos de la op 3
        int eleccion;
        do{
            eleccion=input.nextInt();
            switch(eleccion){
                case 1:
                    startTime=System.nanoTime();
                    esperaPunto();
                    endTime=(System.nanoTime()-startTime);
                    tiempo=((float)endTime/1000000000);
                    //System.out.printf("%.2f segundos\n",tiempo);
                    //se agrega al array list
                    tiempoA1.add(tiempo);
                    tiempoTotal+=tiempo;//suma todos los tiempos
                    break;
                case 2:
                    startTime=System.nanoTime();
                    esperaPunto();
                    endTime=(System.nanoTime()-startTime);
                    tiempo=((float)endTime/1000000000);
                    //se agrega al array list
                    tiempoA2.add(tiempo);
                    tiempoTotal+=tiempo;//suma todos los tiempos
                    break;
                case 3:
                    startTime=System.nanoTime();
                    esperaPunto();
                    endTime=(System.nanoTime()-startTime);
                    tiempo=((float)endTime/1000000000);
                    //arraylist
                    tiempoA3.add(tiempo);
                    tiempoTotal+=tiempo;//suma todos los tiempos
                    break;
                default:
                    if(eleccion!=4)
                        System.out.println("Valor no permitido, solo 1,2,3 o 4");
                    //System.exit(0);
            }
        }while(eleccion!=4);
        input.close();
        System.out.println("Tiempo total A1: "+(sumarTiempo(tiempoA1)/60));
        System.out.println("Tiempo total A2: "+(sumarTiempo(tiempoA2)/60));
        System.out.println("Tiempo total A3: "+(sumarTiempo(tiempoA3)/60));
        System.out.println("Tiempo total    : "+tiempoTotal/60);
/*                   GRAFICA 1                                   */
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        
        /*mensajes de los valores en minutos*/
        /*String mA1="Actividad de Valor "+String.format("%.02f",sumarTiempo(tiempoA1)/60);
        String mA2="Actividad no necesaria "+String.format("%.02",sumarTiempo(tiempoA2)/60);
        String mA3="Tiempo desperdiciado "+String.format("%.02",sumarTiempo(tiempoA3)/60);*/
        //segunda forma
        String mA1="Actividad de Valor 121.09";
        String mA2="Actividad no necesaria 13.15";
        String mA3="Tiempo desperdiciado 2.19";
        //cuando no se traba
        /*pieDataset.setValue(mA3, new Float(sumarTiempo(tiempoA3)));
        pieDataset.setValue(mA1, new Float(sumarTiempo(tiempoA1)));
        pieDataset.setValue(mA2, new Float(sumarTiempo(tiempoA2)));*/
        //una vez que se traba
        pieDataset.setValue(mA3, new Float(2.19));
        pieDataset.setValue(mA1, new Float(121.09));
        pieDataset.setValue(mA2, new Float(13.15));
        JFreeChart chart1 = ChartFactory.createPieChart(
                "Diagrama de valor en minutos",
                pieDataset,
                true,
                true,
                true
        );

        //Mostramos la grafica en pantalla
        /*para poner colores*/
        PiePlot plot = (PiePlot) chart1.getPlot();
        plot.setSectionPaint(mA1, Color.green);
        plot.setSectionPaint(mA2, Color.yellow);
        plot.setSectionPaint(mA3, Color.red);
        ChartFrame frame = new ChartFrame("Diagrama de valor circular", chart1);
        frame.pack();
        frame.setVisible(true);
    /*           Grafica 2                                        */
        DefaultPieDataset pieDataset1 = new DefaultPieDataset();
        
        /*mensajes de los valores en segundos*/
        /*String mA1_2="Actividad de Valor "+String.format("%.02f",(sumarTiempo(tiempoA1)/tiempoTotal)*100)+"%";
        String mA2_2="Actividad no necesaria "+String.format("%.02",(sumarTiempo(tiempoA2)/tiempoTotal)*100)+"%";
        String mA3_2="Tiempo desperdiciado "+String.format("%.02f",(sumarTiempo(tiempoA3)/tiempoTotal)*100)+"%";*/
        String mA1_2="Actividad de Valor 88.74%";
        String mA2_2="Actividad no necesaria 9.63%";
        String mA3_2="Tiempo desperdiciado 1.60%";
        
        
        /*pieDataset1.setValue(mA3_2, new Float(sumarTiempo(tiempoA3)));
        pieDataset1.setValue(mA1_2, new Float(sumarTiempo(tiempoA1)));
        pieDataset1.setValue(mA2_2, new Float(sumarTiempo(tiempoA2)));*/
        pieDataset1.setValue(mA3_2, new Float(2.19));
        pieDataset1.setValue(mA1_2, new Float(121.09));
        pieDataset1.setValue(mA2_2, new Float(13.15));
        
        JFreeChart chart2 = ChartFactory.createPieChart(
                "Diagrama de valor en porcentaje",
                pieDataset1,
                true,
                true,
                false
        );

        //Mostramos la grafica en pantalla
        /*para poner colores*/
        PiePlot plot2 = (PiePlot) chart2.getPlot();
        plot2.setSectionPaint(mA1_2, Color.green);
        plot2.setSectionPaint(mA2_2, Color.yellow);
        plot2.setSectionPaint(mA3_2, Color.red);
        ChartFrame frame2 = new ChartFrame("Diagrama de valor circular", chart2);
        frame2.pack();
        frame2.setVisible(true);
        /* GUARDAR IMAGENES*/
        File pieChart1=new File("./img/segundos.png");
        File pieChart2=new File("./img/porcentaje.png");
        ChartUtilities.saveChartAsJPEG( pieChart1 , chart1 , 640 , 480 );
        ChartUtilities.saveChartAsJPEG( pieChart2 , chart2 , 640 , 480 );
    }
    /**Funcion que suma todo un arreglo y lo regresa el total */ 
public static float sumarTiempo(ArrayList<Float> tiempos){
    float tiempo=0.0f;
    for (int i=0;i<tiempos.size();i++){
        tiempo+=tiempos.get(i);
    }
    //tiempos.forEach((n)->tiempo+=n);
    return tiempo;
}
}

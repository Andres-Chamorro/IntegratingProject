package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import model.Etapa;
import model.Gerente;
import java.util.Scanner;

/**
 * The Proyecto class represents a project with several stages, each stage has a planned and a real date.
 * The class also includes the project's budget, the name of the client, the start and end dates of the project,
 * the project's managers (one for the green team and one for the client), and whether the project is finished or not.
 */

public class Proyecto{
    private String nombre;
    private String cliente;
    private double presupuesto;
    private Calendar fechaInicio;
    private Calendar fechaFin;
    private Etapa[] etapas;
    private int[] mesesPorEtapa;
    private int numEtapa;
    private Gerente gerenteGreen;
    private Gerente gerenteCliente;
    private boolean terminado;

    /**
     * Creates a new project with the given name, client, budget, start and end dates, and number of months per stage.
     * Also, creates the stages and assigns their planned dates.
     * 
     * @param nombre The name of the project.
     * @param cliente The name of the client.
     * @param presupuesto The project's budget.
     * @param fechaInicio The start date of the project.
     * @param fechaFin The end date of the project.
     * @param mesesPorEtapa An array containing the number of months for each stage.
     * @param numEtapa The current stage number.
     */

    public Proyecto(String nombre, String cliente, double presupuesto, Calendar fechaInicio,Calendar fechaFin, int[] mesesPorEtapa, int numEtapa) {
        this.nombre = nombre;
        this.cliente = cliente;
        this.presupuesto = presupuesto;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.etapas = new Etapa[6];
        this.numEtapa=numEtapa;
        this.mesesPorEtapa = mesesPorEtapa;

        // crear las etapas del proyecto y asignarles sus fechas planeadas
        Calendar fechaFinEtapa = (Calendar) fechaInicio.clone();
        fechaFinEtapa.add(Calendar.MONTH, mesesPorEtapa[0]);
        this.etapas[0] = new Etapa("Inicio", mesesPorEtapa[0], false, fechaInicio, fechaFinEtapa, null, null);
        for (int i = 1; i < 6; i++) {
            fechaFinEtapa.add(Calendar.MONTH, mesesPorEtapa[i]);
            this.etapas[i] = new Etapa(getNombreEtapa(i), mesesPorEtapa[i], false, (Calendar) fechaFinEtapa.clone(), fechaFinEtapa, null, null);
        }
        this.numEtapa = 0;

        this.etapas[0].setFechaFinPlaneada(fechaFinEtapa);
        for (int i = 1; i < 6; i++) {
            fechaFinEtapa.add(Calendar.MONTH, mesesPorEtapa[i]);
            this.etapas[i].setFechaInicioPlaneada((Calendar) fechaFinEtapa.clone());
            this.etapas[i].setFechaFinPlaneada(fechaFinEtapa);
        }
    }

    /**
     * Returns the name of the stage with the given number.
     * 
     * @param numEtapa The number of the stage.
     * @return The name of the stage.
     */

    
    private String getNombreEtapa(int numEtapa) {
        String nombreEtapa = "";
        switch (numEtapa) {
            case 1:
                nombreEtapa = "Inicio";
                break;
            case 2:
                nombreEtapa = "Analisis";
                break;
            case 3:
                nombreEtapa = "Disenio";
                break;
            case 4:
                nombreEtapa = "Ejecucion";
                break;
            case 5:
                nombreEtapa = "Cierre";
                break;
            case 6:
                nombreEtapa = "Seguimiento y Control";
        }
        return nombreEtapa;
    }

    /**
     * Advances the project to the next stage, if there is one. If the project has
     * already completed all stages, a message indicating so is printed.
     */

    public void avanzarEtapa() {
        if (numEtapa < 5) {
            // Culminar la etapa actual
            etapas[numEtapa].setFechaFinReal(Calendar.getInstance());
            etapas[numEtapa].setEtapaAprobada(true);
            System.out.println("Etapa " + getNombreEtapa(numEtapa + 1) + " culminada el " + fechaToString(etapas[numEtapa].getFechaFinReal()));
    
            // Avanzar a la siguiente etapa
            numEtapa++;
            etapas[numEtapa].setFechaInicioReal(Calendar.getInstance());
            System.out.println("Comenzando la etapa " + getNombreEtapa(numEtapa+1));
        } else {
            // El proyecto ya ha terminado
            System.out.println("El proyecto ya ha finalizado.");
        }
    }

    /**
     * Sets the Green Manager for this project.
     * 
     * @param gerenteGreen The Green Manager for this project
     */
    
    public void setGerenteGreen(Gerente gerenteGreen) {
        this.gerenteGreen = gerenteGreen;
    }

    /**
     * Sets the Client Manager for this project.
     * 
     * @param gerenteCliente The Client Manager for this project
     */
    
    public void setGerenteCliente(Gerente gerenteCliente) {
        this.gerenteCliente = gerenteCliente;
    } 

    /**
     * Returns an array of all the stages in this project.
     * 
     * @return An array of all the stages in this project
     */

    public Etapa[] getEtapas() {
        return etapas;
    }

    /**
     * Converts a Calendar object to a string in the format "dd/MM/yyyy".
     *  
     * @param fecha The Calendar object to convert
     * @return The string representation of the Calendar object
     */

    private String fechaToString(Calendar fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(fecha.getTime());
    }

    /**
     * Returns the name of this project.
     * 
     * @return The name of this project
     */

    public String getNombreProyecto() {
        return nombre;
    }

    /**
     * Returns the current stage of this project.
     * 
     * @return The current stage of this project
     */

    public Etapa getEtapaActual() {
        return etapas[numEtapa];
    }

    /**
     * Returns the Green Manager for this project.
     * 
     * @return The Green Manager for this project
     */

    public Gerente getGerenteGreen() {
        return gerenteGreen;
    }

    /**
     * Returns the Client Manager for this project.
     * 
     * @return The Client Manager for this project
     */
    
    public Gerente getGerenteCliente() {
        return gerenteCliente;
    }

    /**
     * Sets whether this project is finished or not.
     * 
     * @param terminado True if the project is finished, false otherwise
     */

    public void setTerminado(boolean terminado) {
        this.terminado = terminado;
    }
    
}
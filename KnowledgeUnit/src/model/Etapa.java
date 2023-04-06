package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import model.Proyecto;

/**
 * A class representing a project stage.
 */

public class Etapa {
    private String nombre;
    private int duracion;
    private Calendar fechaInicioPlaneada;
    private Calendar fechaFinPlaneada;
    private Calendar fechaInicioReal;
    private Calendar fechaFinReal;
    private boolean aprobada;
    private String status;
    private Budge budge;
    private Collaborator collaborator;
    private Budge[] budges; // arreglo de objetos Budge

    /**
     * Constructor for Etapa class.
     *
     * @param nombre              The name of the stage.
     * @param duracion            The duration of the stage in months.
     * @param aprobada            Whether the stage has been approved or not.
     * @param fechaInicioPlaneada The planned start date of the stage.
     * @param fechaFinPlaneada    The planned end date of the stage.
     * @param fechaInicioReal     The actual start date of the stage.
     * @param fechaFinReal        The actual end date of the stage.
    */

    public Etapa(String nombre, int duracion, boolean aprobada, Calendar fechaInicioPlaneada, Calendar fechaFinPlaneada, Calendar fechaInicioReal, Calendar fechaFinReal) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.fechaInicioPlaneada = fechaInicioPlaneada;
        this.fechaFinPlaneada = (Calendar) fechaInicioPlaneada.clone();
        this.fechaFinPlaneada.add(Calendar.MONTH, duracion);
        this.fechaInicioReal = null;
        this.fechaFinReal = null;
        this.aprobada = false;
        this.budge = budge;
        this.budges = new Budge[50];

    }
    /**
     * Returns the capsule of the stage.
     *
     * @return The capsule of the stage.
    */

    public Budge getCapsula(){
        return budge;
    }

    /**
     * Returns the name of the stage.
     *
     * @return The name of the stage.
    */

    public String getNombre() {
        return nombre;
    }

    /**
     * Approves the given budge.
     *
     * @param budge The budge to approve.
    */

    public void aprobarBudge(Budge budge) {
        budge.setAprobada(true);
        budge.setFechaAprobacion(Calendar.getInstance().getTime());
    }

    /**
     * Sets the planned start date of the stage.
     *
     * @param fechaInicioPlaneada The planned start date of the stage.
    */
    
    public void setFechaInicioPlaneada(Calendar fechaInicioPlaneada) {
        this.fechaInicioPlaneada = fechaInicioPlaneada;
        this.fechaFinPlaneada = (Calendar) fechaInicioPlaneada.clone();
        this.fechaFinPlaneada.add(Calendar.MONTH, duracion);
    }

    /**
     * Sets the planned end date of the stage.
     *
     * @param fechaFinPlaneada The planned end date of the stage.
    */

    public void setFechaFinPlaneada(Calendar fechaFinPlaneada) {
        this.fechaFinPlaneada = fechaFinPlaneada;
    }

    /**
     * Sets the actual start date of the stage.
     *
     * @param fechaInicioReal The actual start date of the stage.
    */
    
    public void setFechaInicioReal(Calendar fechaInicioReal) {
        if (fechaInicioReal.after(fechaFinPlaneada)) {
            throw new IllegalArgumentException("La fecha de inicio real no puede ser posterior a la fecha de fin planeada");
        }
        this.fechaInicioReal = fechaInicioReal;
    }
    
    /**
     * Sets the real end date of the stage. Throws an IllegalArgumentException if the end date is before the real start date.
     * 
     * @param fechaFinReal the real end date of the stage
    */

    public void setFechaFinReal(Calendar fechaFinReal) {
        if (fechaFinReal.before(fechaInicioReal)) {
            throw new IllegalArgumentException("La fecha de fin real no puede ser anterior a la fecha de inicio real");
        }
        this.fechaFinReal = fechaFinReal;
    }
    /**
     * Sets the status of the stage.
     * 
     * @param status the status of the stage
    */

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns the real end date of the stage.
     * 
     * @return the real end date of the stage
    */
    
    public Calendar getFechaFinReal() {
        return fechaFinReal;
    }

    /**
     * Returns true if the stage has been approved.
     * 
     * @return true if the stage has been approved, false otherwise
    */
    
    public boolean isEtapaAprobada() {
        return aprobada;
    }

    /**
     * Sets whether the stage has been approved or not.
     * 
     * @param aprobada true if the stage has been approved, false otherwise
    */
    
    public void setEtapaAprobada(boolean aprobada) {
        this.aprobada = aprobada;
    }

    /**
     * Adds a budge to the stage.
     * 
     * @param budge the budge to add to the stage
    */

    public void addBudge(Budge budge) {
        this.budge = budge;
    }

    /**
     * Adds a collaborator to the stage.
     * 
     * @param collaborator the collaborator to add to the stage
    */

    public void addCollaborator(Collaborator collaborator) {
        this.collaborator = collaborator;
    }

    /**
     * Returns an array of the budges associated with the stage.
     * 
     * @return an array of the budges associated with the stage
    */
    
    public Budge[] getBudges() {
        return budges;
    }

    /**
     * Returns a String representation of the stage.
     * 
     * @return a String representation of the stage
    */

    public String toString() {
        String estado = fechaFinReal != null ? (aprobada ? "Aprobada" : "No aprobada") : "En progreso";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return nombre + ": " + duracion + " meses, fecha inicio planeada: " + formatter.format(fechaInicioPlaneada.getTime()) +
                ", fecha fin planeada: " + formatter.format(fechaFinPlaneada.getTime()) + ", fecha inicio real: " +
                (fechaInicioReal != null ? formatter.format(fechaInicioReal.getTime()) : "No iniciada") + ", fecha fin real: " +
                (fechaFinReal != null ? formatter.format(fechaFinReal.getTime()) : "No finalizada") + ", estado: " + estado;
    }
}
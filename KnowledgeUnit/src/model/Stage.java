/**
 * The Stage class represents a stage in a project. A stage has a name, duration, planned start and finish dates,
 * real start and finish dates, approval status, status, budget, and collaborator.
 */
package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Constructs a stage with a name, duration, planned start and finish dates, real start and finish dates,
 * and approval status.
 * @param nameStage the name of the stage
 * @param duration the duration of the stage in months
 * @param approved the approval status of the stage
 * @param dateStartPlanned the planned start date of the stage
 * @param dateFinishPlanned the planned finish date of the stage
 * @param dateStartReal the real start date of the stage
 * @param dateFinishReal the real finish date of the stage
 */

public class Stage {
    private String nameStage;
    private int duration;
    private Calendar dateStartPlanned;
    private Calendar dateFinishPlanned;
    private Calendar dateStartReal;
    private Calendar dateFinishReal;
    private boolean approved;
    private String status;
    private Budge budge;
    private Collaborator collaborator;

    public Stage(String nameStage, int duration, boolean approved, Calendar dateStartPlanned, Calendar dateFinishPlanned, Calendar dateStartReal, Calendar dateFinishReal) {
        this.nameStage = nameStage;
        this.duration = duration;
        this.dateStartPlanned = dateStartPlanned;
        this.dateFinishPlanned = (Calendar) dateStartPlanned.clone();
        this.dateFinishPlanned.add(Calendar.MONTH, duration);
        this.dateStartReal = null;
        this.dateFinishReal = null;
        this.approved= false;
    }

    /**
     * Returns the name of the stage.
     * @return the name of the stage
     */

    public String getNameStage() {
        return nameStage;
    }

    /**
     * Approves the budget for this stage.
     * @param budge the budget to approve
     */

    public void approveBudge(Budge budge) {
        budge.setApproved(true);
        budge.setDateAprobation(Calendar.getInstance().getTime());
    }

    /**
     * Sets the planned start date of the stage.
     * @param dateStartPlanned the planned start date of the stage
     */

    public void setDateStartPlanned(Calendar dateStartPlanned) {
        this.dateStartPlanned = dateStartPlanned;
        this.dateFinishPlanned= (Calendar) dateStartPlanned.clone();
        this.dateFinishPlanned.add(Calendar.MONTH, duration);
    }
    /**
     * Sets the planned finish date of the stage.
     * @param dateFinishPlanned the planned finish date of the stage
     */

    public void setDateFinishPlanned(Calendar dateFinishPlanned) {
        this.dateFinishPlanned = dateFinishPlanned;
    }

    /**
     * Sets the real start date of the stage.
     * @param dateStartReal the real start date of the stage
     * @throws IllegalArgumentException if the real start date is after the planned finish date
     */

    public void setDateStartReal(Calendar dateStartReal) {
        if (dateStartReal.after(dateFinishPlanned)) {
            throw new IllegalArgumentException("La fecha de inicio real no puede ser posterior a la fecha de fin planeada");
        }
        this.dateStartReal = dateStartReal;
    }

    /**
     * Set the real finish date for the stage.
     * 
     * @param dateFinishReal The real finish date for the stage.
     * @throws IllegalArgumentException if the real finish date is before the real start date.
     */
    public void setDateFinishReal(Calendar dateFinishReal) {
        if (dateFinishReal.before(dateStartReal)) {
            throw new IllegalArgumentException("La fecha de fin real no puede ser anterior a la fecha de inicio real");
        }
        this.dateFinishReal = dateFinishReal;
    }
    /**
     * Set the status of the stage.
     * 
     * @param status The status of the stage.
     */

    public void setStatus(String status) {
        this.status = status;
    }
       
    /**
     * Get the real finish date for the stage.
     * 
     * @return The real finish date for the stage.
     */

    public Calendar getDateFinishReal() {
        return dateFinishReal;
    }

    /**
     * Check if the stage is approved.
     * 
     * @return true if the stage is approved, false otherwise.
     */

    public boolean isStageApproved() {
        return approved;
    }

    /**
     * Set the approval status of the stage.
     * 
     * @param approved The approval status of the stage.
     */

    public void setStageApproved(boolean approved) {
        this.approved = approved;
    }

    /**
     * Add a budget to the stage.
     * 
     * @param budge The budget to add to the stage.
     */
    public void addBudge(Budge budge) {
        this.budge = budge;
    }

    /**
     * Add a collaborator to the stage.
     * 
     * @param collaborator The collaborator to add to the stage.
     */

    public void addCollaborator(Collaborator collaborator) {
        this.collaborator = collaborator;
    }

    
}
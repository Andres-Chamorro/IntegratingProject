package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import model.Stage;
import java.util.Scanner;
import model.Budge;
import model.Budge.TypeBudge;;

/**
 * The Proyecto class represents a project with several stages, each stage has a planned and a real date.
 * The class also includes the project's budget, the name of the client, the start and end dates of the project,
 * the project's managers (one for the green team and one for the client), and whether the project is finished or not.
 */

public class Project{
    private String projectName;
    private String clientName;
    private double budget;
    private Calendar startDate;
    private Calendar finishDate;
    private Stage[] stages;
    private int[] monthByStage;
    private int numStage;
    private String nameManagerGreen;
    private String phoneManagerGreen;
    private String nameManagerClient;
    private String phoneManagerClient;
    private boolean finish;
    private Collaborator[] colaboradores;
    private Budge budge;

    /**
     * Creates a new project with the given name, client, budget, start and end dates, and number of months per stage.
     * Also, creates the stages and assigns their planned dates.
     * 
     * @param projectName The name of the project.
     * @param clientName The name of the client.
     * @param budget The project's budget.
     * @param startDate The start date of the project.
     * @param finishDate The end date of the project.
     * @param monthByStage An array containing the number of months for each stage.
     * @param numStage The current stage number.
     */

    public Project(String projectName, String clientName, double budget, Calendar startDate,Calendar finishDate, int[] monthByStage) {
        this.projectName = projectName;
        this.clientName = clientName;
        this.budget = budget;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.stages = new Stage[6];
        this.numStage=numStage;
        this.monthByStage = monthByStage;

        Calendar dateFinishStage = (Calendar) startDate.clone();
        dateFinishStage.add(Calendar.MONTH, monthByStage[0]);
        this.stages[0] = new Stage("initiation", monthByStage[0], false, startDate, dateFinishStage, null, null);
        for (int i = 1; i < 6; i++) {
            dateFinishStage.add(Calendar.MONTH, monthByStage[i]);
            this.stages[i] = new Stage(getStageName(i), monthByStage[i], false, (Calendar) dateFinishStage.clone(), dateFinishStage, null, null);
        }
        this.numStage = 0;

        this.stages[0].setDateFinishPlanned(dateFinishStage);
        for (int i = 1; i < 6; i++) {
            dateFinishStage.add(Calendar.MONTH, monthByStage[i]);
            this.stages[i].setDateStartPlanned((Calendar) dateFinishStage.clone());
            this.stages[i].setDateFinishPlanned(dateFinishStage);
        }
    }

    /**
     * Returns the name of the stage with the given number.
     * 
     * @param numStage The number of the stage.
     * @return The name of the stage.
     */

    
    public String getStageName(int numStage) {
        String nameStage = "";
        switch (numStage) {
            case 1:
                nameStage = "Inicio";
                break;
            case 2:
                nameStage = "Analisis";
                break;
            case 3:
                nameStage = "Disenio";
                break;
            case 4:
                nameStage = "Ejecucion";
                break;
            case 5:
                nameStage = "Cierre";
                break;
            case 6:
                nameStage = "Seguimiento y Control";
        }
        return nameStage;
    }

    public int getNumStage() {
        return numStage;
    }
    

    public void culminateStage(int index) {
        stages[index].setDateFinishReal(Calendar.getInstance());
        stages[index].setStageApproved(true);
        System.out.println("--------------------------------------------");
        System.out.println("Etapa " + getStageName(index + 1) + " culminada el " + dateToString(stages[index].getDateFinishReal()));
    }
    

    /**
     * Returns an array of all the stages in this project.
     * 
     * @return An array of all the stages in this project
     */

    public Stage[] getStages() {
        return stages;
    }

    /**
     * Converts a Calendar object to a string in the format "dd/MM/yyyy".
     *  
     * @param date The Calendar object to convert
     * @return The string representation of the Calendar object
     */

    private String dateToString(Calendar date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date.getTime());
    }

    /**
     * Returns the name of this project.
     * 
     * @return The name of this project
     */

    public String getNameProject() {
        return projectName;
    }

    /**
     * Returns the current stage of this project.
     * 
     * @return The current stage of this project
     */

    public Stage getStageActual() {
        return stages[numStage];
    }

    /**
     * Sets whether this project is finished or not.
     * 
     * @param finish True if the project is finished, false otherwise
     */

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    /**
     * Starts the next stage of the project.
     * If the current stage is less than 5, sets the start date of the next stage to the current date
     * and prints a message indicating the start of the stage.
     * If the current stage is 5 or greater, prints a message indicating that the project has already finished.
     */
    public void startNextStage() {
        if (numStage < 5) {
            numStage++;
            stages[numStage].setDateStartReal(Calendar.getInstance());
            System.out.println("Comenzando la etapa " + getStageName(numStage+1));
        } else {
            // El proyecto ya ha terminado
            System.out.println("El proyecto ya ha finalizado.");
        }
    }
    /**
     * Searches for a stage with the given name in the array of stages.
     * If a stage with the given name is found, returns that stage.
     * If no stage with the given name is found, returns null.
     * @param name the name of the stage to search for
     * @return the stage with the given name, or null if no such stage is found
     */

    
	public Stage searchStage(String name) {
		for (Stage stage : stages) {
			if (stage.getNameStage().equalsIgnoreCase(name)) {
				return stage;
			}
		}
		return null; 
	}
    
}
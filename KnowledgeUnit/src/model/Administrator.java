package model;

import java.util.Calendar;
import java.util.Scanner;
import model.Project;
import model.Stage;
import model.Budge;
import model.Budge.TypeBudge;;
import model.Collaborator;

/**
 * The Administrator class represents an administrator in charge of managing projects and budgets.
 */

public class Administrator {

    public static final int SIZE_PROJECT = 10;
	public static final int SIZE_BUDGES = 50;
    private Project[] projects;
	private Budge[] budges; // arreglo de objetos Budge
	private Scanner reader; 
	private int numBudges; 
	private int option;

	/**
     * Constructs an administrator with an empty array of projects and budgets.
     */
    public Administrator() {
        projects = new Project[SIZE_PROJECT];
		budges = new Budge[SIZE_BUDGES];
		this.reader = new Scanner(System.in); 
		numBudges=0;
        option = 0;
    }

	/**
     * Adds a new project to the list of projects.
     * @param project the project to be added
     */
    public void addProject(Project project) {
        for (int i = 0; i < projects.length; i++) {
            if (projects[i] == null) {
                projects[i] = project;
                break;
            }
        }
    }

	/**
     * Adds a new budget to the current stage of the specified project.
     * @param nameProject the name of the project to which the budget will be added
     * @param idBudge the ID of the new budget
     * @param description the description of the new budget
     * @param tipoBudge the type of the new budget
     * @param lessonLearned the lesson learned from the new budget
     * @param nameCollaborator the name of the collaborator associated with the new budget
     * @param jobCompany the job title of the collaborator associated with the new budget
     */

	public void addBudgeToCurrentEtapa(String nameProject, String idBudge, String description, int tipoBudge, String lessonLearned, String nameCollaborator, String jobCompany) {
		Project project = findProjectByName(nameProject);
	
		if (project != null) {
			Stage currentStage = project.getStageActual();
	
			Collaborator newCollaborator = new Collaborator(nameCollaborator, jobCompany);
	
			TypeBudge type = null;
			switch (tipoBudge) {
				case 1:
					type = TypeBudge.TECHNICAL;
					break;
				case 2:
					type = TypeBudge.MANAGEMENT;
					break;
				case 3:
					type = TypeBudge.DOMAIN;
					break;
				case 4:
					type = TypeBudge.EXPERIENCES;
					break;
				default:
					System.out.println("invalid option");
					return;
			}
			Budge newBudge = new Budge(idBudge, description, type, lessonLearned, newCollaborator);
			newBudge.setApproved(false);
			Calendar dateActual = Calendar.getInstance();
			newBudge.setDateAprobation(dateActual.getTime());
	
			currentStage.addBudge(newBudge);
			currentStage.addCollaborator(newCollaborator);
			budges[numBudges] = newBudge;
			numBudges++;
	
			System.out.println("--------------------------------------------");
			System.out.println("'The capsule has been registered and is under review'");
		} else {
			System.out.println("--------------------------------------------");
			System.out.println("Project not found");
		}
	}

	/**
     * Returns the list of projects.
     * @return the list of projects
     */

    public Project[] getProjects() {
        return projects;
    }

	/**
     * Returns the list of budgets.
     * @return the list of budgets
     */

	public Budge[] getBudges() {
        return budges;
    }

	/**
     * Returns the number of budgets.
     * @return the number of budgets
     */

    public int getNumBudges() {
        return numBudges;
    }

	/**
     * Sets the list of projects to the specified list.
     * @param projects the new list of projects
     */

    public void setProjects(Project[] projects) {
        this.projects = projects;
    }

	/**
     * Searches for a project with the specified name.
     * @param name the name of the project to search for
     * @return the project with the specified name, or null if no such project exists
     */

    public Project findProjectByName(String name) {
        for (Project project : projects) {
            if (project != null && project.getNameProject().equalsIgnoreCase(name)) {
                return project;
            }
        }
        return null;
    }
	/**
     * Culminates the current stage of the specified project and starts the next stage.
     * @param nameProject the name of the project to be updated
     */

	public void culminateStage(String nameProject) {
		Project project = findProjectByName(nameProject);
		if (project == null) {
			System.out.println("project not found");
			return;
		}
	
		int numStage = project.getNumStage(); 
		project.culminateStage(numStage);
		project.startNextStage();
	}

	/**
     * Approves budgets for the specified project and stage.
     * @param nameProject the name of the project for which budgets will be approved
     */

	public void approvedBudges(String nameProject) {
		Project project = findProjectByName(nameProject);
		if (project == null) {
			System.out.println("The project does not exist.");
			return;
		}
		Stage stage = project.getStageActual();
		if (stage == null) {
			System.out.println("There are no stages registered in the project.");
			return;
		}
		
		System.out.println("Capsules registered in the stage " + stage.getNameStage());
		System.out.println("--------------------------------------------");
		for (int i = 0; i < numBudges; i++) {
			if (!budges[i].isApproved()) {
				System.out.println(i + 1 + ") " + budges[i].getId() + " - " + budges[i].getDescription());
			}
		}
		System.out.println("--------------------------------------------");
		System.out.println("Enter the number of the capsule you want to approve (0 to exit)");
		int opcion = reader.nextInt();
		while (opcion != 0) {
			if (opcion > 0 && opcion <= numBudges && !budges[opcion-1].isApproved()) {
				budges[opcion-1].setApproved(true);
				Calendar fechaActual = Calendar.getInstance();
				budges[opcion-1].setDateAprobation(fechaActual.getTime());
				System.out.println("--------------------------------------------");
				System.out.println("The Budge" + budges[opcion-1].getId() + " has been approved on " + fechaActual.get(Calendar.DAY_OF_MONTH) + "/" + (fechaActual.get(Calendar.MONTH) + 1) + "/" + fechaActual.get(Calendar.YEAR) + " " + fechaActual.get(Calendar.HOUR_OF_DAY) + ":" + fechaActual.get(Calendar.MINUTE) + ":" + fechaActual.get(Calendar.SECOND));
				System.out.println("--------------------------------------------");
			} else {
				System.out.println("Capsule does not exist or has already been approved.");
			}
			System.out.println("Enter the number of the capsule you want to approve (0 to exit)");
			opcion = reader.nextInt();
		}
	}

	/**
	 * Prints the approved and unpublished budges for a given project, allowing the user to publish them
	 * @param nameProject the name of the project
	 */
	public void publishedBudgesApproved(String nameProject) {
		Project project = findProjectByName(nameProject);
		if (project != null) {
			System.out.println("Capsules approved for the project " + project.getNameProject() + ":");
			System.out.println("--------------------------------------------");
			for (int i = 0; i < getNumBudges(); i++) {
				if (getBudges()[i].isApproved() && !getBudges()[i].isPublished()) {
					System.out.println(i + 1 + ") " + getBudges()[i].getId() + " - " + getBudges()[i].getDescription());
				}
			}
			System.out.println("--------------------------------------------");
			System.out.println("Enter the number of the capsule you want to publish (0 to exit)");
			int opcion = reader.nextInt();
			while (opcion != 0) {
				if (opcion > 0 && opcion <= getNumBudges() && getBudges()[opcion-1].isApproved() && !getBudges()[opcion-1].isPublished()) {
					getBudges()[opcion-1].setPublished(true);
					String url = "https://www.budges.com/budge/" + getBudges()[opcion-1].getId();
					System.out.println("--------------------------------------------");
					System.out.println("The budge " + getBudges()[opcion-1].getId() + " has been published at the following URL:");
					System.out.println(url);
					System.out.println("--------------------------------------------");
				} else {
					System.out.println("Capsule not existing or already published");
				}
				System.out.println("Enter the number of the capsule you want to publish (0 to exit)");
				opcion = reader.nextInt();
			}
		} else {
			System.out.println("Project not found");
		}
	}

	/**
	 * Generates a report of the types of budges registered for a given project
	 * @param nameProject the name of the project
	 */
	
	public void createBudgeReport(String nameProject) {
		Project project = findProjectByName(nameProject);
		if (project == null) {
			System.out.println("Project not found");
			return;
		}
		
		int technicalCount = 0;
		int managementCount = 0;
		int domainCount = 0;
		int experiencesCount = 0;
		
		Budge[] budges = getBudges();
		for (Budge budge : budges) {
			if (budge == null) {
				continue;
			}
			
			switch (budge.getBudge()) {
				case TECHNICAL:
					technicalCount++;
					break;
				case MANAGEMENT:
					managementCount++;
					break;
				case DOMAIN:
					domainCount++;
					break;
				case EXPERIENCES:
					experiencesCount++;
					break;
			}
		}
		System.out.println("--------------------------------------------");
		System.out.println("Report of capsules registered in the project " + project.getNameProject() + ":");
		System.out.println("Technical capsules: " + technicalCount);
		System.out.println("Management capsules: " + managementCount);
		System.out.println("Domain capsules: " + domainCount);
		System.out.println("Experiences capsules: " + experiencesCount);
	}

	/**
	 * Searches for a collaborator by their name and prints out their associated budges.
	 * @param nameCollaborator the name of the collaborator to search for
	 */
	public void searchCollaborator(String nameCollaborator) {
		if (budges == null) {
			System.out.println("No budges created.");
			return;
		}
		
		boolean found = false;
        System.out.println("Contributor Budges " + nameCollaborator + ":");
        System.out.println("-----------------------------------------------------");
        for (Budge budge : budges) {
           if (budge != null && budge.getCreator() != null && budge.getCreator().getNameCollaborator().equalsIgnoreCase(nameCollaborator)) {
               found = true;
               System.out.println("- " + budge.getId() + " - " + budge.getDescription());
            }
        }
        System.out.println("-----------------------------------------------------");
        if (!found) {
           System.out.println("Contributor not found " + nameCollaborator + " in no project.");
        }
	}
	/**
	 * Finds the project with the most associated budges and returns its name.
	 * @return the name of the project with the most associated budges, or null if there are no budges
	 */
	public String projectWithMoreBudges() {
		String projectMoreBudges = null;
		int maxBudges = 0;
		for (Project project : projects) {
			int numBudge = 0;
			for (Budge budge : getBudges()) {
				if (budge != null) {
					numBudge++;
				}
			}
			if (numBudge > maxBudges) {
				maxBudges = numBudge;
				projectMoreBudges = project.getNameProject();
			}
		}
		if (maxBudges == 0) {
			return null;
		}
		return projectMoreBudges;
	}

	/**
	 * Displays the lessons learned in a specific project and stage.
	 * @param project the project to search for lessons learned.
	 * @param stage the stage within the project to search for lessons learned.
	 */

	public void informationLessonsLearning(Project project, Stage stage) {
		System.out.println("Lecciones aprendidas del proyecto " + project.getNameProject() + " en la etapa " + stage.getNameStage() + ":");
		boolean registerBudges = false;
		for (Budge budge : getBudges()) {
			// Verificar si la cápsula está en la etapa especificada
			if (budge != null && budge.getStage() != null && budge.getStage().equals(stage)) {
				System.out.println("- " + budge.getLearning());
				registerBudges = true;
			}
		}
		if (!registerBudges) {
			System.out.println("No hay capsulas registradas en la etapa " + stage.getNameStage() + " del proyecto " + project.getNameProject() + ".");
		}
	}

	/**
	 * Search for budges in a specific project that contain a keyword.
	 * @param nameProject the name of the project to search for budges.
	 * @param wordKey the keyword to search for in the budges.
	 * 
	 */
	public void searchBudges(String nameProject, String wordKey) {
		Project project = findProjectByName(nameProject);
		if (project != null) {
			for (Budge budge : getBudges()) {
				if (budge.getDescription().contains(wordKey)) {
					System.out.println("Budge: " + budge.getDescription());
					System.out.println("Lesson learned: " + budge.getLearning());
				}
			}
		} else {
			System.out.println("No se encontro ningun proyecto con el nombre '" + nameProject + "'.");
		}
	}
}
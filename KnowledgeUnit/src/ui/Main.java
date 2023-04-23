package ui; 

import java.util.Scanner;
import java.text.SimpleDateFormat;
import model.Project;
import model.Stage;
import model.Budge;
import model.Collaborator;
import java.util.Calendar;
import model.Administrator;
/**
 * The Main class represents the main entry point for the project management system.
 * It handles the user interface and executes the requested options.
 */
public class Main{

    private Administrator controller;
    private Scanner reader;  
    
    /**
     * Constructs a new instance of the Main class, initializing the scanner and the controller.
     */

    public Main(){
        this.reader = new Scanner(System.in); 
        controller = new Administrator();
    }
    /**
     * The main method that initializes the Main class and executes the user interface loop.
     * @param args An array of command-line arguments for the main method.
     */

    public static void main(String[] args){
        Main view = new Main(); 
        int option = 0; 

        do{
            view.menu(); 
            option = view.validateIntegerInput(); 
            view.executeOption(option);

        }while(option != 11);


        view.reader.close();
    }

    /**
     * Prints the main menu options for the user to choose from.
     */

    public void menu(){
        System.out.println("--------------------------------------------");
        System.out.println("Menu:");
        System.out.println("1. Create a project");
        System.out.println("2. Complete stage of a project");
        System.out.println("3. Registrar capsula");
        System.out.println("4. Approve capsule");
        System.out.println("5. Published capsule");
        System.out.println("6. Consult capsules by type");
        System.out.println("7. Check list of lessons learned");
        System.out.println("8. Consult project with more capsules");
        System.out.println("9. Consult creation of capsules by collaborator");
        System.out.println("10. Consult descriptions and lessons learned by text string");
        System.out.println("11. Exit");
        System.out.println("--------------------------------------------");
        System.out.println("Write the number of the option you want to perform:"); 

    }
    /**
     * Executes the selected option by the user.
     * @param option The integer representing the selected option.
     */

    public void executeOption(int option){
        switch (option) {
            case 1:
                registerProject();
                break;

            case 2:
                culminateStage();
                break;

            case 3: 
                registerBudge();
                break;
            case 4:
                approvedBudges();
                break;
            case 5:
                publishedBudge();
                break;
            case 6:
                budgesByType();
                break;
            case 7:
                informationLessonsLearning();
                break;
            case 8:
                searchProjectMoreBudges();
                break;
            case 9:
                searchCollaboratorBudge();
                break;
            case 10:
                searchCapsulesByKeyword();
                break;
            case 11:
                System.out.println("'Exit.'"); 
                break; 
            default:
                System.out.println("'Invalid Option!!'"); 
                break; 
        }
    }

    /**
     * Validates the user input as an integer value.
     * @return The integer value entered by the user, or -1 if the input is not valid.
     */

    public int validateIntegerInput(){
        int option = 0; 
        if(reader.hasNextInt()){
            option = reader.nextInt(); 
        }
        else{
            reader.nextLine();// limpiar el scanner  
            option=-1;
            System.out.println("Enter an integer value"); 
        }
        return option; 
    }

    /**
     * Prompts the user to enter the details of a new project and adds it to the system.
     */

    public void registerProject(){
        reader.nextLine();
        System.out.println("--------------------------------------------");
        System.out.println("Enter project data:");
        System.out.println("--------------------------------------------");
        System.out.println("Project name:");
        String projectName = reader.nextLine();
    
        System.out.println("Customer name:");
        String clientName = reader.nextLine();

        double budget = 0;
        while (true) {
            System.out.println("Project budget value:");
            if (reader.hasNextDouble()) {
                budget = reader.nextDouble();
                break;
            } else {
                System.out.println("--------------------------------------------");
                System.out.println("Please enter a valid numeric value.");
                reader.next(); 
                System.out.println("--------------------------------------------");
            }
        }
        reader.nextLine();
        System.out.println("Green manager name for this project:");
        String greenManagerName = reader.nextLine();
    
        System.out.println("Green manager cell number for this project:");
        String greenManagerPhone = reader.nextLine();
    
        System.out.println("Name of client manager for this project:");
        String clientManagerName = reader.nextLine();
    
        System.out.println("Client manager cell number for this project:");
        String clientManagerPhone = reader.nextLine();
        System.out.println("--------------------------------------------");
        System.out.println("Enter the months of duration of each stage:");
        int[] monthByStage = new int[6];
        String[] nameStage = {"Initiation", "Analysis", "Design", "Execution", "Closing and follow up", "Project control"};
        for (int i = 0; i < 6; i++) {
            boolean validInput = false;
            do {
                System.out.print(nameStage[i] + ": ");
                if (reader.hasNextInt()) {
                    monthByStage[i] = reader.nextInt();
                    validInput = true;
                } else {
                    System.out.println("You must enter a valid numeric value.");
                    reader.nextLine(); 
                }
            } while (!validInput);
        }

        Calendar dateStart = Calendar.getInstance();
        Calendar dateFinish = (Calendar) dateStart.clone();
        int totalMonth = 0;
        for (int month : monthByStage) {
            totalMonth += month;
        }
        dateFinish.add(Calendar.MONTH, totalMonth);

        Project newProject = new Project(projectName, clientName, budget, dateStart, dateFinish, monthByStage);
        controller.addProject(newProject);

        System.out.println("--------------------------------------------");
        System.out.println("Project successfully registered.");

        System.out.println("--------------------------------------------");
        System.out.println("Project start date: " + dateStart.getTime());
        System.out.println("Project finish date: " + dateFinish.getTime());
    }

    /**
     * Method to culminate a project stage.
     * It prompts the user to enter the project name,
     * then passes it to the controller to culminate the stage.
     */

    public void culminateStage() {
        reader.nextLine();
        System.out.println("--------------------------------------------");
        System.out.print("Enter the project name: ");
        String nameProject = reader.nextLine();

        controller.culminateStage(nameProject);
    }

    /**
     * Method to register a new project budge.
     * It prompts the user to enter information about the budge,
     * validates user input, and then passes it to the controller to be added to the current project stage
     */

    public void registerBudge() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter the project name:");
        String nameProject= reader.nextLine();
        System.out.println("Enter the capsule identifier:");
        String idBudge = reader.nextLine();
        System.out.println("Enter the description of the capsule:");
        String description = reader.nextLine();

        while (!description.matches(".*#\\w+#.*")) {
            System.out.println("The description must include at least one keyword. Enter the description of the capsule:");
            description = reader.nextLine();
        }

        int typeBudge = 0;        
        System.out.println("--------------------------------------------");
        System.out.println("Enter the type of capsule:");
        System.out.println("1. Technical");
        System.out.println("2. Management");
        System.out.println("3. Domain");
        System.out.println("4. Experiences");
        
        boolean validType = false;
        while (!validType) {
            if (reader.hasNextInt()) {
                typeBudge = reader.nextInt();
                reader.nextLine(); // Limpiar el buffer del scanner
        
                if (typeBudge >= 1 && typeBudge <= 4) {
                    validType = true;
                } else {
                    System.out.println("Invalid option, please enter a number between 1 and 4:");
                }
            } else {
                reader.nextLine(); // Limpiar el buffer del scanner
                System.out.println("Invalid option, please enter a number:");
            }
        }
        System.out.println("--------------------------------------------");
        System.out.println("Enter the lesson learned within the stage:");
        String lessonLearned = reader.nextLine();

        while (!lessonLearned.matches(".*#\\w+#.*")) {
            System.out.println("The lesson learned must include at least one keyword. Enter the lesson learned from the capsule:");
            lessonLearned = reader.nextLine();
        }
        System.out.println("Enter the name of the collaborator:");
        String nameCollaborator = reader.nextLine();
        System.out.println("Enter the position of the collaborator:");
        String jobCompany = reader.nextLine();
    
        controller.addBudgeToCurrentEtapa(nameProject, idBudge, description, typeBudge, lessonLearned, nameCollaborator, jobCompany);
    }

    /**
     * This method prompts the user to enter a project name, and calls the 'approvedBudges' method of the controller,
     * passing the project name as a parameter.
     */

    public void approvedBudges() {
        reader.nextLine();
        System.out.print("Enter the project name: ");
        String nameProject = reader.nextLine();

        controller.approvedBudges(nameProject);
    }

    /**
     * This method prompts the user to enter a project name, and calls the 'publishedBudgesApproved' method of the controller,
     * passing the project name as a parameter.
     */

    public void publishedBudge() {
        reader.nextLine();
        System.out.print("Enter the project name: ");
        String nameProject = reader.nextLine();

        controller.publishedBudgesApproved(nameProject);
    }
    /**
     * This method prompts the user to enter a project name, and calls the 'createBudgeReport' method of the controller,
     * passing the project name as a parameter.
     */

    public void budgesByType() {
        reader.nextLine();
        System.out.print("Enter the project name: ");
        String nameProject = reader.nextLine();

        controller.createBudgeReport(nameProject);
    }
    /**
     * This method prompts the user to enter a collaborator's name, and calls the 'searchCollaborator' method of the controller,
     * passing the collaborator's name as a parameter.
     */

    public void searchCollaboratorBudge() {
        reader.nextLine();
        System.out.print("Enter the name of the collaborator: ");
        String nameCollaborator = reader.nextLine();

        controller.searchCollaborator(nameCollaborator);
    }

    /**
     * This method calls the 'projectWithMoreBudges' method of the controller to find the project with the most capsules.
     * If a project with capsules is found, the method displays its name, otherwise it prints a message indicating that there are no projects with capsules.
     */

    public void searchProjectMoreBudges(){
        String projectMoreBudges = controller.projectWithMoreBudges();
        if (projectMoreBudges != null) {
            System.out.println("--------------------------------------------");
            System.out.println("The project with more capsules is: " + projectMoreBudges);
        } else {
            System.out.println("There are no projects with capsules.");
        }
    }
    /**
     * This method prompts the user to enter a project name and stage name, and calls the 'informationLessonsLearning' method of the controller,
     * passing the corresponding project and stage objects as parameters.
     */

    public void informationLessonsLearning() {
        reader.nextLine();
        System.out.println("Enter the project name: ");
        String nameProject = reader.nextLine();
        Project project = controller.findProjectByName(nameProject);
        if (project == null) {
            System.out.println("Not project exist");
            return;
        }
        System.out.println("Enter the name of the stage: ");
        String nameStage = reader.nextLine();
        Stage stage = project.searchStage(nameStage);
        if (stage == null) {
            System.out.println("The stage is not active in this project");
            return;
        }
        controller.informationLessonsLearning(project, stage);
    }

    /**
     * This method prompts the user to enter a project name and a keyword, and calls the 'searchBudges' method of the controller,
     * passing the project name and keyword as parameters.
     */

    public void searchCapsulesByKeyword() {
        reader.nextLine();
        System.out.println("Enter the project name:");
        String projectName = reader.nextLine();
        System.out.println("enter keyword:");
        String keyword = reader.nextLine();
        controller.searchBudges(projectName, keyword);
    }
}

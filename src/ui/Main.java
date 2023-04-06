package ui;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import model.Proyecto;
import model.Gerente;
import model.Etapa;
import model.Budge;
import model.Budge.TipoCapsula;
import model.Collaborator;
import java.util.Calendar;

/**
 * The Main class represents the main class of the program that allows users to
 * create, manage and publish projects and their budgets.
 */

public class Main {
    private Scanner reader;
    static int numProyectos;
    static int numBudges;
    private Proyecto[] proyectos; 
    private Budge[] budges;
    private Proyecto proyectoActual; // Declaring proyectoActual as a class member

    /**
     * Constructs a new Main object, initializes its fields, and creates a new Administrator object.
     */

    public Main(){
        this.reader = new Scanner(System.in);
        proyectos = new Proyecto[10];       // inicializamos el arreglo de proyectos
        budges = new Budge[50];
        Proyecto proyectoActual = null;

    }

    /**
     * The main method that runs the program and displays the menu options to the user.
     * It repeatedly prompts the user to input an option number until they choose to exit.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main view = new Main();
        numProyectos=0;
        numBudges=0;
        int option = 0;
        int contadorProyectos = 0; // variable para contar los proyectos creados
        
        do {
            view.menu();
            option = view.validateIntegerInput(); 
            view.executeOption(option);
        
        } while (option != 6);
    }

    /**
     * Displays the menu options to the user.
     */

    public void menu(){
        System.out.println("--------------------------------------------");
        System.out.println("Menu:");
        System.out.println("1. Crear un proyecto");
        System.out.println("2. Culminar etapa de un proyecto");
        System.out.println("3. Registrar capsula");
        System.out.println("4. Aprobar capsula");
        System.out.println("5. Publicar capsula");
        System.out.println("6. Salir");
        System.out.println("--------------------------------------------");
        System.out.println("Escriba el numero de la opcion que desea realizar:");
        
    }

    /**
     * Prompts the user to input an integer and validates that the input is indeed an integer.
     * 
     * @return the integer input by the user, or -1 if the input was not an integer
     */

    public int validateIntegerInput(){
        int option = 0; 
        if(reader.hasNextInt()){
            option = reader.nextInt(); 
            System.out.println("--------------------------------------------");
        }
        else{
            reader.nextLine();// limpiar el scanner 
            option = -1; 
            System.out.println("Ingrese un valor entero."); 
        }
        return option; 
    }

    /**
     * Executes the menu option chosen by the user based on the input integer.
     * 
     * @param option the integer input by the user representing the menu option
     */

    public void executeOption(int option){
        switch (option) {
            case 1:
                registerProject();
                System.out.println("");
                System.out.println("'Proyecto registrado correctamente'");
                break;
            case 2:
                if (proyectoActual != null){
                    proyectos[numProyectos - 1].avanzarEtapa();
                }else{
                    System.out.println("No hay ningún proyecto seleccionado.");
                } 
                break;
            case 3:
                if (proyectoActual!=null){
                    createBudge(proyectoActual.getEtapaActual());
                }else{
                    System.out.println("No hay ningún proyecto seleccionado.");
                }
                break;
            case 4:   
               if (proyectoActual != null){
                   Etapa etapaActual = proyectoActual.getEtapaActual();
                   aprobarBudge(etapaActual);
                }else{
                   System.out.println("No hay ningún proyecto seleccionado.");
                } 
                break; 
            case 5:
                if (proyectoActual != null){
                   Etapa etapaActual = proyectoActual.getEtapaActual();
                   publicarBudge(etapaActual);
                }else{
                   System.out.println("No hay ningún proyecto seleccionado.");
                } 
                break;
            case 6:
                System.out.println("Exit."); 
                System.out.println("--------------------------------------------");
                break;
            default:
                System.out.println("Invalit Option!!"); 
                break;
                
        }
    }

    /**
     * Creates a new project and adds it to the array of projects. The user is prompted to enter the
     * name of the project, the name of the client, the project budget, and the duration of each stage.
     * The start and end dates of the project are calculated based on the duration of each stage. If the
     * number of projects in the array of projects is less than 10, the project is added to the array and
     * becomes the current project. Otherwise, an error message is displayed.
     */

    public void registerProject() {
        if (numProyectos < 10) {
            System.out.println("Ingrese el nombre del proyecto:");
            String nombreProyecto = reader.nextLine();
            reader.nextLine();
            System.out.println("Ingrese el nombre del cliente:");
            String cliente = reader.nextLine();
            System.out.println("Ingrese el presupuesto del proyecto:");
            double presupuesto = reader.nextDouble();
    
            // Pide al usuario los meses de duración de cada etapa
            System.out.println("Ingrese los meses de duracion de cada etapa:");
            int[] mesesPorEtapa = new int[6];
            String[] nombresEtapas = {"Inicio", "Analisis", "Diseno", "Ejecucion", "Cierre y seguimiento", "Control del proyecto"};
            for (int i = 0; i < 6; i++) {
                System.out.print(nombresEtapas[i] + ": ");
                mesesPorEtapa[i] = reader.nextInt();
            }
    
            Calendar fechaInicio = Calendar.getInstance();
            Calendar fechaFin = (Calendar) fechaInicio.clone();
            int totalMeses = 0;
            for (int meses : mesesPorEtapa) {
                totalMeses += meses;
            }
            fechaFin.add(Calendar.MONTH, totalMeses);
    
            Proyecto proyecto = new Proyecto(nombreProyecto, cliente, presupuesto, fechaInicio, fechaFin, mesesPorEtapa, 0);
    
            // Agregar el proyecto al arreglo de proyectos
            proyectos[numProyectos] = proyecto;
            numProyectos++;

            gerentesProject();

            proyectoActual = proyecto;
            System.out.println("--------------------------------------------");
            System.out.println("Fecha de inicio del proyecto: " + fechaInicio.getTime());
            System.out.println("Fecha de fin del proyecto: " + fechaFin.getTime());
            System.out.println("--------------------------------------------");
        } else {
            System.out.println("'No se pueden crear mas proyectos'");
        }
    }

    /**
     * Prompts the user to enter the name and phone number of the Green manager and the name and phone
     * number of the client manager for the current project. Creates new Gerente objects for each
     * manager and sets them as the Green manager and client manager for the current project.
     */

    public void gerentesProject() {
        System.out.println("Ingrese el nombre del gerente del Green:");
        String nombreGreen = reader.nextLine();
        reader.nextLine();
        System.out.println("Ingrese el numero de telefono del gerente del Green:");
        String telefonoGreen = reader.next();
        reader.nextLine();
    
        Gerente gerenteGreen = new Gerente(nombreGreen, telefonoGreen, null, null);
    
        System.out.println("Ingrese el nombre del gerente del cliente:");
        String nombreCliente = reader.nextLine();
        System.out.println("Ingrese el numero de telefono del gerente del cliente:");
        String telefonoCliente = reader.next();
    
        Gerente gerenteCliente = new Gerente(null, null, nombreCliente, telefonoCliente);

        proyectos[numProyectos-1].setGerenteGreen(gerenteGreen);
        proyectos[numProyectos-1].setGerenteCliente(gerenteCliente);
    }
   
    public void createBudge(Etapa currentEtapa) {  
        reader.nextLine();  
        if (numBudges < budges.length) {
            System.out.println("Ingrese el identificador de la capsula");
            String idBudge = reader.nextLine();
            System.out.println("Ingrese la descripcion de la capsula");
            String description = reader.nextLine();

            System.out.println("Ingrese el tipo de capsula:");
			System.out.println("1. Tecnico");
			System.out.println("2. Gestion");
			System.out.println("3. Dominio");
            System.out.println("4.Experiencias");
		
			int tipoBudge = reader.nextInt();
		
			TipoCapsula type = null;
		
			switch (tipoBudge) {
				case 1:
					type = TipoCapsula.TECNICO;
					break;
				case 2:
					type = TipoCapsula.GESTION;
					break;
				case 3:
					type = TipoCapsula.DOMINIO;
					break;
                case 4:
                    type = TipoCapsula.EXPERIENCIAS;
				default:
					System.out.println("Opción inválida");
			}
            reader.nextLine();
            System.out.println("Ingrese la leccion aprendida dentro de la etapa");
            String lessonLearned = reader.nextLine();
            System.out.println("Ingrese su nombre (Colaborador):");
            String nameCollaborator = reader.nextLine();
            System.out.println("Ingrese su cargo (Colaborador):");
            String jobCompany = reader.nextLine();
    
            Collaborator newCollaborator = new Collaborator(nameCollaborator, jobCompany);
    
            Budge newBudge = new Budge(idBudge, description, type, lessonLearned, newCollaborator);
    
            newBudge.setAprobada(false);
    
            Calendar fechaActual = Calendar.getInstance();
            newBudge.setFechaAprobacion(fechaActual.getTime());
    
            budges[numBudges] = newBudge;
            numBudges++;
    
            currentEtapa.addBudge(newBudge);
            currentEtapa.addCollaborator(newCollaborator);
    
            System.out.println("--------------------------------------------");
            System.out.println("'La capsula ha sido registrada y se encuentra en revision'");
            System.out.println("--------------------------------------------");
        } else {
            System.out.println("No se pueden crear mas capsulas");
        }
    }
    
    /**
     * Creates a new budge for the current stage with the given information and adds it to the budget array.
     * Also adds the collaborator to the current stage.
     *
     * @param currentEtapa the current stage to add the new budge and collaborator to.
     */
    public void aprobarBudge(Etapa etapa) {
        System.out.println("Capsulas registradas en la etapa " + etapa.getNombre());
        System.out.println("--------------------------------------------");
        for (int i = 0; i < numBudges; i++) {
            if (!budges[i].isAprobada()) {
                System.out.println(i + 1 + ") " + budges[i].getIdentificador() + " - " + budges[i].getDescripcion());
            }
        }
        System.out.println("--------------------------------------------");
        System.out.println("Ingrese el número de la cápsula que desea aprobar (0 para salir)");
        int opcion = reader.nextInt();
        while (opcion != 0) {
            if (opcion > 0 && opcion <= numBudges && !budges[opcion-1].isAprobada()) {
                budges[opcion-1].setAprobada(true);
                Calendar fechaActual = Calendar.getInstance();
                budges[opcion-1].setFechaAprobacion(fechaActual.getTime());
                System.out.println("--------------------------------------------");
                System.out.println("La cápsula " + budges[opcion-1].getIdentificador() + " ha sido aprobada el " + fechaActual.get(Calendar.DAY_OF_MONTH) + "/" + (fechaActual.get(Calendar.MONTH) + 1) + "/" + fechaActual.get(Calendar.YEAR) + " " + fechaActual.get(Calendar.HOUR_OF_DAY) + ":" + fechaActual.get(Calendar.MINUTE) + ":" + fechaActual.get(Calendar.SECOND));
                System.out.println("--------------------------------------------");
            } else {
                System.out.println("Cápsula no existente");
            }
            System.out.println("Ingrese el número de la cápsula que desea aprobar (0 para salir)");
            opcion = reader.nextInt();
        }
    }

    /**
     * Displays a list of approved capsules and allows the user to publish one of them
     * by entering its number. The published capsule's URL is then displayed.
     * If a valid badge number is entered and it has not been already published, the badge is marked as published
     * @param etapa the stage in which the capsules were approved
     */

    public void publicarBudge(Etapa etapa) {
        System.out.println("Capsulas aprobadas:");
        System.out.println("--------------------------------------------");
        for (int i = 0; i < numBudges; i++) {
            if (budges[i].isAprobada() && !budges[i].isPublicada()) {
                System.out.println(i + 1 + ") " + budges[i].getIdentificador() + " - " + budges[i].getDescripcion());
            }
        }
        System.out.println("--------------------------------------------");
        System.out.println("Ingrese el número de la cápsula que desea publicar (0 para salir)");
        int opcion = reader.nextInt();
        while (opcion != 0) {
            if (opcion > 0 && opcion <= numBudges && budges[opcion-1].isAprobada() && !budges[opcion-1].isPublicada()) {
                budges[opcion-1].setPublicada(true);
                String url = "https://www.capsulas.com/capsula/" + budges[opcion-1].getIdentificador();
                System.out.println("--------------------------------------------");
                System.out.println("La cápsula " + budges[opcion-1].getIdentificador() + " ha sido publicada en la siguiente URL:");
                System.out.println(url);
                System.out.println("--------------------------------------------");
            } else {
                System.out.println("Cápsula no existente o ya publicada");
            }
            System.out.println("Ingrese el número de la cápsula que desea publicar (0 para salir)");
            opcion = reader.nextInt();
        }
    }
}
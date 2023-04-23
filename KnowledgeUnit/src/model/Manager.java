package model;

import java.util.Scanner;

/**
 * Represents a manager who works for the company. This class contains information
 * about the manager's name, phone number, and the name and phone number of the manager's
 * client.
 */
public class Manager {
    private String nameGreen;
    private String phoneGreen;
    private String nameManagerClient;
    private String phoneManagerClient;
    private Gerente gerenteGreen; // Declare gerenteGreen variable
    private Gerente gerenteCliente; // Declare gerenteCliente variable

    /**
     * Creates a new Gerente object with the specified name, phone number, and client information.
     *
     * @param nameGreen the name of the manager
     * @param phoneGreen the phone number of the manager
     * @param nameManagerClient the name of the manager's client
     * @param phoneManagerClient the phone number of the manager's client
     */
    public Manager(String nameGreen, String phoneGreen, String nameManagerClient, String phoneManagerClient) {
        this.nameGreen = nameGreen;
        this.phoneGreen = phoneGreen;
        this.nameManagerClient = nameManagerClient;
        this.phoneManagerClient = phoneManagerClient;
    }

    /**
     * Returns the name of the manager.
     *
     * @return the name of the manager
     */
    public String getNameGreen() {
        return nameGreen;
    }

    /**
     * Returns the phone number of the manager.
     *
     * @return the phone number of the manager
     */
    public String getPhoneGreen() {
        return phoneGreen;
    }

    /**
     * Returns the name of the manager's client.
     *
     * @return the name of the manager's client
     */
    public String getNameManagerClient() {
        return nameManagerClient;
    }

    /**
     * Returns the phone number of the manager's client.
     *
     * @return the phone number of the manager's client
     */
    public String getPhoneManagerClient() {
        return phoneManagerClient;
    }
}
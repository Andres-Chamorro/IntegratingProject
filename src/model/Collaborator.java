package model;

/**
 * This class represents a collaborator who works at the company.
 * It contains their name and job position at the company.
 */
public class Collaborator {
    private String nameCollaborator;
    private String jobCompany;

    /**
     * Creates a new collaborator with the specified name and job position.
     *
     * @param nameCollaborator the name of the collaborator
     * @param jobCompany the job position of the collaborator at the company
     */
    public Collaborator(String nameCollaborator, String jobCompany) {
        this.nameCollaborator = nameCollaborator;
        this.jobCompany = jobCompany;
    }

    /**
     * Returns the name of the collaborator.
     *
     * @return the name of the collaborator
     */
    public String getNameCollaborator() {
        return nameCollaborator;
    }

    /**
     * Returns the job position of the collaborator at the company.
     *
     * @return the job position of the collaborator at the company
     */
    public String getJobCompanyCollaborator() {
        return jobCompany;
    }
}
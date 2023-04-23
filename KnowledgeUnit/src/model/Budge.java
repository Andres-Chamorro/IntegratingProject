package model;

import java.util.Calendar;
import java.util.Date;
import model.Collaborator;
import model.Project;
import model.Stage;

/**
 * The Budge class represents a budget item or a capsule.
 * It contains information such as its identifier, description, type, lesson learned, creator,
 * creation date, approval date, whether it is approved or not, hashtags, stage, and whether it is published or not.
 */

public class Budge {
    private String id;
    private String description;
    private TypeBudge type;
    private String lessonLearned;
    private String[] hashtags;
    private Calendar dateCreation;
    private Calendar dateAprobation;
    private boolean approved;
    private Collaborator creator;
    private boolean published;
    private Project project;
    private Stage stage;

    /**
     * Constructs a Budge object with the specified identifier, description, type, lesson learned, and creator.
     *
     * @param id the identifier of this Budge.
     * @param description the description of this Budge.
     * @param type the type of this Budge.
     * @param lessonLearned the lesson learned of this Budge.
     * @param creator the creator of this Budge.
    */

    public Budge(String id, String description, TypeBudge type, String lessonLearned, Collaborator creator) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.lessonLearned = lessonLearned;
        this.dateCreation = Calendar.getInstance();
        this.creator = creator;
    }

    /**
     * The enumeration of the different types of a Budge.
    */

    public enum TypeBudge {
        TECHNICAL,
        MANAGEMENT,
        DOMAIN,
        EXPERIENCES
    }


    /**
     * Returns the identifier of this Budge.
     *
     * @return the identifier of this Budge.
    */
    
    public String getId() {
        return id;
    }

    /**
     * Returns the description of this Budge.
     *
     * @return the description of this Budge.
    */

    public String getDescription() {
        return description;
    }
    
    /**
     * Returns the type of this Budge.
     *
     * @return the type of this Budge.
     */
    public TypeBudge getBudge(){
        return type;
    }
    
    /**
     * Returns the lesson learned of this Budge.
     *
     * @return the lesson learned of this Budge.
     */
    public String getLearning() {
        return lessonLearned;
    }
    
    /**
     * Returns the approval status of this Budge.
     *
     * @return true if this Budge is approved, false otherwise.
     */
    public boolean isApproved(){
        return approved;
    }

    /**
     * Sets the approval status of the capsule.
     * 
     * @param aprobada the approval status of the capsule
     */
    public void setApproved(boolean approved) {
        this.approved = approved;
       if (approved) {
           this.dateAprobation = Calendar.getInstance();
        } else {
           this.dateAprobation = null;
       }
    }

  /**
 * Approve the capsule.
 * 
 * Note: This method changes the approval status of the capsule to true.
 */
  public void approved() {
    this.approved = true;
  }

/**
 * Obtains the approval date of the capsule.
 * 
 * @return the approval date of the capsule
 */
   public Calendar getDateAprobation() {
      return dateAprobation;   
    }

/**
 * Gets the date of creation of the capsule.
 * 
 * @return the date of creation of the capsule
 */
  public Calendar getDateCreation() {
    return dateCreation;
  }

/**
 * Sets the approval date of the capsule.
 * 
 * @param dateAprobation the approval date of the capsule
 */
  public void setDateAprobation(Date dateAprobation) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(dateAprobation);
    this.dateAprobation = cal;
  }

/**
 * gets the creator of the capsule.
 * 
 * @return the creator of the capsule
 */
  public Collaborator getCreator() {
    return creator;
  }

/**
 * Check if the capsule has been published.
 * 
 * @return true if the capsule has been published, otherwise false
 */
  public boolean isPublished() {
    return published;
  }

/**
 * Sets whether the capsule has been published.
 * 
 * @param publicada the publication status of the capsule
 */
  public void setPublished(boolean published) {
    this.published = published;
  }

  /**
   * Returns the current stage of the project.
   * @return the current stage of the project
   */

  public Stage getStage() {
    return stage;
  }
  /**
   * Returns the project associated with this stage.
   * @return the project associated with this stage
   */

  public Project getProject() {
    return project;
  }


}
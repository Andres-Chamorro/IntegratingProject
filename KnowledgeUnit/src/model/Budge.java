package model;
import java.util.Calendar;
import java.util.Date;
import model.Collaborator;
import model.Proyecto;
import model.Etapa;

/**
 * The Budge class represents a budget item or a capsule.
 * It contains information such as its identifier, description, type, lesson learned, creator,
 * creation date, approval date, whether it is approved or not, hashtags, stage, and whether it is published or not.
 */

public class Budge {
    private String id;
    private String description;
    private TipoCapsula type;
    private String lessonLearned;
    private String etapa;
    private String[] hashtags;
    private Calendar fechaCreacion;
    private Calendar fechaAprobacion;
    private boolean aprobada;
    private Collaborator creator;
    private boolean publicada;

    /**
     * Constructs a Budge object with the specified identifier, description, type, lesson learned, and creator.
     *
     * @param id the identifier of this Budge.
     * @param description the description of this Budge.
     * @param type the type of this Budge.
     * @param lessonLearned the lesson learned of this Budge.
     * @param creator the creator of this Budge.
    */

    public Budge(String id, String description, TipoCapsula type, String lessonLearned, Collaborator creator) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.lessonLearned = lessonLearned;
        this.fechaCreacion = Calendar.getInstance();
        this.creator = creator;
    }

    /**
     * The enumeration of the different types of a Budge.
    */

    public enum TipoCapsula {
        TECNICO,
        GESTION,
        DOMINIO,
        EXPERIENCIAS
    }

    /**
     * Returns the identifier of this Budge.
     *
     * @return the identifier of this Budge.
    */
    
    public String getIdentificador() {
        return id;
    }

    /**
     * Returns the description of this Budge.
     *
     * @return the description of this Budge.
    */

    public String getDescripcion() {
        return description;
    }
    
    /**
     * Returns the type of this Budge.
     *
     * @return the type of this Budge.
     */
    public TipoCapsula getBudge(){
        return type;
    }
    
    /**
     * Returns the lesson learned of this Budge.
     *
     * @return the lesson learned of this Budge.
     */
    public String getAprendizaje() {
        return lessonLearned;
    }
    
    /**
     * Returns the hashtags associated with this Budge.
     *
     * @return the hashtags associated with this Budge.
     */
    public String[] getHashtags() {
        return hashtags;
    }
    
    /**
     * Returns the approval status of this Budge.
     *
     * @return true if this Budge is approved, false otherwise.
     */
    public boolean isAprobada(){
        return aprobada;
    }

    /**
     * Sets the approval status of the capsule.
     * 
     * @param aprobada the approval status of the capsule
     */
public void setAprobada(boolean aprobada) {
    this.aprobada = aprobada;
    if (aprobada) {
        this.fechaAprobacion = Calendar.getInstance();
    } else {
        this.fechaAprobacion = null;
    }
}

/**
 * Approve the capsule.
 * 
 * Note: This method changes the approval status of the capsule to true.
 */
public void aprobar() {
    this.aprobada = true;
}

/**
 * Obtains the approval date of the capsule.
 * 
 * @return the approval date of the capsule
 */
public Calendar getFechaAprobacion() {
    return fechaAprobacion;
}

/**
 * Gets the date of creation of the capsule.
 * 
 * @return the date of creation of the capsule
 */
public Calendar getFechaCreacion() {
    return fechaCreacion;
}

/**
 * Sets the approval date of the capsule.
 * 
 * @param fechaAprobacion the approval date of the capsule
 */
public void setFechaAprobacion(Date fechaAprobacion) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(fechaAprobacion);
    this.fechaAprobacion = cal;
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
public boolean isPublicada() {
    return publicada;
}

/**
 * Sets whether the capsule has been published.
 * 
 * @param publicada the publication status of the capsule
 */
public void setPublicada(boolean publicada) {
    this.publicada = publicada;
}

}
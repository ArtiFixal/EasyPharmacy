package artifixal.easypharmacy.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

/**
 * Base class for entities containing personal data.
 * 
 * @author ArtiFixal
 */
@Data
@EqualsAndHashCode(callSuper=true)
public abstract class Person extends BaseEntity<Long>{
    
    public final static int MAX_FIRST_NAME_LENGTH=60;
    public final static int MAX_LAST_NAME_LENGTH=80;
    public final static int MAX_EMAIL_LENGTH=128;
    
    @NotNull
    @Length(max=MAX_FIRST_NAME_LENGTH,message="person.firstName.length")
    private String firstName;
    
    @NotNull
    @Length(max=MAX_FIRST_NAME_LENGTH,message="person.lastName.length")
    private String lastName;
    
    @NotNull
    @Email
    private String email;
    
    @NotNull
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private String password;
    
    private boolean active;

    public Person(Long id,String firstName,String lastName,String email,String password,boolean active){
        super(id);
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.password=password;
        this.active=active;
    }
}

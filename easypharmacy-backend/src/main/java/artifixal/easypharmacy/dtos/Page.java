package artifixal.easypharmacy.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author ArtiFixal
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Page {
    private int page;
    private int pageSize;
    
    public long getOffset(){
        return page*pageSize;
    }
}

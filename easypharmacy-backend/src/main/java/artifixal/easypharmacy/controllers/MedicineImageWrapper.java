package artifixal.easypharmacy.controllers;

import artifixal.easypharmacy.dtos.medicine.MedicineCreationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ArtiFixal
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineImageWrapper {
    private MultipartFile image;
    private MedicineCreationDTO medicine;
}

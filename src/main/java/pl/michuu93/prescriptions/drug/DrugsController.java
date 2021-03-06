package pl.michuu93.prescriptions.drug;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.michuu93.prescriptions.drug.model.Drug;
import pl.michuu93.prescriptions.drug.model.DrugsList;

import java.util.Optional;

@RestController
@RequestMapping("/api/drugs")
public class DrugsController {
    private DrugService drugService;

    public DrugsController(DrugService drugService) {
        this.drugService = drugService;
    }

    @GetMapping
    public Page<Drug> getDrugs(Pageable pageable) {
        return drugService.getDrugs(pageable);
    }

    @GetMapping("/search/{name}")
    public Page<Drug> getDrugs(Pageable pageable, @PathVariable String name) {
        return drugService.getDrugsByName(pageable, name);
    }

    @GetMapping("/{bl7}")
    public ResponseEntity<Drug> getDrugByBl7(@PathVariable String bl7) {
        Optional<Drug> optionalMovie = drugService.getDrugByBl7(bl7);
        return optionalMovie.isPresent() ? ResponseEntity.ok(optionalMovie.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<DrugsUpdateResponse> updateDrugs(@RequestBody DrugsList drugs) {
        DrugsUpdateResponse response = drugService.updateDrugs(drugs.getDrugs());
        return ResponseEntity.ok(response);
    }
}
package ucb.edu.bo.sabicovid19.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucb.edu.bo.sabicovid19.bl.CaseBl;
import ucb.edu.bo.sabicovid19.domain.BiCase;
import ucb.edu.bo.sabicovid19.dto.CaseDto;
import ucb.edu.bo.sabicovid19.dto.CasePostDto;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/register-case")
public class CaseController {
    final
    private CaseBl caseBl;

    public CaseController(CaseBl caseBl) {
        this.caseBl = caseBl;
    }

    @GetMapping
    public ResponseEntity<List<CaseDto>> findAllCases() {
        return ResponseEntity.ok(this.caseBl.findAllCases());
    }

    @PostMapping
    public ResponseEntity<CasePostDto> createCustomer(@RequestBody CasePostDto caseDto) {
        return new ResponseEntity<>(this.caseBl.createCase(caseDto), HttpStatus.CREATED);
    }

}

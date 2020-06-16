package ucb.edu.bo.sabicovid19.dto;

import lombok.Data;
import ucb.edu.bo.sabicovid19.domain.BiOriginContagion;

@Data
public class OriginContagionDto {
    private Integer oriContgId;
    private String originContagion;

    public OriginContagionDto(BiOriginContagion originContagion) {
        this.oriContgId = originContagion.getOriContgId();
        this.originContagion = originContagion.getOriginContagion();
    }
}

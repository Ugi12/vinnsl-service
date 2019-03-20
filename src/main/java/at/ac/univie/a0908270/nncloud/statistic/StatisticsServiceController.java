package at.ac.univie.a0908270.nncloud.statistic;

import at.ac.univie.a0908270.nncloud.db.NeuronalNetworkRepository;
import at.ac.univie.a0908270.nncloud.db.Statistic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@Api(description = "ViNNSL Worker Statistic Service")
@RestController
public class StatisticsServiceController {

    @Autowired
    NeuronalNetworkRepository nnRepository;

    @PostMapping(value = "/statistic/iris", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "save statistic for iris network")
    ResponseEntity<?> addIrisStatistic(@RequestBody Statistic statistic){


        return ResponseEntity.ok().body(statistic);
    }

}

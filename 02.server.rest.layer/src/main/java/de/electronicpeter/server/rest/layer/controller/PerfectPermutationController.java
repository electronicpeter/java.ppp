package de.electronicpeter.server.rest.layer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.electronicpeter.server.rest.layer.generated.PerfectPermutationApi;
import de.electronicpeter.server.rest.layer.generated.PerfectPermutationResponseContent;
import de.electronicpeter.server.rest.layer.generated.VersionResponseContent;
import de.electronicpeter.server.rest.layer.mapper.Service2RestMapper;
import de.electronicpeter.server.service.layer.PerfectPermutationResult;
import de.electronicpeter.server.service.layer.PerfectPermutationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class PerfectPermutationController implements PerfectPermutationApi {
    @Autowired
    private PerfectPermutationService perfectPermutationService;

    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    @Override
    public ResponseEntity<PerfectPermutationResponseContent> calculatePerfectPermutation(Integer numberOfElements) {
        PerfectPermutationResult perfectPermutation = perfectPermutationService.getPerfectPermutation(numberOfElements);
        PerfectPermutationResponseContent perfectPermutationResponseContent = new PerfectPermutationResponseContent();
        perfectPermutationResponseContent.setMetainfo(Mappers.getMapper(Service2RestMapper.class).mapMetaInfo(perfectPermutation.getStatistic()));
        perfectPermutationResponseContent.setCycles(Mappers.getMapper(Service2RestMapper.class).mapCycles(perfectPermutation.getCycles()));
        perfectPermutationResponseContent.setSquare(Mappers.getMapper(Service2RestMapper.class).mapSquare(perfectPermutation.getCycles().getSquare()));
        return new ResponseEntity<>(perfectPermutationResponseContent, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<VersionResponseContent> version() {
        VersionResponseContent versionResponseContent = new VersionResponseContent();
        versionResponseContent.setVersion("1.0");
        return new ResponseEntity<>(versionResponseContent, HttpStatus.OK);
    }
}

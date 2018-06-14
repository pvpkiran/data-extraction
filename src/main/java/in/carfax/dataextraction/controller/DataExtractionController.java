package in.carfax.dataextraction.controller;

import in.carfax.dataextraction.model.FileResult;
import in.carfax.dataextraction.model.Result;
import in.carfax.dataextraction.service.DataExtractionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Api(value = "Data Extractor", tags = "Data Extractor", produces = MediaType.APPLICATION_JSON_VALUE)
public class DataExtractionController {

  private final DataExtractionService dataExtractionService;

  public DataExtractionController(final DataExtractionService dataExtractionService) {
    this.dataExtractionService = dataExtractionService;
  }

  @PostMapping(value = "/data", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Single Record", notes= "parse and extract vin and make")
  public ResponseEntity<Result> processIndividualRecord(@RequestBody final String singleRecord) {
    return ResponseEntity.ok(dataExtractionService.processIndividualRecord(singleRecord));
  }

  @PostMapping(value = "/data/file", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "File Upload", notes= "parse and extract vin and make from the file")
  public ResponseEntity<FileResult> processFile(@ApiParam(required = true)
                                                   @RequestPart("file") final MultipartFile file){
    return ResponseEntity.ok(dataExtractionService.processFile(file));
  }
}

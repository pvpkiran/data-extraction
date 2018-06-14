package in.carfax.dataextraction.service;

import in.carfax.dataextraction.model.FileResult;
import in.carfax.dataextraction.model.Result;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.ResourceUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DataExtractionServiceTest {

  private DataExtractionService dataExtractionService;

  @Before
  public void setUp() {
    dataExtractionService = new DataExtractionServiceImpl();
  }

  @Test
  public void testProcessIndividualRecord() {
    String input = "VF1KMS40A36042123,KB,H1,RENAULT";
    Result result = dataExtractionService.processIndividualRecord(input);
    assertNotNull(result);
    assertEquals("VF1KMS40A36042123", result.getVin());
    assertEquals("RENAULT", result.getMake());
  }

  @Test
  public void testProcessIndividualRecordForInvalidInput() {
    String input = "VF1KMS40A36042123,KB,H1";
    Result result = dataExtractionService.processIndividualRecord(input);
    assertNotNull(result);
    assertNull(result.getVin());
    assertNull(result.getMake());
  }

  @Test
  public void testProcessFile() throws IOException {
    File file = ResourceUtils.getFile("classpath:vehicles.csv");
    InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

    MockMultipartFile multipartFile = new MockMultipartFile("file", inputStream);

    FileResult fileResult = dataExtractionService.processFile(multipartFile);
    assertNotNull(fileResult);
    assertNotNull(fileResult.getResults());
    assertEquals(8, fileResult.getResults().size());
  }
}
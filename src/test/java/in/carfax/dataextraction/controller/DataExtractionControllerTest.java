package in.carfax.dataextraction.controller;

import in.carfax.dataextraction.model.FileResult;
import in.carfax.dataextraction.model.Result;
import in.carfax.dataextraction.service.DataExtractionService;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.common.collect.Lists;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = DataExtractionController.class)
public class DataExtractionControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private DataExtractionService dataExtractionService;

  @Test
  public void processIndividualRecord() throws Exception {
    when(dataExtractionService.processIndividualRecord("SHSRE67507U001669,KB,H1,HONDA"))
        .thenReturn(new Result("SHSRE67507U001669", "HONDA"));
    mvc.perform(MockMvcRequestBuilders.post("/data").content("SHSRE67507U001669,KB,H1,HONDA"))
        .andExpect(status().isOk())
        .andExpect(content().json("{\"vin\":\"SHSRE67507U001669\",\"make\":\"HONDA\"}"));
  }

  @Test
  public void processFile() throws Exception {
    Result result1 = new Result("VF3LB9HCGES022011","PEUGEOT");
    Result result2 = new Result("WVWZZZ9NZ7Y062120","VW");
    Result result3 = new Result("JHMBE17407S200596","HONDA");

    List<Result> results = Lists.newArrayList(result1, result2, result3);

    FileResult fileResult  = new FileResult();
    fileResult.setResults(results);

    MockMultipartFile multipartFile = new MockMultipartFile("file", "VF3LB9HCGES022011,VA,H1,PEUGEOT\nWVWZZZ9NZ7Y062120,VA,H3,VW\nJHMBE17407S200596,KB,H3,HONDA".getBytes());

    when(dataExtractionService.processFile(any(MultipartFile.class)))
        .thenReturn(fileResult);
    mvc.perform(MockMvcRequestBuilders.multipart("/data/file").file(multipartFile))
        .andExpect(status().isOk())
        .andExpect(content().json("{\"results\":[{\"vin\":\"VF3LB9HCGES022011\",\"make\":\"PEUGEOT\"},{\"vin\":\"WVWZZZ9NZ7Y062120\",\"make\":\"VW\"},{\"vin\":\"JHMBE17407S200596\",\"make\":\"HONDA\"}]}"));
  }
}
package in.carfax.dataextraction.service;

import in.carfax.dataextraction.model.FileResult;
import in.carfax.dataextraction.model.Result;

import org.springframework.web.multipart.MultipartFile;


public interface DataExtractionService {

  Result processIndividualRecord(final String record);
  FileResult processFile(final MultipartFile file);
}

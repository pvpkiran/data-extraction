package in.carfax.dataextraction.service;

import in.carfax.dataextraction.model.FileResult;
import in.carfax.dataextraction.model.Result;

import com.google.common.collect.Lists;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

@Service
public class DataExtractionServiceImpl implements DataExtractionService {

  private static final Logger LOGGER = LoggerFactory.getLogger(DataExtractionServiceImpl.class);

  /**
   * Function to process single line of record. If there are more than one line, it is ignored.
   * @param record single line representing single record with comma separated values
   * @return Result object containing vin and make
   */
  @Override
  public Result processIndividualRecord(final String record) {
    final Result result = new Result();
    LOGGER.debug("Processed a single record at {} with content {}", new Date(), record);

    final String[] lines = record.split("\n");

    String[] split = lines[0].split(","); //If there are multiple lines, take first and ignore rest.

    if (split.length >= 4) { // If there are more than 4 columns, ignore rest.
      result.setVin(split[0]);
      result.setMake(split[3]);
    }
    return result;
  }

  /**
   *
   * @param file CSV file uploaded by user
   * @return an object of FileResult type which has a list of vin and make.
   */
  @Override
  public FileResult processFile(final MultipartFile file) {

    final List<Result> results = Lists.newArrayList();
    final FileResult fileResult = new FileResult();

    try {
      final ByteArrayInputStream stream = new ByteArrayInputStream(file.getBytes());
      final CSVParser parser =  CSVParser.parse(stream, Charset.defaultCharset(), CSVFormat.DEFAULT);

      parser.getRecords()
          .stream()
          .filter(this::validLine)
          .forEach(csvRecord -> {
            final String vin = csvRecord.get(0);
            final String make = csvRecord.get(3);
            final Result result = new Result(vin, make);
            results.add(result);
          });
    } catch (IOException e) {
      e.printStackTrace();
    }
    fileResult.setResults(results);
    LOGGER.debug("Processed file at {} with {} entries", new Date(), results.size());
    return fileResult;
  }

  private boolean validLine(final CSVRecord csvRecord) {
    if(csvRecord.size() < 3) { // Not checking for > 3 because it  will be ignored, if present.
      LOGGER.debug("Line format  {} not correct. Skipping it.", csvRecord);
      return false;
    }
    return true;
  }
}

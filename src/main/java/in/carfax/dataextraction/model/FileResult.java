package in.carfax.dataextraction.model;

import java.util.List;

public class FileResult {
  private List<Result> results;

  public List<Result> getResults() {
    return results;
  }

  public void setResults(final List<Result> results) {
    this.results = results;
  }
}

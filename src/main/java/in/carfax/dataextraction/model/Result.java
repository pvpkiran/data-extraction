package in.carfax.dataextraction.model;

public class Result {
  private String vin;
  private String make;

  public Result(String vin, String make) {
    this.vin = vin;
    this.make = make;
  }

  public Result() {
  }

  public String getVin() {
    return vin;
  }

  public void setVin(final String vin) {
    this.vin = vin;
  }

  public String getMake() {
    return make;
  }

  public void setMake(final String make) {
    this.make = make;
  }
}

package org.getRichFast.Data;

public interface DataReceiver {

  /**
   * Must bei initialized before starting functions
   */
  void initialize();

  void getMaximalDateValue();
  void getMaximalDateSymbolValue();
  void getMaximalSymbolValue();
  void getMaximalValue();

  void getMinimalDateValue();
  void getMinimalDateSymbolValue();
  void getMinimalSymbolValue();
  void getMinimalValue();

}

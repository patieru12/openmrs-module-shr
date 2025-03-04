package org.openmrs.module.basicmodule.utils;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class MyErrorHandler implements ResponseErrorHandler {
  @Override
  public void handleError(ClientHttpResponse response) throws IOException {
    // your error handling here
  }

  @Override
  public boolean hasError(ClientHttpResponse response) throws IOException {
    return true;
  }
}

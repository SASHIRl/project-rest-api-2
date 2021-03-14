package org.example.project.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Set;

//Sempre que for criado um ExceptionMapper ou algo que sobrescreva um comportamento padrão da
//aplicação, devemos chamar de @Provider
@Provider
public class BadRequestExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

  @Override
  public Response toResponse(ConstraintViolationException exception) {

    return Response.status(Status.BAD_REQUEST)
            .entity(new BadRequestBody(exception.getConstraintViolations()))
            .build();
  }

  class BadRequestBody {
    public List<Violation> violations;
    public BadRequestBody(Set<ConstraintViolation<?>> ConstraintViolation) {
      this.violations = ConstraintViolation.stream()
              .map(cv -> new Violation(cv.getPropertyPath().toString(), cv.getMessage()))
              .collect(Collectors.toList());
    }
  }

  class Violation {
    public String path;
    public String message;

    public Violation(String path, String message) {
      this.path = path;
      this.message = message;
    }
    
  }

}
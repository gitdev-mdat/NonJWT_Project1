package estate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(InvalidBuildingDTOException.class)
	public ResponseEntity<ErrorResponse> handleInvalidDTO(InvalidBuildingDTOException e) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErrorResponse err = new ErrorResponse(status.value(),e.getMessage());
		return ResponseEntity.status(status).body(err);
	}
	@ExceptionHandler(IllegalArguementException.class)
	public ResponseEntity<ErrorResponse> handleNotFoundDTO(IllegalArguementException e) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		ErrorResponse err = new ErrorResponse(status.value(),e.getMessage());
		return ResponseEntity.status(status).body(err);
	}
} 
		
package app.controller;

import app.api.ApiFunctions.ApiCreateCall;
import app.api.ApiFunctions.ApiFindCall;
import app.api.ApiFunctions.ApiValidateCall;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public abstract class BaseApiController {

    public <T> ResponseEntity<T> apiCreate(ApiCreateCall<T> apiCreateCall) {
        return ResponseEntity.ok(apiCreateCall.create());
    }

    public <T> ResponseEntity<T> apiFind(ApiFindCall<T> apiFindCall, BindingResult bindingResult) {
        return ResponseEntity.ok(apiFindCall.find());
    }

    public ResponseEntity<Boolean> apiValidate(ApiValidateCall apiValidateCall, BindingResult bindingResult) {
        return ResponseEntity.ok(apiValidateCall.validate());
    }
}
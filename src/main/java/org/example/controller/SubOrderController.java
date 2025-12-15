package org.example.controller;

import org.example.model.Sku;
import org.example.model.SubOrder;
import org.example.service.SubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(
        origins = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS},
        allowedHeaders = "*",
        allowCredentials = "false",
        maxAge = 3600
)
@RestController
@RequestMapping("/api/subOrder")
public class SubOrderController {

    @Autowired
    private SubService subService;

    @PostMapping("/commit")
    public ResponseEntity<?> commit(@RequestBody SubOrder suborder) {
        Map<String, Object> response = new HashMap<>();
        if(suborder == null) {
            response.put("message", "commit is null");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }else{
            try{
                boolean result = subService.commit(suborder);
                if(result){
                    response.put("message", "success");
                    return new ResponseEntity<>(response, HttpStatus.OK);

                }else {
                    response.put("message", "fail");
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }
            }catch (Exception e){
                response.put("message",e.getMessage());
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }

    }
    @PostMapping("/check")
    public ResponseEntity<?> check(@RequestBody SubOrder suborder) {
        Map<String, Object> response = new HashMap<>();
        if(suborder == null){
            response.put("message", "commit is null");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }else{
            try{
                    List<SubOrder> Skulist = subService.check(suborder);
                    response.put("message", "success");
                    response.put("data", Skulist);
                    return new ResponseEntity<>(response, HttpStatus.OK);
            }catch (Exception e){
                response.put("message",e.getMessage());
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }
    }







}

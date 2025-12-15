package org.example.controller;

import org.example.model.Sku;
import org.example.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(
        origins = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS},
        allowedHeaders = "*",
        allowCredentials = "false",
        maxAge = 3600
)
@RestController
@RequestMapping("/api/inventory")
public class SkuController {

    @Autowired
    private SkuService skuService;

    @PostMapping("/commit")
    public ResponseEntity<?> commit(@RequestBody Sku sku) {
        Map<String, Object> response = new HashMap<>();
        if(sku == null) {
            response.put("message", "sku is null");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }else{
            try{
                boolean result = skuService.commit(sku);
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
    @PostMapping("/ReCommit")
    public ResponseEntity<?> ReCommit(@RequestBody Sku sku) {
        Map<String, Object> response = new HashMap<>();
        if(sku == null) {
            response.put("message", "sku is null");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }else{
            try{
                boolean result = skuService.ReCommit(sku);
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
    public ResponseEntity<?> check(@RequestBody Sku sku) {
        Map<String, Object> response = new HashMap<>();
        if(sku == null){
            response.put("message", "sku is null");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }else{
            try{
                System.out.println(sku.getSku());
                System.out.println(sku.getStock());
                    List<Sku> Skulist = skuService.check(sku);
                    response.put("message", "success");
                    response.put("data", Skulist);
                    return new ResponseEntity<>(response, HttpStatus.OK);
            }catch (Exception e){
                response.put("message",e.getMessage());
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }
    }
    @PostMapping("/Recheck")
    public ResponseEntity<?> Recheck(@RequestBody Sku sku) {
        Map<String, Object> response = new HashMap<>();
        if(sku == null){
            response.put("message", "sku is null");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }else{
            try{
                System.out.println(sku.getSku());
                System.out.println(sku.getStock());
                List<Sku> Skulist = skuService.Recheck(sku);
                response.put("message", "success");
                response.put("data", Skulist);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }catch (Exception e){
                response.put("message",e.getMessage());
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }
    }

    public static class ChangeString {
        private Long logsId;
        private String preString;

        public void setLogsId(Long logsId) {
            this.logsId = logsId;
        }

        public void setPreString(String preString) {
            this.preString = preString;
        }

        public Long getLogsId() {
            return logsId;
        }

        public String getPreString() {
            return preString;
        }
    }
    @PostMapping("/changeName")
    public ResponseEntity<?> changeName(@RequestBody ChangeString request) {
        Map<String,Object> response = new HashMap<>();
        try{
            Long logsId = request.getLogsId();
            String preName = request.getPreString();
            if(logsId == null || preName == null){
                response.put("message", "后端接收不到数据");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }else{
                boolean result = skuService.changeName(logsId, preName);
                if(result){
                    response.put("message", "success");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }else{
                    response.put("message", "fail");
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }
            }
        }catch (Exception e){
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping("/changeNumber")
    public ResponseEntity<?> changeNumber(@RequestBody ChangeString request) {
        Map<String,Object> response = new HashMap<>();
        try{
            Long logsId = request.getLogsId();
            String preNumber = request.getPreString();
            if(logsId == null || preNumber == null){
                response.put("message", "后端接收不到数据");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }else{
                boolean result = skuService.changeNumber(logsId, preNumber);
                if(result){
                    response.put("message", "success");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }else{
                    response.put("message", "fail");
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }
            }
        }catch (Exception e){
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody List<Sku> skulist) {
        Map<String,Object> response = new HashMap<>();
        try{
           if(skulist == null || skulist.size() == 0){
               response.put("message", "后端接收不到数据");
               return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
           }else{
               for(Sku sku:skulist){
                   if(sku.getLogs_id()== null){
                       response.put("message", "后端接收不到id");
                       return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                   }
               }
               boolean result = skuService.delete(skulist);
               if(result){
                   response.put("message", "success");
                   return new ResponseEntity<>(response, HttpStatus.OK);
               }else{
                   response.put("message", "fail");
                   return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
               }
           }

        }catch (Exception e){
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/Redelete")
    public ResponseEntity<?> Redelete(@RequestBody List<Sku> skulist) {
        Map<String,Object> response = new HashMap<>();
        try{
            if(skulist == null || skulist.size() == 0){
                response.put("message", "后端接收不到数据");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }else{
                for(Sku sku:skulist){
                    if(sku.getLogs_id()== null){
                        response.put("message", "后端接收不到id");
                        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                    }
                }
                boolean result = skuService.Redelete(skulist);
                if(result){
                    response.put("message", "success");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }else{
                    response.put("message", "fail");
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }
            }

        }catch (Exception e){
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/checkSkuStyle")
    public ResponseEntity<?> checkSkuStyle(@RequestBody Sku sku) {
        Map<String,Object> response = new HashMap<>();
        try{
            if(sku == null || sku.getSku() == null){
                response.put("message", "后端接收不到数据");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }else {
                Sku result = skuService.checkSkuStyle(sku.getSku());
                if (result != null) {
                    response.put("code", "200");
                    response.put("message", "success");
                    response.put("data", result);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }else{
                    response.put("code", "500");
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }
            }
        }catch(Exception e){
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/YcBoxCheck")
    public ResponseEntity<?> YcBoxCheck(@RequestBody Sku sku) {
        Map<String,Object> response = new HashMap<>();
        try{
            if(sku == null || sku.getBoxNo() == null){
                response.put("message", "后端接收不到数据");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }else{
                List<Sku> skuList = skuService.YcBoxCheck(sku);
                if (skuList != null) {
                    response.put("code", "200");
                    response.put("message", "success");
                    response.put("data", skuList);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }else{
                    response.put("code", "500");
                    response.put("message", "fail");
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }
            }


        }catch (Exception e){

            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/YcBoxCheckDel")
    public ResponseEntity<?> YcBoxCheckDel(@RequestBody Sku sku) {
        Map<String,Object> response = new HashMap<>();
        try{
            if(sku == null || sku.getBoxNo() == null){
                response.put("message", "后端接收不到数据");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }else{
                List<Sku> skuList = skuService.YcBoxCheckDel(sku);
                if (skuList != null) {
                    response.put("code", "200");
                    response.put("message", "success");
                    response.put("data", skuList);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }else{
                    response.put("code", "500");
                    response.put("message", "fail");
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }
            }


        }catch (Exception e){

            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/YcBoxAdd")
    public ResponseEntity<?> YcBoxAdd(@RequestBody List<Sku> sku) {
        Map<String,Object> response = new HashMap<>();
        try{
            if(sku == null || sku.size() == 0){
                response.put("message", "后端接收不到数据");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }else{
                boolean result = skuService.YcBoxAdd(sku);
                if (result) {
                    response.put("code", "200");
                    response.put("message", "success");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }else{
                    response.put("code", "500");
                    response.put("message", "fail");
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }
            }


        }catch (Exception e){

            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }




}

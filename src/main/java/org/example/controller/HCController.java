package org.example.controller;

import org.example.model.BackGoods;
import org.example.model.Sku;
import org.example.service.BackGoodsService;
import org.example.service.HCService;
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
@RequestMapping("/api/HC")
public class HCController {

    @Autowired
    private HCService hcService;

    @PostMapping("/commit")
    public ResponseEntity<?> commitOrders(@RequestBody Sku sku) {
        Map<String, Object> result = new HashMap<>();
        try{
            boolean res = hcService.commit(sku);
            if(res){
                result.put("code", 200);
                result.put("message", "提交成功");
                return new ResponseEntity<>(result, HttpStatus.OK);
            }else{
                result.put("code", 500);
                result.put("message", "提交失败1");
                return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e) {
            result.put("code", 500);
            result.put("message", "服务器错误");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/check")
    public ResponseEntity<?> checkOrders(@RequestBody Sku sku) {
        Map<String, Object> result = new HashMap<>();
        try{
            List<Sku> res = hcService.check(sku);
            if(res != null){
                result.put("code", 200);
                result.put("message", "复核成功");
                result.put("data", res);
                return new ResponseEntity<>(result, HttpStatus.OK);
            }else{
                result.put("code", 500);
                result.put("message", "复核失败1");
                return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e) {
            result.put("code", 500);
            result.put("message", "服务器错误");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/delete")
    public ResponseEntity<?> deleteOrders(@RequestBody Sku sku) {
        Map<String, Object> result = new HashMap<>();
        try {
            boolean res = hcService.delete(sku);
            if (res) {
                result.put("code", 200);
                result.put("message", "删除成功");
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                result.put("code", 500);
                result.put("message", "删除失败1");
                return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", "服务器错误");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/deleteAll")
    public ResponseEntity<?> deleteAllOrders() {
        Map<String, Object> result = new HashMap<>();
        try {
            boolean res = hcService.deleteAll();
            if (res) {
                result.put("code", 200);
                result.put("message", "删除成功");
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                result.put("code", 500);
                result.put("message", "删除失败1");
                return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", "服务器错误");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
    @GetMapping("/deleteChaos")
    public ResponseEntity<?> chaosAllOrders() {
        Map<String, Object> result = new HashMap<>();
        try {
            boolean res = hcService.deleteChaos();
            if (res) {
                result.put("code", 200);
                result.put("message", "删除成功");
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                result.put("code", 500);
                result.put("message", "删除失败1");
                return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", "服务器错误");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/InventoryCommit")
    public ResponseEntity<?> commitInventory(@RequestBody Sku sku) {
        Map<String, Object> result = new HashMap<>();
        try{
            boolean res = hcService.commitInventory(sku);
            if(res){
                result.put("code", 200);
                result.put("message", "提交成功");
                return new ResponseEntity<>(result, HttpStatus.OK);
            }else{
                result.put("code", 500);
                result.put("message", "提交失败1");
                return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e) {
            result.put("code", 500);
            result.put("message", "服务器错误");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/InventoryCheck")
    public ResponseEntity<?> checkInventory(@RequestBody Sku sku) {
        Map<String, Object> response = new HashMap<>();
        if(sku == null){
            response.put("message", "sku is null");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }else{
            try{
//                System.out.println(sku.getSku());
//                System.out.println(sku.getStock());
                List<Sku> Skulist = hcService.checkInventory(sku);
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
    @PostMapping("/InventoryChangeName")
    public ResponseEntity<?> InventoryChangeName(@RequestBody SkuController.ChangeString request) {
        Map<String,Object> response = new HashMap<>();
        try{
            Long logsId = request.getLogsId();
            String preName = request.getPreString();
            if(logsId == null || preName == null){
                response.put("message", "后端接收不到数据");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }else{
                boolean result = hcService.InventoryChangeName(logsId, preName);
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
    @PostMapping("/InventoryChangeNumber")
    public ResponseEntity<?> InventoryChangeNumber(@RequestBody SkuController.ChangeString request) {
        Map<String,Object> response = new HashMap<>();
        try{
            Long logsId = request.getLogsId();
            String preNumber = request.getPreString();
            if(logsId == null || preNumber == null){
                response.put("message", "后端接收不到数据");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }else{
                boolean result = hcService.InventoryChangeNumber(logsId, preNumber);
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
    @PostMapping("/InventoryDelete")
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
                boolean result = hcService.InventoryDelete(skulist);
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










}

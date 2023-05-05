package scut.deng.didservice.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import scut.deng.didservice.exception.MyException;
import scut.deng.didservice.pojo.response.BaseResponse;
import scut.deng.didservice.service.IssuerService;

import java.util.Map;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/scutIssuer")
public class IssuerController {


    @Autowired
    public IssuerService issuerService;

    @GetMapping("/getAll")
    public BaseResponse getAllIssuers(){
        return issuerService.getIssuerLists();
    }

    @PostMapping("/applyVC")
    public BaseResponse applyVC(@RequestBody Map<String, Object> map) throws MyException {
        JSONObject didInfo = JSONUtil.parseObj(map.get("didInfo"));
        JSONObject provideData = JSONUtil.parseObj(map.get("provideData"));
        return issuerService.applyForVC(didInfo,provideData);
    }

}

package scut.deng.didservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import scut.deng.didservice.exception.MyException;
import scut.deng.didservice.pojo.request.VerifyVCRequest;
import scut.deng.didservice.pojo.response.BaseResponse;
import scut.deng.didservice.service.AppService;

@RestController
@RequestMapping("/app")
@Validated
public class AppController {


//    1. 先认证did是属于自己的
//    2.再认证vc
    @Autowired
    public AppService appService;


    @RequestMapping("/login")
    public BaseResponse loginApp(@RequestParam(name = "did") String did) throws MyException {


        return appService.loginApp(did);

    }

    @PostMapping("/verifyVC")
    public BaseResponse verifyVC(@RequestBody VerifyVCRequest encodeMsg) throws MyException {
        return appService.verifyVC(encodeMsg);
    }

    @PostMapping("/verifyVP")
    public BaseResponse verifyVP(@RequestBody VerifyVCRequest encodeMsg) throws MyException {
        return appService.verifyVP(encodeMsg);
    }

}

package scut.deng.didservice.pojo;

import cn.hutool.core.bean.BeanUtil;
import lombok.Data;
import scut.deng.didservice.pojo.constant.EncryptType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class BaseDidDoc {
    private List<PublicKey> keyList;
    private String authentication;
    private String recovery;

    public static BaseDidDoc setUpDoc(String keyMain, String keyRecover, EncryptType type){
        BaseDidDoc doc = new BaseDidDoc();
        PublicKey key_1 = new PublicKey();
        key_1.setType(type.getType());
        key_1.setKeyString(keyMain);
        key_1.setId("#key-1");

        /*初始化公钥列表*/
        PublicKey key_2 = new PublicKey();
        key_2.setType(type.getType());
        key_2.setKeyString(keyRecover);
        key_2.setId("#key-2");
        List<PublicKey> keyList = new ArrayList<>();
        keyList.add(key_1);
        keyList.add(key_2);
        doc.setKeyList(keyList);

        doc.setAuthentication("#key-1");
        doc.setRecovery("#key-2");


        return doc;
    }
}

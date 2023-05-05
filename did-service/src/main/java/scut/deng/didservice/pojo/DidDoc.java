package scut.deng.didservice.pojo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import scut.deng.didservice.pojo.constant.Constant;
import scut.deng.didservice.pojo.constant.EncryptType;
import scut.deng.didservice.util.EncUtil;

import java.util.List;

@Data
public class DidDoc {

    @NotNull
    private String didID;
    @NotNull
    private Integer version;

    private String createTime;

    private String updateTime;

    private List<PublicKey> keyList;

    private String type;

    private String comment;
    @NotNull(message = "公钥对应的私钥的用户就是此 DID 的拥有者，不能为空")
    private String authentication;

    @NotNull(message = "恢复公钥不能为空")
    private String recovery;

    private List<DidService> serviceList;

    private Proof proof;
    public static DidDoc createNewDID(String did, BaseDidDoc baseDidDoc, String sk){
        DidDoc didDoc = new DidDoc();
        // 设置did标识
        didDoc.setDidID(did);
        // 设置时间
        String now = DateUtil.now();
        didDoc.setCreateTime(now);
        didDoc.setUpdateTime(now);
        // 设置版本
        didDoc.setVersion(1);
        // 设置认证以及恢复私钥
        didDoc.setAuthentication(did + baseDidDoc.getAuthentication());
        didDoc.setRecovery(did + baseDidDoc.getRecovery());
        //
        baseDidDoc.getKeyList().stream().forEach( x -> {
            x.setId(did + x.getId());
        });
        // 设置公钥列表
        didDoc.setKeyList(baseDidDoc.getKeyList());
        // 使用私钥将密文加密
        System.out.println(didDoc);
        String docString = JSONUtil.toJsonStr(BeanUtil.beanToMap(didDoc));
        String encstring = EncUtil.digestMsgUseSK(docString, sk);
        Proof proof = new Proof();
        proof.setType(EncryptType.RSA.getType());
        proof.setCreator(did + "#key-1");
        proof.setSignatureValue(encstring);
        didDoc.setProof(proof);

        return didDoc;
    }
}

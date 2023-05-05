package scut.deng.didservice.util;

import cn.hutool.crypto.SmUtil;
import scut.deng.didservice.pojo.MerkleTreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MerkleTreeUtil {

    private static final String addConstant = "supplement";

    private List<String> data;

    private String[] tree;
    private int oriLen;
    public MerkleTreeUtil(List<String> data){

        oriLen = data.size();
        this.data = data;
        if (oriLen%2!=0){
            this.data =data;
            this.data.add(addConstant);
            oriLen = oriLen+1;
        }
        tree =new String[2*oriLen - 1];
        int hashLen = tree.length - oriLen;
        for (int i = 0; i < data.size(); i++) {
            tree[i+hashLen] = getHash(data.get(i));
        }
        for (int i=oriLen-2; i>=0; i--){
            tree[i] = getHash(tree[2 * i + 1] + tree[2 * i + 2]);
        }
    }

    public String returnRoot(){
        return tree[0];
    }

    public String[]  returnTree(){
        return tree;
    }

    public static String getHash(String data){
        String hash = SmUtil.sm3(data);
        return hash;
    }



    public static ArrayList<String> getMerklePath(String[] data, String[] hashVec, int index){
       index = index - 1;
       index = index + data.length;
        ArrayList<String> path = new ArrayList<>();
        while (index>0){
           if (index%2 != 0){ //左节点
               path.add(hashVec[index+1]);
               index = (index-1) / 2;
           }else {
               path.add(hashVec[index-1]);
               index = (index-2) / 2;
           }
       }

        return path;
    }

    public static boolean verifyPath(String root, String data, int dataSize, int index, String[] path){
        String cur = getHash(data);
        int treeIndex = dataSize+index-2;
        for (int i = 0; i < path.length; i++) {
            if (treeIndex%2 == 0){ //右节点
                cur = getHash(path[i]+cur);
                treeIndex = treeIndex/2 -1;
            }else {
                cur = getHash(cur+path[i]);
                treeIndex = treeIndex/2;
            }

        }

        return root.equals(cur);
    }

}

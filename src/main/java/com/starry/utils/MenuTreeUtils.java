package com.starry.utils;

import com.starry.vo.tree.BaseTree;
import com.starry.vo.tree.MenuTreeVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MenuTreeUtils {

    private static final long serialVersionUID = -6948127879934737045L;

    public static <T extends BaseTree> List<T> build(List<T> treeNodes, Long parentId) {
        List<T> treeList = new ArrayList<>();
        for(T treeNode : treeNodes) {
            //判断对象是否为根节点
            if (parentId.equals(treeNode.getParentId())) {
                treeList.add(findChildren(treeNodes, treeNode));
            }
        }
        return treeList;
    }

    /**
     * 查找子节点
     */
    private static <T extends BaseTree> T findChildren(List<T> treeNodes, T rootNode) {
        for(T treeNode : treeNodes) {
            if(rootNode.getId().equals(treeNode.getParentId())) {
                rootNode.getChildren().add(findChildren(treeNodes, treeNode));
            }
        }
        return rootNode;
    }

    /**
     * 构建树节点
     */
    public static <T extends BaseTree> List<T> build(List<T> treeNodes) {
        List<T> result = new ArrayList<>();
        Map<Long, T> nodeMap = new LinkedHashMap<>(treeNodes.size());
        for(T treeNode : treeNodes){
            nodeMap.put(treeNode.getId(), treeNode);
        }

        for(T node : nodeMap.values()) {
            T parent = nodeMap.get(node.getParentId());
            if(parent != null && !(node.getId().equals(parent.getId()))){
                parent.getChildren().add(node);
                continue;
            }
            result.add(node);
        }
        return result;
    }

}

package org.gnuhpc.bigdata.systemdesign.simplezk.pojo;

import lombok.*;
import lombok.extern.log4j.Log4j;
import org.gnuhpc.bigdata.systemdesign.simplezk.callback.CallBack;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Log4j
public class Node {
    public static final String DEFAULTVAL = "N/Sample";
    // 节点值
    private String nodeValue = DEFAULTVAL;
    // 节点的多个子节点 <PathString, Node>
    private final Map<String, Node> children = new HashMap<>() ;
    // 节点的Callback 列表
    private final List<CallBack> callBackList = new LinkedList<>();

    public Node(String nodeValue){
        this.nodeValue = nodeValue;
    }

}

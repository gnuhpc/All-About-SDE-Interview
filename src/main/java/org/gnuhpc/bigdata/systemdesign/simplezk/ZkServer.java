package org.gnuhpc.bigdata.systemdesign.simplezk;

import com.google.common.base.Strings;
import lombok.extern.log4j.Log4j;
import org.gnuhpc.bigdata.systemdesign.simplezk.callback.CallBack;
import org.gnuhpc.bigdata.systemdesign.simplezk.exception.InvalidPathFormatException;
import org.gnuhpc.bigdata.systemdesign.simplezk.exception.NodeAlreadyExistException;
import org.gnuhpc.bigdata.systemdesign.simplezk.exception.PathNonExistException;
import org.gnuhpc.bigdata.systemdesign.simplezk.pojo.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j
public class ZkServer {
    private static final String DELIMITER = "/";
    private Node root;

    public ZkServer() {
        root = new Node();
    }

    public Node create_path(String path, String value) throws PathNonExistException, NodeAlreadyExistException, InvalidPathFormatException {
        Node parent;
        //格式化path
        String formattedPath = formatPath(path);

        // /a/b/c -> ["", a,b,c]
        // /a -> ["",a]
        // / -> []
        String[] pathArray = formattedPath.split(DELIMITER);
        String nodePath = null, parentPath = null;
        //path = "/"
        if (pathArray.length == 0){
            parent = root;
        }
        else {
            // /a/b/c
            //获取创建节点的path，nodePath = c;
            nodePath = pathArray[pathArray.length-1];
            //获取创建节点的父节点path， parentPath = /a/b
            parentPath = constructParentPath(pathArray);
            try {
                // 获取这个路径所代表的节点
                parent = getNode(parentPath);
            } catch (PathNonExistException e) {
                log.error(e.getMessage());
                throw e;
            }
        }
        //If the node is not exist, create it
        if (!checkNodeExist(parent, nodePath)) {
            Node n = new Node(value);
            // Set the cor's children list
            parent.getChildren().put(nodePath, n);
            return n;
        } else {
            throw new NodeAlreadyExistException();
        }
    }

    public Node set_value(String path, String newValue) throws InvalidPathFormatException, PathNonExistException {
        List<String> nodePathList = getNodeListAlong(path);
        Node ret = null;

        for (int i = 0; i < nodePathList.size();i++){
            Node n;
            String p = nodePathList.get(i);
            try {
                n = getNode(p);
            } catch (PathNonExistException e) {
                log.error(e.getMessage());
                throw e;
            }

            //获取回调函数
            List<CallBack> callBackList = n.getCallBackList();
            for (CallBack handler : callBackList){
                handler.onChange(path,newValue);
            }

            // 到最后一个节点时进行实际的set
            if (i == nodePathList.size()-1){
                n.setNodeValue(newValue);
                ret = n;
            }
        }

        return ret;
    }

    public String get_value(String path) throws InvalidPathFormatException, PathNonExistException {
        path = formatPath(path);
        Node n;
        try {
            n = getNode(path);
        } catch (PathNonExistException e) {
            log.error(e.getMessage());
            throw e;
        }


        return n.getNodeValue();
    }

    public void watch(String path, CallBack callBack) throws PathNonExistException, InvalidPathFormatException {
        path = formatPath(path);
        Node n;
        try {
            n = getNode(path);
        } catch (PathNonExistException e) {
            log.error(e.getMessage());
            throw e;
        }

        List<CallBack> callBackList = n.getCallBackList();
        callBackList.add(callBack);
    }

    // 构建父节点路径，只在create_path上使用
    private String constructParentPath(String[] pathArray) {
        if (pathArray.length == 2) return DELIMITER;
        StringBuilder sb = new StringBuilder();

        sb.append(DELIMITER);

        for (int i = 1; i < pathArray.length - 1; i++) {
            sb.append(pathArray[i]).append(DELIMITER);
        }

        return sb.toString().substring(0,sb.toString().length()-1);
    }

    /**
     * 获取path沿途的节点路径列表 /a/b/c -> [/a, /a/b, /a/b/c]
     * @param path 路径
     * @return 返回沿途节点路径列表
     * @throws InvalidPathFormatException
     */

    private List<String> getNodeListAlong(String path) throws InvalidPathFormatException {
        String[] pathArray = formatPath(path).split(DELIMITER);
        List<String> res = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < pathArray.length; i++) {
            sb.append(DELIMITER).append(pathArray[i]);
            res.add(sb.toString());
        }

        return res;
    }

    /**
     * 获取path的Node
     * @param path
     * @return 返回Node
     * @throws PathNonExistException
     */
    private Node getNode(String path) throws PathNonExistException {

        // /a/b/c  -> ["","a","b","c"]
        String[] pathArray = path.split(DELIMITER);

        if (pathArray.length == 0) return root;

        Map<String, Node> level = root.getChildren();
        Node n = null;
        for (int i = 1; i < pathArray.length; i++) {
            n = level.get(pathArray[i]);
            if (n == null) throw new PathNonExistException();
            else {
                level = n.getChildren();
            }
        }

        return n;
    }


    /**
     * 判断是否在parent节点下的path路径 是否已经有节点
     * @param parent parent节点
     * @param path 要在parent节点下创建节点的路径
     * @return 是否存在
     */
    private boolean checkNodeExist(Node parent, String path) {
        return (parent != null && Strings.isNullOrEmpty(path)) || (parent != null && parent.getChildren().get(path) != null);
    }

    /**
     * 格式化路径，检查有效性
     * @param path
     * @return 格式化后的Path
     * @throws InvalidPathFormatException
     */
    private String formatPath(String path) throws InvalidPathFormatException {
        if (path == null) throw new InvalidPathFormatException();

        path = path.replaceAll("/+", "/").trim();
        if (Strings.isNullOrEmpty(path) || !String.valueOf(path.charAt(0)).equals(DELIMITER) || (path.length() != 1 && path.charAt(path.length() - 1) == '/'))
            throw new InvalidPathFormatException();

        return path;
    }
}

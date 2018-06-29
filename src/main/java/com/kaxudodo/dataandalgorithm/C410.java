package com.kaxudodo.dataandalgorithm;

/**
 * Created by aaron on 16/9/5.
 */
public class C410 {

    private static class TreeNode{
        Object element;
        TreeNode firstChild;
        TreeNode nextSibling;
        int size;

        public TreeNode(Object e,TreeNode f,TreeNode n,int _size){
            element = e;
            firstChild = f;
            nextSibling = n;
            size = _size;
        }

        public Object getElement() {
            return element;
        }

        public void setElement(Object element) {
            this.element = element;
        }

        public TreeNode getFirstChild() {
            return firstChild;
        }

        public void setFirstChild(TreeNode firstChild) {
            this.firstChild = firstChild;
        }

        public TreeNode getNextSibling() {
            return nextSibling;
        }

        public void setNextSibling(TreeNode nextSibling) {
            this.nextSibling = nextSibling;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }

    private int listAll(TreeNode root,String tabs){
        int size = 0; //当前节点的总大小
        int tatolSize = 0; //当前兄弟所有节点的大小
        while (root != null){
            size = root.getSize();
            TreeNode child = root.getFirstChild();
            if(child != null){
                String tab = tabs + tabs;
                size = listAll(child,tab);
                size = size + root.getSize();
            }
            System.out.println(tabs+root.getElement()+"--"+size);
            tatolSize = tatolSize + size;
            root = root.getNextSibling();
        }
        return tatolSize;
    }

    public static void main(String[] args) {
        TreeNode r5 = new TreeNode("r13",null,null,4);
        TreeNode r4 = new TreeNode("r22",null,null,4);
        TreeNode r3 = new TreeNode("r21",null,r4,3);
        TreeNode r2 = new TreeNode("r12",r3,r5,1);
        TreeNode r1 = new TreeNode("r11",null,r2,1);
        TreeNode r = new TreeNode("r",r1,null,1);
        C410 c410 = new C410();
        c410.listAll(r,"-");
    }
}

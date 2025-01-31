package nodes;

import java.util.ArrayList;

public abstract class Node {
    private ArrayList<Integer> keys;
    private parentNode parent;
    private boolean isLeaf;
    private boolean isRoot;

    public Node() {
        keys = new ArrayList<Integer>();
        isLeaf = false;
        isRoot = false;
    }

    //get leaf
    public boolean getIsLeaf() {
        return isLeaf;
    }

    //set leaf
    public void setIsLeaf(boolean leafiness) {
        isLeaf = leafiness;
    }

    //get root
    public boolean getIsRoot() {
        return isRoot;
    }

    //set root
    public void setIsRoot(boolean rootiness) {
        isRoot = rootiness;
    }

    //get parent
    public parentNode getParent() {
        return parent;
    }

    //set parent
    public void setParent(parentNode progenitor) {
        parent = progenitor;
    }

    //get all keys
    public ArrayList<Integer> getAllKey() {
        return keys;
    }

    //get key at index
    public int getOneKey(int index) {
        return keys.get(index);
    }

    //add key
    public int addKey(int key) {
        if(this.getAllKey().size() == 0){
            this.keys.add(key);
            return 0;
        }

        int i;
        keys.add(key);
        for(i = keys.size() - 2; i >= 0; i--) {
            if(keys.get(i) <= key) {
                i++;
                keys.set(i, key);
                break;
            }

            keys.set(i + 1, keys.get(i));

            if(i == 0) {
                keys.set(i, key);
                break;
            }
        }
        return i;
    }
    
    //delete one key
    public void deleteOneKey(int index) {
        keys.remove(index);
    }

    //delete all key
    public void deleteAllKey() {
        keys = new ArrayList<Integer>();
    }

    //find smallest key
    public int findSmallestKey() {
        int key;
        parentNode node;

        if(!this.getIsLeaf()) {
            node = (parentNode) this;
            while(!node.getChild(0).getIsLeaf()) {
                node = (parentNode) node.getChild(0);
            }
            key = node.getChild(0).getOneKey(0);
        }
        else {
            key = this.getOneKey(0);
        }
        return key;
    }

    //delete node
    public void deleteNode() {
        if(parent != null) {
            parent.deleteOneChild(this);
            parent = null;
        }

        if(this.isLeaf) {
            leafNode node = (leafNode) this;
            node.deleteAllRecord();
            node.setNext(null);
        }
        else {
            parentNode node = (parentNode) this;
            node.deleteAllChildren();
        }

        isLeaf = false;
        isRoot = false;
        keys = new ArrayList<Integer>();
    }

    abstract void logStructure();
}

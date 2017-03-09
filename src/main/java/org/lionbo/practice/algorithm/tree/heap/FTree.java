package org.lionbo.practice.algorithm.tree.heap;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class FTree {

    private int size;

    private Long value;

    private FTree leftChild;

    private FTree rightChild;

    private boolean hasVisit = false;

    public boolean isHasVisit() {
        return hasVisit;
    }

    public void setHasVisit(boolean hasVisit) {
        this.hasVisit = hasVisit;
    }

    public FTree() {

    }

    public boolean hasLeftChild() {
        return leftChild == null ? false : true;
    }

    public boolean hasRightChild() {
        return rightChild == null ? false : true;
    }

    public FTree(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public FTree getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(FTree leftChild) {
        this.leftChild = leftChild;
    }

    public FTree getRightChild() {
        return rightChild;
    }

    public void setRightChild(FTree rightChild) {
        this.rightChild = rightChild;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}

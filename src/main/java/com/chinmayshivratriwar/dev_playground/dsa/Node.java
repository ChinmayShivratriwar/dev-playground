package com.chinmayshivratriwar.dev_playground.dsa;

class Node {
    int key;
    Node left, right, parent;

    public Node(int key) {
        this.key = key;
        left = right = parent = null;
    }
}



import java.util.NoSuchElementException;
import java.util.Optional;

public class BSTree<T1 extends Comparable<T1>, T2> {


    static class Node<T1, T2> {
        T1 key;
        T2 value;
        Node<T1, T2> left, right;

        Node(T1 key, T2 value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<T1, T2> root = null;

    private int length = 1;

    private int height() {
        return length;
    }

    public boolean containsKey(T1 key) {
        Node<T1, T2> x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) {
                return true;
            }
            if (cmp < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        return false;
    }

    public Optional<T2> get(T1 key) {
        Optional<T2> optional = Optional.empty();
        Node<T1, T2> x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) {
                optional = Optional.of(x.value);
                return optional;
            }
            if (cmp < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
            optional.orElseThrow(NoSuchElementException::new);
            return optional;

    }

    public void add(T1 key, T2 value) {
        Node<T1, T2> x = root, y = null;
        int i = 0;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) {
                x.value = value;
                return;
            } else {
                y = x;
                if (cmp < 0) {
                    x = x.left;
                } else {
                    x = x.right;
                }
            }
            i++;
        }
        Node<T1, T2> newNode = new Node<T1, T2>(key, value);
        if (y == null) {
            root = newNode;
        } else {
            if (key.compareTo(y.key) < 0) {
                y.left = newNode;
            } else {
                y.right = newNode;
            }
        }
        if (i > length) {
            length++;
        }
    }

    public void show() {
        Node<T1, T2> x = root;
        int l = 0;
        Boolean[] way = new Boolean[height()];
        for (int i = 0; i < height(); i++) {
            way[i] = false;
        }
        System.out.println(x.key);
        for (int j = 0; j < Math.pow(2, height()); j++) {
            for (int i = 0; i < height(); i++) {
                if (!way[i]) {
                    if (x.left != null) {
                        x = x.left;
                    } else {
                        break;
                    }
                } else {
                    if (x.right != null) {
                        x = x.right;
                    } else {
                        break;
                    }
                }
                System.out.print(x.key + " ");
            }
            System.out.println();

            for (int i = height() - 1; i >= 0; i--) {
                if (!way[i]) {
                    way[i] = true;
                    for (int k = i + 1; k < height(); k++) {
                        way[k] = false;
                    }
                }
            }
            x = root;
        }
    }

    public void remove(T1 key) throws RemoveException {
        if (height() == 0) {
            new RemoveException("Tree is empty");
        } else {
            Node<T1, T2> x = root, y = null;
            while (x != null) {
                int cmp = key.compareTo(x.key);
                if (cmp == 0) {
                    break;
                } else {
                    y = x;
                    if (cmp < 0) {
                        x = x.left;
                    } else {
                        x = x.right;
                    }
                }
            }
            if (x == null) {
                return;
            }
            if (x.right == null) {
                if (y == null) {
                    root = x.left;
                } else {
                    if (x == y.left) {
                        y.left = x.left;
                    } else {
                        y.right = x.left;
                    }
                }
            } else {
                Node<T1, T2> leftMost = x.right;
                y = null;
                while (leftMost.left != null) {
                    y = leftMost;
                    leftMost = leftMost.left;
                }
                if (y != null) {
                    y.left = leftMost.right;
                } else {
                    x.right = leftMost.right;
                }
                x.key = leftMost.key;
                x.value = leftMost.value;
            }
        }
    }
}
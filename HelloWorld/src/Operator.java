interface Operator {
    int calc(int a, int b);

}

class Add implements Operator {

    @Override
    public int calc(int a, int b) {
        return a+b;
    }
}

class  Sub implements Operator {
    @Override
    public int calc(int a, int b) {
        return a - b;
    }
}

class Multiple implements Operator {

    @Override
    public int calc(int a, int b) {
        return a * b;
    }
}

class Divide implements Operator {

    @Override
    public int calc(int a, int b) {
        return a / b;
    }
}